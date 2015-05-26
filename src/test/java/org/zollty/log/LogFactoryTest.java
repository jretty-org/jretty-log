package org.zollty.log;

import org.zollty.log.LogFactory;
import org.zollty.log.Logger;

public class LogFactoryTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Logger log1 = LogFactory.getLogger(LogFactoryTest.class);
        log1.error("1.OK");
        
        Logger log2 = LogFactory.getLogger();
        log2.error("2.OK");
        
        
        LogFactory.refreshZolltyLogConfig("zollty-log.properties");
        System.out.println("3.OK");
        
    }

}
