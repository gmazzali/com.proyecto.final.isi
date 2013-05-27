package com.proyecto.model.instrument;

/**
 * La clase que define los instrumentos formales del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum FormalInstrument {

	/**
	 * El elemento de los instrumentos formales de ensayos.
	 */
	ESSAY_ACTIVITY("Essay activity"),
	/**
	 * El elemento de los instrumentos formales objetivos.
	 */
	OBJETIVE_ACTIVITY("Objetive activity");

	/**
	 * El nombre del instrumento formal.
	 */
	private String name;

	/**
	 * El constructor que recibe los parámetros.
	 * 
	 * @param name
	 *            El nombre del instrumento formal.
	 */
	private FormalInstrument(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * La función que retorna el nombre del instrumento formal.
	 * 
	 * @return El nombre del instrumento formal.
	 */
	public String getName() {
		return this.name;
	}
}
