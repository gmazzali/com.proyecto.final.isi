package com.proyecto.model.reactive.type.impl;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;
import com.proyecto.model.instrument.type.impl.InstrumentType;
import com.proyecto.model.reactive.type.ReactiveType;

/**
 * La enumeración que nos permite definir los tipos de reactivos que vamos a poder definir dentro de un sistema de acuerdo a los tipos de instrumentos que podemos asignarles a los mismos.
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ReactiveTypeImpl implements ReactiveType {

	/**
	 * El tipo de reactivo que solo permite instrumentos formales.
	 */
	FORMAL(new InstrumentType[]{ InstrumentType.FORMAL }), 
	/**
	 * El tipo de reactivo que solo permite instrumentos semiformales.
	 */
	SEMIFORMAL(new InstrumentType[]{ InstrumentType.SEMIFORMAL });
	
	/**
	 * Los tipos de instrumentos que se permite cargar dentro de este tipo de reactivo.
	 */
	private InstrumentTypeInterface[] instrumentsTypeAllowed;
	
	/**
	 * El constructor de un tipo de reactivo.
	 * @param instrumentsTypeAllowed Los tipos de instrumentos permitidos dentro de este reactivo.
	 */
	private ReactiveTypeImpl(InstrumentTypeInterface[] instrumentsTypeAllowed) {
		this.instrumentsTypeAllowed = instrumentsTypeAllowed;
	}
	
	@Override
	public InstrumentTypeInterface[] getInstrumentsTypeAllowed() {
		return instrumentsTypeAllowed;
	}
	
	public Boolean isInstrumentClassAllowed(Class<? extends Instrument> instrumentClass) {
		for (InstrumentTypeInterface instrumentType : instrumentsTypeAllowed) {
			if(instrumentType.getInstrumentClass().isAssignableFrom(instrumentClass)) {
				return true;
			}
		}
		return false;
	}
}
