package com.proyecto.converter;

import java.util.HashSet;
import java.util.Set;

import com.proyecto.model.instrument.type.InstrumentType;
import com.proyecto.model.reactive.type.ReactiveType;

/**
 * La clase que nos permite convertir un listado de reactivos habilitados en un listado de los instrumentos habilitados.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveTypeToInstrumentTypeConverter {

	public static InstrumentType[] converter(ReactiveType[] reactiveType) {

		// El set de los instrumentos.
		Set<InstrumentType> instrumentType = new HashSet<InstrumentType>();

		// Recorremos el listado de los tipos de reactivos.
		for (ReactiveType type : reactiveType) {
			for (InstrumentType instrument : type.getInstrumentsTypeAllowed()) {
				instrumentType.add(instrument);
			}
		}

		// Cargamos el arreglo de retorno.
		InstrumentType[] returnArray = new InstrumentType[instrumentType.size()];
		Integer index = 0;
		for (InstrumentType instrument : instrumentType) {
			returnArray[index++] = instrument;
		}

		return returnArray;
	}
}