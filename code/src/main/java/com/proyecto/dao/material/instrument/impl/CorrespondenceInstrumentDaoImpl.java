package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.CorrespondenceInstrumentDao;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos para las correspondencias que tenemos dentro del
 * sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class CorrespondenceInstrumentDaoImpl extends ObjectiveActivityInstrumentDaoImpl<CorrespondenceInstrument> implements
		CorrespondenceInstrumentDao {

	private static final long serialVersionUID = -4018479378326155859L;
}