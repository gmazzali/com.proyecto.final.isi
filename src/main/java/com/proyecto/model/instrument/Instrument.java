package com.proyecto.model.instrument;

/**
 * La enumeración que ocupamos para definir los distintos tipos de instrumentos que tenemos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum Instrument {

	/**
	 * El elemento de los instrumentos formales.
	 */
	FORMAL("Formal", new FormalInstrument[]
		{ FormalInstrument.ESSAY_ACTIVITY, FormalInstrument.OBJETIVE_ACTIVITY }),
	/**
	 * El elemento de los instrumentos semiformales.
	 */
	SEMIFORMAL("Semiformal", new FormalInstrument[]
		{ FormalInstrument.ESSAY_ACTIVITY, FormalInstrument.OBJETIVE_ACTIVITY });

	/**
	 * El nombre del instrumento.
	 */
	private String name;
	/**
	 * Las enumeraciones que contiene los sub-instrumentos.
	 */
	private Enum<?>[] instruments;

	/**
	 * El constructor que recibe los parámetros.
	 * 
	 * @param name
	 *            El nombre del instrumento.
	 * @param instruments
	 *            Los sub-instrumentos de este instrumento.
	 */
	private Instrument(String name, Enum<?>[] instruments) {
		this.name = name;
		this.instruments = instruments;
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * La función que retorna el nombre del instrumento.
	 * 
	 * @return El nombre del instrumento.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * La función que retorna las instancias de la enumeración del instrumento.
	 * 
	 * @return Las instancias de la enumeración del instrumento.
	 */
	public Enum<?>[] getInstruments() {
		return this.instruments;
	}
}
