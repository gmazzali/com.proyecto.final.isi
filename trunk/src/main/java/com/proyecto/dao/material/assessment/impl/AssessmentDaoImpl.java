package com.proyecto.dao.material.assessment.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.MaterialDaoImpl;
import com.proyecto.dao.material.assessment.AssessmentDao;

/**
 * La clase que implementa la interfaz del DAO para las evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class AssessmentDaoImpl extends MaterialDaoImpl<Activity, Integer> implements AssessmentDao {
}