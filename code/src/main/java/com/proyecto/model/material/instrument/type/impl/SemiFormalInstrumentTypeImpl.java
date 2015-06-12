package com.proyecto.model.material.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.CompositeInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.SimpleInstrument;
import com.proyecto.model.material.instrument.type.InstrumentType;

/**
 * La enumeración que contiene los instrumentos semiformales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum SemiFormalInstrumentTypeImpl implements InstrumentType {

	/**
	 * El elemento de los instrumentos semiformales simples.
	 */
	SIMPLE_INSTRUMENT("type.instrument.semiformal.simple", SimpleInstrument.class, SimpleInstrumentTypeImpl.values()),
	/**
	 * El elemento de los instrumentos semiformales compuestos.
	 */
	COMPOSITE_INSTRUMENT("type.instrument.semiformal.composite", CompositeInstrument.class, CompositeInstrumentTypeImpl.values());

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
	private SemiFormalInstrumentTypeImpl(String name, Class<? extends Instrument> instrumentClass, InstrumentType[] subInstruments) {
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