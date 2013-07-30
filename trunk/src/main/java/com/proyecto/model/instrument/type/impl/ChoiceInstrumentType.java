package com.proyecto.model.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que contiene los tipos de instrumentos que corresponde a los tipos de instrumentos formales objetivos de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ChoiceInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento que define un instrumento formal objetivo de selección simple.
	 */
	SINGLE_CHOICE("instrument.type.formal.objective.choice.single", SingleChoiceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo de selección multiple.
	 */
	MULTIPLE_CHOICE("instrument.type.formal.objective.choice.multiple", MultipleChoiceInstrument.class, null);

	/**
	 * El nombre del instrumento.
	 */
	private final String name;
	/**
	 * La clase que corresponde al instrumento.
	 */
	private Class<? extends Instrument> instrumentClass;
	/**
	 * Las enumeraciones que contiene los sub-instrumentos.
	 */
	private final InstrumentTypeInterface[] subInstruments;

	/**
	 * El constructor que recibe los parámetros.
	 * 
	 * @param name
	 *            El nombre del instrumento.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private ChoiceInstrumentType(String name, Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
		this.name = HolderMessage.getMessage(name);
		this.instrumentClass = instrumentClass;
		this.subInstruments = subInstruments;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public InstrumentTypeInterface[] getSubInstruments() {
		return this.subInstruments;
	}

	@Override
	public Class<? extends Instrument> getInstrumentClass() {
		return this.instrumentClass;
	}
}