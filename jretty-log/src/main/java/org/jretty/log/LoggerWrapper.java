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
 * Create by ZollTy on 2013-6-21 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.jretty.log.LogFactory.LogManager;

/**
 * @author zollty
 * @since 2013-6-21
 */
public class LoggerWrapper implements Logger, LoggerWrapperAPI, Serializable {
    
    private static final long serialVersionUID = 3584029831676135578L;
    /** Zollty-Log 全局 Level 阀门 */
    private static Level threshold = Level.ALL;
    /** Zollty-Log 全局 ParentLoggers 设置 */
    @SuppressWarnings("unchecked")
    private static Map<String, Level> parentLoggers = Collections.EMPTY_MAP;

    /** Logger name */
    private String name;
    
    /** 如果设置了locallevel，则以它为准，否则以全局threshold为准 */
    private Level localLevel;
    
    private transient volatile Logger logger = null;
    
    public LoggerWrapper(Logger logger) {
        this.logger = logger;
        this.name = logger.getName();
        
        LogManager.cacheLoggerMap.put(name, this);
    }
    
    /** Zollty-Log 全局 Level 阀门 */
    public static Level getThreshold() {
        return LoggerWrapper.threshold;
    }

    /** Zollty-Log 全局 Level 阀门 */
    public static void setThreshold(Level threshold) {
        LoggerWrapper.threshold = threshold;
    }
    
    /** Zollty-Log 全局 ParentLoggers 设置 */
    public static void setParentLoggers(Map<String, Level> parentLoggers) {
        if (parentLoggers != null) {
            /** 根据key进行降序排序，例如<pre>
             *  org.zollty.util=INFO
                org.zollty.util.config.xml=ERROR
                org.zollty=WARN
                org.zollty.util.support=TRACE
                org.zollty.util.config=INFO
             排序后变成：
                org.zollty.util.support=TRACE
                org.zollty.util.config.xml=ERROR
                org.zollty.util.config=INFO
                org.zollty.util=INFO
                org.zollty=WARN
             * <pre>
             */
            TreeMap<String, Level> temp = new TreeMap<String, Level>(new Comparator<String>() {
                @Override
                public int compare(String obj1, String obj2) {
                    return obj2.compareTo(obj1); // 降序排序
                }
            });
            
            temp.putAll(parentLoggers);
            
            LoggerWrapper.parentLoggers = temp;
        }
    }
    
    // ~ LoggerWrapperAPI-----
    
    /**
     * Don't change the logger, beacause it's transient volatile.
     */
    @Override
    public final Logger getLogger() {
        return logger;
    }
    
    @Override
    public void setLogger(Logger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("the new logger can't be null");
        }
        if (logger.getName().equals(name)) {
            this.logger = logger;
        }
        else {
            LogManager.cacheLoggerMap.remove(name); // 移除旧对象
            this.name = logger.getName();
            this.logger = logger;
            LogManager.cacheLoggerMap.put(name, this); // 对象和新name关联
        }
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Level getLocalLevel() {
        return localLevel;
    }

    @Override
    public void setLocalLevel(Level localLevel) {
        this.localLevel = localLevel;
    }
    
    private boolean isEffectiveLevel(Level level) {
        boolean ret = localLevel == null ? level.isGreaterOrEqual(threshold) : level.isGreaterOrEqual(localLevel);
        if (!ret) {
            return ret;
        }
        for (String key : parentLoggers.keySet()) {
            if (name.startsWith(key)) {
                if (!level.isGreaterOrEqual(parentLoggers.get(key))) {
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    // ~ Logger API-----

    @Override
    public void info(Object message) {
        if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
            LoggerManager.infoIncrement(name);
            this.logger.info(message);
        }
    }

    @Override
    public void info(Throwable e) {
        if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
            LoggerManager.infoIncrement(name);
            this.logger.info(e);
        }
    }

    @Override
    public void info(Throwable e, Object message) {
        if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
            LoggerManager.infoIncrement(name);
            this.logger.info(e, message);
        }
    }

    @Override
    public void info(Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
            LoggerManager.infoIncrement(name);
            this.logger.info(message, msgParams);
        }
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
            LoggerManager.infoIncrement(name);
            this.logger.info(e, message, msgParams);
        }
    }

    @Override
    public void warn(Object message) {
        if (isEffectiveLevel(Level.WARN)) {
            LoggerManager.warnIncrement(name);
            this.logger.warn(message);
        }
    }

    @Override
    public void warn(Throwable e) {
        if (isEffectiveLevel(Level.WARN)) {
            LoggerManager.warnIncrement(name);
            this.logger.warn(e);
        }
    }

    @Override
    public void warn(Throwable e, Object message) {
        if (isEffectiveLevel(Level.WARN)) {
            LoggerManager.warnIncrement(name);
            this.logger.warn(e, message);
        }
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.WARN)) {
            LoggerManager.warnIncrement(name);
            this.logger.warn(message, msgParams);
        }
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.WARN)) {
            LoggerManager.warnIncrement(name);
            this.logger.warn(e, message, msgParams);
        }
    }

    @Override
    public void error(Object message) {
        if (isEffectiveLevel(Level.ERROR)) {
            LoggerManager.errorIncrement(name);
            this.logger.error(message);
        }
    }

    @Override
    public void error(Throwable e) {
        if (isEffectiveLevel(Level.ERROR)) {
            LoggerManager.errorIncrement(name);
            this.logger.error(e);
        }
    }

    @Override
    public void error(Throwable e, Object message) {
        if (isEffectiveLevel(Level.ERROR)) {
            LoggerManager.errorIncrement(name);
            this.logger.error(e, message);
        }
    }

    @Override
    public void error(Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.ERROR)) {
            LoggerManager.errorIncrement(name);
            this.logger.error(message, msgParams);
        }
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.ERROR)) {
            LoggerManager.errorIncrement(name);
            this.logger.error(e, message, msgParams);
        }
    }

    @Override
    public void debug(Object message) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message);
        }
    }

    @Override
    public void debug(Throwable e) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(e);
        }
    }

    @Override
    public void debug(Throwable e, Object message) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(e, message);
        }
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, msgParams);
        }
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(e, message, msgParams);
        }
    }

    @Override
    public void trace(Object message) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message);
        }
    }

    @Override
    public void trace(Throwable e) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(e);
        }
    }

    @Override
    public void trace(Throwable e, Object message) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(e, message);
        }
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, msgParams);
        }
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(e, message, msgParams);
        }
    }

    @Override
    public void log(String callerFQCN, org.jretty.log.Level lev, Throwable t, Object msg, Object... msgParams) {
        if (org.jretty.log.Level.TRACE.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
                LoggerManager.traceIncrement(name);
                this.logger.log(callerFQCN, lev, t, msg, msgParams);
            }
        }
        else if (org.jretty.log.Level.DEBUG.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
                LoggerManager.debugIncrement(name);
                this.logger.log(callerFQCN, lev, t, msg, msgParams);
            }
        }
        else if (org.jretty.log.Level.INFO.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.INFO) && this.logger.isInfoEnabled()) {
                LoggerManager.infoIncrement(name);
                this.logger.log(callerFQCN, lev, t, msg, msgParams);
            }
        }
        else if (org.jretty.log.Level.WARN.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.WARN)) {
                LoggerManager.warnIncrement(name);
                this.logger.log(callerFQCN, lev, t, msg, msgParams);
            }
        }
        else if (org.jretty.log.Level.ERROR.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.ERROR)) {
                LoggerManager.errorIncrement(name);
                this.logger.log(callerFQCN, lev, t, msg, msgParams);
            }
        }
        else if (org.jretty.log.Level.FATAL.isGreaterOrEqual(lev)) {
            if (isEffectiveLevel(Level.FATAL)) {
                LoggerManager.errorIncrement(name);
                this.logger.log(callerFQCN, Level.FATAL, t, msg, msgParams);
            }
        }
        else {
            throw new IllegalStateException("Level " + lev + " is not recognized.");
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return isEffectiveLevel(Level.TRACE) ? this.logger.isTraceEnabled() : false;
    }

    @Override
    public boolean isDebugEnabled() {
        return isEffectiveLevel(Level.DEBUG) ? this.logger.isDebugEnabled() : false;
    }

    @Override
    public boolean isInfoEnabled() {
        return isEffectiveLevel(Level.INFO) ? this.logger.isInfoEnabled() : false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void trace(Object message, Object p0) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4, p5);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4, p5, p6);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (isEffectiveLevel(Level.TRACE) && this.logger.isTraceEnabled()) {
            LoggerManager.traceIncrement(name);
            this.logger.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        }
    }

    @Override
    public void debug(Object message, Object p0) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4, p5);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4, p5, p6);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (isEffectiveLevel(Level.DEBUG) && this.logger.isDebugEnabled()) {
            LoggerManager.debugIncrement(name);
            this.logger.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
        }
    }

}
