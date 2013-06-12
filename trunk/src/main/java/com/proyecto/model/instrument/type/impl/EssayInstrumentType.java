package com.proyecto.model.instrument.type.impl;

import com.proyecto.model.instrument.RestrictedEssayInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La clase que define los instrumentos formales de ensayos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum EssayInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos formales de ensayos restringidos.
	 */
	RESTRICTED_INSTRUMENT(1, "Restricted instrument", RestrictedEssayInstrument.class, null),
	/**
	 * El elemento de los instrumentos formales de ensayos no restringidos.
	 */
	UNRESTRICTED_INSTRUMENT(2, "Unrestricted instrument", UnrestrictedEssayInstrument.class, null);

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
	private EssayInstrumentType(Integer code, String name, Class<?> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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
