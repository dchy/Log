package com.rxjy.logUtil.core.convert.impl;

import com.rxjy.logUtil.core.convert.AbstractConverter;
import com.rxjy.logUtil.core.convert.ConverterRegistry;
import com.rxjy.logUtil.core.util.ClassUtil;

import java.util.concurrent.atomic.AtomicReference;

/**
 * {@link AtomicReference}转换器
 * 
 * @author Looly
 * @since 3.0.8
 */
@SuppressWarnings("rawtypes")
public class AtomicReferenceConverter extends AbstractConverter<AtomicReference> {
	
	@Override
	protected AtomicReference<?> convertInternal(Object value) {
		
		//尝试将值转换为Reference泛型的类型
		Object targetValue = null;
		final Class<?> paramType = ClassUtil.getTypeArgument(AtomicReference.class);
		if(null != paramType){
			targetValue = ConverterRegistry.getInstance().convert(paramType, value);
		}
		if(null == targetValue){
			targetValue = value;
		}
		
		return new AtomicReference(targetValue);
	}

}
