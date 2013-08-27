package com.proyecto.service.material.reactive;

import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.material.reactive.type.ReactiveType;
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

	/**
	 * La función encargada de recuperar el listado de materiales de acuerdo a la asignatura que tenemos seleccionada dentro del sistema al momento de
	 * ingresar a la aplicación y que además cumplen con la condición de contener un conjunto de instrumentos dados por los tipos de reactivos.
	 * 
	 * @param subjectSelected
	 *            La materia que tenemos ingresada dentro del sistema, puede ser nula.
	 * @param reactiveTypes
	 *            El listado de los tipos de reactivos que vamos a verificar, en caso de que sea nulo, retornamos el listado de materiales completo.
	 * @return El listado de los materiales que corresponde con la asignatura que recibimos y que corresponde a un tipo del listado recibido, en caso
	 *         de ser nula se retornará todos los materiales que hay detallados dentro del sistema y que no tengan una asignatura asignada.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla al momento de recuperar los materiales desde la base de datos.
	 */
	public List<Reactive> findBySubject(Subject subjectSelected, List<ReactiveType> reactiveTypes) throws CheckedException;
}