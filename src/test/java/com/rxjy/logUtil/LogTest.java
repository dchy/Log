package com.rxjy.logUtil;


import com.rxjy.logUtil.core.lang.Console;
import com.rxjy.logUtil.dialect.commons.ApacheCommonsLogFactory;
import com.rxjy.logUtil.dialect.console.ConsoleLogFactory;
import com.rxjy.logUtil.dialect.jdk.JdkLogFactory;
import com.rxjy.logUtil.dialect.log4j.Log4jLogFactory;
import com.rxjy.logUtil.dialect.tinylog.TinyLogFactory;
import com.rxjy.logUtil.level.Level;
import org.junit.Test;

/**
 * 日志门面单元测试
 * @author Looly
 *
 */
public class LogTest {
	@Test
	public void logTest(){
		Log log = LogFactory.get();
		// 自动选择日志实现
		log.debug("This is {} log", Level.DEBUG);
		log.info("This is {} log", Level.INFO);
		log.warn("This is {} log", Level.WARN);
		
//		Exception e = new Exception("test Exception");
//		log.error(e, "This is {} log", Level.ERROR);
	}
	
	@Test
	public void customLogTest(){
		// 自动选择日志实现
		Log log = LogFactory.get(LogTest.class);
		log.debug("This is {} log", "default");
		Console.log("----------------------------------------------------------------------");
		
		//自定义日志实现为Apache Commons Logging
		LogFactory.setCurrentLogFactory(new ApacheCommonsLogFactory());

		// 自动选择日志实现
		log.debug("This is {} log", "custom apache commons logging");
		Console.log("----------------------------------------------------------------------");
		
		//自定义日志实现为TinyLog
		LogFactory.setCurrentLogFactory(new TinyLogFactory());
		// 自动选择日志实现
		log.debug("This is {} log", "custom tinylog");
		Console.log("----------------------------------------------------------------------");
		
		//自定义日志实现为JDK Logging
		LogFactory.setCurrentLogFactory(new JdkLogFactory());
		// 自动选择日志实现
		log.info("This is {} log", "custom jdk logging");
		Console.log("----------------------------------------------------------------------");
		
		//自定义日志实现为Console Logging
		LogFactory.setCurrentLogFactory(new ConsoleLogFactory());
		// 自动选择日志实现
		log.info("This is {} log", "custom Console");
		Console.log("----------------------------------------------------------------------");
//
//		LogFactory.setCurrentLogFactory(new Log4jLogFactory());
//		log.info("This is {} log", "custom Log4j");

	}

	@Test
	public void statictest(){
		LogFactory.setCurrentLogFactory(new Log4jLogFactory());
		Log log = LogFactory.get();
		log.info("This is {} log", "custom Log4j");

	}


}
