package org.zollty.log;

import org.junit.Test;

/**
 * Logger Test
 * @author zollty
 */
public class LogMainTest {

	private static final Logger LOG = LogFactory.getLogger(); // 简洁的定义方式
	
	@Test
	public void test1() {
	    LOG.debug("===========debug Level=============");
        LOG.info( "===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");
        
        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());
	}

}