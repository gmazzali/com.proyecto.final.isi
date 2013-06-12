package com.proyecto.model.instrument.type.impl;

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
	SINGLE_CHOICE(1, "Single choice", SingleChoiceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo de selección multiple.
	 */
	MULTIPLE_CHOICE(2, "Multiple choice", MultipleChoiceInstrument.class, null);

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
	private ChoiceInstrumentType(Integer code, String name, Class<?> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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