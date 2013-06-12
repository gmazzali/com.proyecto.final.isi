package com.proyecto.model.instrument;

import com.common.util.model.Entity;

/**
 * La clase que definimos para los distintos tipos de instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class Instrument extends Entity<Integer> {

	private static final long serialVersionUID = -932523909953824546L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String DESCRIPTION = "description";
	}

	/**
	 * La descripción del instrumento.
	 */
	protected String description;

	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función encargada de retornar la descripción del instrumento.
	 * 
	 * @return La descripción del instrumento.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función encargada de cargar la descripción del instrumento.
	 * 
	 * @param description
	 *            La descripción del instrumento que vamos a utilizar.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
