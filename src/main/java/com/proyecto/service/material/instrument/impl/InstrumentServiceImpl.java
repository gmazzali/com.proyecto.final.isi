package com.proyecto.service.material.instrument.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.exception.CheckedException;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.service.material.MaterialServiceImpl;
import com.proyecto.service.material.instrument.InstrumentService;
import com.proyecto.service.material.reactive.ReactiveService;

/**
 * La clase que implementa la interfaz que define los servicios que van a ofrecerse a los instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del servicio.
 */
public abstract class InstrumentServiceImpl<E extends Instrument> extends MaterialServiceImpl<E, Integer> implements InstrumentService<E> {

	@Autowired
	private ReactiveService reactiveService;

	@Override
	public Boolean isValidDeleteInstrument(E instrument) throws CheckedException {
		List<Reactive> reactives = this.reactiveService.findByInstrument(instrument);
		if (reactives != null && reactives.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void delete(E entity) throws CheckedException {
		if (this.isValidDeleteInstrument(entity)) {
			super.delete(entity);
		} else {
			throw new CheckedException("instrument.delete.invalid.reference");
		}
	}
}