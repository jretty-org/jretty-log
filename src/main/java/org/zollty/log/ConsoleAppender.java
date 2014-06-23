/* @(#)ConsoleAppender.java 
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

import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zollty 
 * @since 2013-6-20
 */
public abstract class ConsoleAppender implements BasicLog, LoggerSupport {
	
    private static Level globalLevel = Level.DEBUG;
	private static final String MSG_SPLIT = " |- ";
	private static String classNameLayout;
	private static Map<String, Level> parentLoggers = new TreeMap<String, Level>(new Comparator<String>() {
        @Override
        public int compare(String obj1, String obj2) {
            return obj2.compareTo(obj1); // 降序排序
        }
    });
	
	
    protected void add(Object message, String className, Level level, Throwable throwable) {
		
		if( !level.isGreaterOrEqual(globalLevel) ) return;
		
		for(String key: parentLoggers.keySet()){
		    if(className.startsWith(key)){
		        if( !level.isGreaterOrEqual(parentLoggers.get(key)) ){
		            return;
		        }
		        break;
		    }
		}
		
		//if( isTraceEnabled() ) System.err.println("===============================================================================================");
		StringBuilder sb = new StringBuilder();
		sb.append(LogUtils.format(new Date()));
		sb.append(" [");
		sb.append(level);
		sb.append("] ");
		if("simple".equals(classNameLayout)){
		    sb.append(stripToSimpleClassName(className));
		}else{
		    sb.append(className);
		}
		//sb.append(" - ");
		if (message != null) {
			sb.append( MSG_SPLIT );
			sb.append( message.toString() );
		}
		if (throwable != null) {
			//sb.append(" [STACK] ").append( LogUtils.getStackTraceStr(throwable) );
			sb.append( MSG_SPLIT );
			sb.append( LogUtils.getStackTraceStr(throwable) );
		}
		System.out.println(sb.toString());
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
    
    public static Map<String, Level> getParentLoggers() {
        return parentLoggers;
    }

    public static void setParentLoggers(Map<String, Level> parentLoggers) {
        ConsoleAppender.parentLoggers = parentLoggers;
    }

    public static void setClassNameLayout(String classNameLayout) {
        ConsoleAppender.classNameLayout = classNameLayout;
    }
    
    // --------- helper methods for this class-------
    private static String stripToSimpleClassName(String className){
        int pos = className.lastIndexOf(".")+1;
        return className.substring(pos, className.length());
    }

}
