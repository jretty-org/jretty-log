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
 * Create by ZollTy on 2013-6-04 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;

/**
 * @author zollty
 * @since 2014-6-4
 */
public class LoggerInfo implements Serializable {

    private static final long serialVersionUID = 14463265368828542L;

    private Logger logger;

    private String level;
    
    public LoggerInfo(Logger logger) {
        super();
        this.logger = logger;
    }

    public LoggerInfo(Logger logger, String level) {
        super();
        this.logger = logger;
        this.level = level;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
}
