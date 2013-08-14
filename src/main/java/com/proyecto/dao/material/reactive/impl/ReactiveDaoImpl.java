package com.proyecto.dao.material.reactive.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.material.MaterialDaoImpl;
import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La clase que implementa la interfaz del DAO para los reactivos que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Dao
public class ReactiveDaoImpl extends MaterialDaoImpl<Reactive, Integer> implements ReactiveDao {
}