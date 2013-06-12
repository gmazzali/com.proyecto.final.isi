package com.proyecto.model.instrument.type.impl;

import java.lang.reflect.Modifier;

import com.proyecto.model.instrument.FormalInstrument;
import com.proyecto.model.instrument.SemiFormalInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;

/**
 * La enumeración que ocupamos para definir los distintos tipos de instrumentos que tenemos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum InstrumentType implements InstrumentTypeInterface {

	/**
	 * El elemento de los instrumentos formales.
	 */
	FORMAL(1, "Formal", FormalInstrument.class, FormalInstrumentType.values()),
	/**
	 * El elemento de los instrumentos semiformales.
	 */
	SEMIFORMAL(2, "SemiFormal", SemiFormalInstrument.class, SemiFormalInstrumentType.values());

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
	private InstrumentType(Integer code, String name, Class<?> instrumentClass, InstrumentTypeInterface[] subInstruments) {
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

	private static void recursivePrint(InstrumentTypeInterface instrument, Integer level) {
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
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

		if (instrument.getSubInstruments() != null) {
			for (InstrumentTypeInterface i : instrument.getSubInstruments()) {
				InstrumentType.recursivePrint(i, level + 4);
			}
		}
	}

	public static void main(String[] args) {
		InstrumentType.recursivePrint(InstrumentType.FORMAL, 0);
		InstrumentType.recursivePrint(InstrumentType.SEMIFORMAL, 0);
	}
}
