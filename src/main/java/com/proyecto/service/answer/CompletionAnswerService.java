package com.proyecto.service.answer;

import com.proyecto.dao.answer.CompletionAnswerDao;
import com.proyecto.model.answer.CompletionAnswer;

/**
 * La interfaz que define los servicios para las respuestas para completar de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CompletionAnswerService extends AnswerService<CompletionAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param completionAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setCompletionAnswerDao(CompletionAnswerDao completionAnswerDao);
}