package com.rxjy.logUtil.dialect.commons;


import com.rxjy.logUtil.Log;
import com.rxjy.logUtil.LogFactory;

/**
 *  Apache Commons Logging
 * @author Looly
 *
 */
public class ApacheCommonsLogFactory extends LogFactory {

	public ApacheCommonsLogFactory() {
		super("Apache Common Logging");
		checkLogExist(org.apache.commons.logging.LogFactory.class);
	}

	public Log createLog(String name) {
		try {
			return new ApacheCommonsLog4JLog(name);
		}catch (Exception e){
			return new ApacheCommonsLog(name);
		}
	}

	public Log createLog(Class<?> clazz) {
		try{
			return new ApacheCommonsLog4JLog(clazz);
		}catch (Exception e){
			return new ApacheCommonsLog(clazz);
		}
	}
}
