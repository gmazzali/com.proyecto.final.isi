package com.proyecto.dao.reactive.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.ProyectoDaoImpl;
import com.proyecto.dao.reactive.ReactiveDao;
import com.proyecto.model.reactive.Reactive;

/**
 * La clase que implementa la interfaz del DAO para los reactivos que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Dao
public class ReactiveDaoImpl extends ProyectoDaoImpl<Reactive, Integer> implements ReactiveDao {
}