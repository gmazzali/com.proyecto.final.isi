package com.proyecto.service.option.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.option.TrueOptionDao;
import com.proyecto.model.option.TrueOption;
import com.proyecto.service.option.TrueOptionService;

/**
 * La clase que implementa la interfaz que define los servicios para las opciones verdaderas de los instrumentos de selección del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class TrueOptionServiceImpl extends OptionServiceImpl<TrueOption> implements TrueOptionService {

	@Override
	@Autowired
	public void setTrueOptionDao(TrueOptionDao trueOptionDao) {
		this.setDao(trueOptionDao);
	}

	@Override
	public void validate(TrueOption entity) throws CheckedException {
		// TODO gmazzali Terminar el validador de las opciones verdaderas.
	}
}