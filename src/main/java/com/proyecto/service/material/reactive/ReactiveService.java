package com.proyecto.service.material.reactive;

import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.material.instrument.Instrument;
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

	/**
	 * La función encargada de recuperar los reactivos que hacen uso de un instrumento en particular.
	 * 
	 * @param instrument
	 *            El instrumento que vamos a utilizar para recuperar todos los reactivos que lo usan.
	 * @return El listado de los reactivos que hacen uso de ese instrumento, en caso de que el instrumento recibido sea nulo, retornamos una lista
	 *         NULL.
	 * @throws CheckedException
	 *             En caso de que ocurra un error a la hora de recuperar los reactivos del instrumento.
	 */
	public List<Reactive> findByInstrument(Instrument instrument) throws CheckedException;
}