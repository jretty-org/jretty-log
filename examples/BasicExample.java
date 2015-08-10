package org.zollty.log.example;

import org.zollty.log.LogFactory;
import org.zollty.log.Logger;

/**
 * Basic Logger using example
 * @author zollty
 */
public class BasicExample {

    private static final Logger LOG = LogFactory.getLogger(); // 简洁的定义方式

    public static void main(String[] args) {
        LOG.debug("===========debug Level=============");
        LOG.info( "===========info  Level=============");
        LOG.warn("============warn中文测试============");
        LOG.error("===========error Level=============");

        // 支持参数的占位符
        LOG.error("hello {}, now time is {}. ", "guys", new java.util.Date());

        if( LOG.isDebugEnabled() ){
            try{
                writeOrderInfoToFile("OrderInfo-Mock-Data");
            } catch (RuntimeException e) {
                LOG.error("[{}] [orderNo={}] {}", "0x0001", "HQ3SD6", e.getMessage());
            }
        }

        try{
            doService("OrderInfo-Mock-Data");
        } catch (RuntimeException e) {
            if( LOG.isDebugEnabled() ) {
                LOG.error(e, "Order reservation errors, orderNo is {}, detail exception info: ", "HQ3SD6");
            }
        }

    }

    private static void writeOrderInfoToFile(String args) {
        // TODO
    }

    private static void doService(String args) {
        // TODO
    }
}
