package com.proyecto.service.option.impl;

import com.proyecto.model.option.Option;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.option.OptionService;

/**
 * La clase que implementa la interfaz que define los servicios para las opciones de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opción.
 */
public abstract class OptionServiceImpl<E extends Option> extends ProyectoServiceImpl<E, Integer> implements OptionService<E> {
}