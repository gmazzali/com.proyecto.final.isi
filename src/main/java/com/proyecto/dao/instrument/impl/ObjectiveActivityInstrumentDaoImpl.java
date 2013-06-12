package com.proyecto.dao.instrument.impl;

import com.proyecto.dao.instrument.ObjectiveActivityInstrumentDao;
import com.proyecto.model.instrument.ObjectiveActivityInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo que vamos a manipular dentro del DAO.
 */
public abstract class ObjectiveActivityInstrumentDaoImpl<E extends ObjectiveActivityInstrument> extends FormalInstrumentDaoImpl<E> implements
		ObjectiveActivityInstrumentDao<E> {
}