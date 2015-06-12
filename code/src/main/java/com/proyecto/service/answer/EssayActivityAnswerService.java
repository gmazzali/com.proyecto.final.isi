package com.proyecto.service.answer;

import com.proyecto.dao.answer.EssayActivityAnswerDao;
import com.proyecto.model.answer.EssayActivityAnswer;

/**
 * La interfaz que define los servicios para las respuestas de los instrumentos de ensayos objetivos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface EssayActivityAnswerService extends AnswerService<EssayActivityAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param essayActivityAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setEssayActivityAnswerDao(EssayActivityAnswerDao essayActivityAnswerDao);
}