package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.MultipleChoiceInstrumentDao;
import com.proyecto.model.instrument.MultipleChoiceInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos de selecci�n multiple que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class MultipleChoiceInstrumentDaoImpl extends ChoiceInstrumentDaoImpl<MultipleChoiceInstrument> implements MultipleChoiceInstrumentDao {
}