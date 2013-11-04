package com.proyecto.ontology.rdf.material.assessment.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.factory.material.activity.ActivityFactoryRdf;
import com.proyecto.ontology.rdf.material.MaterialRdfImpl;
import com.proyecto.ontology.rdf.material.assessment.AssessmentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de las evaluaciones dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class AssessmentRdfImpl extends MaterialRdfImpl<Assessment> implements AssessmentRdf {

	private static final long serialVersionUID = -6076534416545428673L;

	/**
	 * El servicio de las actividades.
	 */
	@Autowired
	private ActivityFactoryRdf activityFactoryRdf;

	/**
	 * La clase de las evaluaciones.
	 */
	private OntClass assessmentClass;
	/**
	 * Las relaciones de la evaluación.
	 */
	private DatatypeProperty haveDescription;
	private DatatypeProperty haveDate;
	private DatatypeProperty haveMoment;
	private ObjectProperty haveActivity;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String assessmentClassName = this.namespace + OntologyConstants.ClassName.ASSESSMENT;
		this.assessmentClass = ontology.getOntClass(assessmentClassName);
		if (this.assessmentClass == null) {
			this.assessmentClass = ontology.createClass(assessmentClassName);
		}

		// Creamos las relaciones.
		String description = this.namespace + OntologyConstants.PropertyName.ASSESSMENT_HAS_DESCRIPTION;
		this.haveDescription = ontology.getDatatypeProperty(description);
		if (this.haveDescription == null) {
			this.haveDescription = ontology.createDatatypeProperty(description);
		}

		String date = this.namespace + OntologyConstants.PropertyName.ASSESSMENT_HAS_DATE;
		this.haveDate = ontology.getDatatypeProperty(date);
		if (this.haveDate == null) {
			this.haveDate = ontology.createDatatypeProperty(date);
		}

		String moment = this.namespace + OntologyConstants.PropertyName.ASSESSMENT_HAS_MOMENT;
		this.haveMoment = ontology.getDatatypeProperty(moment);
		if (this.haveMoment == null) {
			this.haveMoment = ontology.createDatatypeProperty(moment);
		}

		String activity = this.namespace + OntologyConstants.PropertyName.ASSESSMENT_HAS_ACTIVITY;
		this.haveActivity = ontology.getObjectProperty(activity);
		if (this.haveActivity == null) {
			this.haveActivity = ontology.createObjectProperty(activity);
			this.haveActivity.addDomain(this.assessmentClass);
			this.haveActivity.addRange(this.activityFactoryRdf.topClassHierachy(ontology));
		}

		return this.assessmentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, Assessment entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);
		Literal moment = ontology.createTypedLiteral(entity.getAssessmentMoment(), XSDDatatype.XSDstring);
		Literal date = ontology.createTypedLiteral(entity.getAssessmentDate(), XSDDatatype.XSDdate);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveDescription, description));
		statements.add(ontology.createLiteralStatement(individual, this.haveDate, date));
		statements.add(ontology.createLiteralStatement(individual, this.haveMoment, moment));

		if (entity.getActivities() != null && !entity.getActivities().isEmpty()) {
			RDFNode[] activitiesNodes = new RDFNode[entity.getActivities().size()];
			int index = 0;
			for (Activity activity : entity.getActivities()) {
				activitiesNodes[index++] = this.activityFactoryRdf.loadEntityToOntology(ontology, activity);
			}
			statements.add(ontology.createLiteralStatement(individual, this.haveActivity, ontology.createList(activitiesNodes)));
		}

		ontology.add(statements);

		return individual;
	}
}