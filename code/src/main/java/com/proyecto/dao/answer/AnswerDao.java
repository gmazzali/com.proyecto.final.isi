package com.proyecto.dao.answer;

import com.proyecto.dao.ProyectoDao;
import com.proyecto.model.answer.Answer;

/**
 * La interfaz que define el DAO para las respuestas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como respuestas.
 */
public interface AnswerDao<E extends Answer> extends ProyectoDao<E, Integer> {
}