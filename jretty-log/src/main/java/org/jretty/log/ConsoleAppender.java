/*
 * Copyright (C) 2013-2025 the original author or authors.
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
 * Create by ZollTy on 2013-6-20 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zollty
 * @since 2013-6-20
 */
public abstract class ConsoleAppender implements Logger, LoggerSupport, Serializable {

    private static final long serialVersionUID = -4590068452485045670L;

    protected static Level globalLevel = Level.DEBUG;
    private static final String MSG_SPLIT = " --> ";
    public static final String CAUSED_BY = " --> Caused by: \n|- ";
    private static String classNameLayout;
    private static boolean showThread;
    private static int lineLengthLimit = 32000;
    private boolean showMeta = true;

    protected boolean isEffectiveLevel(Level level) {
        return level.isGreaterOrEqual(globalLevel);
    }

    protected void add(Object message, String className, Level level, Throwable throwable) {
        if (!isEffectiveLevel(level)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (showMeta) {

            sb.append(LogUtils.format(new Date()));
            if (showThread) {
                sb.append(" [");
                sb.append(Thread.currentThread().getName());
                sb.append("]");
            }
            sb.append(" [");
            sb.append(level);
            sb.append("] ");
            if ("simple".equals(classNameLayout)) {
                sb.append(stripToSimpleClassName(className));
            } else {
                sb.append(className);
            }
            // sb.append(" - ");
            if (message != null) {
                sb.append(MSG_SPLIT);
                sb.append(message);
            }
            if (throwable != null) {
                String stack = LogUtils.getStackTraceStr(throwable);
                if (stack != null && !stack.isEmpty()) {
                    sb.append(CAUSED_BY).append(stack);
                }
            }

        } else {
            if (message != null) {
                if (throwable != null) {
                    sb.append(message).append(CAUSED_BY).append(LogUtils.getStackTraceStr(throwable));
                } else {
                    sb.append(message);
                }
            } else if (throwable != null) {
                sb.append(LogUtils.getStackTraceStr(throwable));
            }
        }

        if (level.isGreaterOrEqual(Level.ERROR)) {
            if (sb.length() <= lineLengthLimit) {
                System.err.println(sb.toString());
            } else {
                System.err.println(sb.replace(lineLengthLimit - 32, sb.length(), "....(len=" + sb.length() + ")"));
            }
        } else {
            if (sb.length() <= lineLengthLimit) {
                System.out.println(sb);
            } else {
                System.out.println(sb.replace(lineLengthLimit - 32, sb.length(), "....(len=" + sb.length() + ")"));
            }
        }
    }

    public static void setGlobalLevel(final String level) {
        ConsoleAppender.globalLevel = Level.toLevel(level);
    }

    public static Level getGlobalLevel() {
        return globalLevel;
    }

    public static String getClassNameLayout() {
        return classNameLayout;
    }

    public static void setClassNameLayout(String classNameLayout) {
        ConsoleAppender.classNameLayout = classNameLayout;
    }

    public static void setShowThread(boolean showThread) {
        ConsoleAppender.showThread = showThread;
    }

    public static void setLineLengthLimit(int lineLengthLimit) {
        ConsoleAppender.lineLengthLimit = lineLengthLimit;
    }

    public void setShowMeta(boolean showMeta) {
        this.showMeta = showMeta;
    }

    // --------- helper methods for this class-------
    private static String stripToSimpleClassName(String className) {
        int pos = className.lastIndexOf(".") + 1;
        return className.substring(pos);
    }

}
