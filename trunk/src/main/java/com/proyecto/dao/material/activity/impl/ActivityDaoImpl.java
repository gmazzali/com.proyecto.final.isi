package com.proyecto.dao.material.activity.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.MaterialDaoImpl;
import com.proyecto.dao.material.activity.ActivityDao;
import com.proyecto.model.material.activity.Activity;

/**
 * La clase que implementa la interfaz del DAO para las actividades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class ActivityDaoImpl extends MaterialDaoImpl<Activity, Integer> implements ActivityDao {

	private static final long serialVersionUID = 2963374124968729559L;
}