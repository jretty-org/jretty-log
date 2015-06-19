package org.zollty.log;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class TreeMapTest {
    
    @Test
    public void test() {
        
        Map<String, Level> map = new HashMap<String, Level>();
        
        map.put("org.zollty.util", Level.INFO);
        map.put("org.zollty.util.support", Level.TRACE);
        map.put("org.zollty.util.config.xml", Level.WARN);
        map.put("org.zollty.util.config", Level.ERROR);
        map.put("org.zollty", Level.WARN);
        
        System.out.println(LogUtils.toString(map));
        
        /** 根据key进行降序排序，例如
         * <pre>
         *  org.zollty.util=INFO
            org.zollty=WARN
            org.zollty.util.config.xml=ERROR
            org.zollty.util.support=TRACE
            org.zollty.util.config=INFO
         排序后变成：
            org.zollty.util.support=TRACE
            org.zollty.util.config.xml=ERROR
            org.zollty.util.config=INFO
            org.zollty.util=INFO
            org.zollty=WARN
         * <pre>
         */
        Map<String, Level> tree = new TreeMap<String, Level>(new Comparator<String>() {
            @Override
            public int compare(String obj1, String obj2) {
                return obj2.compareTo(obj1); // 降序排序
            }
        });

        tree.putAll(map);
        System.out.println("---------------------");
        System.out.println(LogUtils.toString(tree));
        
    }

}
