package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.EssayInstrumentDao;
import com.proyecto.model.material.instrument.EssayInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales simples para los ensayos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class EssayInstrumentDaoImpl extends SimpleInstrumentDaoImpl<EssayInstrument> implements EssayInstrumentDao {

	private static final long serialVersionUID = 1769320604964327302L;
}