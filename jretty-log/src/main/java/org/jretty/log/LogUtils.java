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
 * Create by ZollTy on 2013-6-20 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

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
//			sbuf.append( calendar.get(1) );
//			//month
//			String month = getMonth( calendar.get(2) );
//			sbuf.append(_SEP_SL).append(month).append(_SEP_SL);
//			//date
//			int day = calendar.get(5);
//			if (day < 10)
//				sbuf.append('0');
//			sbuf.append(day);
//			sbuf.append(' ');
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
            return creator;
        } catch (Throwable e) {
            throw new IllegalArgumentException("can not instance [" + name + "], make sure it a validate class name.");
        }
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
    
    
    /**
     * log4j中取resource的工具类
     * @param resource
     */
    public static URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;
        url = Thread.currentThread().getContextClassLoader().getResource(resource);
        if (url != null) {
            return url;
        }
        // We could not find resource. Ler us now try with the
        // classloader that loaded this class.
        classLoader = LogUtils.class.getClassLoader();
        if (classLoader != null) {
            url = classLoader.getResource(resource);
            if (url != null) {
                return url;
            }
        }
        // Last ditch attempt: get the resource from the class path. It
        // may be the case that clazz was loaded by the Extentsion class
        // loader which the parent of the system class loader. Hence the
        // code below.
        return ClassLoader.getSystemResource(resource);
    }
    
    
    // StringUtils
    
    private static final char EXTENSION_SEPARATOR = '.';
    
    /** 文件分隔符'/'，用于Unix等系统 */
    public static final String FOLDER_SEPARATOR = "/";
    /** WIN系统文件分隔符'\\'用于MS Windows系统 */
    public static final String WINDOWS_FOLDER_SEPARATOR = "\\";
    
    
    /**
     * 从路径（url或者目录都可以）中获取文件名称（不带后缀，形如 abc） Extract the filename without it's extension from the given path, e.g. "mypath/myfile.txt" -> "myfile".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename, or <code>null</code> if none
     */
    public static String getFilenameWithoutExtension(String path) {
        if (null == path)
            return null;
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return path.substring(path.lastIndexOf(FOLDER_SEPARATOR) + 1, path.length());
            // return null;
        }
        String path2 = path.replace(WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            // throw new RuntimeException("the path form is not correct!--"+path);
            return null;
        }
        return path2.substring(folderIndex + 1, extIndex);
    }

    
    /**
     * 从路径（url或者目录都可以）中获取文件后缀（比如 txt）<br>
     * Extract the filename extension from the given path, e.g. "mypath/myfile.txt" -> "txt".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the extracted filename extension, or <code>null</code> if none
     */
    public static String getFilenameExtension(String path) {
        if (null == path)
            return null;
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        String path2 = path.replace(WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return path2.substring(extIndex + 1);
    }
    
    
    /**
     * 从路径（url或者目录都可以）中剥去文件名，获得文件所在的目录。 <br>
     * Strip the filename from the given path, e.g. "mypath/myfile.txt" -> "mypath/".
     * 
     * @param path
     *            the file path (may be <code>null</code>)
     * @return the path with stripped filename, or <code>null</code> if none
     */
    public static String stripFilenameFromPath(String path) {
        if (null == path)
            return null;
        String path2 = path.replace(WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);
        int folderIndex = path2.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex == -1) {
            return path2;
        }
        return path2.substring(0, folderIndex + 1);
    }
    
    // IOUtils -----
    /**
     * 静默关闭输出流
     */
    public final static void closeIO(OutputStream out) {
        if (null != out) {
            try {
                out.flush();
            }
            catch (IOException e) {
                // 
            }
            try {
                out.close();
            }
            catch (Exception e) {
                // 
            }
        }
    }
    
    /**
     * 静默关闭输入流
     */
    public final static void closeIO(InputStream in) {
        if (null != in) {
            try {
                in.close();
            }
            catch (Exception e) {
                //
            }
        }
    }
    
    /**
     * max read file buffer size. default 500k
     */
    private static final int MAX_BUFFER_SIZE = 512000;

    /**
     * min read file buffer size. default 1k
     */
    private static final int MIN_BUFFER_SIZE = 1024;

    /**
     * default read file buffer size. default 4k
     */
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    
    /**
     * @param len
     *            in-source-length e.g. long len = fileIn.length()
     */
    public final static int clone(final InputStream in, long len, final OutputStream out) throws IOException {
        try {
            byte[] buf;
            // 动态缓存大小
            // case1 LEN>200000kb(195M) -- BUF=500kb e.g. 200M--500k
            // case2 400kb< LEN <200000kb -- BUF=LEN/400 e.g. 100M--250k, 10M--25k, 400kb--1kb
            // case3 LEN<400kb -- BUF=1kb e.g. 300kb--1kb, 0kb-1kb
            if (len > MAX_BUFFER_SIZE * 400) {
                buf = new byte[MAX_BUFFER_SIZE];
            }
            else if (len > MIN_BUFFER_SIZE * 400) {
                buf = new byte[(int) len / 400];
            }
            else {
                buf = new byte[DEFAULT_BUFFER_SIZE];
            }

            int byteCount = 0;
            int bytesRead = 0;
            while (-1 != (bytesRead = in.read(buf))) {
                out.write(buf, 0, bytesRead);
                byteCount += bytesRead;
            }
            return byteCount;
        } finally {
            try {
                in.close();
            }
            catch (IOException ex) {
            }
            try {
                out.flush();
            }
            catch (IOException e) {
            }
            try {
                out.close();
            }
            catch (IOException ex) {
            }
        }
    }
    
    
    public static void cloneFile(final File fileIn, final File fileOut) throws IOException {
        long len = fileIn.length();
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(fileIn);
            out = new FileOutputStream(fileOut);
            clone(in, len, out);
        } finally {
            closeIO(in);
            closeIO(out);
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String toString(Map map) {
        if ( map == null || map.isEmpty() ) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        String delim = "\r\n";
        Iterator<Entry> it = map.entrySet().iterator();
        Entry entry;
        while (it.hasNext()) {
            entry = it.next();
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            if (it.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }
    
    
    private static StringBuilder innerLogCache;

    public static void report(String info, Logger log) {
        if (innerLogCache == null) {
            innerLogCache = new StringBuilder();
        }
        innerLogCache.append(info).append("\n");
        if (log != null) {
            log.info(innerLogCache.toString());
            innerLogCache = null;
        }
    }
}
