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
import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author zollty
 * @since 2013-6-22
 */
public class Log4jLogger implements org.jretty.log.Logger, LoggerSupport, Serializable {

    private static final long serialVersionUID = -226607492227272649L;

    public static String configConfigLocation = "log4j-config.xml";

    public static final String LOG_NAME = "LOG4J";

    private static final String FQCN = LoggerWrapper.class.getName();

    /** Logger name */
    private final String name;

    /**
     * The underlying Logger implementation we are using.
     */
    protected transient volatile org.apache.log4j.Logger logger = null;

    private static final Level traceLevel;

    static {
        if (!Priority.class.isAssignableFrom(Level.class)) {
            // nope, this is log4j 1.3, so force an ExceptionInInitializerError
            throw new InstantiationError("Log4J 1.2 not available");
        }

        // Releases of log4j1.2 >= 1.2.12 have Priority.TRACE available, earlier
        // versions do not. If TRACE is not available, then we have to map
        // calls to Log.trace(...) onto the DEBUG level.

        Level _traceLevel;
        try {
            _traceLevel = (Level) Level.class.getDeclaredField("TRACE").get(null);
        } catch (Exception ex) {
            // ok, trace not available
            _traceLevel = Level.DEBUG;
        }
        traceLevel = _traceLevel;
    }

    /**
     * Return the native Logger instance we are using.
     */
    public org.apache.log4j.Logger getLogger() {
        org.apache.log4j.Logger result = logger;
        if (result == null) {
            synchronized (this) {
                result = logger;
                if (result == null) {
                    logger = result = org.apache.log4j.Logger.getLogger(name);
                }
            }
        }
        return result;
    }

    public Log4jLogger() {
        this.name = null;
    }

    public Log4jLogger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    @Override
    public org.jretty.log.Logger newInstance(String name) {
        return new Log4jLogger(name);
    }

    public static void refreshLog4jConfig() {
        URL url = LogUtils.getResource(configConfigLocation);
        if (url != null) {
            DOMConfigurator.configure(url);
        }
    }

    @Override
    public void info(Object message) {
        getLogger().log(FQCN, Level.INFO, message, null);
    }

    @Override
    public void info(Throwable e) {
        getLogger().log(FQCN, Level.INFO, null, e);
    }

    @Override
    public void info(Throwable e, Object message) {
        getLogger().log(FQCN, Level.INFO, message, e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            getLogger().log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            getLogger().log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void warn(Object message) {
        getLogger().log(FQCN, Level.WARN, message, null);
    }

    @Override
    public void warn(Throwable e) {
        getLogger().log(FQCN, Level.WARN, null, e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        getLogger().log(FQCN, Level.WARN, message, e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        if (getLogger().isEnabledFor(Level.WARN)) {
            getLogger().log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isEnabledFor(Level.WARN)) {
            getLogger().log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void error(Object message) {
        getLogger().log(FQCN, Level.ERROR, message, null);
    }

    @Override
    public void error(Throwable e) {
        getLogger().log(FQCN, Level.ERROR, null, e);
    }

    @Override
    public void error(Throwable e, Object message) {
        getLogger().log(FQCN, Level.ERROR, message, e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        getLogger().log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), null);
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        getLogger().log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void debug(Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, null);
    }

    @Override
    public void debug(Throwable e) {
        getLogger().log(FQCN, Level.DEBUG, null, e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void trace(Object message) {
        getLogger().log(FQCN, traceLevel, message, null);
    }

    @Override
    public void trace(Throwable e) {
        getLogger().log(FQCN, traceLevel, null, e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        getLogger().log(FQCN, traceLevel, message, e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void log(String callerFQCN, org.jretty.log.Level lev, Throwable t, Object msg, Object... msgParams) {
        Level level;
        if (org.jretty.log.Level.TRACE.isGreaterOrEqual(lev)) {
            level = traceLevel;
        }
        else if (org.jretty.log.Level.DEBUG.isGreaterOrEqual(lev)) {
            level = Level.DEBUG;
        }
        else if (org.jretty.log.Level.INFO.isGreaterOrEqual(lev)) {
            level = Level.INFO;
        }
        else if (org.jretty.log.Level.WARN.isGreaterOrEqual(lev)) {
            level = Level.WARN;
        }
        else if (org.jretty.log.Level.ERROR.isGreaterOrEqual(lev)) {
            level = Level.ERROR;
        }
        else if (org.jretty.log.Level.FATAL.isGreaterOrEqual(lev)) {
            level = Level.FATAL;
        }
        else {
            throw new IllegalStateException("Level " + lev + " is not recognized.");
        }
        logger.log(callerFQCN, level, msg, t);
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
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), p0), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), p0, p1), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), p0, p1, p2), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, LogUtils.replace(message.toString(), p0, p1, p2, p3), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8), null);
        }
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, traceLevel, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8, p9), null);
        }
    }

    @Override
    public void debug(Object message, Object p0) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), p0), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8), null);
        }
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, 
                    LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8, p9), null);
        }
    }

}
