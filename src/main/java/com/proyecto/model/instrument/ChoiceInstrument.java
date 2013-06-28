package com.proyecto.model.instrument;

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
 * La clase que nos permite definir los instrumentos de selección, ya sean multiples o simples.
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
	 * El constructor por omisión.
	 */
	public ChoiceInstrument() {
		this.options = new ArrayList<>();
	}

	/**
	 * La función encargada de retornar el listado de las opciones que tenemos dentro de este instrumento.
	 * 
	 * @return El listado de opciones que tenemos dentro de este elemento.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "choiceInstrument", orphanRemoval = true)
	public List<Option> getOptions() {
		return this.options;
	}

	/**
	 * La función encargada de cargar el listado de las opciones que tenemos dentro de este instrumento.
	 * 
	 * @param options
	 *            El listado de opciones que vamos a cargar.
	 */
	public void setOptions(List<Option> options) {
		this.options = options;
	}
}
