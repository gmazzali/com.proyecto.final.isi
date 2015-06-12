package com.proyecto.service.option.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.option.DistractorDao;
import com.proyecto.model.option.Distractor;
import com.proyecto.service.option.DistractorService;

/**
 * La clase que implementa la interfaz que define los servicios para las opciones falsas de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class DistractorServiceImpl extends OptionServiceImpl<Distractor> implements DistractorService {

	@Override
	@Autowired
	public void setDistractorDao(DistractorDao distractorDao) {
		this.setDao(distractorDao);
	}

	@Override
	public void validate(Distractor entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las opciones falsas.
	}
}