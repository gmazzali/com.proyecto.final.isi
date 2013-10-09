package com.proyecto.dao.material;

import java.io.Serializable;

import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.model.material.Material;

/**
 * La clase que va a implementar el DAO base para todos los DAOs de los materiales dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de material que vamos a manejar dentro del DAO.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase de material manejada.
 */
public abstract class MaterialDaoImpl<E extends Material<PK>, PK extends Serializable> extends ProyectoDaoImpl<E, PK> implements MaterialDao<E, PK> {

	private static final long serialVersionUID = 4766507864207673508L;
}