package test;

import org.junit.Test;
import org.zollty.log.LogFactory;
import org.zollty.log.Logger;

public class LogTest {
    
    private static final Logger LOG = LogFactory.getLogger(); // 简洁的定义方式
    
    @Test
    public void test1() {
        LOG.debug("===========debug Level=============");
        LOG.info( "===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");
        
        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());
        
        
        LogFactory.LogManager.refreshZolltyLogConfig("LOG4J", "ALL");
        LOG.debug("===========debug Level=============");
        LOG.info( "===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");
        
        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());
    }

}
