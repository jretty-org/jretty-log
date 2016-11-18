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
 * Create by ZollTy on 2014-6-09 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.jretty.log.LogFactory.LogManager;

/**
 * java.util.logging.Logger的包装类，支持SUN JDK和IBM JDK，其他JDK未经过测试。
 * 
 * @author zollty
 * @since 2014-6-9
 */
public class Jdk14Logger implements org.jretty.log.Logger, LoggerSupport, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4683808963404613055L;

    /**
     * The underlying Logger implementation we are using.
     */
    protected transient Logger log;

    /**
     * The name of the logger we are wrapping.
     */
    protected final String loggerName;

    public Jdk14Logger() {
        this.loggerName = null;
    }

    public Jdk14Logger(String name) {
        this.loggerName = name;
        this.log = Logger.getLogger(name);
    }

    @Override
    public org.jretty.log.Logger newInstance(String name) {
        return new Jdk14Logger(name);
    }

    public static Level getLevel() {
        if (LogManager.getThreshold().equals(org.jretty.log.Level.FATAL) 
                || LogManager.getThreshold().equals(org.jretty.log.Level.ERROR)) {
            return Level.SEVERE;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.WARN)) {
            return Level.WARNING;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.INFO)) {
            return Level.INFO;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.DEBUG)) {
            return Level.FINE;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.TRACE)) {
            return Level.FINEST;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.ALL)) {
            return Level.ALL;
        }
        if (LogManager.getThreshold().equals(org.jretty.log.Level.OFF)) {
            return Level.OFF;
        }
        return Level.INFO;
    }

    /**
     * Logs a message with <code>java.util.logging.Level.FINEST</code>.
     *
     * @param message
     *            to log
     * @see org.apache.commons.logging.Log#trace(Object)
     */
    @Override
    public void trace(Object message) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), message.toString());
    }

    @Override
    public void trace(Throwable e) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), "", e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), message.toString(), e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams));
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void debug(Object message) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), message.toString());
    }

    @Override
    public void debug(Throwable e) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), "", e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), message.toString(), e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams));
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void info(Object message) {
        log.logp(Level.INFO, loggerName, getMethodInfo(loggerName), message.toString());
    }

    @Override
    public void info(Throwable e) {
        log.logp(Level.INFO, loggerName, getMethodInfo(loggerName), "", e);
    }

    @Override
    public void info(Throwable e, Object message) {
        log.logp(Level.INFO, loggerName, getMethodInfo(loggerName), message.toString(), e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        log.logp(Level.INFO, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams));
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        log.logp(Level.INFO, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void warn(Object message) {
        log.logp(Level.WARNING, loggerName, getMethodInfo(loggerName), message.toString());
    }

    @Override
    public void warn(Throwable e) {
        log.logp(Level.WARNING, loggerName, getMethodInfo(loggerName), "", e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        log.logp(Level.WARNING, loggerName, getMethodInfo(loggerName), message.toString(), e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        log.logp(Level.WARNING, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams));
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        log.logp(Level.WARNING, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void error(Object message) {
        log.logp(Level.SEVERE, loggerName, getMethodInfo(loggerName), message.toString());
    }

    @Override
    public void error(Throwable e) {
        log.logp(Level.SEVERE, loggerName, getMethodInfo(loggerName), "", e);
    }

    @Override
    public void error(Throwable e, Object message) {
        log.logp(Level.SEVERE, loggerName, getMethodInfo(loggerName), message.toString(), e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        log.logp(Level.SEVERE, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams));
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        log.logp(Level.SEVERE, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void log(String callerFQCN, org.jretty.log.Level level, Throwable t, Object message, Object... msgParams) {
        Level julLevel;
        if (level == null) {
            julLevel = getLevel();
        }
        else if (level.equals(org.jretty.log.Level.FATAL) || level.equals(org.jretty.log.Level.ERROR)) {
            julLevel = Level.SEVERE;
        }
        else if (level.equals(org.jretty.log.Level.WARN)) {
            julLevel = Level.WARNING;
        }
        else if (level.equals(org.jretty.log.Level.INFO)) {
            julLevel = Level.INFO;
        }
        else if (level.equals(org.jretty.log.Level.DEBUG)) {
            julLevel = Level.FINE;
        }
        else if (level.equals(org.jretty.log.Level.TRACE)) {
            julLevel = Level.FINEST;
        }
        else if (level.equals(org.jretty.log.Level.ALL)) {
            julLevel = Level.ALL;
        }
        else if (level.equals(org.jretty.log.Level.OFF)) {
            julLevel = Level.OFF;
        }
        else {
            throw new IllegalStateException("Level " + level + " is not recognized.");
        }
        if (log.isLoggable(julLevel)) {
            log(callerFQCN, julLevel, LogUtils.replace(message.toString(), msgParams), t);
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isLoggable(Level.FINEST);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isLoggable(Level.FINE);
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isLoggable(Level.INFO);
    }

    // --------------------------------------------------------- Private Methods

    private static final int pos = 3;

    // static {
    // if (JvmUtils.JDK_VENDER_IBM.equals(JvmUtils.getJDKVernder())) {
    // pos = 5;
    // }
    private static String getMethodInfo(String loggerName) {
        // Hack to get the stack trace.
        Throwable dummyException = new Throwable();
        StackTraceElement st[] = dummyException.getStackTrace();

        if (st.length > pos) {
            if (st[pos].getClassName().equals(loggerName)) {
                return "#" + st[pos].getMethodName() + "-" + st[pos].getLineNumber();
            }
            return st[pos].getClassName() + "#" + st[pos].getMethodName() + "-" + st[pos].getLineNumber();
        }
        return "unknown";
    }

    /**
     * Log the message at the specified level with the specified throwable if any. This method creates a LogRecord and fills in caller date before calling this
     * instance's JDK14 logger.
     */
    private void log(String callerFQCN, Level level, String msg, Throwable t) {
        // millis and thread are filled by the constructor
        LogRecord record = new LogRecord(level, msg);
        record.setLoggerName(loggerName);
        record.setThrown(t);
        fillCallerData(callerFQCN, record);
        log.log(record);
    }

    /**
     * Fill in caller data if possible.
     * 
     * @param record
     *            The record to update
     */
    private final void fillCallerData(String callerFQCN, LogRecord record) {
        StackTraceElement[] steArray = new Throwable().getStackTrace();
        int selfIndex = -1;
        for (int i = 0; i < steArray.length; i++) {
            final String className = steArray[i].getClassName();
            if (className.equals(callerFQCN)) {
                selfIndex = i;
                break;
            }
        }

        int found = -1;
        for (int i = selfIndex + 1; i < steArray.length; i++) {
            final String className = steArray[i].getClassName();
            if (!(className.equals(callerFQCN))) {
                found = i;
                break;
            }
        }

        if (found != -1) {
            StackTraceElement ste = steArray[found];
            // setting the class name has the side effect of setting
            // the needToInferCaller variable to false.
            record.setSourceClassName(ste.getClassName());
            record.setSourceMethodName(ste.getMethodName());
        }
    }

    @Override
    public String getName() {
        return loggerName;
    }

    @Override
    public void trace(Object message, Object p0) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), p0));
    }

    @Override
    public void trace(Object message, Object p0, Object p1) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8));
    }

    @Override
    public void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        log.logp(Level.FINEST, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8, p9));
    }

    @Override
    public void debug(Object message, Object p0) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), LogUtils.replace(message.toString(), p0));
    }

    @Override
    public void debug(Object message, Object p0, Object p1) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8));
    }

    @Override
    public void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        log.logp(Level.FINE, loggerName, getMethodInfo(loggerName), 
                LogUtils.replace(message.toString(), p0, p1, p2, p3, p4, p5, p6, p7, p8, p9));
    }

}
