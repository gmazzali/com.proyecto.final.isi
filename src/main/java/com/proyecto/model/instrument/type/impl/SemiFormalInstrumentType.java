package com.proyecto.model.instrument.type.impl;

import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.instrument.CompositeInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.SimpleInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que contiene los instrumentos semiformales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum SemiFormalInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos semiformales simples.
	 */
	SIMPLE_INSTRUMENT(SimpleInstrument.class, SimpleInstrumentType.values()),
	/**
	 * El elemento de los instrumentos semiformales compuestos.
	 */
	COMPOSITE_INSTRUMENT(CompositeInstrument.class, CompositeInstrumentType.values());

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
	private SemiFormalInstrumentType(Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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