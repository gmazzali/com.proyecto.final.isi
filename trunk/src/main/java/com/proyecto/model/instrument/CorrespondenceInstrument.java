package com.proyecto.model.instrument;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.RelationAnswer;

/**
 * La clase que permite definir el instrumento que permite definir una relación de respuestas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "CORRESPONDENCE_INSTRUMENTS")
@javax.persistence.Entity(name = "CorrespondenceInstrument")
public class CorrespondenceInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = -6845704597580790861L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
		static final String CORRESPONDENCES = "relations";
	}

	/**
	 * El listado de las relaciones que tenemos dentro de este instrumento.
	 */
	private List<RelationAnswer> relations;

	/**
	 * El constructor por omisión de este instrumento.
	 */
	public CorrespondenceInstrument() {
		this.relations = new ArrayList<>();
	}

	/**
	 * La función que retorna el listado de las relaciones que tenemos dentro de este instrumento.
	 * 
	 * @return El listado de las relaciones que tenemos dentro de este instrumento.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "correspondenceInstrument", orphanRemoval = true)
	public List<RelationAnswer> getRelations() {
		return this.relations;
	}

	/**
	 * La función que permite cargar el listado de las relaciones que tenemos para este instrumento.
	 * 
	 * @param relations
	 *            El listado de las relaciones que cargamos a este instrumento.
	 */
	public void setRelations(List<RelationAnswer> relations) {
		this.relations = relations;
	}
}
