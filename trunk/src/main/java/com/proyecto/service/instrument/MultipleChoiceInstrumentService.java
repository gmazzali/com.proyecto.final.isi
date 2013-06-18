package com.proyecto.service.instrument;

import com.proyecto.dao.instrument.MultipleChoiceInstrumentDao;
import com.proyecto.model.instrument.MultipleChoiceInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selección multiple que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface MultipleChoiceInstrumentService extends ChoiceInstrumentService<MultipleChoiceInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos formales objetivos de selección multiple.
	 * 
	 * @param multipleChoiceInstrumentDao
	 *            el DAO de los instrumentos formales objetivos de selección multiple.
	 */
	public void setMultipleChoiceInstrumentDao(MultipleChoiceInstrumentDao multipleChoiceInstrumentDao);
}