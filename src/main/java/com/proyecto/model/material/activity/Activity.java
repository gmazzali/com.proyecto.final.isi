package com.proyecto.model.material.activity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.common.util.model.Entity;
import com.proyecto.model.material.Material;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La clase que nos permite definir las actividades que vamos a poder asignar a una evaluaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Activity extends Material<Integer> {

	private static final long serialVersionUID = 7010944735827120774L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Material.Attributes {
		static final String DESCRIPTION = "description";
		static final String REACTIVES = "reactives";
	}

	/**
	 * La descripci�n de la actividad.
	 */
	private String description;

	/**
	 * El listado de los reactivos de la actividad.
	 */
	private List<Reactive> reactives;

	/**
	 * El constructor por omisi�n.
	 */
	public Activity() {
		super();
		this.description = null;
		this.reactives = new ArrayList<>();
	}

	/**
	 * La funci�n encargada de retornar la descripci�n de esta actividad.
	 * 
	 * @return La descripci�n de la actividad.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "text", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n encargada de retornar el listado de reactivos que pertenece a esta actividad.
	 * 
	 * @return El listado de reactivos que pertenece a esta actividad.
	 */
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Reactive.class)
	@JoinTable(name = "REACTIVES_IN_ACTIVITIES", joinColumns = { @JoinColumn(name = "ID_ACTIVITY") }, inverseJoinColumns = { @JoinColumn(name = "ID_REACTIVE") })
	public List<Reactive> getReactives() {
		return this.reactives;
	}

	/**
	 * La funci�n encargada de cargar la descripci�n de la actividad.
	 * 
	 * @param description
	 *            La descripci�n de la actividad.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n encargada de cargar el listado de reactivos que tenemos dentro de esta actividad.
	 * 
	 * @param reactives
	 *            El listado de reactivos de esta actividad.
	 */
	public void setReactives(List<Reactive> reactives) {
		this.reactives = reactives;
	}
}