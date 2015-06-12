package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.EssayActivityAnswerDao;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.service.answer.EssayActivityAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas de los instrumentos de ensayos objetivos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class EssayActivityAnswerServiceImpl extends AnswerServiceImpl<EssayActivityAnswer> implements EssayActivityAnswerService {

	@Override
	@Autowired
	public void setEssayActivityAnswerDao(EssayActivityAnswerDao essayActivityAnswerDao) {
		this.setDao(essayActivityAnswerDao);
	}

	@Override
	public void validate(EssayActivityAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas de ensayos.
	}
}