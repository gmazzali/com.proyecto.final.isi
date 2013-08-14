package com.proyecto.service.material.reactive;

import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.service.material.MaterialService;

/**
 * La interfaz que define los servicios para los reactivos que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
public interface ReactiveService extends MaterialService<Reactive, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param reactiveDao
	 *            El dao para el servicio de los reactivos.
	 */
	public void setReactiveDao(ReactiveDao reactiveDao);
}