package com.proyecto.model.instrument.type.impl;

import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.PortfolioInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que contiene los instrumentos semiformales compuestos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum CompositeInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos semiformales compuesto de portfolio.
	 */
	PORTFOLIO(PortfolioInstrument.class, null);

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
	private CompositeInstrumentType(Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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