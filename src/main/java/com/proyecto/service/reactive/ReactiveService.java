package com.proyecto.service.reactive;

import com.proyecto.dao.reactive.ReactiveDao;
import com.proyecto.model.reactive.Reactive;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios para los reactivos que tenemos dentro del sistema.
 * 
 * @version 1.0
 */
public interface ReactiveService extends ProyectoService<Reactive, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param reactiveDao
	 *            El dao para el servicio de los reactivos.
	 */
	public void setReactiveDao(ReactiveDao reactiveDao);
}