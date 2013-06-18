package com.proyecto.service.instrument;

import com.proyecto.dao.instrument.ExerciseInstrumentDao;
import com.proyecto.model.instrument.ExerciseInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simple para los ejercicios que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ExerciseInstrumentService extends SimpleInstrumentService<ExerciseInstrument> {

	/**
	 * La funci�n encargada de cargar el DAO de los instrumentos semiformales simple para los ejercicios.
	 * 
	 * @param exerciseInstrumentDao
	 *            el DAO de los instrumentos semiformales simple para los ejercicios.
	 */
	public void setExerciseInstrumentDao(ExerciseInstrumentDao exerciseInstrumentDao);
}