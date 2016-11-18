package test;

import org.junit.Test;
import org.jretty.log.LogFactory;
import org.jretty.log.Logger;

public class LogTest {

    private static final Logger LOG = LogFactory.getLogger(); // 简洁的定义方式

    @Test
    public void test1() {
        LOG.trace("===========trace Level=============");
        LOG.debug("===========debug Level=============");
        LOG.info("===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");

        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());

        LogFactory.LogManager.refreshLogConfig("LOGBACK", "TRACE");
        Logger LOG = LogFactory.getLogger("MyLog");
        LOG.trace("===========trace Level=============");
        LOG.debug("===========debug Level=============");
        LOG.info("===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");

        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());
    }

}