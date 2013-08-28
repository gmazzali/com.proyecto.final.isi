package com.proyecto.service.material;

import java.io.Serializable;
import java.util.List;

import com.common.util.exception.CheckedException;
import com.common.util.model.query.filter.Filter;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.material.Material;
import com.proyecto.service.ProyectoServiceImpl;

/**
 * La clase que va a implementar el servicio base para todos los servicio del sistema de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de material que vamos a manejar dentro del servicio.
 * @param <PK>
 *            La clase que va a hacer de identificador de la clase manejada.
 */
public abstract class MaterialServiceImpl<E extends Material<PK>, PK extends Serializable> extends ProyectoServiceImpl<E, PK> implements
		MaterialService<E, PK> {

	@Override
	public List<E> findBySubject(Subject subjectSelected) throws CheckedException {
		// Si la asignatura no es nula, creamos el filtro y buscamos los materiales.
		if (subjectSelected != null) {
			Filter filter = Filter.eq(Material.Attributes.SUBJECT, subjectSelected);
			return this.findByFilter(filter);
		}
		// Si la asignatura es nula, recuperamos solos los materiales sin asignatura.
		else {
			Filter filter = Filter.isNull(Material.Attributes.SUBJECT);
			return this.findByFilter(filter);
		}
	}
}