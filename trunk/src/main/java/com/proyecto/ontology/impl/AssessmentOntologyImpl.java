package com.proyecto.ontology.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.ProfileRegistry;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.ontology.ActivityOntology;
import com.proyecto.ontology.AssessmentOntology;
import com.proyecto.util.Constants;

/**
 * La clase que va a crear y mantener la ontología de una evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AssessmentOntologyImpl implements AssessmentOntology {

	private static final long serialVersionUID = 4074693337639314224L;

	/**
	 * El servicio de la carga de las actividades.
	 */
	@Autowired
	private ActivityOntology activityOntology;

	@Override
	public OntModel loadAssessmentToOntology(Assessment assessment) {
		OntModel assessmentOntology = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
		assessmentOntology.prepare();

		this.loadAssessmentInOntology(assessmentOntology, assessment);

		return assessmentOntology;
	}

	/**
	 * La función encargada de cargar dentro de la ontología el contenido de una evaluación.
	 * 
	 * @param ontology
	 *            La ontología que vamos a cargar.
	 * @param assessment
	 *            La evaluación que vamos a cargar.
	 */
	private void loadAssessmentInOntology(OntModel ontology, Assessment assessment) {
		String className = assessment.getClass().getSimpleName();
		String individualName = className + "_" + assessment.getId();

		// Creamos la clase y la instancia de la evaluación.
		OntClass assessmentClass = ontology.createClass(Constants.NAMESPACE + className);
		Individual assessmentIndividual = assessmentClass.createIndividual(Constants.NAMESPACE + individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DESCRIPTION);
		DatatypeProperty haveMoment = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_MOMENT);
		DatatypeProperty haveDate = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_DATE);
		DatatypeProperty haveActivitiy = ontology.createDatatypeProperty(Constants.PROPERTY_ASSESSMENT_HAVE_ACTIVITY);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(assessment.getDescription(), XSDDatatype.XSDstring);
		Literal moment = ontology.createTypedLiteral(assessment.getAssessmentMoment(), XSDDatatype.XSDstring);
		Literal date = ontology.createTypedLiteral(assessment.getAssessmentDate(), XSDDatatype.XSDdate);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(assessmentIndividual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(assessmentIndividual, haveMoment, moment));
		statements.add(ontology.createLiteralStatement(assessmentIndividual, haveDate, date));

		for (Activity activity : assessment.getActivities()) {
			statements.add(ontology.createLiteralStatement(assessmentIndividual, haveActivitiy,
					this.activityOntology.loadActivityToOntology(ontology, assessmentIndividual, activity)));
		}

		ontology.add(statements);
	}
}
