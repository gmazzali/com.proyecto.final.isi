package com.proyecto.service.material.assessment;

import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.assessment.AssessmentDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.assessment.type.AssessmentType;
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

	/**
	 * La función encargada de recuperar el listado de materiales de acuerdo a la asignatura que tenemos seleccionada dentro del sistema al momento de
	 * ingresar a la aplicación y filtrada por el listado de los tipos de evaluaciones que recibimos.
	 * 
	 * @param subjectSelected
	 *            La materia que tenemos ingresada dentro del sistema, puede ser nula.
	 * @param assessmentTypes
	 *            El listado de los tipos de evaluaciones que recibimos para filtrado de las mismas que recuperamos.
	 * @return El listado de los materiales que corresponde con la asignatura que recibimos, en caso de ser nula se retornará todos los materiales que
	 *         hay detallados dentro del sistema y que no tengan una asignatura asignada.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla al momento de recuperar los materiales desde la base de datos.
	 */
	public List<Assessment> findBySubject(Subject subjectSelected, List<AssessmentType> assessmentTypes) throws CheckedException;
}
