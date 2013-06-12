package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.UnrestrictedEssayActivityInstrumentDao;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales de ensayos no restringidos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class UnrestrictedEssayActivityInstrumentDaoImpl extends EssayActivityInstrumentDaoImpl<UnrestrictedEssayActivityInstrument> implements
		UnrestrictedEssayActivityInstrumentDao {
}