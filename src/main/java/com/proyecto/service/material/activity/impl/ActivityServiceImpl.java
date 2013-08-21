package com.proyecto.service.material.activity.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.activity.ActivityDao;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.service.material.MaterialServiceImpl;
import com.proyecto.service.material.activity.ActivityService;

/**
 * La clase que nos permite implementar la interfaz de los servicios de las actividades que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Service
public class ActivityServiceImpl extends MaterialServiceImpl<Activity, Integer> implements ActivityService {

	@Override
	@Autowired
	public void setActivityDao(ActivityDao activityDao) {
		this.setDao(activityDao);
	}

	@Override
	public void validate(Activity entity) throws CheckedException {
		// TODO gmazzali Falta hacer la validación de las actividades del sistema.
	}
}