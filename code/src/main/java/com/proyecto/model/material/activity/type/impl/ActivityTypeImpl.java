package com.proyecto.model.material.activity.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.model.material.reactive.type.ReactiveType;
import com.proyecto.model.material.reactive.type.impl.ReactiveTypeImpl;

/**
 * La enumeración que define los tipos de actividades que podemos definir dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum ActivityTypeImpl implements ActivityType {

	/**
	 * El tipo de actividad que solo permite reactivos formales.
	 */
	FORMAL("type.activity.formal", new ReactiveType[] { ReactiveTypeImpl.FORMAL }),
	/**
	 * El tipo de actividad que solo permite reactivos semiformales.
	 */
	SEMIFORMAL("type.activity.semiformal", new ReactiveType[] { ReactiveTypeImpl.SEMIFORMAL });

	/**
	 * El nombre del tipo de actividad.
	 */
	private String name;
	/**
	 * Los tipos de reactivos que se permite cargar dentro de este tipo de actividad.
	 */
	private ReactiveType[] reactivesTypeAllowed;

	/**
	 * El constructor de un tipo de actividad.
	 * 
	 * @param name
	 *            El nombre del tipo de actividad.
	 * @param reactivesTypesAllowed
	 *            Los tipos de reactivos permitidos dentro de este actividad.
	 */
	private ActivityTypeImpl(String name, ReactiveType[] reactivesTypesAllowed) {
		this.name = name;
		this.reactivesTypeAllowed = reactivesTypesAllowed;
	}

	@Override
	public String toString() {
		return HolderMessage.getMessage(this.name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ReactiveType[] getReactiveTypesAllowed() {
		return this.reactivesTypeAllowed;
	}
}