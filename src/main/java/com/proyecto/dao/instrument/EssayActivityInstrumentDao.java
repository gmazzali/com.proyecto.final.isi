package com.proyecto.dao.instrument;

import com.proyecto.model.instrument.EssayActivityInstrument;

/**
 * La interfaz que define el DAO de los instrumentos formales de ensayos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayo que vamos a manipular dentro del DAO.
 */
public interface EssayActivityInstrumentDao<E extends EssayActivityInstrument> extends FormalInstrumentDao<E> {
}