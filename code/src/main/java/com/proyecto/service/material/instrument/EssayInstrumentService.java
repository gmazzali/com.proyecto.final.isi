package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.EssayInstrumentDao;
import com.proyecto.model.material.instrument.EssayInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos semiformales simple para los ensayos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface EssayInstrumentService extends SimpleInstrumentService<EssayInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos semiformales simple para los ensayos.
	 * 
	 * @param essayInstrumentDao
	 *            el DAO de los instrumentos semiformales simple para los ensayos.
	 */
	public void setEssayInstrumentDao(EssayInstrumentDao essayInstrumentDao);
}