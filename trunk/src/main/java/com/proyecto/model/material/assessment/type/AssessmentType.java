package com.proyecto.model.material.assessment.type;

import com.proyecto.model.material.activity.type.ActivityType;

/**
 * La interfaz que define los tipos de evaluaciones que podemos crear dentro del sistema de acuerdo al tipo de contenido que vamos a poder asignarle a las mismas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentType {

	/**
	 * La función encargada de retornar el nombre del tipo de evaluación.
	 * 
	 * @return El nombre del tipo de evaluación.
	 */
	public String getName();

	/**
	 * La función que retorna el listado de los tipos de actividades que tenemos permitidos dentro de este tipo de evaluación.
	 * 
	 * @return El listado de los tipos de actividades permitidos dentro de este tipo de evaluación.
	 */
	public ActivityType[] getActivityTypesAllowed();
}