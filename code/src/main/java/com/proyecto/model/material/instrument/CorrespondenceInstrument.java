package com.proyecto.model.material.instrument;

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
 * La clase que permite definir el instrumento que permite definir una relaci�n de respuestas.
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
		static final String RELATIONS = "relations";
	}

	/**
	 * El listado de las relaciones que tenemos dentro de este instrumento.
	 */
	private List<RelationAnswer> relations;

	/**
	 * El constructor por omisi�n de este instrumento.
	 */
	public CorrespondenceInstrument() {
		super();
		this.relations = new ArrayList<RelationAnswer>();
	}

	/**
	 * La funci�n encargada de agregar una nueva relaci�n dentro de este instrumento.
	 * 
	 * @param relationAnswer
	 *            La relaci�n que vamos a agregar dentro de este instrumento.
	 */
	public void addRelation(RelationAnswer relationAnswer) {
		relationAnswer.setInstrument(this);
		this.relations.add(relationAnswer);
	}

	/**
	 * La funci�n encargada de agregar un listado de relaciones dentro de las que ya tenemos dentro del instrumento.
	 * 
	 * @param relationAnswers
	 *            El listado de relaciones que vamos a agregar dentro del que ya tenemos.
	 */
	public void addAllRelations(List<RelationAnswer> relationAnswers) {
		for (RelationAnswer relationAnswer : relationAnswers) {
			this.addRelation(relationAnswer);
		}
	}

	/**
	 * La funci�n encargada de quitar una relaci�n de el listado de relaciones que tiene este instrumento.
	 * 
	 * @param relationAnswer
	 *            La relaci�n que quiere quitarse de este instrumento.
	 */
	public void removeRelation(RelationAnswer relationAnswer) {
		this.relations.remove(relationAnswer);
	}

	/**
	 * La funci�n encargada de borrar todas las relaciones que se encuentra dentro de este instrumento y que est�n en el listado de relaciones a
	 * borrar.
	 * 
	 * @param relationAnswers
	 *            El listado de las relaciones a borrar.
	 */
	public void removeAllRelations(List<RelationAnswer> relationAnswers) {
		for (RelationAnswer relationAnswer : relationAnswers) {
			this.removeRelation(relationAnswer);
		}
	}

	/**
	 * la funci�n encargada de vaciar el listado de las relaciones que tenemos dentro de este instrumento.
	 */
	public void clearRelations() {
		this.relations.clear();
	}

	/**
	 * La funci�n que retorna el listado de las relaciones que tenemos dentro de este instrumento.
	 * 
	 * @return El listado de las relaciones que tenemos dentro de este instrumento.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instrument", orphanRemoval = true)
	public List<RelationAnswer> getRelations() {
		return this.relations;
	}

	/**
	 * La funci�n que permite cargar el listado de las relaciones que tenemos para este instrumento.
	 * 
	 * @param relations
	 *            El listado de las relaciones que cargamos a este instrumento.
	 */
	public void setRelations(List<RelationAnswer> relations) {
		this.relations = relations;
	}
}
