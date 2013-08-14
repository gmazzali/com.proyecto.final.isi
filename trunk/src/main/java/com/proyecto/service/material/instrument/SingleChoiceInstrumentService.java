package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.SingleChoiceInstrumentDao;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales objetivos de selecci�n simple que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface SingleChoiceInstrumentService extends ChoiceInstrumentService<SingleChoiceInstrument> {

	/**
	 * La funci�n encargada de cargar el DAO de los instrumentos formales objetivos de selecci�n simple.
	 * 
	 * @param singleChoiceInstrumentDao
	 *            el DAO de los instrumentos formales objetivos de selecci�n simple.
	 */
	public void setSingleChoiceInstrumentDao(SingleChoiceInstrumentDao singleChoiceInstrumentDao);
}