package com.proyecto.service.material.activity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.activity.ActivityDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.activity.type.ActivityType;
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

	@Override
	public List<Activity> findBySubject(Subject subjectSelected, final List<ActivityType> activityTypes) throws CheckedException {
		// El listado de las actividades.
		List<Activity> activities = this.findBySubject(subjectSelected);

		// Filtramos solamente cuando tenemos algún tipo de actividad.
		if (activityTypes != null && !activityTypes.isEmpty()) {

			// Filtramos la lista.
			CollectionUtils.filter(activities, new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					Activity activity = (Activity) arg0;
					if (activityTypes.contains(activity.getActivityType())) {
						return true;
					} else {
						return false;
					}
				}
			});
		}

		// retornamos la lista.
		return activities;
	}
}