package com.proyecto.service.material.reactive.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;

import com.common.util.annotations.Service;
import com.common.util.exception.CheckedException;
import com.common.util.model.query.filter.Filter;
import com.proyecto.dao.material.reactive.ReactiveDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.material.reactive.type.ReactiveType;
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

	@Override
	public List<Reactive> findByInstrument(Instrument instrument) throws CheckedException {
		if (instrument != null) {
			Filter filter = Filter.eq(Reactive.Attributes.INSTRUMENT, instrument);
			return this.findByFilter(filter);
		}
		return null;
	}

	@Override
	public List<Reactive> findBySubject(Subject subjectSelected, final List<ReactiveType> reactiveTypes) throws CheckedException {
		// El listado de los reactivos.
		List<Reactive> reactives = this.findBySubject(subjectSelected);

		// Filtramos solo si el listado de tipos de reactivos no es nulo ni vacío.
		if (reactiveTypes != null && !reactiveTypes.isEmpty()) {

			// Filtramos la lista.
			CollectionUtils.filter(reactives, new Predicate() {

				@Override
				public boolean evaluate(Object arg0) {
					Reactive reactive = (Reactive) arg0;
					if (reactiveTypes.contains(reactive.getReactiveType())) {
						return true;
					} else {
						return false;
					}
				}
			});
		}

		// Retornamos la lista.
		return reactives;
	}
}