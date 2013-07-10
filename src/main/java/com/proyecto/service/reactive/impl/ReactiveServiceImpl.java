package com.proyecto.service.reactive.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.proyecto.dao.reactive.ReactiveDao;
import com.proyecto.model.reactive.Reactive;
import com.proyecto.service.ProyectoServiceImpl;
import com.proyecto.service.reactive.ReactiveService;

/**
 * La clase que nos permite implementar la interfaz de los servicios de los reactivos que tenemos dentro del sistema.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@Service
public class ReactiveServiceImpl extends ProyectoServiceImpl<Reactive, Integer> implements ReactiveService {

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