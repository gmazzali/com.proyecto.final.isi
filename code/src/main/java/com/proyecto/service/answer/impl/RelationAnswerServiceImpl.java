package com.proyecto.service.answer.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.answer.RelationAnswerDao;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.service.answer.RelationAnswerService;

/**
 * La clase que implementa la interfaz que define los servicios para las respuestas de los instrumentos de relación del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RelationAnswerServiceImpl extends AnswerServiceImpl<RelationAnswer> implements RelationAnswerService {

	@Override
	@Autowired
	public void setRelationAnswerDao(RelationAnswerDao relationAnswerDao) {
		this.setDao(relationAnswerDao);
	}

	@Override
	public void validate(RelationAnswer entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las respuestas relacionales.
	}
}