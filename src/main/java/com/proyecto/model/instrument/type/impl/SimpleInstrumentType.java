package com.proyecto.model.instrument.type.impl;

import com.common.util.holder.HolderMessage;
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
	CONCEPTUAL_MAP("instrument.type.semiformal.simple.comceptual", ConceptualMapInstrument.class, null),
	/**
	 * El elemento de los instrumentos semiformales simples de ensayos.
	 */
	ESSAY("instrument.type.semiformal.simple.essay", EssayInstrument.class, null),
	/**
	 * El elemento de los instrumentos semiformales simples de ejercicios.
	 */
	EXERCISE("instrument.type.semiformal.simple.exercise", ExerciseInstrument.class, null);

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
	private SimpleInstrumentType(String name, Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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