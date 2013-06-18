package com.proyecto.service.answer;

import com.proyecto.model.answer.Answer;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios para las respuestas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a utilizar como respuestas.
 */
public interface AnswerService<E extends Answer<?>> extends ProyectoService<E, Integer> {
}