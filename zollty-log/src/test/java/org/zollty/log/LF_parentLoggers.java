package org.zollty.log;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.zollty.log.LogFactory.LogManager;

public class LF_parentLoggers {
    
    private static final Logger LOG = LogFactory.getLogger("org.zollty.dbk.support.Test"); 

    @Test
    public void parrentLoggerConfigTest() {
        
        Logger logger = LOG;
        
        LogFactory.LogManager.refreshZolltyLogConfig(LogManager.DEFAULT_CONFIG_PATH);
        
        // $$ 正常输出 $$
        logger.info("---------------------------");
        logger.info("---------------------------");
        
        // change to Log4jLogger
        LogFactory.LogManager.refreshZolltyLogConfig("zollty-log-spec.properties");
        
        // no need to reload, it will atuo reload.
        //logger = LogFactory.getLogger(); 
        
        // $$ 无输出 $$
        logger.info("---------------------------");
        logger.info("---------------------------");
        
    }
    
    @Test
    public void basicLogTest() {
        BasicLog log = new LoggerWrapper(new ConsoleLogger("org.zollty.util.config.xml.Test"));
        
        // $$ 5个都输出 $$
        log.debug("===========debug Level=============");
        log.info("===========info  Level=============");
        log.warn("============warn  Level============");
        log.error("===========error Level=============");
        log.error("hello {}, now time is {}. ", "guys", new java.util.Date());
    }

    @Test
    public void parrentTest() {
//        TreeMap<String, Level> parentLoggers = new TreeMap<String, Level>(new Comparator<String>() {
//            @Override
//            public int compare(String obj1, String obj2) {
//                return obj2.compareTo(obj1); // 降序排序
//            }
//        });
        Map<String, Level> parentLoggers = new HashMap<String, Level>();
        parentLoggers.put("org.zollty.util.config", Level.toLevel("ERROR"));
        parentLoggers.put("org.zollty.util.config.xml", Level.toLevel("WARN"));
        
        // 为用TreeMap排序前， "org.zollty.util.config" 排在前面，应该匹配ERROR级别，但是排序后
        // "org.zollty.util.config.xml" 排在前面，所以匹配 WARN级别
        System.out.println(LogUtils.toString(parentLoggers));

        LoggerWrapper.setParentLoggers(parentLoggers);

        System.out.println("==================parrentTest================");
        // $$ 只输出 warn error $$
        basicLogTest();
        System.out.println("==================parrentTest================");
    }

}
