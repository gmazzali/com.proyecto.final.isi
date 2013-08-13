package com.proyecto.model.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentType;

/**
 * La enumeración que contiene los tipos de instrumentos que corresponde a los tipos de instrumentos formales objetivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ObjectiveActivityInstrumentTypeImpl implements InstrumentType {

	/**
	 * El elemento que define un instrumento formal objetivo de selección.
	 */
	CHOICE("instrument.type.formal.objective.choice", ChoiceInstrument.class, ChoiceInstrumentTypeImpl.values()),
	/**
	 * El elemento que define un instrumento formal objetivo de correspondencia.
	 */
	CORRESPONDENCE("instrument.type.formal.objective.correspondence", CorrespondenceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo para completar.
	 */
	COMPLETION("instrument.type.formal.objective.completion", CompletionInstrument.class, null);

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
	private ObjectiveActivityInstrumentTypeImpl(String name, Class<? extends Instrument> instrumentClass, InstrumentType[] subInstruments) {
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