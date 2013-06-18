package com.proyecto.dao.answer.impl;

import com.common.util.annotations.Dao;
import com.proyecto.dao.answer.TextAnswerDao;
import com.proyecto.model.answer.TextAnswer;

/**
 * La clase que implementa la interfaz que define el DAO para las respuestas textuales de los instrumentos del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Dao
public class TextAnswerDaoImpl extends AnswerDaoImpl<TextAnswer> implements TextAnswerDao {
}