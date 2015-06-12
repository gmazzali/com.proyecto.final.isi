package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.instrument.CompositeInstrumentDao;
import com.proyecto.model.material.instrument.CompositeInstrument;

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

	private static final long serialVersionUID = -4486638142332475456L;
}