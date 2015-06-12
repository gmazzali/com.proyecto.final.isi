package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.MaterialDaoImpl;
import com.proyecto.dao.material.instrument.InstrumentDao;
import com.proyecto.model.material.instrument.Instrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del DAO.
 */
public abstract class InstrumentDaoImpl<E extends Instrument> extends MaterialDaoImpl<E, Integer> implements InstrumentDao<E> {

	private static final long serialVersionUID = 7075892586868301998L;
}