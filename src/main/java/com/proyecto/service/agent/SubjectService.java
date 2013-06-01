package com.proyecto.service.agent;

import com.proyecto.dao.agent.SubjectDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que define los servicios que vamos a ofrecer a las materias que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface SubjectService extends ProyectoService<Subject, Integer> {

	/**
	 * La función encargada de cargar el DAO para el servicio de las materias.
	 * 
	 * @param subjectDao
	 *            El DAO para los servicios de las materias.
	 */
	public void setSubjectDao(SubjectDao subjectDao);
}
