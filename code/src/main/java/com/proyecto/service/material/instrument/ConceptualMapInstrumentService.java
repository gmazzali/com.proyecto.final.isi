package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.ConceptualMapInstrumentDao;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simple para los mapas conceptuales que tenemos en el
 * sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ConceptualMapInstrumentService extends SimpleInstrumentService<ConceptualMapInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos semiformales simple para los mapas conceptuales.
	 * 
	 * @param conceptualMapInstrumentDao
	 *            el DAO de los instrumentos semiformales simple para los mapas conceptuales.
	 */
	public void setConceptualMapInstrumentDao(ConceptualMapInstrumentDao conceptualMapInstrumentDao);
}