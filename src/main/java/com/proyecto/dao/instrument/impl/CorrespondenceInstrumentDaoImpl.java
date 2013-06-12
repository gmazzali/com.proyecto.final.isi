package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.CorrespondenceInstrumentDao;
import com.proyecto.model.instrument.CorrespondenceInstrument;

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
}