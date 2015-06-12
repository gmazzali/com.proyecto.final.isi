package com.proyecto.service.material.instrument.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.instrument.PortfolioInstrumentDao;
import com.proyecto.model.material.instrument.PortfolioInstrument;
import com.proyecto.service.material.instrument.PortfolioInstrumentService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales compuestos de portfolio que
 * tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class PortfolioInstrumentServiceImpl extends CompositeInstrumentServiceImpl<PortfolioInstrument> implements PortfolioInstrumentService {

	@Override
	@Autowired
	public void setPortfolioInstrumentDao(PortfolioInstrumentDao portfolioInstrumentDao) {
		this.setDao(portfolioInstrumentDao);
	}

	@Override
	public void validate(PortfolioInstrument entity) throws CheckedException {
		// TODO gmazzali Falta lo del validador de los instrumentos semiformales compuestos de portfolio.
	}
}