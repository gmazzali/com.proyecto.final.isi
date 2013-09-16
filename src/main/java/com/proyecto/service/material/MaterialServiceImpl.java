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
		Filter filter = new Filter();

		// Si la asignatura no es nula, cargamos el filtro y buscamos los materiales.
		if (subjectSelected != null) {
			filter = Filter.eq(Material.Attributes.SUBJECT, subjectSelected);
		} else {
			filter = Filter.isNull(Material.Attributes.SUBJECT);
		}

		// Cargamos solos los materiales activos.
		filter = Filter.and(filter, Filter.eq(Material.Attributes.ACTIVE, true));

		return this.findByFilter(filter);
	}

	@Override
	public List<E> findAll() throws CheckedException {
		Filter filter = new Filter();

		// Cargamos solos los materiales activos.
		filter = Filter.and(filter, Filter.eq(Material.Attributes.ACTIVE, true));

		return this.findByFilter(filter);
	}

	/**
	 * El borrado se realiza cambiando el estado del material a inactivo.
	 * 
	 * @param entity
	 *            El material que vamos a eliminar.
	 */
	@Override
	public void delete(E entity) throws CheckedException {
		// Cargamos el estado de inactivo y actualizamos.
		entity.setActive(false);
		this.update(entity);
	}

	/**
	 * El borrado se realiza cambiando el estado del material que corresponde con el id a inactivo.
	 * 
	 * @param id
	 *            El identificador del material que vamos a eliminar.
	 */
	@Override
	public void deleteById(PK id) throws CheckedException {
		// Recuperamos el material, lo marcamos como inactivo y lo actualizamos.
		E entity = this.findById(id);
		entity.setActive(false);
		this.update(entity);
	}
}