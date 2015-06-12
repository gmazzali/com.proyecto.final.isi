package com.proyecto.service.agent.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.agent.SubjectDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.agent.SubjectService;

/**
 * La clase que implementa los servicios que vamos a utilizar sobre las materias del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class SubjectServiceImpl extends ProyectoServiceImpl<Subject, Integer> implements SubjectService {

	@Override
	@Autowired
	public void setSubjectDao(SubjectDao subjectDao) {
		this.setDao(subjectDao);
	}

	@Override
	public void validate(Subject entity) throws CheckedException {
	}
}
