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
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.ontology.rdf.material.MaterialRdfImpl;
import com.proyecto.ontology.rdf.material.activity.factory.ActivityFactoryRdf;
import com.proyecto.ontology.rdf.material.assessment.AssessmentRdf;
import com.proyecto.util.Constants;

/**
 * La interfaz que define el comportamiento de las evaluaciones dentro de una ontología.
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
		if (this.assessmentClass == null) {

			String assessmentClassName = Constants.NAMESPACE + Assessment.class.getSimpleName();
			this.assessmentClass = ontology.getOntClass(assessmentClassName);

			if (this.assessmentClass == null) {
				this.assessmentClass = ontology.createClass(assessmentClassName);
			}
		}

		// Creamos las relaciones.
		if (this.haveDescription == null) {
			this.haveDescription = ontology.getDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DESCRIPTION);
			if (this.haveDescription == null) {
				this.haveDescription = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DESCRIPTION);
			}
		}

		if (this.haveDate == null) {
			this.haveDate = ontology.getDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DATE);
			if (this.haveDate == null) {
				this.haveDate = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DATE);
			}
		}

		if (this.haveMoment == null) {
			this.haveMoment = ontology.getDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_MOMENT);
			if (this.haveMoment == null) {
				this.haveMoment = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_MOMENT);
			}
		}

		if (this.haveActivity == null) {
			this.haveActivity = ontology.getObjectProperty(Constants.PROPERTY_ASSESSMENT_HAVE_ACTIVITY);
			if (this.haveActivity == null) {
				this.haveActivity = ontology.createObjectProperty(Constants.PROPERTY_ASSESSMENT_HAVE_ACTIVITY);
				this.haveActivity.addDomain(this.assessmentClass);
				this.haveActivity.addRange(this.activityFactoryRdf.topClassHierachy(ontology));
			}
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

		for (Activity activity : entity.getActivities()) {
			statements.add(ontology.createLiteralStatement(individual, this.haveActivity,
					this.activityFactoryRdf.loadEntityToOntology(ontology, activity)));
		}

		ontology.add(statements);

		return individual;
	}
}