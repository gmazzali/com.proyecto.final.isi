package com.proyecto.model.instrument.type.impl;

import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.ObjectiveActivityInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La clase que define los instrumentos formales del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum FormalInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos formales objetivos.
	 */
	OBJETIVE_ACTIVITY(ObjectiveActivityInstrument.class, ObjectiveActivityInstrumentType.values()),
	/**
	 * El elemento de los instrumentos formales de ensayos.
	 */
	ESSAY_ACTIVITY(EssayActivityInstrument.class, EssayActivityInstrumentType.values());

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
	 * @param instrumentClass
	 *            La clase de los instrumentos.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private FormalInstrumentType(Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
		this.instrumentClass = instrumentClass;
		this.subInstruments = subInstruments;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public String getName() {
		return InstrumentClassToNameConverter.converter(this.instrumentClass);
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
