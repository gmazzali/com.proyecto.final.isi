package com.proyecto.service.option;

import com.proyecto.dao.option.TrueOptionDao;
import com.proyecto.model.option.TrueOption;

/**
 * La interfaz que define los servicios para las opciones verdaderas de los instrumentos de selecci�n del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface TrueOptionService extends OptionService<TrueOption> {

	/**
	 * La funci�n encargada de cargar el DAO que vamos a utilizar dentro de este servicio.
	 * 
	 * @param trueOptionDao
	 *            El DAO que vamos a usar dentro de este servicio.
	 */
	public void setTrueOptionDao(TrueOptionDao trueOptionDao);
}