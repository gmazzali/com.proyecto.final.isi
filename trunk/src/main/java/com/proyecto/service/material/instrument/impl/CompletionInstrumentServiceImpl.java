package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.CompletionInstrumentDao;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.service.material.instrument.CompletionInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos para completar que tenemos
 * en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class CompletionInstrumentServiceImpl extends ObjectiveActivityInstrumentServiceImpl<CompletionInstrument> implements
		CompletionInstrumentService {

	@Override
	@Autowired
	public void setCompletionInstrumentDao(CompletionInstrumentDao completionInstrumentDao) {
		this.setDao(completionInstrumentDao);
	}

	@Override
	public void validate(CompletionInstrument entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de los instrumentos para completar.
	}
}