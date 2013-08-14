package com.proyecto.dao.material.instrument;

import com.proyecto.model.material.instrument.ChoiceInstrument;

/**
 * La interfaz que define el DAO de los instrumentos formales objetivos de selecci�n que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento formal objetivo de selecci�n que vamos a manipular dentro del DAO.
 */
public interface ChoiceInstrumentDao<E extends ChoiceInstrument> extends ObjectiveActivityInstrumentDao<E> {
}