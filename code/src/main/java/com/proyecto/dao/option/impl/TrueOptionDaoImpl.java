package com.proyecto.dao.option.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.option.TrueOptionDao;
import com.proyecto.model.option.TrueOption;

/**
 * La clase que implementa la interfaz que define el DAO para las opciones verdaderas de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class TrueOptionDaoImpl extends OptionDaoImpl<TrueOption> implements TrueOptionDao {

	private static final long serialVersionUID = 3392839526463789376L;
}