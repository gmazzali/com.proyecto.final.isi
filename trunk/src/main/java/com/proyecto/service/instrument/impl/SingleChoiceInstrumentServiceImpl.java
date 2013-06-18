package com.proyecto.service.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.instrument.SingleChoiceInstrumentDao;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.service.instrument.SingleChoiceInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selección simple que
 * tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class SingleChoiceInstrumentServiceImpl extends ChoiceInstrumentServiceImpl<SingleChoiceInstrument> implements SingleChoiceInstrumentService {

	@Override
	@Autowired
	public void setSingleChoiceInstrumentDao(SingleChoiceInstrumentDao singleChoiceInstrumentDao) {
		this.setDao(singleChoiceInstrumentDao);
	}

	@Override
	public void validate(SingleChoiceInstrument entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de los instrumentos de selección simple.
	}
}