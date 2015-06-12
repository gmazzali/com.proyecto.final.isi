package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.CompletionAnswerDao;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.service.answer.CompletionAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas para completar de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class CompletionAnswerServiceImpl extends AnswerServiceImpl<CompletionAnswer> implements CompletionAnswerService {

	@Override
	@Autowired
	public void setCompletionAnswerDao(CompletionAnswerDao completionAnswerDao) {
		this.setDao(completionAnswerDao);
	}

	@Override
	public void validate(CompletionAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas numéricas.
	}
}