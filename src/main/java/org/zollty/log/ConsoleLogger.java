/* @(#)ConsoleLogger.java 
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

/**
 * @author zollty
 * @since 2013-6-20
 */
public class ConsoleLogger extends ConsoleAppender {

    private String className;

    public ConsoleLogger(String className) {
        this.className = className;
    }

    public ConsoleLogger() {
        // doService(0);
    }

    @Override
    public BasicLog newInstance(String name) {
        return new ConsoleLogger(name);
    }

    @Override
    public Object[] doService(int serviceID, Object... args) {
        return new Object[] {};
    }

    @Override
    public void info(Object message) {
        // if(Level.INFO.isGreaterOrEqual(level))
        add(message, className, Level.INFO, null);
    }

    @Override
    public void info(Throwable e) {
        // if(Level.INFO.isGreaterOrEqual(level))
        add(null, className, Level.INFO, e);
    }

    @Override
    public void info(Throwable e, Object message) {
        // if(Level.INFO.isGreaterOrEqual(level))
        add(message, className, Level.INFO, e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        // if(Level.INFO.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.INFO, null);
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        // if(Level.INFO.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.INFO, e);
    }

    @Override
    public void warn(Object message) {
        // if(Level.WARN.isGreaterOrEqual(level))
        add(message, className, Level.WARN, null);
    }

    @Override
    public void warn(Throwable e) {
        // if(Level.WARN.isGreaterOrEqual(level))
        add(null, className, Level.WARN, e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        // if(Level.WARN.isGreaterOrEqual(level))
        add(message, className, Level.WARN, e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        // if(Level.WARN.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.WARN, null);
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        // if(Level.WARN.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.WARN, e);
    }

    @Override
    public void error(Object message) {
        // if(Level.ERROR.isGreaterOrEqual(level))
        add(message, className, Level.ERROR, null);
    }

    @Override
    public void error(Throwable e) {
        // if(Level.ERROR.isGreaterOrEqual(level))
        add(null, className, Level.ERROR, e);
    }

    @Override
    public void error(Throwable e, Object message) {
        // if(Level.ERROR.isGreaterOrEqual(level))
        add(message, className, Level.ERROR, e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        // if(Level.ERROR.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.ERROR, null);
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        // if(Level.ERROR.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.ERROR, e);
    }

    @Override
    public void debug(Object message) {
        // if(Level.DEBUG.isGreaterOrEqual(level))
        add(message, className, Level.DEBUG, null);
    }

    @Override
    public void debug(Throwable e) {
        // if(Level.DEBUG.isGreaterOrEqual(level))
        add(null, className, Level.DEBUG, e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        // if(Level.DEBUG.isGreaterOrEqual(level))
        add(message, className, Level.DEBUG, e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        // if(Level.DEBUG.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.DEBUG, null);
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        // if(Level.DEBUG.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.DEBUG, e);
    }

    @Override
    public void trace(Object message) {
        // if(Level.TRACE.isGreaterOrEqual(level))
        add(message, className, Level.TRACE, null);
    }

    @Override
    public void trace(Throwable e) {
        // if(Level.TRACE.isGreaterOrEqual(level))
        add(null, className, Level.TRACE, e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        // if(Level.TRACE.isGreaterOrEqual(level))
        add(message, className, Level.TRACE, e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        // if(Level.TRACE.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.TRACE, null);
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        // if(Level.TRACE.isGreaterOrEqual(level))
        add(LogUtils.replace(message.toString(), msgParams), className, Level.TRACE, e);
    }
}
