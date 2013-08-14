package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.ExerciseInstrumentDao;
import com.proyecto.model.material.instrument.ExerciseInstrument;
import com.proyecto.service.material.instrument.ExerciseInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simples para los ejercicios que
 * tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class ExerciseInstrumentServiceImpl extends SimpleInstrumentServiceImpl<ExerciseInstrument> implements ExerciseInstrumentService {

	@Override
	@Autowired
	public void setExerciseInstrumentDao(ExerciseInstrumentDao exerciseInstrumentDao) {
		this.setDao(exerciseInstrumentDao);
	}

	@Override
	public void validate(ExerciseInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos semiformales simples para los ejercicios.
	}
}