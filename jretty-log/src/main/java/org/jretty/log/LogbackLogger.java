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
 * Create by ZollTy on 2013-6-22 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;

import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.spi.LocationAwareLogger;

/**
 * @author zollty
 * @since 2013-6-22
 */
public class LogbackLogger implements org.jretty.log.Logger, LoggerSupport, Serializable {

    private static final long serialVersionUID = -226607492227272649L;
    
    public static final String LOG_NAME = "LOGBACK";
    
    private static final String FQCN = LoggerWrapper.class.getName();

    /** Logger name */
    private final String name;

    /**
     * The underlying Logger implementation we are using.
     */
    protected transient volatile org.slf4j.Logger logger = null;
    
    static {
        org.slf4j.LoggerFactory.getLogger(LogbackLogger.class);
    }

    /**
     * Return the native Logger instance we are using.
     */
    public org.slf4j.Logger getLogger() {
        return StaticLoggerBinder.getSingleton().getLoggerFactory().getLogger(name);
    }

    public LogbackLogger() {
        this.name = null;
    }

    public LogbackLogger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    @Override
    public org.jretty.log.Logger newInstance(String name) {
        return new LogbackLogger(name);
    }

    @Override
    public void info(Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.INFO_INT, String.valueOf(message), null, null);
    }

    @Override
    public void info(Throwable e) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.INFO_INT, null, null, e);
    }

    @Override
    public void info(Throwable e, Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.INFO_INT, String.valueOf(message), null, e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.INFO_INT, String.valueOf(message), msgParams, null);
        }
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.INFO_INT, String.valueOf(message), msgParams, e);
        }
    }

    @Override
    public void warn(Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.WARN_INT, String.valueOf(message), null, null);
    }

    @Override
    public void warn(Throwable e) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.WARN_INT, null, null, e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.WARN_INT, String.valueOf(message), null, e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        if (getLogger().isWarnEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.WARN_INT, String.valueOf(message), msgParams, null);
        }
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isWarnEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.WARN_INT, String.valueOf(message), msgParams, e);
        }
    }

    @Override
    public void error(Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.ERROR_INT, String.valueOf(message), null, null);
    }

    @Override
    public void error(Throwable e) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.ERROR_INT, null, null, e);
    }

    @Override
    public void error(Throwable e, Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.ERROR_INT, String.valueOf(message), null, e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        if (getLogger().isErrorEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.ERROR_INT, String.valueOf(message), msgParams, null);
        }
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isErrorEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.ERROR_INT, String.valueOf(message), msgParams, e);
        }
    }

    @Override
    public void debug(Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, String.valueOf(message), null, null);
    }

    @Override
    public void debug(Throwable e) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, null, null, e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, String.valueOf(message), null, e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, String.valueOf(message), msgParams, null);
        }
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, String.valueOf(message), msgParams, e);
        }
    }

    @Override
    public void trace(Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, String.valueOf(message), null, null);
    }

    @Override
    public void trace(Throwable e) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, null, null, e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, String.valueOf(message), null, e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, String.valueOf(message), msgParams, null);
        }
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, String.valueOf(message), msgParams, e);
        }
    }

    @Override
    public void log(String callerFQCN, org.jretty.log.Level lev, Throwable t, Object msg, Object... msgParams) {
        int level;
        if (org.jretty.log.Level.TRACE.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.TRACE_INT;
        }
        else if (org.jretty.log.Level.DEBUG.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.DEBUG_INT;
        }
        else if (org.jretty.log.Level.INFO.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.INFO_INT;
        }
        else if (org.jretty.log.Level.WARN.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.WARN_INT;
        }
        else if (org.jretty.log.Level.ERROR.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.ERROR_INT;
        }
        else if (org.jretty.log.Level.FATAL.isGreaterOrEqual(lev)) {
            level = LocationAwareLogger.ERROR_INT;
        }
        else {
            throw new IllegalStateException("Level " + lev + " is not recognized.");
        }
        ((LocationAwareLogger)getLogger()).log(null, FQCN, level, String.valueOf(msg), msgParams, t);
    }

    @Override
    public boolean isTraceEnabled() {
        return getLogger().isTraceEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void trace(Object message, Object p0) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7}, null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8}, null);
        }
    }
    
    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (getLogger().isTraceEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.TRACE_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8, p9}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7}, null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8}, null);
        }
    }
    
    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (getLogger().isDebugEnabled()) {
            ((LocationAwareLogger)getLogger()).log(null, FQCN, LocationAwareLogger.DEBUG_INT, 
                    String.valueOf(message), new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8, p9}, null);
        }
    }

}