package com.proyecto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * La anotación de los servicios que vamos a ocupar para manipular las ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Scope(value = "singleton")
public @interface RdfService {
	public String value() default "";
}