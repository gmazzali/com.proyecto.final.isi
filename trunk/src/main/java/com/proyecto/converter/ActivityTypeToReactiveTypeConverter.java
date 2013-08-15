package com.proyecto.converter;

import java.util.HashSet;
import java.util.Set;

import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.model.material.activity.type.impl.ActivityTypeImpl;
import com.proyecto.model.material.reactive.type.ReactiveType;

/**
 * La clase que nos permite convertir un listado de tipos de actividades habilitados en un listado de tipos de reactivos habilitados.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActivityTypeToReactiveTypeConverter {

	/**
	 * La función encargada de tomar un listado de los tipos de actividades y retornar un listado de los posibles tipos de reactivos para ese
	 * conjunto de tipos de actividades.
	 * 
	 * @param activityTypes
	 *            El conjunto de tipos de actividades que vamos a analizar, puede ser nulo.
	 * @return El listado de los tipos de reactivos válidos para el conjunto de tipos de actividades recibido, en caso de recibir un conjunto de
	 *         tipos de actividades nulo, retornamos un listado de los tipos de reactivos válido para todo los tipos de actividades.
	 */
	public static ReactiveType[] converter(ActivityType[] activityTypes) {

		// El set de los reactivos.
		Set<ReactiveType> reactiveTypes = new HashSet<ReactiveType>();

		// Si el listado recibido no es nulo, lo recorremos.
		if (activityTypes != null) {
			// Recorremos el listado de los tipos de actividades.
			for (ActivityType activityType : activityTypes) {
				for (ReactiveType reactiveType : activityType.getReactiveTypesAllowed()) {
					reactiveTypes.add(reactiveType);
				}
			}
		} else {
			for (ActivityType activityType : ActivityTypeImpl.values()) {
				for (ReactiveType reactiveType : activityType.getReactiveTypesAllowed()) {
					reactiveTypes.add(reactiveType);
				}
			}
		}

		// Cargamos el arreglo de retorno.
		ReactiveType[] returnArray = new ReactiveType[reactiveTypes.size()];
		Integer index = 0;
		for (ReactiveType reactiveType : reactiveTypes) {
			returnArray[index++] = reactiveType;
		}

		return returnArray;
	}
}