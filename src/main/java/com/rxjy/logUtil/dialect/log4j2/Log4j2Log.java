package com.rxjy.logUtil.dialect.log4j2;

import com.rxjy.logUtil.AbstractLocationAwareLog;
import com.rxjy.logUtil.core.util.StrUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.AbstractLogger;

/**
 * <a href="http://logging.apache.org/log4j/2.x/index.html">Apache Log4J 2</a> log.<br>
 * 
 * @author Looly
 *
 */
public class Log4j2Log extends AbstractLocationAwareLog {
	private static final long serialVersionUID = -6843151523380063975L;
	
	private static final String FQCN = Log4j2Log.class.getName();

	private final transient Logger logger;

	// ------------------------------------------------------------------------- Constructor
	public Log4j2Log(Logger logger) {
		this.logger = logger;
	}

	public Log4j2Log(Class<?> clazz) {
		this(LogManager.getLogger(clazz));
	}

	public Log4j2Log(String name) {
		this(LogManager.getLogger(name));
	}

	public String getName() {
		return logger.getName();
	}

	// ------------------------------------------------------------------------- Trace
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}
	public void trace(String format, Object... arguments) {
		trace(null, format, arguments);
	}

	public void trace(Throwable t, String format, Object... arguments) {
		if(false == logIfEnabled(Level.TRACE, t, format, arguments)){
			logger.trace(StrUtil.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Debug
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public void debug(String format, Object... arguments) {
		debug(null, format, arguments);
	}

	public void debug(Throwable t, String format, Object... arguments) {
		if(false == logIfEnabled(Level.DEBUG, t, format, arguments)){
			logger.debug(StrUtil.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Info
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void info(String format, Object... arguments) {
		info(null, format, arguments);
	}

	public void info(Throwable t, String format, Object... arguments) {
		if(false == logIfEnabled(Level.INFO, t, format, arguments)){
			logger.info(StrUtil.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Warn
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public void warn(String format, Object... arguments) {
		warn(null, format, arguments);
	}

	public void warn(Throwable t, String format, Object... arguments) {
		if(false == logIfEnabled(Level.WARN, t, format, arguments)){
			logger.warn(StrUtil.format(format, arguments), t);
		}
	}

	// ------------------------------------------------------------------------- Error
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	public void error(String format, Object... arguments) {
		error(null, format, arguments);
	}

	public void error(Throwable t, String format, Object... arguments) {
		if(false == logIfEnabled(Level.ERROR, t, format, arguments)){
			logger.warn(StrUtil.format(format, arguments), t);
		}
	}
	
	// ------------------------------------------------------------------------- Log
	public void log(com.rxjy.logUtil.level.Level level, String format, Object... arguments) {
		this.log(level, null, format, arguments);
	}
	
	public void log(com.rxjy.logUtil.level.Level level, Throwable t, String format, Object... arguments) {
		this.log(FQCN, level, t, format, arguments);
	}
	
	public void log(String fqcn, com.rxjy.logUtil.level.Level level, Throwable t, String format, Object... arguments) {
		Level log4j2Level;
		switch (level) {
			case TRACE:
				log4j2Level = Level.TRACE;
				break;
			case DEBUG:
				log4j2Level = Level.DEBUG;
				break;
			case INFO:
				log4j2Level = Level.INFO;
				break;
			case WARN:
				log4j2Level = Level.WARN;
				break;
			case ERROR:
				log4j2Level = Level.ERROR;
				break;
			default:
				throw new Error(StrUtil.format("Can not identify level: {}", level));
		}
		logIfEnabled(fqcn, log4j2Level, t, format, arguments);
	}

	// ------------------------------------------------------------------------- Private method
	/**
	 * 打印日志<br>
	 * 此方法用于兼容底层日志实现，通过传入当前包装类名，以解决打印日志中行号错误问题
	 * @param level 日志级别，使用org.apache.logging.log4j.Level中的常量
	 * @param t 异常
	 * @param msgTemplate 消息模板
	 * @param arguments 参数
	 * @return 是否支持 LocationAwareLogger对象，如果不支持需要日志方法调用被包装类的相应方法
	 */
	private boolean logIfEnabled(Level level, Throwable t, String msgTemplate, Object... arguments) {
		return logIfEnabled(FQCN, level, t, msgTemplate, arguments);
	}
	
	/**
	 * 打印日志<br>
	 * 此方法用于兼容底层日志实现，通过传入当前包装类名，以解决打印日志中行号错误问题
	 * @param fqcn 完全限定类名(Fully Qualified Class Name)，用于纠正定位错误行号
	 * @param level 日志级别，使用org.apache.logging.log4j.Level中的常量
	 * @param t 异常
	 * @param msgTemplate 消息模板
	 * @param arguments 参数
	 * @return 是否支持 LocationAwareLogger对象，如果不支持需要日志方法调用被包装类的相应方法
	 */
	private boolean logIfEnabled(String fqcn, Level level, Throwable t, String msgTemplate, Object... arguments) {
		if(this.logger instanceof AbstractLogger){
			((AbstractLogger)this.logger).logIfEnabled(fqcn, level, null, StrUtil.format(msgTemplate, arguments), t);
			return true;
		}else{
			return false;
		}
	}
}
