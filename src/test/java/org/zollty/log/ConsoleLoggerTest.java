package org.zollty.log;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.zollty.log.BasicLog;
import org.zollty.log.ConsoleAppender;
import org.zollty.log.ConsoleLogger;
import org.zollty.log.Level;

public class ConsoleLoggerTest {
    
    
    public static void main(String[] args) {
        
        basicLogTest();
        parrentTest();
        
    }
    
    public static void basicLogTest(){
        BasicLog log = new ConsoleLogger(ConsoleLoggerTest.class.getName());
        
        log.debug("===========debug Level=============");
        log.info( "===========info  Level=============");
        log.warn("============warn  Level============");
        log.error("===========error Level=============");
        log.error("hello {}, now time is {}. ", "guys", new java.util.Date());
    }
    
    public static void parrentTest() {
        Map<String, Level> parentLoggers = new TreeMap<String, Level>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1); // 降序排序
            }
        });
        parentLoggers.put("org.zollty.log.test", Level.toLevel("WARN"));
        
        ConsoleAppender.setParentLoggers(parentLoggers);
        
        System.out.println("==================parrentTest================");
        basicLogTest();
        System.out.println("==================parrentTest================");
    }


}
