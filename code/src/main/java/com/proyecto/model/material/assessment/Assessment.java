package com.proyecto.model.material.assessment;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import com.proyecto.model.material.assessment.type.impl.AssessmentMomentImpl;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;

/**
 * La clase que nos permite representar una evaluación dentro del sistema.
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
		static final String ASSESSMENT_MOMENT = "assessmentMoment";
		static final String ASSESSMENT_DATE = "assessmentDate";
		static final String ACTIVITIES = "activities";
	}

	/**
	 * La descripción de la evaluación.
	 */
	private String description;
	/**
	 * El tipo de evaluación de acuerdo al momento en el que va a tomarse la misma.
	 */
	private AssessmentMomentImpl assessmentMoment;
	/**
	 * La fecha asignada a la evaluación.
	 */
	private Date assessmentDate;
	/**
	 * El listado de las actividades de la evaluación.
	 */
	private List<Activity> activities;

	/**
	 * El constructor por omisión de la evaluación.
	 */
	public Assessment() {
		super();
		this.description = null;
		this.assessmentMoment = null;
		this.assessmentDate = null;
		this.activities = new ArrayList<Activity>();
	}

	@Override
	public String toString() {
		return this.description;
	}

	@Override
	public void print(OutputStream stream) {
		try {
			stream.write("#################### ASSESSMENT ####################\n".getBytes());
			stream.write(("ID: " + this.id + "\n").getBytes());
			stream.write(("Subject: " + this.subject + "\n").getBytes());
			stream.write(("Descripción: " + this.description + "\n").getBytes());
			stream.write(("Fecha: " + new SimpleDateFormat("dd/MM/yyyy").format(this.assessmentDate) + "\n").getBytes());
			stream.write(("Momento: " + this.assessmentMoment.getName() + "\n").getBytes());

			for (Activity activity : this.activities) {
				activity.print(stream);
			}
			stream.write("\n".getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Id
	@Column(name = "ID_ASSESSMENT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Override
	public Integer getId() {
		return super.getId();
	}

	/**
	 * La función encargada de agregar una actividad dentro de la evaluación.
	 * 
	 * @param activity
	 *            La actividad que vamos a agregar dentro de la evaluación.
	 */
	public void addActivity(Activity activity) {
		if (this.activities != null && activity != null) {
			this.activities.add(activity);
		}
	}

	/**
	 * La función encargada de agregar un listado de actividades dentro de las que tenemos dentro de la evaluación.
	 * 
	 * @param activities
	 *            El listado de actividades que vamos a agregar dentro de esta evaluación.
	 */
	public void addActivities(List<Activity> activities) {
		if (this.activities != null && activities != null) {
			this.activities.addAll(activities);
		}
	}

	/**
	 * La función encargada de eliminar una actividad de las que tenemos dentro de la evaluación.
	 * 
	 * @param activity
	 *            La actividad que queremos quitar dentro de esta evaluación.
	 */
	public void removeActivity(Activity activity) {
		if (this.activities != null && activity != null) {
			this.activities.add(activity);
		}
	}

	/**
	 * La función encargada de quitar un conjunto de actividades de las que tenemos dentro de esta evaluación.
	 * 
	 * @param activities
	 *            El listado de las actividades que queremos quitar de dentro de esta evaluación.
	 */
	public void removeActivities(List<Activity> activities) {
		if (this.activities != null && activities != null) {
			this.activities.removeAll(activities);
		}
	}

	/**
	 * La función encargada de limpiar el listado de las actividades que tenemos dentro de esta evaluación.
	 */
	public void clearActivities() {
		if (this.activities != null) {
			this.activities.clear();
		}
	}

	/**
	 * La función encargada de retornar la descripción que tenemos para esta evaluación.
	 * 
	 * @return La descripción que tenemos para esta evaluación.
	 */
	@Column(name = "DESCRIPTION", columnDefinition = "text", nullable = false)
	public String getDescription() {
		return this.description;
	}

	/**
	 * La función encargada de retornar la fecha que tenemos para la evaluación.
	 * 
	 * @return La fecha que corresponde a esta evaluación.
	 */
	@Column(name = "ASSESSMENT_DATE", columnDefinition = "timestamp", nullable = false)
	public Date getAssessmentDate() {
		return this.assessmentDate;
	}

	/**
	 * La función encargada de retornar el momento en el que la evaluación es tomada.
	 * 
	 * @return El momento en el que la evaluación es tomada.
	 */
	@Column(name = "ASSESSMENT_MOMENT", columnDefinition = "varchar(20)", nullable = false)
	@Enumerated(EnumType.STRING)
	public AssessmentMomentImpl getAssessmentMoment() {
		return this.assessmentMoment;
	}

	/**
	 * La función encargada de retornar el listado de las actividades de esta evaluación.
	 * 
	 * @return El listado de las actividades de esta evaluación.
	 */
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = Activity.class)
	@JoinTable(name = "ACTIVITIES_IN_ASSESSMENTS", joinColumns = { @JoinColumn(name = "ID_ASSESSMENT") }, inverseJoinColumns = { @JoinColumn(name = "ID_ACTIVITY") })
	public List<Activity> getActivities() {
		return this.activities;
	}

	/**
	 * La función que nos permite saber a que tipo de evaluación pertenece este elemento.
	 * 
	 * @return El tipo de evaluación a la que pertenece este elemento. En caso de que todavía no tenga una actividad asociada, retornamos un valor
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
	 * La función encargada de cargar la descripción a esta evaluación.
	 * 
	 * @param description
	 *            La descripción de la evaluación.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * La función encargada de cargar la fecha de la evaluación.
	 * 
	 * @param assessmentDate
	 *            La fecha de la evaluación.
	 */
	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 * La función encargada de cargar el momento en el que la evaluación es tomada.
	 * 
	 * @param assessmentMoment
	 *            El momento en el que la evaluación es tomada.
	 */
	public void setAssessmentMoment(AssessmentMomentImpl assessmentMoment) {
		this.assessmentMoment = assessmentMoment;
	}

	/**
	 * La función encargada de cargar el listado de las actividades dentro de esta evaluación.
	 * 
	 * @param activities
	 *            El listado de actividades para esta evaluación.
	 */
	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}
}