package com.proyecto.service.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.instrument.UnrestrictedEssayActivityInstrumentDao;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.service.instrument.UnrestrictedEssayActivityInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos no restringidos que tenemos
 * en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class UnrestrictedEssayActivityInstrumentServiceImpl extends EssayActivityInstrumentServiceImpl<UnrestrictedEssayActivityInstrument> implements
		UnrestrictedEssayActivityInstrumentService {

	@Override
	@Autowired
	public void setUnrestrictedEssayActivityInstrumentDao(UnrestrictedEssayActivityInstrumentDao unrestrictedEssayActivityInstrumentDao) {
		this.setDao(unrestrictedEssayActivityInstrumentDao);
	}

	@Override
	public void validate(UnrestrictedEssayActivityInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos formales de ensayos no restringidos.
	}
}