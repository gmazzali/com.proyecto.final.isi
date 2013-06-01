package com.proyecto.dao.agent;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.model.agent.Subject;

/**
 * La clase que implementa el DAO de las materias del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class SubjectDaoImpl extends ProyectoDaoImpl<Subject, Integer> implements SubjectDao {
}
