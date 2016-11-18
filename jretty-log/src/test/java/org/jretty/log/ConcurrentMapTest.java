package org.jretty.log;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jretty.log.LogFactory.LogManager;

public class ConcurrentMapTest {

    
    public static void main(String[] args) {
        
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                for(int i=0; i<500; i++){
                    new LoggerWrapper(new ConsoleLogger("aaaa" + i));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }).start();
        
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                for(int i=0; i<400; i++){
                    new LoggerWrapper(new ConsoleLogger("bbbb" + i));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }).start();
        
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                for(int i=0; i<300; i++){
                    new LoggerWrapper(new ConsoleLogger("cccc" + i));
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
            }
        }).start();
        
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                for(int i=0; i<300; i++){
                    //System.out.println("---------------------------------------------");
                    LoggerSupport logCreator = new ConsoleLogger();
                    Set<Entry<String, LoggerWrapper>> entrySet = LogManager.cacheLoggerMap.entrySet();
                    Map<String, Logger> newMap = new HashMap<String, Logger>();
                    for (Entry<String, LoggerWrapper> entry : entrySet) {
                        newMap.put(entry.getKey(), logCreator.newInstance(entry.getKey()));
                    }
                    Set<Entry<String, LoggerWrapper>> entrySet2 = LogManager.cacheLoggerMap.entrySet();
                    for (Entry<String, LoggerWrapper> entry : entrySet2) {
                        // 用新logCreator生成的实例，替换原来的Logger
                        if (newMap.containsKey(entry.getKey())) {
                            entry.getValue().setLogger(newMap.get(entry.getKey()));
                        } else {
                            entry.getValue().setLogger(logCreator.newInstance(entry.getKey()));
                            System.out.println("new -------newInstance===");
                        }
                    }
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                
                System.out.println("end...");
                
            }
        }).start();
        
        
        
    }
}
