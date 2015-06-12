package com.proyecto.model.material.assessment.type.impl;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.model.material.activity.type.impl.ActivityTypeImpl;
import com.proyecto.model.material.assessment.type.AssessmentType;

/**
 * La enumeraci�n que nos permite definir los posibles tipos de evaluaciones que tenemos dentro del sistema de acuerdo al contenido que tienen las
 * mismas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum AssessmentTypeImpl implements AssessmentType {

	/**
	 * El tipo de evaluaci�n formal.
	 */
	FORMAL("type.assessment.formal", new ActivityType[] { ActivityTypeImpl.FORMAL }),
	/**
	 * El tipo de evaluaci�n semiformal.
	 */
	SEMIFORMAL("type.assessment.semiformal", new ActivityType[] { ActivityTypeImpl.SEMIFORMAL });

	/**
	 * El nombre del tipo de evaluaci�n por el contenido.
	 */
	private String name;
	/**
	 * Los tipos de instrumentos que tenemos permitidos dentro de cada tipo de evaluaci�n.
	 */
	private ActivityType[] activityTypesAllowed;

	/**
	 * El constructor de un tipo de evaluaci�n dentro del sistema.
	 * 
	 * @param name
	 *            El nombre del tipo de evaluaci�n.
	 * @param activityTypesAllowed
	 *            Los tipos de actividades permitidos dentro de este evaluaci�n.
	 */
	private AssessmentTypeImpl(String name, ActivityType[] activityTypesAllowed) {
		this.name = name;
		this.activityTypesAllowed = activityTypesAllowed;
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
	public ActivityType[] getActivityTypesAllowed() {
		return this.activityTypesAllowed;
	}
}