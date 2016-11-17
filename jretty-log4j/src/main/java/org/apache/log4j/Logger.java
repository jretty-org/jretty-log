/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

/**
 * <p>
 * This class is a minimal implementation of the original
 * <code>org.apache.log4j.Logger</code> class (as found in log4j 1.2) 
 * delegating all calls to a {@link org.slf4j.Logger} instance.
 * </p>
 *
 * @author Ceki G&uuml;lc&uuml; 
 * */
@SuppressWarnings("rawtypes")
public class Logger extends Category {

    protected Logger(String name) {
        super(name);
    }

    public static Logger getLogger(String name) {
        return Log4jLoggerFactory.getLogger(name);
    }

    public static Logger getLogger(String name, LoggerFactory loggerFactory) {
        return Log4jLoggerFactory.getLogger(name, loggerFactory);
    }

    public static Logger getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * Does the obvious.
     * 
     * @return
     */
    public static Logger getRootLogger() {
        return Log4jLoggerFactory.getLogger("ROOT");
    }

}
