package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.instrument.EssayActivityInstrumentDao;
import com.proyecto.model.material.instrument.EssayActivityInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales de ensayos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal de ensayo que vamos a manipular dentro del DAO.
 */
public abstract class EssayActivityInstrumentDaoImpl<E extends EssayActivityInstrument> extends FormalInstrumentDaoImpl<E> implements
		EssayActivityInstrumentDao<E> {
}