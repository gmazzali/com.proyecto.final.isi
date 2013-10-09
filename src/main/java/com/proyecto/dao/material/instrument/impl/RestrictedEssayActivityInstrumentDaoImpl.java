package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.RestrictedEssayActivityInstrumentDao;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales de ensayos restringidos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class RestrictedEssayActivityInstrumentDaoImpl extends EssayActivityInstrumentDaoImpl<RestrictedEssayActivityInstrument> implements
		RestrictedEssayActivityInstrumentDao {

	private static final long serialVersionUID = -3689005349311397891L;
}