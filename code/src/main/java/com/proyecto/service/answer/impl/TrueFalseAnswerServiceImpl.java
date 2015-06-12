package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.TrueFalseAnswerDao;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.service.answer.TrueFalseAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas booleanas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class TrueFalseAnswerServiceImpl extends AnswerServiceImpl<TrueFalseAnswer> implements TrueFalseAnswerService {

	@Override
	@Autowired
	public void setTrueFalseAnswerDao(TrueFalseAnswerDao trueFalseAnswerDao) {
		this.setDao(trueFalseAnswerDao);
	}

	@Override
	public void validate(TrueFalseAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas booleanas.
	}
}