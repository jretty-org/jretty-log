/* 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * [Jretty-Log && Mlf4j (Monitoring Logging Facade for Java)]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by ZollTy on 2013-6-20 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zollty
 * @since 2013-6-20
 */
public class LogFactory {

    private static String logName;
    private volatile static LoggerSupport logCreator;
    private static final String LOGFACTORY_CLASS_NAME = LogFactory.class.getName();

    private LogFactory() {
    }

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
            LogManager.initRefreshConfig();
        }
        LoggerWrapper logger = new LoggerWrapper(logCreator.newInstance(name));
        return logger;
    }

    public static class LogManager {

        /**
         * 线程安全的cacheLoggerMap容器，缓存系统所有Logger，不必每次都去logCreator新实例
         */
        public static final ConcurrentMap<String, LoggerWrapper> cacheLoggerMap = 
                new ConcurrentHashMap<String, LoggerWrapper>();

        public static final String DEFAULT_CONFIG_PATH = "jretty-log-default.properties";
        public static final String SPECIFY_CONFIG_PATH = "jretty-log.properties";
        
        public synchronized static void refreshLogConfig(final String configName) {
            InputStream in = null;
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(configName);
            Properties props = new Properties();
            try {
                props.load(in);
            }
            catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage() + ". Can not load the config: [" + configName
                        + "], please check If it exists? and Can be reached by Thread.currentThread().getContextClassLoader()?");
            }
            Map<String, String> pmap = LogUtils.covertProperties2Map(props);
            refreshLogConfig(pmap);
        }

        /** 
         * 刷新时，一定会更新parrentLogger和Threshold Level，但允许Appender为空，例如：<br>
         * <pre>
         * rootLogger=TRACE
         * #rootLogger=TRACE,LOG4J
         * logger.org.zollty.dbk=ERROR
         * </pre>
         * 没有设置Appender（LOG4J）等，则不更新Appender
         */
        public synchronized static void refreshLogConfig(Map<String, String> pmap) {
            
            String rootLogger = pmap.get("rootLogger");
            
            if(rootLogger==null || rootLogger.trim().length()==0) {
                throw new IllegalArgumentException("the rootLogger config can't be null or empty:"+rootLogger);
            }
            
            rootLogger = rootLogger.replace(" ", "");
            String[] rootarry = rootLogger.split(",");
            String threshold = rootarry[0].toUpperCase();

            String appenderFlag = null;
            if (rootarry.length > 1) {
                appenderFlag = rootarry[1];
            }
            
            // ~ set ParentLoggers -----
            Map<String, Level> parentLoggers = new TreeMap<String, Level>(new Comparator<String>() {
                @Override
                public int compare(String obj1, String obj2) {
                    return obj2.compareTo(obj1); // 降序排序
                }
            });
            for (String key : pmap.keySet()) {
                if (key.startsWith("logger.")) {
                    parentLoggers.put(key.substring(7, key.length()), Level.toLevel(pmap.get(key)));
                }
            }
            
            if (ConsoleLogger.LOG_NAME.equalsIgnoreCase(appenderFlag)
                    || ConsoleLogger.class.getName().equals(appenderFlag)) {

                LogManager.reset(ConsoleLogger.LOG_NAME, threshold, new ConsoleLogger(), parentLoggers);

                // 设置 ConsoleAppender

                ConsoleAppender.setGlobalLevel(threshold);
                String classNameLayout = pmap.get("appender.STDOUT.layout.className");
                if (classNameLayout != null) {
                    ConsoleAppender.setClassNameLayout(classNameLayout);
                }
                String showThread = pmap.get("appender.STDOUT.layout.showThread");
                if( showThread!=null && "true".equalsIgnoreCase(showThread)) {
                    ConsoleAppender.setShowThread(true);
                }
            }
            else if (Log4jLogger.LOG_NAME.equalsIgnoreCase(appenderFlag) 
                    || Log4jLogger.class.getName().equals(appenderFlag)) {

                LogManager.reset(Log4jLogger.LOG_NAME, threshold, new Log4jLogger(), parentLoggers);

                // 如果有log4j-config.xml文件，则reconfig log4j，否则沿用原来的配置。
                Log4jLogger.refreshLog4jConfig();

            }
            else if (LogbackLogger.LOG_NAME.equalsIgnoreCase(appenderFlag) 
                    || LogbackLogger.class.getName().equals(appenderFlag)) {

                LogManager.reset(LogbackLogger.LOG_NAME, threshold, new LogbackLogger(), parentLoggers);
            }
            else if (appenderFlag != null && appenderFlag.length() > 0) {

                LogManager.reset(appenderFlag, threshold, LogUtils.createLogCreator(appenderFlag),
                        parentLoggers);

            }
            
            pmap.put("##rootLogger=", threshold + "," + LogManager.getLogName());
            LogManager.storeConfigContent(pmap);

            if (logCreator == null) {
                throw new IllegalStateException("The log system is not config. Please check your logger config!");
            }

        }
        
        private static void initRefreshConfig() {
            // 指定配置
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(LogManager.SPECIFY_CONFIG_PATH);
            if (in == null) {
                // 默认配置
                in = Thread.currentThread().getContextClassLoader().getResourceAsStream(LogManager.DEFAULT_CONFIG_PATH);
            }
            boolean refreshed = false;
            if (in != null) {
                Properties props = new Properties();
                try {
                    props.load(in);
                    Map<String, String> pmap = LogUtils.covertProperties2Map(props);
                    LogManager.refreshLogConfig(pmap);
                    refreshed = true;
                }
                catch (IOException e) {
                }
                props.clear();
                props = null;
            }
            if (!refreshed) { // logback > log4j_1.2 > ConsoleLogger
                try {
                    Class.forName("ch.qos.logback.classic.LoggerContext");
                    LogManager.refreshLogConfig(LogbackLogger.LOG_NAME, "OFF");
                    LogUtils.report("WARN: No log Config was found, 'LogbackLogger' will be used and threshold level is 'OFF'.", null);
                } catch (ClassNotFoundException e) {
                    try {
                        Class.forName("org.apache.log4j.helpers.DateLayout");
                        LogManager.refreshLogConfig(Log4jLogger.LOG_NAME, "ALL");
                        LogUtils.report("WARN: No log Config was found, 'Log4jLogger' will be used and threshold level is 'OFF'.", null);
                    } catch (ClassNotFoundException es) {
                        LogManager.refreshLogConfig(ConsoleLogger.LOG_NAME, "TRACE");
                        LogUtils.report("WARN: No log Config was found, 'ConsoleLogger' will be used and threshold level is 'TRACE'.", null);
                    }
                }
            }
        }
        
        public synchronized static void refreshLogConfig(String logName, String level) {
            Map<String, String> cmap = new HashMap<String, String>();
            cmap.put("rootLogger", level+","+logName);
            LogManager.refreshLogConfig(cmap);
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

        public static String getLogName() {
            return logName;
        }
        
        /** 重置整个日志库配置到某个状态 */
        private static void reset(String logName, String threshold, LoggerSupport logCreator, Map<String, Level> parentLoggers) {
            
            if (threshold == null || threshold.length() == 0) {
                throw new IllegalArgumentException("the log 'threshold level' can't be null or empty.");
            }
            Level level = Level.toLevel(threshold.toUpperCase());
            
            LogManager.setThreshold(level);
            LoggerWrapper.setParentLoggers(parentLoggers);
            
            if (logName != null) {
                LogFactory.logName = logName;
            }
            if (logCreator != null) {
                LogFactory.logCreator = logCreator;

                boolean flag = true;
                while (flag) { // 防并发控制
                    try {
                        reloadLogger();
                        flag = false;
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
            }

            LogUtils.report("INFO: Refreshing LOG Config, LOG_NAME=[" + logName + "] Threshold LEVEL=[" + threshold + "].", logCreator.newInstance("root"));
        }
        
        // 更新所有已存在的logger
        private static void reloadLogger() {
            Set<Entry<String, LoggerWrapper>> entrySet = LogManager.cacheLoggerMap.entrySet();
            Map<String, Logger> newMap = new HashMap<String, Logger>();
            for (Entry<String, LoggerWrapper> entry : entrySet) {
                newMap.put(entry.getKey(), logCreator.newInstance(entry.getKey()));
            }
            Set<Entry<String, LoggerWrapper>> entrySet2 = LogManager.cacheLoggerMap.entrySet();
            for (Entry<String, LoggerWrapper> entry : entrySet2) {
                // 用新logCreator生成的实例，替换原来的Logger
                if (newMap.containsKey(entry.getKey())) {
                    entry.getValue().setLogger(newMap.get(entry.getKey()));
                } else {
                    entry.getValue().setLogger(logCreator.newInstance(entry.getKey()));
                }
            }
        }
        
        private static String configContent;
        private static Map<String, String> configMap = new HashMap<String, String>();
        
        private static void storeConfigContent(Map<String, String> pmap) {
            
            // 移除parentLogger配置，稍后重新设置
            List<String> loggerKeys = new ArrayList<String>();
            for (String key : configMap.keySet()) {
                if (key.startsWith("logger.")) {
                    loggerKeys.add(key);
                }
            }
            for(String key: loggerKeys) {
                configMap.remove(key);
            }
            
            // 重新设置值，覆盖其他值，比如rootLogger
            configMap.putAll(pmap);
            
            LogManager.configContent = LogUtils.toString(configMap);
        }
        
        public static String getConfigContent() {
            return LogManager.configContent;
        }
        

    }

}