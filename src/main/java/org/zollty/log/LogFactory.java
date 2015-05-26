/* @(#)LogFactory.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-20 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @author zollty
 * @since 2013-6-20
 */
public class LogFactory {

    private static String logName;
    private static boolean isInited;
    private static LoggerSupport logCreator = null;

    private static Map<String, Boolean> logModules = new HashMap<String, Boolean>();

    private static final String LOGFACTORY_CLASS_NAME = LogFactory.class.getName();

    public static Logger getLogger(Class<?> classz) {
        return getLogger(classz.getName());
    }

    /**
     * 返回以调用者的类命名的Log,是获取Log对象最简单的方法!
     */
    public static Logger getLogger() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        for (int i = 0; i < sts.length; i++) {
            if (LOGFACTORY_CLASS_NAME.equals(sts[i].getClassName())) {
                return getLogger(sts[i + 1].getClassName());
            }
        }
        return getLogger(sts[3].getClassName());
    }

    public static Logger getLogger(String name) {
        if (null == logCreator) { // 默认初始化
            refreshZolltyLogConfig("zollty-log.properties");
        }
        return new LoggerWrapper(logCreator.newInstance(name));
    }

    /**
     * @param configName default value "zollty-log.properties"
     */
    public static void refreshZolltyLogConfig(final String configName) {
        InputStream in = null;
        in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configName);
        Properties props = new Properties();
        try {
            props.load(in);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, String> pmap = LogUtils.covertProperties2Map(props);
        String rootLogger = pmap.get("rootLogger");
        rootLogger = rootLogger.replace(" ", "");
        String[] rootarry = rootLogger.split(",");
        String threshold = rootarry[0].toUpperCase();

        Map<String, Level> parentLoggers = new TreeMap<String, Level>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1); // 降序排序
            }
        });
        String key;
        for (Map.Entry<String, String> entry : pmap.entrySet()) {
            key = entry.getKey();
            if (key.startsWith("logger.")) {
                parentLoggers.put(key.substring(7, key.length()), Level.toLevel(entry.getValue()));
            }
        }

        String appenderFlag = rootarry[1];
        if ("STDOUT".equalsIgnoreCase(appenderFlag)) {
            LogManager.setThreshold(threshold); // 重置threshold
            LogFactory.logName = "ConsoleLogger";
            logCreator = new ConsoleLogger(logName);
            ConsoleAppender.setGlobalLevel(threshold);
            String classNameLayout = pmap.get("appender.STDOUT.layout.className");
            ConsoleAppender.setClassNameLayout(classNameLayout);
            ConsoleAppender.setParentLoggers(parentLoggers);
        }
        else if ("LOG4J".equalsIgnoreCase(appenderFlag)) {
            LogManager.setThreshold(threshold); // 重置threshold
            LogFactory.logName = "Log4jLogger";
            logCreator = new Log4jLogger();
        }
        else if ("OTHER".equalsIgnoreCase(appenderFlag)) {
            LogFactory.logName = pmap.get("appender.OTHER.name");
            LogManager.init(logName, threshold);
        }
    }

    private static boolean traceEnabled, debugEnabled, infoEnabled;

    public static boolean isTraceEnabled() {
        return traceEnabled;
    }

    public static boolean isDebugEnabled() {
        return debugEnabled;
    }

    public static boolean isInfoEnabled() {
        return infoEnabled;
    }

    public static boolean isEnabledFor(String moduleId) {
        return LogManager.isEnabledFor(moduleId);
    }

    static {
        LogManager.updateLevelModuleMap("DEBUG");
    }

    public static class LogManager {

        private static Iterator<Entry<String, Boolean>> lmIterator = null;

        public synchronized static void init(String logName, String level) {
            if (isInited) {
                throw new IllegalStateException("already initialized, don't allow initialize again.");
            }
            LogFactory.logName = logName;
            LogManager.setThreshold(level.toUpperCase());
            // if(logName==null || "ConsoleLogger".equals(logName)){
            // logCreator = new ConsoleLogger(logName);
            // }else if("Log4jLogger".equals(logName)){
            // logCreator = new Log4jLogger(logName);
            // }else{
            // logCreator = LogUtils.createLogCreator(logName);
            // }
            logCreator = LogUtils.createLogCreator(logName);

            updateLevelModuleMap(level);
            isInited = true;
        }

        public synchronized static void updateLogModule(String moduleId, boolean value) {
            if (logModules.containsKey(moduleId)) {
                logModules.remove(moduleId);
            }
            logModules.put(moduleId, value);
            refreshStaticRef();
        }

        public synchronized static void updateLogModules(Map<String, Boolean> modules) {
            lmIterator = modules.entrySet().iterator();
            String key;
            Entry<String, Boolean> temp;
            while (lmIterator.hasNext()) {
                temp = lmIterator.next();
                key = temp.getKey();
                if (logModules.containsKey(key)) {
                    logModules.remove(key);
                }
                logModules.put(key, temp.getValue());
            }
            refreshStaticRef();
        }

        public synchronized static boolean addLogModule(String moduleId, boolean value) {
            if (!logModules.put(moduleId, value)) {
                return false;
            }
            refreshStaticRef();
            return true;
        }

        public synchronized static void addLogModules(Map<String, Boolean> modules) {
            logModules.putAll(modules);
            refreshStaticRef();
        }

        public synchronized static void replaceLogModules(Map<String, Boolean> modules) {
            LogFactory.logModules = modules;
            refreshStaticRef();
        }

        private synchronized static boolean isEnabledFor(String moduleId) {
            Iterator<Entry<String, Boolean>> lmIterator = LogFactory.logModules.entrySet().iterator();
            String key;
            Entry<String, Boolean> temp;
            while (lmIterator.hasNext()) {
                temp = lmIterator.next();
                key = temp.getKey();
                if (key.equals(moduleId)) {
                    return temp.getValue();
                }
            }
            // 默认用LogFactory的设置
            return LogFactory.isDebugEnabled();
        }

        public static boolean containsKey(String moduleId) {
            return LogFactory.logModules.containsKey(moduleId);
        }

        public static final Map<String, Boolean> getLogModuleMap() {
            return LogFactory.logModules;
        }

        // LogFactory.logModules有变动 则刷新静态变量
        private static void refreshStaticRef() {
            traceEnabled = LogFactory.logModules.get("TRACE");
            debugEnabled = LogFactory.logModules.get("DEBUG");
            infoEnabled = LogFactory.logModules.get("INFO");
        }

        public static void updateLevelModuleMap(String level) {
            updateLogModules(getLevelModuleMap(level));
        }

        public static Map<String, Boolean> getLevelModuleMap(String level) {
            Map<String, Boolean> modules = new HashMap<String, Boolean>();

            Level lev = Level.toLevel(level);
            if (Level.TRACE.isGreaterOrEqual(lev)) {
                modules.put(Level.TRACE.toString(), true);
            }
            else {
                modules.put(Level.TRACE.toString(), false);
            }
            if (Level.DEBUG.isGreaterOrEqual(lev)) {
                modules.put(Level.DEBUG.toString(), true);
            }
            else {
                modules.put(Level.DEBUG.toString(), false);
            }

            if (Level.INFO.isGreaterOrEqual(lev)) {
                modules.put(Level.INFO.toString(), true);
            }
            else {
                modules.put(Level.INFO.toString(), false);
            }

            if (Level.WARN.isGreaterOrEqual(lev)) {
                modules.put(Level.WARN.toString(), true);
            }
            else {
                modules.put(Level.WARN.toString(), false);
            }

            if (Level.ERROR.isGreaterOrEqual(lev)) {
                modules.put(Level.ERROR.toString(), true);
            }
            else {
                modules.put(Level.ERROR.toString(), false);
            }
            return modules;
        }

        public static void setThreshold(String level) {
            LoggerWrapper.setThreshold(Level.toLevel(level));
        }

        public static void setThreshold(Level level) {
            LoggerWrapper.setThreshold(level);
        }

        public static Level getThreshold() {
            return LoggerWrapper.getThreshold();
        }

        public static final String getLogName() {
            return logName;
        }
    }
}