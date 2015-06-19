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
package org.apache.commons.logging.impl;

import java.io.ObjectStreamException;
import java.io.Serializable;

import org.zollty.log.Level;
import org.zollty.log.Logger;

/**
 * 
 * @author zollty
 * @since 2015-6-10
 */
public class Mlf4jLog implements org.apache.commons.logging.Log, Serializable {

    private static final long serialVersionUID = -6138356624252131937L;

    // used to store this logger's name to recreate it after serialization
    protected String name;

    // in both Log4jLogger and Jdk14Logger classes in the original JCL, the
    // logger instance is transient
    private transient Logger logger;

    private static final String FQCN = Mlf4jLog.class.getName();

    Mlf4jLog(Logger logger) {
        this.logger = logger;
        this.name = logger.getName();
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

    /**
     * Delegates to the <code>isErrorEnabled<code> method of the wrapped 
     */
    public boolean isFatalEnabled() {
        return true;
    }

    /**
     * Converts the input parameter to String and then delegates to the debug
     * method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void trace(Object message) {
        logger.log(FQCN, Level.TRACE, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * debug method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void trace(Object message, Throwable t) {
        logger.log(FQCN, Level.TRACE, t, String.valueOf(message));
    }

    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void debug(Object message) {
        logger.log(FQCN, Level.DEBUG, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void debug(Object message, Throwable t) {
        logger.log(FQCN, Level.DEBUG, t, String.valueOf(message));
    }

    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void info(Object message) {
        logger.log(FQCN, Level.INFO, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void info(Object message, Throwable t) {
        logger.log(FQCN, Level.INFO, t, String.valueOf(message));
    }

    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void warn(Object message) {
        logger.log(FQCN, Level.WARN, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void warn(Object message, Throwable t) {
        logger.log(FQCN, Level.WARN, t, String.valueOf(message));
    }

    /**
     * Converts the input parameter to String and then delegates to the wrapped
     * <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void error(Object message) {
        logger.log(FQCN, Level.ERROR, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void error(Object message, Throwable t) {
        logger.log(FQCN, Level.ERROR, t, String.valueOf(message));
    }

    /**
     * Converts the input parameter to String and then delegates to the error
     * method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     */
    public void fatal(Object message) {
        logger.log(FQCN, Level.FATAL, null, String.valueOf(message));
    }

    /**
     * Converts the first input parameter to String and then delegates to the
     * error method of the wrapped <code>org.slf4j.Logger</code> instance.
     * 
     * @param message
     *          the message to log. Converted to {@link String}
     * @param t
     *          the exception to log
     */
    public void fatal(Object message, Throwable t) {
        logger.log(FQCN, Level.FATAL, t, String.valueOf(message));
    }

    /**
     * Replace this instance with a homonymous (same name) logger returned by
     * LoggerFactory. Note that this method is only called during deserialization.
     * 
     * @return logger with same name as returned by LoggerFactory
     * @throws ObjectStreamException
     */
    protected Object readResolve() throws ObjectStreamException {
        Logger logger = org.zollty.log.LogFactory.getLogger(this.name);
        return new Mlf4jLog(logger);
    }

}
