package com.proyecto.dao.instrument.impl;

import com.proyecto.dao.instrument.SimpleInstrumentDao;
import com.proyecto.model.instrument.SimpleInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales simples que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal simple que vamos a manipular dentro del DAO.
 */
public abstract class SimpleInstrumentDaoImpl<E extends SimpleInstrument> extends SemiFormalInstrumentDaoImpl<E> implements SimpleInstrumentDao<E> {
}