package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.CompletionAnswerDao;
import com.proyecto.model.answer.CompletionAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas para completar de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class CompletionAnswerDaoImpl extends AnswerDaoImpl<CompletionAnswer> implements CompletionAnswerDao {
}