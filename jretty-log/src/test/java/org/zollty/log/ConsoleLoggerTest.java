package org.zollty.log;

import org.junit.Test;

public class ConsoleLoggerTest {

    @Test
    public void basicLogTest() {
        BasicLog log = new ConsoleLogger("org.zollty.log.test");

        log.debug("===========debug Level=============");
        log.info("===========info  Level=============");
        log.warn("============warn  Level============");
        log.error("===========error Level=============");
        log.error("hello {}, now time is {}. ", "guys", new java.util.Date());
    }

}
