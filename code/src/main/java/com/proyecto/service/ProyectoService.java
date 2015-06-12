package com.proyecto.service;

import java.io.Serializable;

import com.common.util.model.Persistence;
import com.common.util.service.GenericService;

/**
 * La interfaz que define el servicio base para los demás servicios del sistema de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase que vamos a manejar dentro del servicio.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public interface ProyectoService<E extends Persistence<PK>, PK extends Serializable> extends GenericService<E, PK> {
}
