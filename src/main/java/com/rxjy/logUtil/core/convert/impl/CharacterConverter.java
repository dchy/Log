package com.rxjy.logUtil.core.convert.impl;


import com.rxjy.logUtil.core.convert.AbstractConverter;
import com.rxjy.logUtil.core.util.StrUtil;

/**
 * 字符转换器
 * @author Looly
 *
 */
public class CharacterConverter extends AbstractConverter<Character> {

	@Override
	protected Character convertInternal(Object value) {
		if(char.class == value.getClass()){
			return Character.valueOf((Character)value);
		}else{
			final String valueStr = convertToStr(value);
			if (StrUtil.isNotBlank(valueStr)) {
				return Character.valueOf(valueStr.charAt(0));
			}
		}
		return null;
	}

}
