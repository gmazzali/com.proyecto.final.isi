package com.proyecto.service.instrument;

import com.proyecto.dao.instrument.PortfolioInstrumentDao;
import com.proyecto.model.instrument.PortfolioInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales compuestos de portfolio que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface PortfolioInstrumentService extends CompositeInstrumentService<PortfolioInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos semiformales compuestos de portfolio.
	 * 
	 * @param portfolioInstrumentDao
	 *            el DAO de los instrumentos semiformales compuestos de portfolio.
	 */
	public void setPortfolioInstrumentDao(PortfolioInstrumentDao portfolioInstrumentDao);
}