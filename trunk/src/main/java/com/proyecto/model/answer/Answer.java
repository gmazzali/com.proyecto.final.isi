package com.proyecto.model.answer;

import java.io.Serializable;

import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una respuesta a los instrumentos que tenemos dentro del sistema para luego agregarle un modulo que verifique las
 * mismas con las que se cargaron a estos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <A>
 *            La clase que define el tipo de respuesta que vamos a tener dentro de este elemento.
 */
public abstract class Answer<A extends Serializable> extends Entity<Integer> {

	private static final long serialVersionUID = 8320735236711354967L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String VALUE = "value";
	}

	/**
	 * La respuesta.
	 */
	protected A value;

	/**
	 * El constructor por default de este elemento.
	 */
	public Answer() {
		super();
		this.value = null;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	/**
	 * La función encargada de retornar el valor que tenemos en la respuesta.
	 * 
	 * @return El valor que tenemos en la respuesta.
	 */
	public A getValue() {
		return this.value;
	}

	/**
	 * La función encargada de cargar el valor a la respuesta.
	 * 
	 * @param value
	 *            El valor que vamos a tener en esta respuesta.
	 */
	public void setValue(A value) {
		this.value = value;
	}
}
