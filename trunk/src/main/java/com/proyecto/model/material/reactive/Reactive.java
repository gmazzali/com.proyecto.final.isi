package com.proyecto.model.material.reactive;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.Material;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.type.impl.ReactiveTypeImpl;

/**
 * La clase que define el reactivo dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "REACTIVES")
@javax.persistence.Entity(name = "Reactive")
public class Reactive extends Material<Integer> {

	private static final long serialVersionUID = -7668405363416480227L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Material.Attributes {
		static final String DESCRIPTION = "description";
		static final String INSTRUMENT = "instrument";
	}

	/**
	 * La descripción del tipo de reactivo.
	 */
	private String description;
	/**
	 * El instrumento asociado a este reactivo.
	 */
	private Instrument instrument;

	/**
	 * Constructor por default de un reactivo
	 */
	public Reactive() {
		super();
		this.description = null;
		this.instrument = null;
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Id
	@Column(name = "ID_REACTIVE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función que retorna la descripción del reactivo.
	 * 
	 * @return La descripción del reactivo.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(100)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función que permite recuperar el instrumento que tenemos dentro de este reactivo.
	 * 
	 * @return El instrumento que tenemos dentro de este reactivo.
	 */
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.EAGER, targetEntity = Instrument.class)
	@JoinColumn(name = "ID_INSTRUMENT", referencedColumnName = "ID_INSTRUMENT", insertable = true, updatable = true, nullable = false)
	public Instrument getInstrument() {
		return this.instrument;
	}

	/**
	 * La función encargada de retornar el tipo de reactivo de acuerdo al tipo de instrumento que tenemos almacenado dentro de este.
	 * 
	 * @return El tipo de reactivo de acuerdo al tipo de instrumento que tenemos dentro de este. En caso de que todavía no tenga un instrumento
	 *         asociado, retornamos un valor nulo.
	 */
	@Transient
	public ReactiveTypeImpl getReactiveType() {
		if (this.instrument != null) {
			switch (this.instrument.getInstrumentType()) {
				case FORMAL:
					return ReactiveTypeImpl.FORMAL;

				case SEMIFORMAL:
					return ReactiveTypeImpl.SEMIFORMAL;
			}
		}
		return null;
	}

	/**
	 * La función que carga la descripción del reactivo.
	 * 
	 * @param description
	 *            La descripción del reactivo.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La función encargada de cargar dentro un instrumento dentro del reactivo.
	 * 
	 * @param instrument
	 *            El instrumento que vamos a cargar dentro de este reactivo.
	 */
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}