package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.instrument.SemiFormalInstrumentDao;
import com.proyecto.model.material.instrument.SemiFormalInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal que vamos a manipular dentro del DAO.
 */
public abstract class SemiFormalInstrumentDaoImpl<E extends SemiFormalInstrument> extends InstrumentDaoImpl<E> implements SemiFormalInstrumentDao<E> {
}