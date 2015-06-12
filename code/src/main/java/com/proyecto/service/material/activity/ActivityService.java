package com.proyecto.service.material.activity;

import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.activity.ActivityDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.service.material.MaterialService;

/**
 * La interfaz que define los servicios para las actividades que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
public interface ActivityService extends MaterialService<Activity, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param activityDao
	 *            El dao para el servicio de las actividades.
	 */
	public void setActivityDao(ActivityDao activityDao);

	/**
	 * La función encargada de recuperar el listado de materiales de acuerdo a la asignatura que tenemos seleccionada dentro del sistema al momento de
	 * ingresar a la aplicación y filtrada de acuerdo a los tipos de actividades que tenemos para filtrar las mismas.
	 * 
	 * @param subjectSelected
	 *            La materia que tenemos ingresada dentro del sistema, puede ser nula.
	 * @param activityTypes
	 *            Los tipos de actividades que tenemos para
	 * @return El listado de los materiales que corresponde con la asignatura que recibimos, en caso de ser nula se retornará todos los materiales que
	 *         hay detallados dentro del sistema y que no tengan una asignatura asignada.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla al momento de recuperar los materiales desde la base de datos.
	 */
	public List<Activity> findBySubject(Subject subjectSelected, List<ActivityType> activityTypes) throws CheckedException;
}