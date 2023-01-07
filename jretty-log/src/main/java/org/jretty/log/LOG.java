package org.jretty.log;

/**
 * 
 * @author zollty
 * @since 2022年6月9日
 */
public final class LOG {
    
    private LOG() {}

    private static Logger log = initLogger();
    
    private static Logger initLogger() {
        ConsoleLogger log = new ConsoleLogger();
        log.setShowMeta(false);
        return log;
    }
    
    public static void resetLogger(Logger log) {
        LOG.log = log;
    }
    
    public static void setGlobalLevel(final String level) {
        ConsoleAppender.globalLevel = Level.toLevel(level);
    }
    
    public static Logger getLogger() {
        return LOG.log;
    }
    
    public static void trace(Object message) {
        log.trace(message);
    }
    
    public static void trace(Throwable e) {
        log.trace(e);
    }

    
    public static void trace(Throwable e, Object message) {
        log.trace(e, message);
    }

    
    public static void trace(Object message, Object... msgParams) {
        log.trace(message, msgParams);
    }

    
    public static void trace(Throwable e, Object message, Object... msgParams) {
        log.trace(e, message, msgParams);
    }

    
    public static void debug(Object message) {
        log.debug(message);
    }

    
    public static void debug(Throwable e) {
        log.debug(e);
    }

    
    public static void debug(Throwable e, Object message) {
        log.debug(e, message);
    }

    
    public static void debug(Object message, Object... msgParams) {
        log.debug(message, msgParams);
    }

    
    public static void debug(Throwable e, Object message, Object... msgParams) {
        log.debug(e, message, msgParams);
    }

    
    public static void info(Object message) {
        log.info(message);
    }

    
    public static void info(Throwable e) {
        log.info(e);
    }

    
    public static void info(Throwable e, Object message) {
        log.info(e, message);
    }

    
    public static void info(Object message, Object... msgParams) {
        log.info(message, msgParams);
    }

    
    public static void info(Throwable e, Object message, Object... msgParams) {
        log.info(e, message, msgParams);
    }

    
    public static void warn(Object message) {
        log.warn(message);
    }

    
    public static void warn(Throwable e) {
        log.warn(e);
    }

    
    public static void warn(Throwable e, Object message) {
        log.warn(e, message);
    }

    
    public static void warn(Object message, Object... msgParams) {
        log.warn(message, msgParams);
    }

    
    public static void warn(Throwable e, Object message, Object... msgParams) {
        log.warn(e, message, msgParams);
    }

    
    public static void error(Object message) {
        log.error(message);
    }

    
    public static void error(Throwable e) {
        log.error(e);
    }

    
    public static void error(Throwable e, Object message) {
        log.error(e, message);
    }

    
    public static void error(Object message, Object... msgParams) {
        log.error(message, msgParams);
    }

    
    public static void error(Throwable e, Object message, Object... msgParams) {
        log.error(e, message, msgParams);
    }

    
    public static void trace(Object message, Object p0) {
        log.trace(message, p0);
    }

    
    public static void trace(Object message, Object p0, Object p1) {
        log.trace(message, p0, p1);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2) {
        log.trace(message, p0, p1, p2);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3) {
        log.trace(message, p0, p1, p2, p3);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log.trace(message, p0, p1, p2, p3, p4);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log.trace(message, p0, p1, p2, p3, p4, p5);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log.trace(message, p0, p1, p2, p3, p4, p5, p6);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        log.trace(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        log.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    
    public static void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        log.trace(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

    
    public static void debug(Object message, Object p0) {
        log.debug(message, p0);
    }

    
    public static void debug(Object message, Object p0, Object p1) {
        log.debug(message, p0, p1);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2) {
        log.debug(message, p0, p1, p2);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3) {
        log.debug(message, p0, p1, p2, p3);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4) {
        log.debug(message, p0, p1, p2, p3, p4);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5) {
        log.debug(message, p0, p1, p2, p3, p4, p5);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
        log.debug(message, p0, p1, p2, p3, p4, p5, p6);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7) {
        log.debug(message, p0, p1, p2, p3, p4, p5, p6, p7);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8) {
        log.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8);
    }

    
    public static void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6,
            Object p7, Object p8, Object p9) {
        log.debug(message, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    }

}
