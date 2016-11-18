package org.jretty.log;

public class VarargsPerformanceTest {
    public static Long count = 0L;
    
    public static void log(String msg, Object ...p){
        count++;
    }
    
    // 性能比 log 方法高3倍左右。
    public static void logVar(String msg, Object p0, Object p1, Object p2, Object p3){
        count++;
    }
    
    
    public static void main(String[] args) {
        int n = 100000000;
        String msg = "";
        Object p0 = new Object();
        Object p1 = new Object();
        Object p2 = new Object();
        Object p3 = new Object();
        for(int i=0; i<1000 ; i++){
            log(msg, p0, p1, p2, p3);
        }
        for(int i=0; i<1000 ; i++){
            logVar(msg, p0, p1, p2, p3);
        }
        long start = System.currentTimeMillis();
        for(int i=0; i<n ; i++){
            log(msg, p0, p1, p2, p3);
        }
        System.out.println("log cost: " + (System.currentTimeMillis() - start));
        
        start = System.currentTimeMillis();
        for(int i=0; i<n ; i++){
            logVar(msg, p0, p1, p2, p3);
        }
        System.out.println("logVar cost: " + (System.currentTimeMillis() - start));
    }
    

}
