package com.proyecto.dao.material.instrument.impl;

import com.proyecto.dao.material.instrument.ChoiceInstrumentDao;
import com.proyecto.model.material.instrument.ChoiceInstrument;

/**
 * La clase que implementa la interfaz que define el DAO de los instrumentos formales objetivos de selección que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo de selección que vamos a manipular dentro del DAO.
 */
public abstract class ChoiceInstrumentDaoImpl<E extends ChoiceInstrument> extends ObjectiveActivityInstrumentDaoImpl<E> implements
		ChoiceInstrumentDao<E> {

	private static final long serialVersionUID = 8385922760331662286L;
}
