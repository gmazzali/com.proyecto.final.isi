package com.proyecto.service.answer.impl;

import com.proyecto.model.answer.Answer;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.answer.AnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como respuestas.
 */
public abstract class AnswerServiceImpl<E extends Answer> extends ProyectoServiceImpl<E, Integer> implements AnswerService<E> {
}