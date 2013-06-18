package com.proyecto.service.answer;

import com.proyecto.dao.answer.NumericAnswerDao;
import com.proyecto.model.answer.NumericAnswer;

/**
 * La interfaz que define los servicios para las respuestas numéricas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface NumericAnswerService extends AnswerService<NumericAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param numericAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setNumericAnswerDao(NumericAnswerDao numericAnswerDao);
}