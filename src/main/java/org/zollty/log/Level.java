/* @(#)Level.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-23 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

/**
 * @author zollty 
 * @since 2013-6-23
 */
public class Level {

    transient int level;
    transient String levelStr;

    public static final Level ALL = new Level(Integer.MIN_VALUE, "ALL");
    public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF");
    public static final Level FATAL = new Level(Logger.FATAL, "FATAL");
    public static final Level ERROR = new Level(Logger.ERROR, "ERROR");
    public static final Level WARN = new Level(Logger.WARN, "WARN");
    public static final Level INFO = new Level(Logger.INFO, "INFO");
    public static final Level DEBUG = new Level(Logger.DEBUG, "DEBUG");
    public static final Level TRACE = new Level(Logger.TRACE, "TRACE");

    protected Level(int level, String levelStr) {
        this.level = level;
        this.levelStr = levelStr;
    }

    public boolean isGreaterOrEqual(Level r) {
        return (this.level >= r.level);
    }

    public static Level toLevel(String level) {
        return toLevel(level, DEBUG);
    }

    public static Level toLevel(int level) {
        return toLevel(level, DEBUG);
    }

    public static Level toLevel(int val, Level defaultLevel) {
        switch (val) {
        case Integer.MIN_VALUE:
            return ALL;
        case Logger.DEBUG:
            return DEBUG;
        case Logger.INFO:
            return INFO;
        case Logger.WARN:
            return WARN;
        case Logger.ERROR:
            return ERROR;
        case Logger.FATAL:
            return FATAL;
        case Logger.TRACE:
            return TRACE;
        case Integer.MAX_VALUE:
            return OFF;
        }
        return defaultLevel;
    }

    public static Level toLevel(String sArg, Level defaultLevel) {
        if (sArg == null) {
            return defaultLevel;
        }
        String s = sArg.toUpperCase();
        if (s.equals("ALL"))
            return ALL;
        if (s.equals("INFO"))
            return INFO;
        if (s.equals("WARN"))
            return WARN;
        if (s.equals("ERROR"))
            return ERROR;
        if (s.equals("DEBUG"))
            return DEBUG;
        if (s.equals("FATAL"))
            return FATAL;
        if (s.equals("TRACE"))
            return TRACE;
        if (s.equals("OFF"))
            return OFF;
        return defaultLevel;
    }

    @Override
    public final String toString() {
        return this.levelStr;
    }

    public final int toInt() {
        return this.level;
    }
}
