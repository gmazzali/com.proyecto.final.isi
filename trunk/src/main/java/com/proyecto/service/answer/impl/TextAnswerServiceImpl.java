package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.TextAnswerDao;
import com.proyecto.model.answer.TextAnswer;
import com.proyecto.service.answer.TextAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas textuales de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class TextAnswerServiceImpl extends AnswerServiceImpl<TextAnswer> implements TextAnswerService {

	@Override
	@Autowired
	public void setTextAnswerDao(TextAnswerDao textAnswerDao) {
		this.setDao(textAnswerDao);
	}

	@Override
	public void validate(TextAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas textuales.
	}
}