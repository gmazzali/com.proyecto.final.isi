package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.RelationAnswerDao;
import com.proyecto.model.answer.RelationAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas relacionales de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class RelationAnswerDaoImpl extends AnswerDaoImpl<RelationAnswer> implements RelationAnswerDao {

	private static final long serialVersionUID = 3899843491265356037L;
}