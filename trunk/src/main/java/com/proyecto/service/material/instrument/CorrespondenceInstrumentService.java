package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.CorrespondenceInstrumentDao;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos para relacionar que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface CorrespondenceInstrumentService extends ObjectiveActivityInstrumentService<CorrespondenceInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos formales objetivos para relacionar.
	 * 
	 * @param correspondenceInstrumentDao
	 *            el DAO de los instrumentos formales objetivos para relacionar.
	 */
	public void setCorrespondenceInstrumentDao(CorrespondenceInstrumentDao correspondenceInstrumentDao);
}