package com.proyecto.dao.instrument.impl;

import com.proyecto.dao.instrument.CompositeInstrumentDao;
import com.proyecto.model.instrument.CompositeInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos semiformales compuestos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal compuesto que vamos a manipular dentro del DAO.
 */
public abstract class CompositeInstrumentDaoImpl<E extends CompositeInstrument> extends SemiFormalInstrumentDaoImpl<E> implements
		CompositeInstrumentDao<E> {
}