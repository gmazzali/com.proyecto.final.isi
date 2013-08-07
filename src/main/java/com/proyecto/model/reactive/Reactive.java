package com.proyecto.model.reactive;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.OneToOne;

/**
 * La clase que define el reactivo dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "REACTIVES")
@javax.persistence.Entity(name = "Reactive")
public class Reactive extends Entity<Integer> {

	private static final long serialVersionUID = -7668405363416480227L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
		static final String DESCRIPTION = "description";
		static final String INSTRUMENT = "instrument";
	}

	/**
	 * La descripci�n del tipo de reactivo.
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
	 * La funci�n que retorna la descripci�n del reactivo.
	 * 
	 * @return La descripci�n del reactivo.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "varchar(100)", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n que permite recuperar el instrumento que tenemos dentro de
	 * este reactivo.
	 * 
	 * @return El instrumento que tenemos dentro de este reactivo.
	 */
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "reactive", targetEntity = Instrument.class)
	public Instrument getInstrument() {
		return instrument;
	}

	/**
	 * La funci�n que setea la descripci�n del reactivo.
	 * 
	 * @param description
	 *            La descripci�n del reactivo.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n encargada de cargar dentro un instrumento dentro del reactivo.
	 * 
	 * @param instrument
	 *            El instrumento que vamos a cargar dentro de este reactivo.
	 */
	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
}