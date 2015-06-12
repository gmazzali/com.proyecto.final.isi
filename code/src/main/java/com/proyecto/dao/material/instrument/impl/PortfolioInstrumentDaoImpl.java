package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.PortfolioInstrumentDao;
import com.proyecto.model.material.instrument.PortfolioInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales compuestos para un portfolio que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class PortfolioInstrumentDaoImpl extends CompositeInstrumentDaoImpl<PortfolioInstrument> implements PortfolioInstrumentDao {

	private static final long serialVersionUID = -65936273693168254L;
}