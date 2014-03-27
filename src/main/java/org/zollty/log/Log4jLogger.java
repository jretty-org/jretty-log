/* @(#)Log4jLogger.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-22 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author zollty
 * @since 2013-6-22
 */
public class Log4jLogger implements org.zollty.log.BasicLog, LoggerSupport {

    private static final String FQCN = LoggerWrapper.class.getName();

    private transient org.apache.log4j.Logger logger = null;

    public Log4jLogger() {
        doService(0); // 初始化log4j配置
    }

    public Log4jLogger(String name) {
        this.logger = org.apache.log4j.Logger.getLogger(name);
    }

    @Override
    public BasicLog newInstance(String name) {
        return new Log4jLogger(name);
    }

    @Override
    public Object[] doService(int serviceID, Object... args) {
        if (serviceID == 0) {
            refreshConfig();
        }
        return new Object[] {};
    }

    public void refreshConfig() {
        refreshLog4jConfig();
    }

    public static void refreshLog4jConfig() {
        URL url;
        url = Thread.currentThread().getContextClassLoader().getResource("log4j-config.xml");
        DOMConfigurator.configure(url);
    }

    @Override
    public void info(Object message) {
        logger.log(FQCN, Level.INFO, message, null);
    }

    @Override
    public void info(Throwable e) {
        logger.log(FQCN, Level.INFO, null, e);
    }

    @Override
    public void info(Throwable e, Object message) {
        logger.log(FQCN, Level.INFO, message, e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        if (logger.isInfoEnabled()) {
            logger.log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        if (logger.isInfoEnabled()) {
            logger.log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void warn(Object message) {
        logger.log(FQCN, Level.WARN, message, null);
    }

    @Override
    public void warn(Throwable e) {
        logger.log(FQCN, Level.WARN, null, e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        logger.log(FQCN, Level.WARN, message, e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        if (logger.isEnabledFor(Level.WARN)) {
            logger.log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        if (logger.isEnabledFor(Level.WARN)) {
            logger.log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void error(Object message) {
        logger.log(FQCN, Level.ERROR, message, null);
    }

    @Override
    public void error(Throwable e) {
        logger.log(FQCN, Level.ERROR, null, e);
    }

    @Override
    public void error(Throwable e, Object message) {
        logger.log(FQCN, Level.ERROR, message, e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        logger.log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), null);
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        logger.log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void debug(Object message) {
        logger.log(FQCN, Level.DEBUG, message, null);
    }

    @Override
    public void debug(Throwable e) {
        logger.log(FQCN, Level.DEBUG, null, e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        logger.log(FQCN, Level.DEBUG, message, e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        if (logger.isDebugEnabled()) {
            logger.log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        if (logger.isDebugEnabled()) {
            logger.log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void trace(Object message) {
        logger.log(FQCN, Level.TRACE, message, null);
    }

    @Override
    public void trace(Throwable e) {
        logger.log(FQCN, Level.TRACE, null, e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        logger.log(FQCN, Level.TRACE, message, e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        if (logger.isTraceEnabled()) {
            logger.log(FQCN, Level.TRACE, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        if (logger.isTraceEnabled()) {
            logger.log(FQCN, Level.TRACE, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

}
