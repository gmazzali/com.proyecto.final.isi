package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.UnrestrictedEssayActivityInstrumentDao;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales de ensayos no restringidos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class UnrestrictedEssayActivityInstrumentDaoImpl extends EssayActivityInstrumentDaoImpl<UnrestrictedEssayActivityInstrument> implements
		UnrestrictedEssayActivityInstrumentDao {

	private static final long serialVersionUID = 1342611511957164353L;
}