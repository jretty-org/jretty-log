package org.zollty.log;

import org.junit.Test;
import org.zollty.log.LogFactory.LogManager;

public class LF_isTraceEnabled {
    
private static final Logger LOG = LogFactory.getLogger(); 
    
    @Test
    public void isTraceEnabledTest() {
        Logger logger = LOG;
        
        LogFactory.refreshZolltyLogConfig(LogManager.DEFAULT_CONFIG_PATH);
        
        // $$ 正常输出 $$
        if(LogFactory.isTraceEnabled()) {
            System.out.println("-----------isTraceEnabled-------------");
        }
        
        // change to Log4jLogger
        LogFactory.refreshZolltyLogConfig("zollty-log-spec.properties");
        
        // $$ 无输出 $$
        if(logger.isTraceEnabled()) {
            System.out.println("-----------isTraceEnabled-------------");
        }
        
    }

}
