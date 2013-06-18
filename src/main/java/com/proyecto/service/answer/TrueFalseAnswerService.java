package com.proyecto.service.answer;

import com.proyecto.dao.answer.TrueFalseAnswerDao;
import com.proyecto.model.answer.TrueFalseAnswer;

/**
 * La interfaz que define los servicios para las respuestas booleanas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface TrueFalseAnswerService extends AnswerService<TrueFalseAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param trueFalseAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setTrueFalseAnswerDao(TrueFalseAnswerDao trueFalseAnswerDao);
}