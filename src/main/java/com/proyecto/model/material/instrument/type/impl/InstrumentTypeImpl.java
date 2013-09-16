package com.proyecto.model.material.instrument.type.impl;

import java.lang.reflect.Modifier;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.FormalInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.SemiFormalInstrument;
import com.proyecto.model.material.instrument.type.InstrumentType;

/**
 * La enumeración que ocupamos para definir los distintos tipos de instrumentos que tenemos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum InstrumentTypeImpl implements InstrumentType {

	/**
	 * El elemento de los instrumentos formales.
	 */
	FORMAL("type.instrument.formal", FormalInstrument.class, FormalInstrumentTypeImpl.values()),
	/**
	 * El elemento de los instrumentos semiformales.
	 */
	SEMIFORMAL("type.instrument.semiformal", SemiFormalInstrument.class, SemiFormalInstrumentTypeImpl.values());

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
	private InstrumentTypeImpl(String name, Class<? extends Instrument> instrumentClass, InstrumentType[] subInstruments) {
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

	private static void recursivePrint(InstrumentType instrument, Integer level) {
		if (instrument == null) {
			return;
		}

		for (int i = 0; i < level; i++) {
			System.out.print(" ");
		}
		System.out.println(instrument + " - " + instrument.getInstrumentClass().getCanonicalName());

		try {
			if (!Modifier.isAbstract(instrument.getInstrumentClass().getModifiers())) {
				for (int i = 0; i < instrument.getName().length() + level; i++) {
					System.out.print(" ");
				}
				Object object = instrument.getInstrumentClass().newInstance();
				System.out.println(" --> " + object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (instrument.getSubInstruments() != null) {
			for (InstrumentType i : instrument.getSubInstruments()) {
				InstrumentTypeImpl.recursivePrint(i, level + 4);
			}
		}
	}

	public static void main(String[] args) {
		InstrumentTypeImpl.recursivePrint(InstrumentTypeImpl.FORMAL, 0);
		InstrumentTypeImpl.recursivePrint(InstrumentTypeImpl.SEMIFORMAL, 0);
	}
}
