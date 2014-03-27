/* @(#)LogUtils.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-20 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

/**
 * @author zollty 
 * @since 2013-6-20
 */
public class LogUtils {
	
	private static Calendar calendar = Calendar.getInstance();
	private static final char _SEP_CO = ':';
	
	public static String format(Date date) {
		StringBuilder sbuf = new StringBuilder();
		synchronized (calendar) {
			calendar.setTime(date);
            int hour = calendar.get(11);
            if (hour < 10) {
                sbuf.append('0');
            }
            sbuf.append(hour);
            sbuf.append(_SEP_CO);
            int mins = calendar.get(12);
            if (mins < 10) {
                sbuf.append('0');
            }
            sbuf.append(mins);
            sbuf.append(_SEP_CO);
            int secs = calendar.get(13);
            if (secs < 10) {
                sbuf.append('0');
            }
            sbuf.append(secs);
        }

        long now = date.getTime();
        int millis = (int) (now % 1000L);

        sbuf.append(',');

        if (millis < 100) {
            sbuf.append('0');
        }
        if (millis < 10) {
            sbuf.append('0');
        }
        sbuf.append(millis);

        return sbuf.toString();
    }
	
	/**
	 * 将StackTrace信息转换成字符串
	 */
	public static String getStackTraceStr(Throwable e) {
		StringWriter str = new StringWriter();
		PrintWriter out = new PrintWriter(str);
		try {
			e.printStackTrace(out);
		} finally {
			out.close();
		}
		return str.toString();
	}
	
    private static boolean android = false;
    static {
        try {
            Class.forName("android.Manifest");
            android = true;
        }
        catch (Throwable e) {
        }
    }
    /** 当前运行的 Java 虚拟机是否是在安卓环境 */
    public static boolean isAndroid() {
        return android;
    }
	
	
	private static final String REPLACE_LABEL = "{}";
	/**
	 * 用objs[]的值去替换字符串s中的{}符号
	 * @author zollty
	 */
    public static String replace(String s, Object... objs) {
        if (s == null) {
            return s;
        }
        if (objs == null || objs.length == 0) {
            return s;
        }
        if (s.indexOf(REPLACE_LABEL) == -1) {
            return s;
        }
        String[] stra = new String[objs.length];
        int len = s.length();
        for (int i = 0; i < objs.length; i++) {
            if (objs[i] != null)
                stra[i] = objs[i].toString();
            else {
                stra[i] = "null";
            }
            len += stra[i].length();
        }

        StringBuilder result = new StringBuilder(len);
        int cursor = 0;
        int index = 0;
        for (int start; (start = s.indexOf(REPLACE_LABEL, cursor)) != -1;) {
            result.append(s.substring(cursor, start));
            if (index < stra.length) {
                result.append(stra[index]);
            } else {
                result.append(REPLACE_LABEL);
            }
            cursor = start + 2;
            index++;
        }
        result.append(s.substring(cursor, s.length()));
        return result.toString();
    }

    public static LoggerSupport createLogCreator(String name) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        LoggerSupport creator = null;
        try {
            Class<?> clazz = loader.loadClass(name);
            Constructor<?> cons = clazz.getDeclaredConstructor();
            creator = (LoggerSupport) cons.newInstance();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return creator;
    }
	
    /**
     * 将Properties资源转换成Map类型
     */
    public static Map<String, String> covertProperties2Map(Properties props) {
        if (props == null) {
            throw new IllegalArgumentException("props==null");
        }
        Set<Entry<Object, Object>> set = props.entrySet();
        Map<String, String> mymap = new HashMap<String, String>();
        for (Entry<Object, Object> oo : set) {
            mymap.put(oo.getKey().toString(), oo.getValue().toString());
        }
        return mymap;
    }
    
}
