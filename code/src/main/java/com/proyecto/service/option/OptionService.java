package com.proyecto.service.option;

import com.proyecto.model.option.Option;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios para las opciones de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opción.
 */
public interface OptionService<E extends Option> extends ProyectoService<E, Integer> {
}