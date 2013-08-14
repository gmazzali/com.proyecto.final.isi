package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.ConceptualMapInstrumentDao;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;
import com.proyecto.service.material.instrument.ConceptualMapInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simples para los mapas
 * conceptuales que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class ConceptualMapInstrumentServiceImpl extends SimpleInstrumentServiceImpl<ConceptualMapInstrument> implements
		ConceptualMapInstrumentService {

	@Override
	@Autowired
	public void setConceptualMapInstrumentDao(ConceptualMapInstrumentDao conceptualMapInstrumentDao) {
		this.setDao(conceptualMapInstrumentDao);
	}

	@Override
	public void validate(ConceptualMapInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos semiformales simples para los mapas conceptuales.
	}
}