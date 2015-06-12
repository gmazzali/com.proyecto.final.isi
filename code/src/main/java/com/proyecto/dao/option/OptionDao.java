package com.proyecto.dao.option;

import com.proyecto.dao.ProyectoDao;
import com.proyecto.model.option.Option;

/**
 * La interfaz que define el DAO para las opciones de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opción.
 */
public interface OptionDao<E extends Option> extends ProyectoDao<E, Integer> {
}