package com.proyecto.service.answer;

import com.proyecto.dao.answer.TextAnswerDao;
import com.proyecto.model.answer.TextAnswer;

/**
 * La interfaz que define los servicios para las respuestas textuales de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface TextAnswerService extends AnswerService<TextAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param textAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setTextAnswerDao(TextAnswerDao textAnswerDao);
}