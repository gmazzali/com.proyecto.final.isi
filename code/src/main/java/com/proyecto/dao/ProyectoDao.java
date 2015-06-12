package com.proyecto.dao;

import java.io.Serializable;

import com.common.util.dao.GenericDao;
import com.common.util.model.Persistence;

/**
 * La interfaz que define el DAO base para los demás DAOs del sistema de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a manejar dentro del DAO.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public interface ProyectoDao<E extends Persistence<PK>, PK extends Serializable> extends GenericDao<E, PK> {
}
