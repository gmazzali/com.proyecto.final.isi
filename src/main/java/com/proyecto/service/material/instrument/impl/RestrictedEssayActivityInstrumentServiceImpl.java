package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.RestrictedEssayActivityInstrumentDao;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.service.material.instrument.RestrictedEssayActivityInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos restringidos que tenemos en
 * el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class RestrictedEssayActivityInstrumentServiceImpl extends EssayActivityInstrumentServiceImpl<RestrictedEssayActivityInstrument> implements
		RestrictedEssayActivityInstrumentService {

	@Override
	@Autowired
	public void setRestrictedEssayActivityInstrumentDao(RestrictedEssayActivityInstrumentDao restrictedEssayActivityInstrumentDao) {
		this.setDao(restrictedEssayActivityInstrumentDao);
	}

	@Override
	public void validate(RestrictedEssayActivityInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos formales de ensayos restringidos.
	}
}