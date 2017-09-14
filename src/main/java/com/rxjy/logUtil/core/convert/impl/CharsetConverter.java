package com.rxjy.logUtil.core.convert.impl;


import com.rxjy.logUtil.core.convert.AbstractConverter;
import com.rxjy.logUtil.core.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 编码对象转换器
 * @author Looly
 *
 */
public class CharsetConverter extends AbstractConverter<Charset> {

	@Override
	protected Charset convertInternal(Object value) {
		return CharsetUtil.charset(convertToStr(value));
	}

}
