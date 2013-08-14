package com.proyecto.model.material.instrument.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.type.InstrumentType;

/**
 * La clase que define los instrumentos formales de ensayos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum EssayActivityInstrumentTypeImpl implements InstrumentType {

	/**
	 * El elemento de los instrumentos formales de ensayos restringidos.
	 */
	RESTRICTED_INSTRUMENT("instrument.type.formal.essay.restricted", RestrictedEssayActivityInstrument.class, null),
	/**
	 * El elemento de los instrumentos formales de ensayos no restringidos.
	 */
	UNRESTRICTED_INSTRUMENT("instrument.type.formal.essay.unrestricted", UnrestrictedEssayActivityInstrument.class, null);

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
	 * El constructor que recibe los par�metros.
	 * 
	 * @param name
	 *            El nombre del instrumento que estamos usando.
	 * @param instrumentClass
	 *            La clase de los instrumentos.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private EssayActivityInstrumentTypeImpl(String name, Class<? extends Instrument> instrumentClass, InstrumentType[] subInstruments) {
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