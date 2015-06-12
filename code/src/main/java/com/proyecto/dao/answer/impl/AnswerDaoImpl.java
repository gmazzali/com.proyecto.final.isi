package com.proyecto.dao.answer.impl;

import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.answer.AnswerDao;
import com.proyecto.model.answer.Answer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como respuestas.
 */
public abstract class AnswerDaoImpl<E extends Answer> extends ProyectoDaoImpl<E, Integer> implements AnswerDao<E> {

	private static final long serialVersionUID = 1820775275137022317L;
}