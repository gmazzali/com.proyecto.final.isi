package com.proyecto.model.material.instrument;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.option.Option;

/**
 * La clase que nos permite definir los instrumentos de selecci�n, ya sean multiples o simples.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "CHOICE_INSTRUMENTS")
@javax.persistence.Entity(name = "ChoiceInstrument")
public abstract class ChoiceInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = 2343373861916155108L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
		static final String OPTIONS = "options";
	}

	/**
	 * El listado de las opciones.
	 */
	protected List<Option> options;

	/**
	 * El constructor por omisi�n.
	 */
	public ChoiceInstrument() {
		super();
		this.options = new ArrayList<Option>();
	}

	/**
	 * La funci�n encargada de agregar una nueva opci�n dentro de este instrumento.
	 * 
	 * @param option
	 *            La opci�n que vamos a agregar dentro de este instrumento.
	 */
	public void addOption(Option option) {
		option.setInstrument(this);
		this.options.add(option);
	}

	/**
	 * La funci�n encargada de agregar un listado de opciones dentro de las que ya tenemos dentro del instrumento.
	 * 
	 * @param options
	 *            El listado de opciones que vamos a agregar dentro del que ya tenemos.
	 */
	public void addAllOptions(List<Option> options) {
		for (Option option : options) {
			this.addOption(option);
		}
	}

	/**
	 * La funci�n encargada de quitar una opci�n de el listado de opciones que tiene este instrumento.
	 * 
	 * @param option
	 *            La opci�n que quiere quitarse de este instrumento.
	 */
	public void removeOption(Option option) {
		this.options.remove(option);
	}

	/**
	 * La funci�n encargada de borrar todas las opciones que se encuentra dentro de este instrumento y que est�n en el listado de opciones a borrar.
	 * 
	 * @param options
	 *            El listado de las opciones a borrar.
	 */
	public void removeAllOptions(List<Option> options) {
		for (Option option : options) {
			this.removeOption(option);
		}
	}

	/**
	 * la funci�n encargada de vaciar el listado de las opciones que tenemos dentro de este instrumento.
	 */
	public void clearOptions() {
		this.options.clear();
	}

	/**
	 * La funci�n encargada de retornar el listado de las opciones que tenemos dentro de este instrumento.
	 * 
	 * @return El listado de opciones que tenemos dentro de este elemento.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instrument", targetEntity = Option.class, orphanRemoval = true)
	public List<Option> getOptions() {
		return this.options;
	}

	/**
	 * La funci�n encargada de cargar el listado de las opciones que tenemos dentro de este instrumento.
	 * 
	 * @param options
	 *            El listado de opciones que vamos a cargar.
	 */
	public void setOptions(List<Option> options) {
		this.options = options;
	}
}
