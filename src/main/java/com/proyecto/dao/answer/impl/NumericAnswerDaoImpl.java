package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.NumericAnswerDao;
import com.proyecto.model.answer.NumericAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas numéricas de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class NumericAnswerDaoImpl extends AnswerDaoImpl<NumericAnswer> implements NumericAnswerDao {
}