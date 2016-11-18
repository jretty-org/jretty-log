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
 * 通用Logger接口、门面（Facade），可接入Log4j、jul等多种logger框架.<br>
 * 
 * [请注意]：<br>
 * Logger的根本作用在于记录日志信息，里面有trace|debug|info|warn|error|fatal等级别， 请不要滥用，例如，打印调试信息，一般不属于"记录日志"的范畴，建议用trace级别。
 * 
 * @author zollty
 * @since 2013-6-15
 */
public interface Logger extends BasicLog {

    boolean isTraceEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    /**
     * 调用底层的通用log方法来输出日志
     * 
     * @param callerFQCN
     *            调用处的class名称
     * @param level
     *            Mlf4j的Level
     * @param t
     *            异常对象
     * @param msg
     *            输出信息
     * @param msgParams
     *            输出信息中占位符'{}'的值
     */
    void log(String callerFQCN, Level level, Throwable t, Object msg, Object... msgParams);

    /**
     * 获取logger的名称
     * 
     * @return logger name
     */
    String getName();
}
