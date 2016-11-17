package test;

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
    
   private Logger log = Logger.getLogger(Log4jTest.class);
   
   @Test
   public void logtest() {
       
       log.trace("===========trace Level=============");
       log.debug("===========debug Level=============");
       log.info("===========info  Level=============");
       log.warn("============warn  Level============");
       log.error("===========error Level=============");
       log.error("hello {}, now time is {}. ");
       
       Logger log = Logger.getLogger("MyLog");
       log.trace("===========trace Level=============");
       log.debug("===========debug Level=============");
       log.info("===========info  Level=============");
       log.warn("============warn  Level============");
       log.error("===========error Level=============");
       log.error("hello {}, now time is {}. ");
   }

}
