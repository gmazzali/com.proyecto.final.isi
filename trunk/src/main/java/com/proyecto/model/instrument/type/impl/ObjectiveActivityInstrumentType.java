package com.proyecto.model.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que contiene los tipos de instrumentos que corresponde a los tipos de instrumentos formales objetivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ObjectiveActivityInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento que define un instrumento formal objetivo de selección.
	 */
	CHOICE("instrument.type.formal.objective.choice", ChoiceInstrument.class, ChoiceInstrumentType.values()),
	/**
	 * El elemento que define un instrumento formal objetivo de correspondencia.
	 */
	CORRESPONDENCE("instrument.type.formal.objective.correspondence", CorrespondenceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo para completar.
	 */
	COMPLETION("instrument.type.formal.objective.completion", CompletionInstrument.class, null);

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
	private ObjectiveActivityInstrumentType(String name, Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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