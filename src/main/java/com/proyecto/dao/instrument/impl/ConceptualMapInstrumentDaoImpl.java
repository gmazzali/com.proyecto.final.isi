package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.ConceptualMapInstrumentDao;
import com.proyecto.model.instrument.ConceptualMapInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales simples para los mapas conceptuales que tenemos dentro del
 * sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class ConceptualMapInstrumentDaoImpl extends SimpleInstrumentDaoImpl<ConceptualMapInstrument> implements ConceptualMapInstrumentDao {
}