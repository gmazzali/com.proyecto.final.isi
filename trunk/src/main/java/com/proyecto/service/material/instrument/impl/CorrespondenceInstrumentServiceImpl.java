package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.CorrespondenceInstrumentDao;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.service.material.instrument.CorrespondenceInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos para relacionar que tenemos
 * en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class CorrespondenceInstrumentServiceImpl extends ObjectiveActivityInstrumentServiceImpl<CorrespondenceInstrument> implements
		CorrespondenceInstrumentService {

	@Override
	@Autowired
	public void setCorrespondenceInstrumentDao(CorrespondenceInstrumentDao correspondenceInstrumentDao) {
		this.setDao(correspondenceInstrumentDao);
	}

	@Override
	public void validate(CorrespondenceInstrument entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de los instrumentos para relacionar.
	}
}