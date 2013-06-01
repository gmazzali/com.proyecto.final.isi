package com.proyecto.service;

import java.io.Serializable;

import com.common.util.model.Persistence;
import com.common.util.service.impl.GenericServiceImpl;

/**
 * La clase que va a implementar el servicio base para todos los servicio del sistema de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a manejar dentro del servicio.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public abstract class ProyectoServiceImpl<E extends Persistence<PK>, PK extends Serializable> extends GenericServiceImpl<E, PK> implements
		ProyectoService<E, PK> {
}
