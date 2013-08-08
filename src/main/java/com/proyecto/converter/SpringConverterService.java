package com.topgroup.wga.converter;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import com.topgroup.commons.utils.lang.ConverterService;

/**
 * La clase que nos permite manipular los conversores que nos ofrece SPRING para convertir entidades entre ellas.
 * @author Guillermo Mazzali
 *@version 1.0
 */
public class SpringConverterService extends DefaultConversionService implements ConverterService, InitializingBean {

	private Set<Converter<?, ?>> converters;

	@Override
	public <S, T> T convertTo(S source, Class<T> targetClass) {
		return convert(source, targetClass);
	}

	@Override
	public <S, T> boolean canConvertTo(Class<S> sourceClass, Class<T> targetClass) {
		return canConvert(sourceClass, targetClass);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (converters != null) {
			for (Converter<?, ?> converter : converters) {
				addConverter(converter);
			}
		}
	}

	public void setConverters(Set<Converter<?, ?>> converters) {
		this.converters = converters;
	}
}