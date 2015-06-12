package com.proyecto.dao.material.instrument;

import com.proyecto.dao.material.MaterialDao;
import com.proyecto.model.material.instrument.Instrument;

/**
 * La interfaz que define el DAO de los instrumentos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del DAO.
 */
public interface InstrumentDao<E extends Instrument> extends MaterialDao<E, Integer> {
}