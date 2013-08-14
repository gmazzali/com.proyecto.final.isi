package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.CompletionInstrumentDao;
import com.proyecto.model.material.instrument.CompletionInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos para completar que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CompletionInstrumentService extends ObjectiveActivityInstrumentService<CompletionInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos formales objetivos para completar.
	 * 
	 * @param completionInstrumentDao
	 *            el DAO de los instrumentos formales objetivos para completar.
	 */
	public void setCompletionInstrumentDao(CompletionInstrumentDao completionInstrumentDao);
}