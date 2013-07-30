package com.proyecto.model.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.CompositeInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.SimpleInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeraci�n que contiene los instrumentos semiformales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum SemiFormalInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos semiformales simples.
	 */
	SIMPLE_INSTRUMENT("instrument.type.semiformal.simple", SimpleInstrument.class, SimpleInstrumentType.values()),
	/**
	 * El elemento de los instrumentos semiformales compuestos.
	 */
	COMPOSITE_INSTRUMENT("instrument.type.semiformal.composite", CompositeInstrument.class, CompositeInstrumentType.values());

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
	 * El constructor que recibe los par�metros.
	 * 
	 * @param name
	 *            El nombre del instrumento.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private SemiFormalInstrumentType(String name, Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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