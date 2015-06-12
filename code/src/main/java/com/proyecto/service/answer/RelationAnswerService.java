package com.proyecto.service.answer;

import com.proyecto.dao.answer.RelationAnswerDao;
import com.proyecto.model.answer.RelationAnswer;

/**
 * La interfaz que define los servicios para las respuestas relacionales de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface RelationAnswerService extends AnswerService<RelationAnswer> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param relationAnswerDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setRelationAnswerDao(RelationAnswerDao relationAnswerDao);
}