package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.instrument.FormalInstrumentDao;
import com.proyecto.model.material.instrument.FormalInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal que vamos a manipular dentro del DAO.
 */
public abstract class FormalInstrumentDaoImpl<E extends FormalInstrument> extends InstrumentDaoImpl<E> implements FormalInstrumentDao<E> {

	private static final long serialVersionUID = -8644053171619098014L;
}