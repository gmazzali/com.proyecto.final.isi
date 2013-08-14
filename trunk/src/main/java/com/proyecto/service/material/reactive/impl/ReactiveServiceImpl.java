package com.proyecto.service.material.reactive.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.service.material.MaterialServiceImpl;
import com.proyecto.service.material.reactive.ReactiveService;

/**
 * La clase que nos permite implementar la interfaz de los servicios de los reactivos que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Service
public class ReactiveServiceImpl extends MaterialServiceImpl<Reactive, Integer> implements ReactiveService {

	@Override
	@Autowired
	public void setReactiveDao(ReactiveDao reactiveDao) {
		this.setDao(reactiveDao);
	}

	@Override
	public void validate(Reactive arg0) throws CheckedException {
		// TODO mromitti Hacer lo de la validación de un reactivo.
	}
}