package com.proyecto.model.instrument.type.impl;

import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La clase que define los instrumentos formales de ensayos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum EssayActivityInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos formales de ensayos restringidos.
	 */
	RESTRICTED_INSTRUMENT(RestrictedEssayActivityInstrument.class, null),
	/**
	 * El elemento de los instrumentos formales de ensayos no restringidos.
	 */
	UNRESTRICTED_INSTRUMENT(UnrestrictedEssayActivityInstrument.class, null);

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
	private EssayActivityInstrumentType(Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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