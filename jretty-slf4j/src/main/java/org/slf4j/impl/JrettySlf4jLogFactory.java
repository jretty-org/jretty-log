/* 
 * Copyright (C) 2013-2015 the original author or authors.
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
 * Create by ZollTy on 2015-6-10 (http://blog.zollty.com/, zollty@163.com)
 */
package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/**
 * 
 * @author zollty
 * @since 2015-6-10
 */
public class JrettySlf4jLogFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(String name) {
        
        return new JrettySlf4jLog( org.zollty.log.LogFactory.getLogger(name) );
    }

}
