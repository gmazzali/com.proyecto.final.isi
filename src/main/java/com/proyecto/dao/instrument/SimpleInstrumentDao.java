package com.proyecto.dao.instrument;

import com.proyecto.model.instrument.SimpleInstrument;

/**
 * La interfaz que define el DAO de los instrumentos semiformales simples que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento semiformal simple que vamos a manipular dentro del DAO.
 */
public interface SimpleInstrumentDao<E extends SimpleInstrument> extends SemiFormalInstrumentDao<E> {
}