package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.ExerciseInstrumentDao;
import com.proyecto.model.material.instrument.ExerciseInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simple para los ejercicios que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ExerciseInstrumentService extends SimpleInstrumentService<ExerciseInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos semiformales simple para los ejercicios.
	 * 
	 * @param exerciseInstrumentDao
	 *            el DAO de los instrumentos semiformales simple para los ejercicios.
	 */
	public void setExerciseInstrumentDao(ExerciseInstrumentDao exerciseInstrumentDao);
}