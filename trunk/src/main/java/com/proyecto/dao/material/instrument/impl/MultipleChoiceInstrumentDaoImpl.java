package com.proyecto.dao.material.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.instrument.MultipleChoiceInstrumentDao;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos de selección multiple que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class MultipleChoiceInstrumentDaoImpl extends ChoiceInstrumentDaoImpl<MultipleChoiceInstrument> implements MultipleChoiceInstrumentDao {

	private static final long serialVersionUID = -3850024648319679879L;
}