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

/**
 * 保存logger调用执行信息
 * @author zollty
 * @since 2014-6-04
 */
public class LoggerExeInfo {

    private String name;
    long traceCount;
    long debugCount;
    long infoCount;
    long warnCount;
    long errorCount;

    public LoggerExeInfo() {
    }

    public LoggerExeInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTraceCount() {
        return traceCount;
    }

    public void setTraceCount(long traceCount) {
        this.traceCount = traceCount;
    }

    public long getDebugCount() {
        return debugCount;
    }

    public void setDebugCount(long debugCount) {
        this.debugCount = debugCount;
    }

    public long getInfoCount() {
        return infoCount;
    }

    public void setInfoCount(long infoCount) {
        this.infoCount = infoCount;
    }

    public long getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(long warnCount) {
        this.warnCount = warnCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

}
