package org.jretty.log;

import org.junit.Test;
import org.jretty.log.LogFactory.LogManager;

public class LF_refreshZolltyLogConfig {
    
    private static final Logger LOG = LogFactory.getLogger(); 

    @Test
    public void refreshZolltyLogConfigTest() {
        
        Logger logger = LOG;
        
        LogFactory.LogManager.refreshZolltyLogConfig(LogManager.DEFAULT_CONFIG_PATH);
        
        // $$ ConsoleLogger输出 $$
        logger.error("---------------------------");
        logger.error("---------------------------");
        
        // change to Log4jLogger
        LogFactory.LogManager.refreshZolltyLogConfig("zollty-log-spec.properties");
        
        // no need to reload, it will atuo reload.
        //logger = LogFactory.getLogger(); 
        
        // $$ Log4jLogger输出 $$
        logger.error("---------------------------");
        logger.error("---------------------------");
        
    }

}
