package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.CompletionInstrumentDao;
import com.proyecto.model.instrument.CompletionInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos para completar que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class CompletionInstrumentDaoImpl extends ObjectiveActivityInstrumentDaoImpl<CompletionInstrument> implements CompletionInstrumentDao {
}