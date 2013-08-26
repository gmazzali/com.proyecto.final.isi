package com.proyecto.service.material.assessment;

import com.proyecto.dao.material.assessment.AssessmentDao;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.service.material.MaterialService;

/**
 * La interfaz que define los servicios para las evaluaciones que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentService extends MaterialService<Assessment, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param assessmentDao
	 *            El dao para el servicio de las evaluaciones.
	 */
	public void setAssessmentDao(AssessmentDao assessmentDao);
}
