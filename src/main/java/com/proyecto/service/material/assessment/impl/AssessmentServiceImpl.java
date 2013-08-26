package com.proyecto.service.material.assessment.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.assessment.AssessmentDao;
import com.proyecto.model.material.assessment.Assessment;
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
}