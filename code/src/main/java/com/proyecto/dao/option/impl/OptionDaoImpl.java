package com.proyecto.dao.option.impl;

import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.option.OptionDao;
import com.proyecto.model.option.Option;

/**
 * La clase que implementa la interfaz que define el DAO para las opciones de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opción.
 */
public abstract class OptionDaoImpl<E extends Option> extends ProyectoDaoImpl<E, Integer> implements OptionDao<E> {

	private static final long serialVersionUID = -8853165592451122931L;
}