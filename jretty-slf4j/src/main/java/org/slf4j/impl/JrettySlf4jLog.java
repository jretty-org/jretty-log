/* 
 * Copyright (C) 2013-2015 the original author or authors.
 * 
 * [Zollty-Log && Mlf4j (Monitoring Logging Facade for Java)]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by ZollTy on 2015-6-10 (http://blog.zollty.com/, zollty@163.com)
 */
package org.slf4j.impl;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.jretty.log.Level;
import org.jretty.log.Logger;

/**
 * 
 * @author zollty
 * @since 2015-6-10
 */
public class JrettySlf4jLog implements org.slf4j.Logger, Serializable {

    private static final long serialVersionUID = -5288628038918521711L;

    // used to store this logger's name to recreate it after serialization
    protected String name;

    // in both Log4jLogger and Jdk14Logger classes in the original JCL, the
    // logger instance is transient
    private transient Logger logger;

    private static final String FQCN = JrettySlf4jLog.class.getName();

    JrettySlf4jLog(Logger logger) {
        this.logger = logger;
        this.name = logger.getName();
    }
    
    
    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arg1
     * @param arg2
     */
    private void formatAndLog(Level level, String format, Object arg1, Object arg2) {
        FormattingTuple tp = MessageFormatter.format(format, arg1, arg2);
        logger.log(FQCN, level, tp.getThrowable(), tp.getMessage());
    }

    /**
     * For formatted messages, first substitute arguments and then log.
     *
     * @param level
     * @param format
     * @param arguments a list of 3 ore more arguments
     */
    private void formatAndLog(Level level, String format, Object... arguments) {
        FormattingTuple tp = MessageFormatter.arrayFormat(format, arguments);
        logger.log(FQCN, level, tp.getThrowable(), tp.getMessage());
    }
    
    
    
    
    /**
     * Delegates to the <code>isTraceEnabled<code> method of the wrapped 
     * <code>org.slf4j.Logger</code> instance.
     */
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    public boolean isWarnEnabled() {
        return true;
    }

    /**
     * Directly delegates to the wrapped <code>org.slf4j.Logger</code> instance.
     */
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public void trace(String msg) {
        logger.log(FQCN, Level.TRACE, null, msg);
    }

    @Override
    public void trace(String format, Object arg) {
        formatAndLog(Level.TRACE, format, arg, null);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        formatAndLog(Level.TRACE, format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
        formatAndLog(Level.TRACE, format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
        logger.log(FQCN, Level.TRACE, t, msg);
    }


    @Override
    public void debug(String msg) {
        logger.log(FQCN, Level.DEBUG, null, msg);
    }

    @Override
    public void debug(String format, Object arg) {
        formatAndLog(Level.DEBUG, format, arg, null);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        formatAndLog(Level.DEBUG, format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
        formatAndLog(Level.DEBUG, format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
        logger.log(FQCN, Level.DEBUG, t, msg);
    }


    @Override
    public void info(String msg) {
        logger.log(FQCN, Level.INFO, null, msg);
    }

    @Override
    public void info(String format, Object arg) {
        formatAndLog(Level.INFO, format, arg, null);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        formatAndLog(Level.INFO, format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
        formatAndLog(Level.INFO, format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
        logger.log(FQCN, Level.INFO, t, msg);
    }

    @Override
    public void warn(String msg) {
        logger.log(FQCN, Level.WARN, null, msg);
    }

    @Override
    public void warn(String format, Object arg) {
        formatAndLog(Level.WARN, format, arg, null);
    }

    @Override
    public void warn(String format, Object... arguments) {
        formatAndLog(Level.WARN, format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        formatAndLog(Level.WARN, format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
        logger.log(FQCN, Level.WARN, t, msg);
    }

    @Override
    public void error(String msg) {
        logger.log(FQCN, Level.ERROR, null, msg);
    }

    @Override
    public void error(String format, Object arg) {
        formatAndLog(Level.ERROR, format, arg, null);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        formatAndLog(Level.ERROR, format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments) {
        formatAndLog(Level.ERROR, format, arguments);
    }

    @Override
    public void error(String msg, Throwable t) {
        logger.log(FQCN, Level.ERROR, t, msg);
    }
    
    
    // --NamedLoggerBase ----------------------------------------

    public String getName() {
        return name;
    }

    /**
     * Replace this instance with a homonymous (same name) logger returned 
     * by LoggerFactory. Note that this method is only called during 
     * deserialization.
     * 
     * <p>
     * This approach will work well if the desired ILoggerFactory is the one
     * references by LoggerFactory. However, if the user manages its logger hierarchy
     * through a different (non-static) mechanism, e.g. dependency injection, then
     * this approach would be mostly counterproductive.
     * 
     * @return logger with same name as returned by LoggerFactory
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException {
        
        Logger logger = org.jretty.log.LogFactory.getLogger(this.name);
        return new JrettySlf4jLog(logger);
    }
    
    
    // MarkerIgnoringBase -----------------------------------
    
    public boolean isTraceEnabled(Marker marker) {
        return isTraceEnabled();
    }

    public void trace(Marker marker, String msg) {
        trace(msg);
    }

    public void trace(Marker marker, String format, Object arg) {
        trace(format, arg);
    }

    public void trace(Marker marker, String format, Object arg1, Object arg2) {
        trace(format, arg1, arg2);
    }

    public void trace(Marker marker, String format, Object... arguments) {
        trace(format, arguments);
    }

    public void trace(Marker marker, String msg, Throwable t) {
        trace(msg, t);
    }

    public boolean isDebugEnabled(Marker marker) {
        return isDebugEnabled();
    }

    public void debug(Marker marker, String msg) {
        debug(msg);
    }

    public void debug(Marker marker, String format, Object arg) {
        debug(format, arg);
    }

    public void debug(Marker marker, String format, Object arg1, Object arg2) {
        debug(format, arg1, arg2);
    }

    public void debug(Marker marker, String format, Object... arguments) {
        debug(format, arguments);
    }

    public void debug(Marker marker, String msg, Throwable t) {
        debug(msg, t);
    }

    public boolean isInfoEnabled(Marker marker) {
        return isInfoEnabled();
    }

    public void info(Marker marker, String msg) {
        info(msg);
    }

    public void info(Marker marker, String format, Object arg) {
        info(format, arg);
    }

    public void info(Marker marker, String format, Object arg1, Object arg2) {
        info(format, arg1, arg2);
    }

    public void info(Marker marker, String format, Object... arguments) {
        info(format, arguments);
    }

    public void info(Marker marker, String msg, Throwable t) {
        info(msg, t);
    }

    public boolean isWarnEnabled(Marker marker) {
        return isWarnEnabled();
    }

    public void warn(Marker marker, String msg) {
        warn(msg);
    }

    public void warn(Marker marker, String format, Object arg) {
        warn(format, arg);
    }

    public void warn(Marker marker, String format, Object arg1, Object arg2) {
        warn(format, arg1, arg2);
    }

    public void warn(Marker marker, String format, Object... arguments) {
        warn(format, arguments);
    }

    public void warn(Marker marker, String msg, Throwable t) {
        warn(msg, t);
    }

    public boolean isErrorEnabled(Marker marker) {
        return isErrorEnabled();
    }

    public void error(Marker marker, String msg) {
        error(msg);
    }

    public void error(Marker marker, String format, Object arg) {
        error(format, arg);
    }

    public void error(Marker marker, String format, Object arg1, Object arg2) {
        error(format, arg1, arg2);
    }

    public void error(Marker marker, String format, Object... arguments) {
        error(format, arguments);
    }

    public void error(Marker marker, String msg, Throwable t) {
        error(msg, t);
    }

}
