/* @(#)LoggerWrapper.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-6-21 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.log;

/**
 * @author zollty 
 * @since 2013-6-21
 */
public class LoggerWrapper implements Logger {

	private transient BasicLog logger = null;
	
	private static Level threshold = Level.ALL;
	
	private Level localLevel; // 如果设置了locallevel，则以它为准，否则以全局threshold为准。
	
	private boolean isEffectiveLevel(Level level){
		if(localLevel==null)
			return level.isGreaterOrEqual(threshold);
		else
			return level.isGreaterOrEqual(localLevel);
	}
	
	public LoggerWrapper(BasicLog logger) {
		this.logger = logger;
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#doService(int, java.lang.Object[])
	 */
	@Override
	public Object[] doService(int serviceID, Object... args) {
		return this.logger.doService(serviceID, args);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#info(java.lang.Object)
	 */
	@Override
	public void info(Object message) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#info(java.lang.Throwable)
	 */
	@Override
	public void info(Throwable e) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#info(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void info(Throwable e, Object message) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e, message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#info(java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void info(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#info(java.lang.Throwable, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void info(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e, message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#warn(java.lang.Object)
	 */
	@Override
	public void warn(Object message) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#warn(java.lang.Throwable)
	 */
	@Override
	public void warn(Throwable e) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#warn(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void warn(Throwable e, Object message) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e, message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#warn(java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void warn(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#warn(java.lang.Throwable, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void warn(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e, message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#error(java.lang.Object)
	 */
	@Override
	public void error(Object message) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#error(java.lang.Throwable)
	 */
	@Override
	public void error(Throwable e) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#error(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void error(Throwable e, Object message) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e, message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#error(java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void error(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#error(java.lang.Throwable, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void error(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e, message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#debug(java.lang.Object)
	 */
	@Override
	public void debug(Object message) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#debug(java.lang.Throwable)
	 */
	@Override
	public void debug(Throwable e) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#debug(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void debug(Throwable e, Object message) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e, message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#debug(java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void debug(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#debug(java.lang.Throwable, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void debug(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e, message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#trace(java.lang.Object)
	 */
	@Override
	public void trace(Object message) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#trace(java.lang.Throwable)
	 */
	@Override
	public void trace(Throwable e) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#trace(java.lang.Throwable, java.lang.Object)
	 */
	@Override
	public void trace(Throwable e, Object message) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e, message);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#trace(java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void trace(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(message, msgParams);
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#trace(java.lang.Throwable, java.lang.Object, java.lang.Object[])
	 */
	@Override
	public void trace(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e, message, msgParams);
	}

	public static Level getThreshold() {
		return threshold;
	}

	public static void setThreshold(Level threshold) {
		LoggerWrapper.threshold = threshold;
	}

	public Level getLocalLevel() {
		return localLevel;
	}

	public void setLocalLevel(Level localLevel) {
		this.localLevel = localLevel;
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#isTraceEnabled()
	 */
	@Override
	public boolean isTraceEnabled() {
		return LogFactory.isTraceEnabled();
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return LogFactory.isDebugEnabled();
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#isInfoEnabled()
	 */
	@Override
	public boolean isInfoEnabled() {
		return LogFactory.isInfoEnabled();
	}

	/** (non-Javadoc)
	 * @see org.zollty.log.Logger#isEnabledFor(java.lang.String)
	 */
	@Override
	public boolean isEnabledFor(String moduleId) {
		return LogFactory.isEnabledFor(moduleId);
	}

}
