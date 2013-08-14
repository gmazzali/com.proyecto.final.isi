package com.proyecto.service.material;

import java.io.Serializable;
import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.Material;
import com.proyecto.service.ProyectoService;

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
public interface MaterialService<E extends Material<PK>, PK extends Serializable> extends ProyectoService<E, PK> {

	/**
	 * La función encargada de recuperar el listado de materiales de acuerdo a la asignatura que tenemos seleccionada dentro del sistema al momento de
	 * ingresar a la aplicación.
	 * 
	 * @param subjectSelected
	 *            La materia que tenemos ingresada dentro del sistema, puede ser nula.
	 * @return El listado de los materiales que corresponde con la asignatura que recibimos, en caso de ser nula se retornará todos los materiales que
	 *         hay detallados dentro del sistema y que no tengan una asignatura asignada.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla al momento de recuperar los materiales desde la base de datos.
	 */
	public List<E> findBySubject(Subject subjectSelected) throws CheckedException;
}