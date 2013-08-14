package com.proyecto.service.material.instrument;

import com.proyecto.dao.material.instrument.RestrictedEssayActivityInstrumentDao;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos restringidos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayo restringido que vamos a manipular dentro del servicio.
 */
public interface RestrictedEssayActivityInstrumentService extends EssayActivityInstrumentService<RestrictedEssayActivityInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos formales de ensayos restringidos.
	 * 
	 * @param restrictedEssayActivityInstrumentDao
	 *            el DAO de los instrumentos formales de ensayos restringidos.
	 */
	public void setRestrictedEssayActivityInstrumentDao(RestrictedEssayActivityInstrumentDao restrictedEssayActivityInstrumentDao);
}