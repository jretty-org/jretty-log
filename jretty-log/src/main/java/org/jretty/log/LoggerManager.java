/* 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * [Jretty-Log && Mlf4j (Monitoring Logging Facade for Java)]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by ZollTy on 2014-6-04 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zollty
 * @since 2014-6-4
 */
public class LoggerManager {

    private static final Map<String, LoggerExeInfo> loggerExeMap = new HashMap<String, LoggerExeInfo>();

    public static Map<String, LoggerExeInfo> getLoggerExeMap() {
        return loggerExeMap;
    }

    public static void traceIncrement(String name) {
        loggerExeCount(name, 5);
    }

    public static void debugIncrement(String name) {
        loggerExeCount(name, 6);
    }

    public static void infoIncrement(String name) {
        loggerExeCount(name, 7);
    }

    public static void warnIncrement(String name) {
        loggerExeCount(name, 8);
    }

    public static void errorIncrement(String name) {
        loggerExeCount(name, 9);
    }

    private static void loggerExeCount(String name, int level) {
        LoggerExeInfo loge;
        synchronized (loggerExeMap) {
            loge = loggerExeMap.get(name);
            if (null == loge) {
                loge = new LoggerExeInfo(name);
                loggerExeMap.put(name, loge);
            }
        }
        switch (level) {
        case 5:
            loge.traceCount++;
            break;
        case 6:
            loge.debugCount++;
            break;
        case 7:
            loge.infoCount++;
            break;
        case 8:
            loge.warnCount++;
            break;
        case 9:
            loge.errorCount++;
            break;
        default:
            break;
        }
    }

    /**
     * 按日志打印的次数多少【从大到小】
     */
    public static class LoggerExeCountComparator implements Comparator<LoggerExeInfo>, Serializable {
        private static final long serialVersionUID = -8738413384601978780L;

        @Override
        public int compare(LoggerExeInfo log1, LoggerExeInfo log2) {
            long count1 = log1.getDebugCount() + log1.getErrorCount() + 
                    log1.getInfoCount() + log1.getTraceCount() + log1.getWarnCount();
            long count2 = log2.getDebugCount() + log2.getErrorCount() + 
                    log2.getInfoCount() + log2.getTraceCount() + log2.getWarnCount();
            if (count1 < count2) { // 降序
                return 1;
            }
            else {
                return -1;
            }
        }
    }

}
