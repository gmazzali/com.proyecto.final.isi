package com.proyecto.dao.instrument.impl;

import com.proyecto.dao.instrument.FormalInstrumentDao;
import com.proyecto.model.instrument.FormalInstrument;

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
}