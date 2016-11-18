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
 * Create by ZollTy on 2013-6-15 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

/**
 * @author zollty
 * @since 2013-6-15
 */
public interface BasicLog {

    int TRACE = 0;
    int DEBUG = 1;
    int INFO = 2;
    int WARN = 3;
    int ERROR = 4;
    int FATAL = 5;

    void trace(Object message);

    void trace(Throwable e);

    void trace(Throwable e, Object message);

    void trace(Object message, Object... msgParams);

    void trace(Throwable e, Object message, Object... msgParams);

    void debug(Object message);

    void debug(Throwable e);

    void debug(Throwable e, Object message);

    void debug(Object message, Object... msgParams);

    void debug(Throwable e, Object message, Object... msgParams);

    void info(Object message);

    void info(Throwable e);

    void info(Throwable e, Object message);

    void info(Object message, Object... msgParams);

    void info(Throwable e, Object message, Object... msgParams);

    void warn(Object message);

    void warn(Throwable e);

    void warn(Throwable e, Object message);

    void warn(Object message, Object... msgParams);

    void warn(Throwable e, Object message, Object... msgParams);

    void error(Object message);

    void error(Throwable e);

    void error(Throwable e, Object message);

    void error(Object message, Object... msgParams);

    void error(Throwable e, Object message, Object... msgParams);
    
    
    // ~ extends
    
    void trace(Object message, Object p0);
    
    void trace(Object message, Object p0, Object p1);

    void trace(Object message, Object p0, Object p1, Object p2);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7,
            Object p8);
    
    void trace(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7,
            Object p8, Object p9);
    
    void debug(Object message, Object p0);
    
    void debug(Object message, Object p0, Object p1);

    void debug(Object message, Object p0, Object p1, Object p2);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7);

    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7,
            Object p8);
    
    void debug(Object message, Object p0, Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7,
            Object p8, Object p9);

}
