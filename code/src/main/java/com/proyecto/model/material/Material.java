package com.proyecto.model.material;

import java.io.OutputStream;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public abstract class Material<PK extends Serializable> extends Entity<PK> {

	private static final long serialVersionUID = -2080313336152464288L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String SUBJECT = "subject";
		static final String ACTIVE = "active";
	}

	/**
	 * La materia a la que pertenece este elemento.
	 */
	protected Subject subject;
	/**
	 * El estado booleano que nos indica si el material esta activo.
	 */
	protected Boolean active;

	/**
	 * El constructor por omisión.
	 */
	public Material() {
		super();
		this.subject = null;
		this.active = true;
	}

	/**
	 * La función encargada de imprimir el contenido del material en la salida que recibimos.
	 * 
	 * @param stream
	 *            El objeto de salida.
	 */
	public abstract void print(OutputStream stream);

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
	 * La función encargada de indicar el estado del material dentro del sistema.
	 * 
	 * @return El estado del material dentro del sistema.
	 */
	@Column(name = "ACTIVE", columnDefinition = "bool", nullable = false)
	public Boolean getActive() {
		return this.active;
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

	/**
	 * La función encargada cargar dentro del material su estado.
	 * 
	 * @param active
	 *            El estado que vamos a cargarle dentro del material.
	 */
	public void setActive(Boolean active) {
		this.active = active;
	}
}