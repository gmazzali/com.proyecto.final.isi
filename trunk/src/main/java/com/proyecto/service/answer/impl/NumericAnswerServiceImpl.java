package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.NumericAnswerDao;
import com.proyecto.model.answer.NumericAnswer;
import com.proyecto.service.answer.NumericAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas numéricas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class NumericAnswerServiceImpl extends AnswerServiceImpl<NumericAnswer> implements NumericAnswerService {

	@Override
	@Autowired
	public void setNumericAnswerDao(NumericAnswerDao numericAnswerDao) {
		this.setDao(numericAnswerDao);
	}

	@Override
	public void validate(NumericAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas numéricas.
	}
}