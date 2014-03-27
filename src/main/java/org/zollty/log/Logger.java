/* @(#)Logger.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-15 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

/**
 * 通用Logger接口，可接入Log4j、zollty-log等多种logger框架.<br> 
 * 
 * [请注意]：<br>
 * Logger的根本作用在于记录日志信息，里面有trace|debug|info|warn|error|fatal等级别，
 * 请不要滥用，例如，打印调试信息，一般不属于"记录日志"的范畴，建议用trace级别。
 * 
 * @author zollty
 * @since 2013-6-15
 */
public interface Logger extends BasicLog {

    boolean isTraceEnabled();

    boolean isDebugEnabled();

    boolean isInfoEnabled();

    boolean isEnabledFor(String moduleId);
}
