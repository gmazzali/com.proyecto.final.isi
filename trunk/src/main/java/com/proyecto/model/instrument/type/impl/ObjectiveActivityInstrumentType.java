package com.proyecto.model.instrument.type.impl;

import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
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
	CHOICE(1, "Choice", ChoiceInstrument.class, ChoiceInstrumentType.values()),
	/**
	 * El elemento que define un instrumento formal objetivo de correspondencia.
	 */
	CORRESPONDENCE(2, "Correspondence", CorrespondenceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo para completar.
	 */
	COMPLETION(3, "Completion", CompletionInstrument.class, null);

	/**
	 * El código del tipo de instrumento.
	 */
	private final Integer code;
	/**
	 * El nombre del instrumento.
	 */
	private final String name;
	/**
	 * La clase que corresponde al instrumento.
	 */
	private Class<?> instrumentClass;
	/**
	 * Las enumeraciones que contiene los sub-instrumentos.
	 */
	private final InstrumentTypeInterface[] subInstruments;

	/**
	 * El constructor que recibe los parámetros.
	 * 
	 * @param code
	 *            El código del instrumento.
	 * @param name
	 *            El nombre del instrumento.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private ObjectiveActivityInstrumentType(Integer code, String name, Class<?> instrumentClass, InstrumentTypeInterface[] subInstruments) {
		this.code = code;
		this.name = name;
		this.instrumentClass = instrumentClass;
		this.subInstruments = subInstruments;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public Integer getCode() {
		return this.code;
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
	public Class<?> getInstrumentClass() {
		return this.instrumentClass;
	}
}
