package com.proyecto.converter;

import java.util.HashSet;
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
	 * La funci�n encargada de tomar un listado de los tipos de evaluaciones y retornar un listado de los posibles tipos de actividades para ese
	 * conjunto de tipos de evaluaciones.
	 * 
	 * @param assessmentTypes
	 *            El conjunto de tipos de evaluaciones que vamos a analizar, puede ser nulo.
	 * @return El listado de los tipos de actividades v�lidos para el conjunto de tipos de evaluaciones recibido, en caso de recibir un conjunto de
	 *         tipos de evaluaciones nulo, retornamos un listado de los tipos de actividades v�lido para todo los tipos de evaluaciones.
	 */
	public static ActivityType[] converter(AssessmentType[] assessmentTypes) {

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

		// Cargamos el arreglo de retorno.
		ActivityType[] returnArray = new ActivityType[activityTypes.size()];
		Integer index = 0;
		for (ActivityType activityType : activityTypes) {
			returnArray[index++] = activityType;
		}

		return returnArray;
	}
}