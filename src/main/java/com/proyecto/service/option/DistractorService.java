package com.proyecto.service.option;

import com.proyecto.dao.option.DistractorDao;
import com.proyecto.model.option.Distractor;

/**
 * La interfaz que define los servicios para las opciones falsas de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface DistractorService extends OptionService<Distractor> {

	/**
	 * La función encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param distractorDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setDistractorDao(DistractorDao distractorDao);
}