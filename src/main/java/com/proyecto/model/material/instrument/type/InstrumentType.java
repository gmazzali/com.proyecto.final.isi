package com.proyecto.model.material.instrument.type;

import com.proyecto.model.material.instrument.Instrument;

/**
 * La interfaz que define los instrumentos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface InstrumentType {

	/**
	 * La funci�n encargada de retornar el listado de los instrumentos que se definen como hijos de este tipo.
	 * 
	 * @return El listado de los tipos de instrumentos que tenemos como hijos de este elemento.
	 */
	public InstrumentType[] getSubInstruments();

	/**
	 * La funci�n encargada de retornar el nombre del tipo de instrumento que tenemos en este elemento.
	 * 
	 * @return El nombre del tipo de instrumento que tenemos en este elemento.
	 */
	public String getName();

	/**
	 * La funci�n que retorna la clase que representa el instrumento que definimos en esta tipo de entidad.
	 * 
	 * @return La clase que corresponde a este tipo de entidad.
	 */
	public Class<? extends Instrument> getInstrumentClass();
}
