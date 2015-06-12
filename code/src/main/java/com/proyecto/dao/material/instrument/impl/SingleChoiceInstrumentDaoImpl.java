package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.SingleChoiceInstrumentDao;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos de selección simple que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class SingleChoiceInstrumentDaoImpl extends ChoiceInstrumentDaoImpl<SingleChoiceInstrument> implements SingleChoiceInstrumentDao {

	private static final long serialVersionUID = -1811510766582540535L;
}