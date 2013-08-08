package com.proyecto.model.instrument.type.impl;

import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeraci�n que contiene los tipos de instrumentos que corresponde a los tipos de instrumentos formales objetivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ObjectiveActivityInstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento que define un instrumento formal objetivo de selecci�n.
	 */
	CHOICE(ChoiceInstrument.class, ChoiceInstrumentType.values()),
	/**
	 * El elemento que define un instrumento formal objetivo de correspondencia.
	 */
	CORRESPONDENCE(CorrespondenceInstrument.class, null),
	/**
	 * El elemento que define un instrumento formal objetivo para completar.
	 */
	COMPLETION(CompletionInstrument.class, null);

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
	 * @param instrumentClass
	 *            La clase de los instrumentos.
	 * @param subInstruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private ObjectiveActivityInstrumentType(Class<? extends Instrument> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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