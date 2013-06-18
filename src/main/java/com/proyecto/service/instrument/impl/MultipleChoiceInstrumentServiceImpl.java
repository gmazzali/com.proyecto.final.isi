package com.proyecto.service.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.instrument.MultipleChoiceInstrumentDao;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.service.instrument.MultipleChoiceInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selección multiple que
 * tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class MultipleChoiceInstrumentServiceImpl extends ChoiceInstrumentServiceImpl<MultipleChoiceInstrument> implements
		MultipleChoiceInstrumentService {

	@Override
	@Autowired
	public void setMultipleChoiceInstrumentDao(MultipleChoiceInstrumentDao multipleChoiceInstrumentDao) {
		this.setDao(multipleChoiceInstrumentDao);
	}

	@Override
	public void validate(MultipleChoiceInstrument entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de los instrumentos de selección multiple.
	}
}