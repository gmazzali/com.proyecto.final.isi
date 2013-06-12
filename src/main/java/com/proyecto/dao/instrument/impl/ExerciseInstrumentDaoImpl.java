package com.proyecto.dao.instrument.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.instrument.ExerciseInstrumentDao;
import com.proyecto.model.instrument.ExerciseInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales simples para los ejercicios que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class ExerciseInstrumentDaoImpl extends SimpleInstrumentDaoImpl<ExerciseInstrument> implements ExerciseInstrumentDao {
}