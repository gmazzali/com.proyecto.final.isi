package com.proyecto.model.instrument;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que definimos para los distintos tipos de instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "INSTRUMENTS")
@javax.persistence.Entity(name = "Instrument")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Instrument extends Entity<Integer> {

	private static final long serialVersionUID = -932523909953824546L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String DESCRIPTION = "description";
	}

	/**
	 * La descripci�n del instrumento.
	 */
	protected String description;

	/**
	 * El constructor por omisi�n del instrumento.
	 */
	public Instrument() {
		super();
		this.description = "";
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Id
	@Column(name = "ID_INSTRUMENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La funci�n encargada de retornar la descripci�n del instrumento.
	 * 
	 * @return La descripci�n del instrumento.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "text", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n encargada de cargar la descripci�n del instrumento.
	 * 
	 * @param description
	 *            La descripci�n del instrumento que vamos a utilizar.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
