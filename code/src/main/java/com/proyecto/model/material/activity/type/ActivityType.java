package com.proyecto.model.material.activity.type;

import com.proyecto.model.material.reactive.type.ReactiveType;

/**
 * La interfaz que define los tipos de actividades que podemos crear dentro del sistema de acuerdo al tipo de evaluación a crear y a los reactivos que
 * podemos asignarle a los mismos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ActivityType {

	/**
	 * La función encargada de retornar el nombre del tipo de actividad.
	 * 
	 * @return El nombre del tipo de actividad.
	 */
	public String getName();

	/**
	 * La función que retorna el listado de los tipos de reactivos que tenemos permitidos dentro de este tipo de actividad.
	 * 
	 * @return El listado de los tipos de reactivos permitidos dentro de este tipo de actividad.
	 */
	public ReactiveType[] getReactiveTypesAllowed();
}