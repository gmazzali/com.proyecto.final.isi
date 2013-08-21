package com.proyecto.service.material.activity;

import com.proyecto.dao.material.activity.ActivityDao;
import com.proyecto.model.material.activity.Activity;
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
}