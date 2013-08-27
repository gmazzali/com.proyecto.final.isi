package com.proyecto.service.material.assessment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.assessment.AssessmentDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.assessment.type.AssessmentType;
import com.proyecto.service.material.MaterialServiceImpl;
import com.proyecto.service.material.assessment.AssessmentService;

/**
 * La clase que nos permite implementar la interfaz de los servicios de las evaluaciones que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Service
public class AssessmentServiceImpl extends MaterialServiceImpl<Assessment, Integer> implements AssessmentService {

	@Override
	@Autowired
	public void setAssessmentDao(AssessmentDao assessmentDao) {
		this.setDao(assessmentDao);
	}

	@Override
	public void validate(Assessment entity) throws CheckedException {
		// TODO gmazzali Falta hacer la validación de las evaluaciones del sistema.
	}

	@Override
	public List<Assessment> findBySubject(Subject subjectSelected, final List<AssessmentType> assessmentTypes) throws CheckedException {
		// El listado de las evaluaciones.
		List<Assessment> assessments = this.findBySubject(subjectSelected);

		// Filtramos solamente cuando tenemos algún tipo de evaluación.
		if (assessmentTypes != null && !assessmentTypes.isEmpty()) {

			// Filtramos la lista.
			CollectionUtils.filter(assessments, new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					Assessment activity = (Assessment) arg0;
					if (assessmentTypes.contains(activity.getAssessmentyType())) {
						return true;
					} else {
						return false;
					}
				}
			});
		}

		// retornamos la lista.
		return assessments;
	}
}