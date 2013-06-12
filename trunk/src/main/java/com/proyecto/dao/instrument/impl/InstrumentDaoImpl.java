package com.proyecto.dao.instrument.impl;

import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.instrument.InstrumentDao;
import com.proyecto.model.instrument.Instrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del DAO.
 */
public abstract class InstrumentDaoImpl<E extends Instrument> extends ProyectoDaoImpl<E, Integer> implements InstrumentDao<E> {
}