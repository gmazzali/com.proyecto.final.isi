package com.proyecto.model.material.reactive.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.type.InstrumentType;
import com.proyecto.model.material.instrument.type.impl.InstrumentTypeImpl;
import com.proyecto.model.material.reactive.type.ReactiveType;

/**
 * La enumeración que nos permite definir los tipos de reactivos que vamos a poder definir dentro de un sistema de acuerdo a los tipos de instrumentos
 * que podemos asignarles a los mismos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ReactiveTypeImpl implements ReactiveType {

	/**
	 * El tipo de reactivo que solo permite instrumentos formales.
	 */
	FORMAL("reactive.type.formal", new InstrumentType[] { InstrumentTypeImpl.FORMAL }),
	/**
	 * El tipo de reactivo que solo permite instrumentos semiformales.
	 */
	SEMIFORMAL("reactive.type.semiformal", new InstrumentType[] { InstrumentTypeImpl.SEMIFORMAL });

	/**
	 * El nombre del tipo de reactivo.
	 */
	private String name;
	/**
	 * Los tipos de instrumentos que se permite cargar dentro de este tipo de reactivo.
	 */
	private InstrumentType[] instrumentsTypeAllowed;

	/**
	 * El constructor de un tipo de reactivo.
	 * 
	 * @param name
	 *            El nombre del tipo de reactivo.
	 * @param instrumentsTypeAllowed
	 *            Los tipos de instrumentos permitidos dentro de este reactivo.
	 */
	private ReactiveTypeImpl(String name, InstrumentType[] instrumentsTypeAllowed) {
		this.name = name;
		this.instrumentsTypeAllowed = instrumentsTypeAllowed;
	}

	@Override
	public InstrumentType[] getInstrumentsTypeAllowed() {
		return this.instrumentsTypeAllowed;
	}

	@Override
	public String getName() {
		return HolderMessage.getMessage(this.name);
	}
}