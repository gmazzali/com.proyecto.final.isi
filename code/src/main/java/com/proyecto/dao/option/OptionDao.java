package com.proyecto.dao.option;

import com.proyecto.dao.ProyectoDao;
import com.proyecto.model.option.Option;

/**
 * La interfaz que define el DAO para las opciones de los instrumentos de selecci�n del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opci�n.
 */
public interface OptionDao<E extends Option> extends ProyectoDao<E, Integer> {
}