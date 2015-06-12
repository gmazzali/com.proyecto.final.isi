package com.proyecto.dao.material;

import java.io.Serializable;

import com.proyecto.dao.ProyectoDao;
import com.proyecto.model.material.Material;

/**
 * La interfaz que define el DAO base para los DAOs de los materiales dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a manejar dentro del DAO.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public interface MaterialDao<E extends Material<PK>, PK extends Serializable> extends ProyectoDao<E, PK> {
}