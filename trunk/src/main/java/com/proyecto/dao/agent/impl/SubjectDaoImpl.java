package com.proyecto.dao.agent.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.agent.SubjectDao;
import com.proyecto.model.agent.Subject;

/**
 * La clase que implementa el DAO de las materias del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class SubjectDaoImpl extends ProyectoDaoImpl<Subject, Integer> implements SubjectDao {

	private static final long serialVersionUID = -8363445321302443652L;
}
