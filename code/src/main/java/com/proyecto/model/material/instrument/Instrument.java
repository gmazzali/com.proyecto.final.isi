package com.proyecto.model.material.instrument;

import java.io.IOException;
import java.io.OutputStream;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.Material;
import com.proyecto.model.material.instrument.type.impl.InstrumentTypeImpl;

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
public abstract class Instrument extends Material<Integer> {

	private static final long serialVersionUID = -932523909953824546L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Material.Attributes {
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
		this.description = null;
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Override
	public void print(OutputStream stream) {
		try {
			stream.write("-------------------- INSTRUMENT --------------------\n".getBytes());
			stream.write(("ID: " + this.id + "\n").getBytes());
			stream.write(("Subject: " + this.subject + "\n").getBytes());
			stream.write(("Descripci�n: " + this.description + "\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * La funci�n encargada de retornar el tipo de instrumento que corresponde con el elemento que esta dentro de este instrumento.
	 * 
	 * @return El tipo de instrumento de este elemento.
	 */
	@Transient
	public abstract InstrumentTypeImpl getInstrumentType();

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
