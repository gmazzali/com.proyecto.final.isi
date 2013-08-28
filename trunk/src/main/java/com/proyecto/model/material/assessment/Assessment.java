package com.proyecto.model.material.assessment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.material.Material;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeByTimeImpl;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;

/**
 * La clase que nos permite representar una evaluaci�n dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "ASSESSMENTS")
@javax.persistence.Entity(name = "Assessment")
public class Assessment extends Material<Integer> {

	private static final long serialVersionUID = 8470060816571221095L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Material.Attributes {
		static final String DESCRIPTION = "description";
		static final String ACTIVITIES = "activities";
	}

	/**
	 * La descripci�n de la evaluaci�n.
	 */
	private String description;
	/**
	 * El tipo de evaluaci�n de acuerdo al momento en el que va a tomarse la misma.
	 */
	private AssessmentTypeByTimeImpl assessmentTypeByTime;
	/**
	 * La fecha asignada a la evaluaci�n.
	 */
	private Date assessmentDate;
	/**
	 * El listado de las actividades de la evaluaci�n.
	 */
	private List<Activity> activities;

	/**
	 * El constructor por omisi�n de la evaluaci�n.
	 */
	public Assessment() {
		super();
		this.description = null;
		this.assessmentTypeByTime = null;
		this.assessmentDate = null;
		this.activities = new ArrayList<Activity>();
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Id
	@Column(name = "ID_ASSESSMENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La funci�n encargada de agregar una actividad dentro de la evaluaci�n.
	 * 
	 * @param activity
	 *            La actividad que vamos a agregar dentro de la evaluaci�n.
	 */
	public void addActivity(Activity activity) {
		if (this.activities != null && activity != null) {
			this.activities.add(activity);
		}
	}

	/**
	 * La funci�n encargada de agregar un listado de actividades dentro de las que tenemos dentro de la evaluaci�n.
	 * 
	 * @param activities
	 *            El listado de actividades que vamos a agregar dentro de esta evaluaci�n.
	 */
	public void addActivities(List<Activity> activities) {
		if (this.activities != null && activities != null) {
			this.activities.addAll(activities);
		}
	}

	/**
	 * La funci�n encargada de eliminar una actividad de las que tenemos dentro de la evaluaci�n.
	 * 
	 * @param activity
	 *            La actividad que queremos quitar dentro de esta evaluaci�n.
	 */
	public void removeActivity(Activity activity) {
		if (this.activities != null && activity != null) {
			this.activities.add(activity);
		}
	}

	/**
	 * La funci�n encargada de quitar un conjunto de actividades de las que tenemos dentro de esta evaluaci�n.
	 * 
	 * @param activities
	 *            El listado de las actividades que queremos quitar de dentro de esta evaluaci�n.
	 */
	public void removeActivities(List<Activity> activities) {
		if (this.activities != null && activities != null) {
			this.activities.removeAll(activities);
		}
	}

	/**
	 * La funci�n encargada de limpiar el listado de las actividades que tenemos dentro de esta evaluaci�n.
	 */
	public void clearActivities() {
		if (this.activities != null) {
			this.activities.clear();
		}
	}

	/**
	 * La funci�n encargada de retornar la descripci�n que tenemos para esta evaluaci�n.
	 * 
	 * @return La descripci�n que tenemos para esta evaluaci�n.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "text", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La funci�n encargada de retornar la fecha que tenemos para la evaluaci�n.
	 * 
	 * @return La fecha que corresponde a esta evaluaci�n.
	 */
	@Column(name = "ASSESSMENT_DATE", columnDefinition = "timestamp", nullable = false)
	public Date getAssessmentDate() {
		return this.assessmentDate;
	}

	/**
	 * La funci�n encargada de retornar el tipo de evaluaci�n de acuerdo al momento en el que la misma es tomada.
	 * 
	 * @return El tipo de evaluaci�n de acuerdo al momento en el que la misma es tomada.
	 */
	@Column(name = "ASSESSMENT_TYPE", columnDefinition = "varchar(20)", nullable = false)
	@Enumerated(EnumType.STRING)
	public AssessmentTypeByTimeImpl getAssessmentTypeByTime() {
		return this.assessmentTypeByTime;
	}

	/**
	 * La funci�n encargada de retornar el listado de las actividades de esta evaluaci�n.
	 * 
	 * @return El listado de las actividades de esta evaluaci�n.
	 */
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Activity.class)
	@JoinTable(name = "ACTIVITIES_IN_ASSESSMENTS", joinColumns = { @JoinColumn(name = "ID_ASSESSMENT") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACTIVITY") })
	public List<Activity> getActivities() {
		return this.activities;
	}

	/**
	 * La funci�n que nos permite saber a que tipo de evaluaci�n pertenece este elemento.
	 * 
	 * @return El tipo de evaluaci�n a la que pertenece este elemento. En caso de que todav�a no tenga una actividad asociada, retornamos un valor
	 *         nulo.
	 */
	@Transient
	public AssessmentTypeImpl getAssessmentyType() {
		if (this.activities != null && !this.activities.isEmpty()) {
			switch (this.activities.get(0).getActivityType()) {
				case FORMAL:
					return AssessmentTypeImpl.FORMAL;

				case SEMIFORMAL:
					return AssessmentTypeImpl.SEMIFORMAL;
			}
		}
		return null;
	}

	/**
	 * La funci�n encargada de cargar la descripci�n a esta evaluaci�n.
	 * 
	 * @param description
	 *            La descripci�n de la evaluaci�n.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La funci�n encargada de cargar la fecha de la evaluaci�n.
	 * 
	 * @param assessmentDate
	 *            La fecha de la evaluaci�n.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 * La funci�n encargada de cargar el tipo de evaluaci�n de acuerdo al momento en el que la misma es establecida.
	 * 
	 * @param assessmentTypeByTime
	 *            El tipo de evaluaci�n de acuerdo al momento en el que la misma es establecida.
	 */
	public void setAssessmentTypeByTime(AssessmentTypeByTimeImpl assessmentTypeByTime) {
		this.assessmentTypeByTime = assessmentTypeByTime;
	}

	/**
	 * La funci�n encargada de cargar el listado de las actividades dentro de esta evaluaci�n.
	 * 
	 * @param activities
	 *            El listado de actividades para esta evaluaci�n.
	 */
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}