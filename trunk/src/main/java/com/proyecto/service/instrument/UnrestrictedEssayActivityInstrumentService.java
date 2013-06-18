package com.proyecto.service.instrument;

import com.proyecto.dao.instrument.UnrestrictedEssayActivityInstrumentDao;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos formales de ensayos no restringidos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayo no restringido que vamos a manipular dentro del servicio.
 */
public interface UnrestrictedEssayActivityInstrumentService extends EssayActivityInstrumentService<UnrestrictedEssayActivityInstrument> {

	/**
	 * La función encargada de cargar el DAO de los instrumentos formales de ensayos no restringidos.
	 * 
	 * @param unrestrictedEssayActivityInstrumentDao
	 *            el DAO de los instrumentos formales de ensayos no restringidos.
	 */
	public void setUnrestrictedEssayActivityInstrumentDao(UnrestrictedEssayActivityInstrumentDao unrestrictedEssayActivityInstrumentDao);
}