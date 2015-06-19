/* 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * [Zollty-Log && Mlf4j (Monitoring Logging Facade for Java)]
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
package org.zollty.log;

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

}
