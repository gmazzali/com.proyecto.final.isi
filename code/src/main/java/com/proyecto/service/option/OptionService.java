package com.proyecto.service.option;

import com.proyecto.model.option.Option;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios para las opciones de los instrumentos de selecci�n del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como opci�n.
 */
public interface OptionService<E extends Option> extends ProyectoService<E, Integer> {
}