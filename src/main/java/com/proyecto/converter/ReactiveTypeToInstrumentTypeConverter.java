package com.proyecto.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.proyecto.model.material.instrument.type.InstrumentType;
import com.proyecto.model.material.reactive.type.ReactiveType;
import com.proyecto.model.material.reactive.type.impl.ReactiveTypeImpl;

/**
 * La clase que nos permite convertir un listado de reactivos habilitados en un listado de los instrumentos habilitados.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveTypeToInstrumentTypeConverter {

	/**
	 * La función encargada de tomar un listado de los tipos de reactivos y retornar un listado de los posibles tipos de instrumentos para ese
	 * conjunto de tipos de reactivos.
	 * 
	 * @param reactiveTypes
	 *            El conjunto de tipos de reactivos que vamos a analizar, puede ser nulo.
	 * @return El listado de los tipos de instrumentos válidos para el conjunto de tipos de reactivo recibido, en caso de recibir un conjunto de tipos
	 *         de reactivos nulo, retornamos un listado de los tipos de instrumento válido para todo los tipos de reactivos.
	 */
	public static List<InstrumentType> converter(List<ReactiveType> reactiveTypes) {

		// El set de los instrumentos.
		Set<InstrumentType> instrumentTypes = new HashSet<InstrumentType>();

		// Si el listado recibido no es nulo, lo recorremos.
		if (reactiveTypes != null) {
			// Recorremos el listado de los tipos de reactivos.
			for (ReactiveType reactiveType : reactiveTypes) {
				for (InstrumentType instrumentType : reactiveType.getInstrumentsTypesAllowed()) {
					instrumentTypes.add(instrumentType);
				}
			}
		} else {
			for (ReactiveType reactiveType : ReactiveTypeImpl.values()) {
				for (InstrumentType instrumentType : reactiveType.getInstrumentsTypesAllowed()) {
					instrumentTypes.add(instrumentType);
				}
			}
		}
		return new ArrayList<InstrumentType>(instrumentTypes);
	}
}