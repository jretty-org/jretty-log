package org.zollty.log;

import org.junit.Test;

/**
 * Logger Test
 * @author zollty
 */
public class LogMainTest {

	private static final Logger LOG = LogFactory.getLogger(); // 简洁的定义方式
	
	public static final String ORDER_QUERY="ORDER_QUERY"; //这个为key，其value可以在数据库中配置（false或者true）
	
	@Test
	public void test1() {
	    LOG.debug("===========debug Level=============");
        LOG.info( "===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");
        
        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());
	}
	
	@Test
	public void test2() {
		
		if( LogFactory.isEnabledFor(ORDER_QUERY) ){
			try{
				writeOrderInfoToFile("OrderInfo-Mock-Data");
			} catch (RuntimeException e) {
				LOG.error("[{}] [orderNo={}] {}", "Code", "orderNo", e.getMessage());
			}
		}
		
		try{
			doService("OrderInfo-Mock-Data");
		} catch (RuntimeException e) {
			if( LogFactory.isDebugEnabled() ) {
				LOG.error(e, "Order reservation errors, orderNo is {}, detail exception info: ", "orderNo");
			}
		}
		
	}
	
	private static void writeOrderInfoToFile(String args) {
	    // do something
	}
	
	private static void doService(String args) {
	    // do something
	}
	

}