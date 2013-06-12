package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.PortfolioInstrumentDao;
import com.proyecto.model.instrument.PortfolioInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales compuestos para un portfolio que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class PortfolioInstrumentDaoImpl extends CompositeInstrumentDaoImpl<PortfolioInstrument> implements PortfolioInstrumentDao {
}