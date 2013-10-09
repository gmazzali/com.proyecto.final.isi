package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.CompletionInstrumentDao;
import com.proyecto.model.material.instrument.CompletionInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos para completar que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class CompletionInstrumentDaoImpl extends ObjectiveActivityInstrumentDaoImpl<CompletionInstrument> implements CompletionInstrumentDao {

	private static final long serialVersionUID = -3772130630835036873L;
}