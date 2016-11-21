package org.jretty.log;

import org.junit.Assert;
import org.junit.Test;

public class LoggerWrapperTest {
    
    private static final Logger LOG = LogFactory.getLogger(); 
    
    @Test
    public void isTraceEnabledTest() {
        
        LoggerWrapperAPI loggerWrapper = (LoggerWrapperAPI) LOG;
        
        Assert.assertEquals("org.jretty.log.LoggerWrapperTest", loggerWrapper.getLogger().getName());
        
    }

}
