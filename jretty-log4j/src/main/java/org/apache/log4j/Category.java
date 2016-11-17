/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import org.apache.log4j.helpers.NullEnumeration;

import java.util.Enumeration;

/**
 * <p>
 * This class is a minimal implementation of the original
 * <code>org.apache.log4j.Category</code> class (as found in log4j 1.2) by
 * delegation of all calls to a {@link org.slf4j.Logger} instance.
 * </p>
 *
 * <p>
 * Log4j's <code>trace</code>, <code>debug()</code>, <code>info()</code>,
 * <code>warn()</code>, <code>error()</code> printing methods are directly
 * mapped to their SLF4J equivalents. Log4j's <code>fatal()</code> printing
 * method is mapped to SLF4J's <code>error()</code> method with a FATAL marker.
 *
 * @author S&eacute;bastien Pennec
 * @author Ceki G&uuml;lc&uuml;
 */
@SuppressWarnings("rawtypes")
public class Category {

    private static final String CATEGORY_FQCN = Category.class.getName();

    private String name;

    protected org.zollty.log.Logger slf4jLogger;
//    private org.slf4j.spi.LocationAwareLogger locationAwareLogger;

//    private static Marker FATAL_MARKER = MarkerFactory.getMarker("FATAL");

    Category(String name) {
        this.name = name;
        slf4jLogger = org.zollty.log.LogFactory.getLogger(name);
//        if (slf4jLogger instanceof LocationAwareLogger) {
//            locationAwareLogger = (LocationAwareLogger) slf4jLogger;
//        }
    }

    public static Category getInstance(Class clazz) {
        return Log4jLoggerFactory.getLogger(clazz.getName());
    }

    public static Category getInstance(String name) {
        return Log4jLoggerFactory.getLogger(name);
    }

    public final Category getParent() {
        return null;
    }

    /**
     * Returns the obvious.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public Appender getAppender(String name) {
        return null;
    }

    public Enumeration getAllAppenders() {
        return NullEnumeration.getInstance();
    }

    /**
     * Return the level in effect for this category/logger.
     *
     * <p>
     * The result is computed by simulation.
     *
     * @return
     */
    public Level getEffectiveLevel() {
        if (slf4jLogger.isTraceEnabled()) {
            return Level.TRACE;
        }
        if (slf4jLogger.isDebugEnabled()) {
            return Level.DEBUG;
        }
        if (slf4jLogger.isInfoEnabled()) {
            return Level.INFO;
        }
        return Level.WARN;
    }

    /**
     * Returns the assigned {@link Level}, if any, for this Category. This
     * implementation always returns null.
     *
     * @return Level - the assigned Level, can be <code>null</code>.
     */
    final public Level getLevel() {
        return null;
    }

    /**
     * @deprecated Please use {@link #getLevel} instead.
     */
    final public Level getPriority() {
        return null;
    }

    /**
     * Delegates to {@link org.slf4j.Logger#isDebugEnabled} method in SLF4J
     */
    public boolean isDebugEnabled() {
        return slf4jLogger.isDebugEnabled();
    }

    /**
     * Delegates to {@link org.slf4j.Logger#isInfoEnabled} method in SLF4J
     */
    public boolean isInfoEnabled() {
        return slf4jLogger.isInfoEnabled();
    }

    /**
     * Delegates tob {@link org.slf4j.Logger#isWarnEnabled} method in SLF4J
     */
    public boolean isWarnEnabled() {
        return slf4jLogger.isInfoEnabled();
    }

    /**
     * Delegates to {@link org.slf4j.Logger#isErrorEnabled} method in SLF4J
     */
    public boolean isErrorEnabled() {
        return slf4jLogger.isInfoEnabled();
    }

    /**
     * Determines whether the priority passed as parameter is enabled in the
     * underlying SLF4J logger. Each log4j priority is mapped directly to its
     * SLF4J equivalent, except for FATAL which is mapped as ERROR.
     *
     * @param p
     *          the priority to check against
     * @return true if this logger is enabled for the given level, false
     *         otherwise.
     */
    public boolean isEnabledFor(Priority p) {
        switch (p.level) {
        case Level.TRACE_INT:
            return slf4jLogger.isTraceEnabled();
        case Level.DEBUG_INT:
            return slf4jLogger.isDebugEnabled();
        case Level.INFO_INT:
            return slf4jLogger.isInfoEnabled();
        case Level.WARN_INT:
            return slf4jLogger.isInfoEnabled();
        case Level.ERROR_INT:
            return slf4jLogger.isInfoEnabled();
        case Priority.FATAL_INT:
            return slf4jLogger.isInfoEnabled();
        }
        return false;
    }
    
    /**
     * Delegates to {@link org.slf4j.Logger#isTraceEnabled} 
     * method of SLF4J.
     */
    public boolean isTraceEnabled() {
        return slf4jLogger.isTraceEnabled();
    }

    /**
     * Delegates to {@link org.slf4j.Logger#trace(String)} method in SLF4J.
     */
    public void trace(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.TRACE, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#trace(String,Throwable)} 
     * method in SLF4J.
     */
    public void trace(Object message, Throwable t) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.TRACE, t, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#debug(String)} method of SLF4J.
     */
    public void debug(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.DEBUG, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#debug(String,Throwable)} method in
     * SLF4J.
     */
    public void debug(Object message, Throwable t) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.DEBUG, t, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#info(String)} method in SLF4J.
     */
    public void info(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.INFO, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#info(String,Throwable)} method in
     * SLF4J.
     */
    public void info(Object message, Throwable t) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.INFO, t, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#warn(String)} method in SLF4J.
     */
    public void warn(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.WARN, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#warn(String,Throwable)} method in
     * SLF4J.
     */
    public void warn(Object message, Throwable t) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.WARN, t, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#error(String)} method in SLF4J.
     */
    public void error(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.ERROR, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#error(String,Throwable)} method in
     * SLF4J.
     */
    public void error(Object message, Throwable t) {
        slf4jLogger.log( CATEGORY_FQCN, org.zollty.log.Level.ERROR, t, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#error(String)} method in SLF4J.
     */
    public void fatal(Object message) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.FATAL, null, message);
    }

    /**
     * Delegates to {@link org.slf4j.Logger#error(String,Throwable)} method in
     * SLF4J. In addition, the call is marked with a marker named "FATAL".
     */
    public void fatal(Object message, Throwable t) {
        slf4jLogger.log(CATEGORY_FQCN, org.zollty.log.Level.FATAL, t, message);
    }

    protected void forcedLog(String FQCN, Priority p, Object msg, Throwable t) {
        log(FQCN, p, msg, t);
    }

    // See also http://jira.qos.ch/browse/SLF4J-159
    public void log(String FQCN, Priority p, Object msg, Throwable t) {
        org.zollty.log.Level levelInt = priorityToLevelInt(p);
        slf4jLogger.log(FQCN, levelInt, t, msg);
    }

    public void log(Priority p, Object message, Throwable t) {
        org.zollty.log.Level levelInt = priorityToLevelInt(p);
        slf4jLogger.log(CATEGORY_FQCN, levelInt, t, message);
    }

    public void log(Priority p, Object message) {
        org.zollty.log.Level levelInt = priorityToLevelInt(p);
        slf4jLogger.log(CATEGORY_FQCN, levelInt, null, message);
    }

    private org.zollty.log.Level priorityToLevelInt(Priority p) {
        switch (p.level) {
        case Level.TRACE_INT:
        case Level.X_TRACE_INT:
            return org.zollty.log.Level.TRACE;
        case Priority.DEBUG_INT:
            return org.zollty.log.Level.DEBUG;
        case Priority.INFO_INT:
            return org.zollty.log.Level.INFO;
        case Priority.WARN_INT:
            return org.zollty.log.Level.WARN;
        case Priority.ERROR_INT:
            return org.zollty.log.Level.ERROR;
        case Priority.FATAL_INT:
            return org.zollty.log.Level.FATAL;
        default:
            throw new IllegalStateException("Unknown Priority " + p);
        }
    }

    protected final String convertToString(Object message) {
        if (message == null) {
            return (String) message;
        } else {
            return message.toString();
        }
    }

    public void setAdditivity(boolean additive) {
        // nothing to do
    }

    public void addAppender(Appender newAppender) {
        // nothing to do
    }

    public void setLevel(Level level) {
        // nothing to do
    }

    public boolean getAdditivity() {
        return false;
    }

    public void assertLog(boolean assertion, String msg) {
        if (!assertion)
            error(msg);
    }

}
