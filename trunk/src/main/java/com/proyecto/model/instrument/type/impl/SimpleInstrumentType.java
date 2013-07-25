package com.proyecto.model.instrument.type.impl;

import com.proyecto.model.instrument.ConceptualMapInstrument;
import com.proyecto.model.instrument.EssayInstrument;
import com.proyecto.model.instrument.ExerciseInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que contiene los instrumentos semiformales simples que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum SimpleInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos semiformales simples de mapas conceptuales.
	 */
	CONCEPTUAL_MAP(1, "Conceptual Map", ConceptualMapInstrument.class, null),
	/**
	 * El elemento de los instrumentos semiformales simples de ensayos.
	 */
	ESSAY(2, "Essay", EssayInstrument.class, null),
	/**
	 * El elemento de los instrumentos semiformales simples de ejercicios.
	 */
	EXERCISE(3, "Exercise", ExerciseInstrument.class, null);

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
	private Class<? extends Instrument> instrumentClass;
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
	private SimpleInstrumentType(Integer code, String name, Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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
	public Class<? extends Instrument> getInstrumentClass() {
		return this.instrumentClass;
	}
}