package com.rxjy.logUtil.core.convert.impl;


import com.rxjy.logUtil.core.convert.AbstractConverter;

/**
 * 字符串转换器
 * @author Looly
 *
 */
public class StringConverter extends AbstractConverter<String> {

	@Override
	protected String convertInternal(Object value) {
		return convertToStr(value);
	}

}
