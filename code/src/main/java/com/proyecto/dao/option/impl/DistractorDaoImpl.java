package com.proyecto.dao.option.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.option.DistractorDao;
import com.proyecto.model.option.Distractor;

/**
 * La clase que implementa la interfaz que define el DAO para las opciones falsas de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class DistractorDaoImpl extends OptionDaoImpl<Distractor> implements DistractorDao {

	private static final long serialVersionUID = -8775560227395824779L;
}