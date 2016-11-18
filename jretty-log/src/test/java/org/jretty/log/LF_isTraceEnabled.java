package org.jretty.log;

import org.junit.Test;
import org.jretty.log.LogFactory.LogManager;

public class LF_isTraceEnabled {
    
private static final Logger LOG = LogFactory.getLogger(); 
    
    @Test
    public void isTraceEnabledTest() {
        Logger logger = LOG;
        
        LogFactory.LogManager.refreshLogConfig(LogManager.DEFAULT_CONFIG_PATH);
        
        // $$ 正常输出 $$
        if(logger.isTraceEnabled()) {
            System.out.println("-----------isTraceEnabled-------------");
        }
        
        // change to Log4jLogger
        LogFactory.LogManager.refreshLogConfig("zollty-log-spec.properties");
        
        // $$ LOG4J无输出 $$
        if(logger.isTraceEnabled()) {
            System.out.println("-----------isTraceEnabled-------------");
        }
        
    }

}
