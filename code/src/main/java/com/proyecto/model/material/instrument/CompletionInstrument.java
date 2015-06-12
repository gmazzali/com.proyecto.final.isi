package com.proyecto.model.material.instrument;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.CompletionAnswer;

/**
 * La clase que permite definir el instrumento que permite completar una palabra en una frase.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "COMPLETION_INSTRUMENTS")
@javax.persistence.Entity(name = "CompletionInstrument")
public class CompletionInstrument extends ObjectiveActivityInstrument {

	private static final long serialVersionUID = -3676039584247522076L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends ObjectiveActivityInstrument.Attributes {
		static final String COMPLETES = "completes";
	}

	/**
	 * El listado de las respuestas que vamos a tener dentro de este instrumento.
	 */
	private List<CompletionAnswer> completes;

	/**
	 * El constructor por omisión de un instrumento para completar.
	 */
	public CompletionInstrument() {
		super();
		this.completes = new ArrayList<CompletionAnswer>();
	}

	/**
	 * La función encargada de agregar una nueva respuesta dentro de este instrumento.
	 * 
	 * @param completeAnswer
	 *            La respuesta que vamos a agregar dentro de este instrumento.
	 */
	public void addComplete(CompletionAnswer completeAnswer) {
		completeAnswer.setInstrument(this);
		this.completes.add(completeAnswer);
	}

	/**
	 * La función encargada de agregar un listado de respuestas dentro de las que ya tenemos dentro del instrumento.
	 * 
	 * @param completeAnswers
	 *            El listado de respuestas que vamos a agregar dentro del que ya tenemos.
	 */
	public void addAllCompletes(List<CompletionAnswer> completeAnswers) {
		for (CompletionAnswer completeAnswer : completeAnswers) {
			this.addComplete(completeAnswer);
		}
	}

	/**
	 * La función encargada de quitar una respuesta de el listado de respuestas que tiene este instrumento.
	 * 
	 * @param completeAnswer
	 *            La respuesta que quiere quitarse de este instrumento.
	 */
	public void removeComplete(CompletionAnswer completeAnswer) {
		this.completes.remove(completeAnswer);
	}

	/**
	 * La función encargada de borrar todas las respuestas que se encuentra dentro de este instrumento y que están en el listado de respuestas a
	 * borrar.
	 * 
	 * @param completeAnswers
	 *            El listado de las respuestas a borrar.
	 */
	public void removeAllCompletes(List<CompletionAnswer> completeAnswers) {
		for (CompletionAnswer completeAnswer : completeAnswers) {
			this.removeComplete(completeAnswer);
		}
	}

	/**
	 * la función encargada de vaciar el listado de las respuestas que tenemos dentro de este instrumento.
	 */
	public void clearCompletes() {
		this.completes.clear();
	}

	/**
	 * La función encargada de retornar el listado de las respuestas que corresponden con este instrumento.
	 * 
	 * @return El listado de las respuestas que corresponden con este instrumento.
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "instrument", targetEntity = CompletionAnswer.class, orphanRemoval = true)
	public List<CompletionAnswer> getAnswers() {
		return this.completes;
	}

	/**
	 * La función encargada de cargar el listado de las respuestas que corresponden con este instrumento.
	 * 
	 * @param answers
	 *            El listado de las respuestas que corresponden con este instrumento.
	 */
	public void setAnswers(List<CompletionAnswer> answers) {
		this.completes = answers;
	}
}
