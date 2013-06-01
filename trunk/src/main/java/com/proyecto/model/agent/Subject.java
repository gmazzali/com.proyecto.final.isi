package com.proyecto.model.agent;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una materia en las que se inscriben los agentes del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@javax.persistence.Entity(name = "Subject")
@Table(name = "SUBJECTS")
public class Subject extends Entity<Integer> {

	private static final long serialVersionUID = 4055617195283157310L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String NAME = "name";
	}

	/**
	 * El nombre de la materia.
	 */
	private String name;

	/**
	 * El constructor de una materia.
	 */
	public Subject() {
		this.name = "";
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * La función encargada de retornar el id de una materia.
	 * 
	 * @return El identificador de una materia.
	 */
	@Id
	@Column(name = "ID_SUBJECT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función encargada de retornar el nombre de la materia.
	 * 
	 * @return El nombre de la materia.
	 */
	@Column(name = "NAME", columnDefinition = "varchar(100)", nullable = false)
	public String getName() {
		return this.name;
	}

	/**
	 * La función encargada de cargar el nombre de la materia.
	 * 
	 * @param name
	 *            El nombre de la materia.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
