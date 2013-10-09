package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.EssayActivityAnswerDao;
import com.proyecto.model.answer.EssayActivityAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas de los instrumentos de ensayos objetivos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class EssayActivityAnswerDaoImpl extends AnswerDaoImpl<EssayActivityAnswer> implements EssayActivityAnswerDao {

	private static final long serialVersionUID = 8928266986355785084L;
}