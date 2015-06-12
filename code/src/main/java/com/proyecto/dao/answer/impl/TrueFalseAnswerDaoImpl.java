package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.TrueFalseAnswerDao;
import com.proyecto.model.answer.TrueFalseAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas booleanas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class TrueFalseAnswerDaoImpl extends AnswerDaoImpl<TrueFalseAnswer> implements TrueFalseAnswerDao {

	private static final long serialVersionUID = 6319513278911011316L;
}