package com.proyecto.model.material.activity;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.Material;
import com.proyecto.model.material.activity.type.impl.ActivityTypeImpl;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La clase que nos permite definir las actividades que vamos a poder asignar a una evaluaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "ACTIVITIES")
@javax.persistence.Entity(name = "Activity")
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
		this.reactives = new ArrayList<Reactive>();
	}

	@Override
	public String toString() {
		return this.description;
	};

	@Override
	public void print(OutputStream stream) {
		try {
			stream.write("///////////////////// ACTIVITY /////////////////////\n".getBytes());
			stream.write(("ID: " + this.id + "\n").getBytes());
			stream.write(("Subject: " + this.subject + "\n").getBytes());
			stream.write(("Descripci�n: " + this.description + "\n").getBytes());

			for (Reactive reactive : this.reactives) {
				reactive.print(stream);
			}
			stream.write("\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Id
	@Column(name = "ID_ACTIVITY")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La funci�n encargada de cargar un reactivo dentro de esta actividad.
	 * 
	 * @param reactive
	 *            El reactivo que queremos almacenar dentro de esta actividad.
	 */
	public void addReactive(Reactive reactive) {
		if (this.reactives != null && reactive != null) {
			this.reactives.add(reactive);
		}
	}

	/**
	 * La funci�n encargada de cargar un listado de reactivos dentro de esta actividad.
	 * 
	 * @param reactives
	 *            El listado de reactivos que tenemos dentro de esta actividad.
	 */
	public void addAllReactives(List<Reactive> reactives) {
		if (this.reactives != null && reactives != null) {
			this.reactives.addAll(reactives);
		}
	}

	/**
	 * La funci�n encargada de quitar un reactivo dentro de los que tenemos dentro de esta actividad.
	 * 
	 * @param reactive
	 *            El reactivo que queremos quitar dentro de esta actividad.
	 */
	public void removeReactive(Reactive reactive) {
		if (this.reactives != null && reactive != null) {
			this.reactives.remove(reactive);
		}
	}

	/**
	 * La funci�n encargada de quitar un listado de reactivos de los que tenemos dentro de esta actividad.
	 * 
	 * @param reactives
	 *            El listado de reactivos que queremos quitar dentro de esta actividad.
	 */
	public void removeAllReactive(List<Reactive> reactives) {
		if (this.reactives != null && reactives != null) {
			this.reactives.removeAll(reactives);
		}
	}

	/**
	 * La funci�n encargada de vaciar el contenido de los reactivos que tenemos dentro de esta actividad.
	 */
	public void clearReactive() {
		if (this.reactives != null) {
			this.reactives.clear();
		}
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
	 * La funci�n que nos permite saber a que tipo de actividad pertenece este elemento.
	 * 
	 * @return El tipo de actividad a la que pertenece este elemento. En caso de que todav�a no tenga un reactivo asociado, retornamos un valor nulo.
	 */
	@Transient
	public ActivityTypeImpl getActivityType() {
		if (this.reactives != null && !this.reactives.isEmpty()) {
			switch (this.reactives.get(0).getReactiveType()) {
				case FORMAL:
					return ActivityTypeImpl.FORMAL;

				case SEMIFORMAL:
					return ActivityTypeImpl.SEMIFORMAL;
			}
		}
		return null;
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