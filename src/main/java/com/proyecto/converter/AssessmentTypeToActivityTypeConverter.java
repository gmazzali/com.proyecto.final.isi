package com.proyecto.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.model.material.assessment.type.AssessmentType;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;

/**
 * La clase que nos permite convertir un listado de tipos de evaluaciones habilitados en un listado de los tipos de evaluaciones habilitados.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AssessmentTypeToActivityTypeConverter {

	/**
	 * La función encargada de tomar un listado de los tipos de evaluaciones y retornar un listado de los posibles tipos de actividades para ese
	 * conjunto de tipos de evaluaciones.
	 * 
	 * @param assessmentTypes
	 *            El conjunto de tipos de evaluaciones que vamos a analizar, puede ser nulo.
	 * @return El listado de los tipos de actividades válidos para el conjunto de tipos de evaluaciones recibido, en caso de recibir un conjunto de
	 *         tipos de evaluaciones nulo, retornamos un listado de los tipos de actividades válido para todo los tipos de evaluaciones.
	 */
	public static List<ActivityType> converter(List<AssessmentType> assessmentTypes) {

		// El set de los actividades.
		Set<ActivityType> activityTypes = new HashSet<ActivityType>();

		// Si el listado recibido no es nulo, lo recorremos.
		if (assessmentTypes != null) {
			// Recorremos el listado de los tipos de evaluaciones.
			for (AssessmentType assessmentType : assessmentTypes) {
				for (ActivityType activityType : assessmentType.getActivityTypesAllowed()) {
					activityTypes.add(activityType);
				}
			}
		} else {
			for (AssessmentType assessmentType : AssessmentTypeImpl.values()) {
				for (ActivityType activityType : assessmentType.getActivityTypesAllowed()) {
					activityTypes.add(activityType);
				}
			}
		}

		return new ArrayList<ActivityType>(activityTypes);
	}
}