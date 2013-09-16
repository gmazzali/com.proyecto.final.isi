package com.proyecto.model.material.reactive.type.impl;

import java.util.Arrays;
import java.util.List;

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
	FORMAL("type.reactive.formal", new InstrumentType[] { InstrumentTypeImpl.FORMAL }),
	/**
	 * El tipo de reactivo que solo permite instrumentos semiformales.
	 */
	SEMIFORMAL("type.reactive.semiformal", new InstrumentType[] { InstrumentTypeImpl.SEMIFORMAL });

	/**
	 * El nombre del tipo de reactivo.
	 */
	private String name;
	/**
	 * Los tipos de instrumentos que se permite cargar dentro de este tipo de reactivo.
	 */
	private List<InstrumentType> instrumentsTypeAllowed;

	/**
	 * El constructor de un tipo de reactivo.
	 * 
	 * @param name
	 *            El nombre del tipo de reactivo.
	 * @param instrumentsTypesAllowed
	 *            Los tipos de instrumentos permitidos dentro de este reactivo.
	 */
	private ReactiveTypeImpl(String name, InstrumentType[] instrumentsTypesAllowed) {
		this.name = name;
		this.instrumentsTypeAllowed = Arrays.asList(instrumentsTypesAllowed);
	}

	@Override
	public String toString() {
		return HolderMessage.getMessage(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<InstrumentType> getInstrumentsTypesAllowed() {
		return this.instrumentsTypeAllowed;
	}
}