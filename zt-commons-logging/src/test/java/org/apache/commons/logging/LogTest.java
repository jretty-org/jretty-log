package org.apache.commons.logging;

public class LogTest {
    
    public static void main(String[] args) {
        testPrintAPI();
    }
    
    
    public static void testPrintAPI() {
        Log log = LogFactory.getLog(LogTest.class);
        Exception e = new Exception("just testing");

        log.trace(null);
        log.trace("trace message");

        log.debug(null);
        log.debug("debug message");

        log.info(null);
        log.info("info  message");

        log.warn(null);
        log.warn("warn message");

        log.error(null);
        log.error("error message");

        log.fatal(null);
        log.fatal("fatal message");

        log.trace(null, e);
        log.trace("trace message", e);

        log.debug(null, e);
        log.debug("debug message", e);

        log.info(null, e);
        log.info("info  message", e);

        log.warn(null, e);
        log.warn("warn message", e);

        log.error(null, e);
        log.error("error message", e);

        log.fatal(null, e);
        log.fatal("fatal message", e);
    }

}
