package com.proyecto.service.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.instrument.EssayInstrumentDao;
import com.proyecto.model.instrument.EssayInstrument;
import com.proyecto.service.instrument.EssayInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simples para los ensayos que
 * tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class EssayInstrumentServiceImpl extends SimpleInstrumentServiceImpl<EssayInstrument> implements EssayInstrumentService {

	@Override
	@Autowired
	public void setEssayInstrumentDao(EssayInstrumentDao essayInstrumentDao) {
		this.setDao(essayInstrumentDao);
	}

	@Override
	public void validate(EssayInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos semiformales simples para los ensayos.
	}
}