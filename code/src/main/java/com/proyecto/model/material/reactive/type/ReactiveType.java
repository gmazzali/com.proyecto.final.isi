package com.proyecto.model.material.reactive.type;

import java.util.List;

import com.proyecto.model.material.instrument.type.InstrumentType;

/**
 * La interfaz que define el comportamiento para los tipos de reactivos que podemos llegar a crear dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ReactiveType {

	/**
	 * La función encargada de retornar el nombre del tipo de reactivo.
	 * 
	 * @return El nombre del tipo de reactivo.
	 */
	public String getName();

	/**
	 * La función que retorna el listado de los tipos de instrumentos que tenemos permitidos dentro de este tipo de reactivo.
	 * 
	 * @return El listado de los tipos de instrumentos permitidos dentro de este tipo de reactivo.
	 */
	public List<InstrumentType> getInstrumentsTypesAllowed();
}