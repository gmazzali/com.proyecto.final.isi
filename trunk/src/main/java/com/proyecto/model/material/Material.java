package com.proyecto.model.material;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.agent.Subject;

/**
 * La clase que representa un material de estudio dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <PK>
 *            La clase que nos permite identificar un elemento dentro de la base de datos.
 */
@Model
@MappedSuperclass
public class Material<PK extends Serializable> extends Entity<PK> {

	private static final long serialVersionUID = -2080313336152464288L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String SUBJECT = "subject";
	}

	/**
	 * La materia a la que pertenece este elemento.
	 */
	private Subject subject;

	/**
	 * El constructor por omisión.
	 */
	public Material() {
		super();
		this.subject = null;
	}

	/**
	 * La función que retorna la materia a la que pertenece este elemento.
	 * 
	 * @return La materia a la que pertenece este elemento.
	 */
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Subject.class)
	@JoinColumn(name = "ID_SUBJECT", referencedColumnName = "ID_SUBJECT", insertable = true, updatable = true, nullable = true)
	public Subject getSubject() {
		return this.subject;
	}

	/**
	 * La función encargada de cargar la materia a la que pertenece este elemento.
	 * 
	 * @param subject
	 *            La materia a la que pertenece este elemento.
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}