package com.proyecto.model.material.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.type.InstrumentType;

/**
 * La enumeración que contiene los tipos de instrumentos que corresponde a los tipos de instrumentos formales objetivos de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ChoiceInstrumentTypeImpl implements InstrumentType {

	/**
	 * El elemento que define un instrumento formal objetivo de selección simple.
	 */
	SINGLE_CHOICE("instrument.type.formal.objective.choice.single", SingleChoiceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo de selección multiple.
	 */
	MULTIPLE_CHOICE("instrument.type.formal.objective.choice.multiple", MultipleChoiceInstrument.class, null);

	/**
	 * El nombre del tipo de instrumento.
	 */
	private String name;
	/**
	 * La clase que corresponde al instrumento.
	 */
	private Class<? extends Instrument> instrumentClass;
	/**
	 * Las enumeraciones que contiene los sub-instrumentos.
	 */
	private final InstrumentType[] subInstruments;

	/**
	 * El constructor que recibe los parámetros.
	 * 
	 * @param name
	 *            El nombre del instrumento que estamos usando.
	 * @param instrumentClass
	 *            La clase de los instrumentos.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private ChoiceInstrumentTypeImpl(String name, Class<? extends Instrument> instrumentClass, InstrumentType[] subInstruments) {
		this.name = name;
		this.instrumentClass = instrumentClass;
		this.subInstruments = subInstruments;
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
	public InstrumentType[] getSubInstruments() {
		return this.subInstruments;
	}

	@Override
	public Class<? extends Instrument> getInstrumentClass() {
		return this.instrumentClass;
	}
}