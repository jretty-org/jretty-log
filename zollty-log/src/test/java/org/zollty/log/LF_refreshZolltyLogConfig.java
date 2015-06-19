package org.zollty.log;

import org.junit.Test;
import org.zollty.log.LogFactory.LogManager;

public class LF_refreshZolltyLogConfig {
    
    private static final Logger LOG = LogFactory.getLogger(); 

    @Test
    public void refreshZolltyLogConfigTest() {
        
        Logger logger = LOG;
        
        LogFactory.refreshZolltyLogConfig(LogManager.DEFAULT_CONFIG_PATH);
        
        // $$ ConsoleLogger输出 $$
        logger.error("---------------------------");
        logger.error("---------------------------");
        
        // change to Log4jLogger
        LogFactory.refreshZolltyLogConfig("zollty-log-spec.properties");
        
        // no need to reload, it will atuo reload.
        //logger = LogFactory.getLogger(); 
        
        // $$ Log4jLogger输出 $$
        logger.error("---------------------------");
        logger.error("---------------------------");
        
    }

}
