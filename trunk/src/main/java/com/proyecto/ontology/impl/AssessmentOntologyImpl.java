package com.proyecto.ontology.impl;

import java.util.ArrayList;
import java.util.List;

import com.common.util.annotations.Service;
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
import com.proyecto.model.material.instrument.EssayInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.AssessmentOntology;

/**
 * La clase que va a crear y mantener la ontología de una evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AssessmentOntologyImpl implements AssessmentOntology {

	private static final long serialVersionUID = 4074693337639314224L;

	/**
	 * Las cadenas que vamos a utilizar.
	 */
	protected static final String NAMESPACE = "http://www.assessments.com/#";

	protected static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = AssessmentOntologyImpl.NAMESPACE + "have_description";
	protected static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = AssessmentOntologyImpl.NAMESPACE + "have_moment";
	protected static final String PROPERTY_ASSESSMENT_HAVE_DATE = AssessmentOntologyImpl.NAMESPACE + "have_date";
	protected static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = AssessmentOntologyImpl.NAMESPACE + "have_activity";

	protected static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = AssessmentOntologyImpl.NAMESPACE + "have_description";
	protected static final String PROPERTY_ACTIVITY_HAVE_REACTIVES = AssessmentOntologyImpl.NAMESPACE + "have_reactives";

	protected static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = AssessmentOntologyImpl.NAMESPACE + "have_description";
	protected static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = AssessmentOntologyImpl.NAMESPACE + "have_instrument";

	protected static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = AssessmentOntologyImpl.NAMESPACE + "have_description";

	protected static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = AssessmentOntologyImpl.NAMESPACE + "have_answer";

	protected static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = AssessmentOntologyImpl.NAMESPACE + "have_relation";

	protected static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = AssessmentOntologyImpl.NAMESPACE + "have_option";

	protected static final String PROPERTY_HAVE_OPTION = AssessmentOntologyImpl.NAMESPACE + "have_option";
	protected static final String PROPERTY_HAVE_ANSWER = AssessmentOntologyImpl.NAMESPACE + "have_answer";

	@Override
	public OntModel initAssessmentOntology(Assessment assessment) {
		OntModel assessmentOntology = null;
		// Creamos una ontología vacía.
		assessmentOntology = ModelFactory.createOntologyModel(ProfileRegistry.OWL_LANG);
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
		OntClass assessmentClass = ontology.createClass(AssessmentOntologyImpl.NAMESPACE + className);
		Individual assessmentIndividual = assessmentClass.createIndividual(AssessmentOntologyImpl.NAMESPACE + individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ASSESSMENT_HAVE_DESCRIPTION);
		DatatypeProperty haveMoment = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ASSESSMENT_HAVE_MOMENT);
		DatatypeProperty haveDate = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ASSESSMENT_HAVE_DATE);
		DatatypeProperty haveActivities = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ASSESSMENT_HAVE_ACTIVITY);

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
			statements.add(ontology.createLiteralStatement(assessmentIndividual, haveActivities, this.loadActivitiesOntology(ontology, activity)));
		}

		ontology.add(statements);
	}

	/**
	 * La función encargada de cargar una actividad dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a guardar la actividad.
	 * @param activity
	 *            La actividad que vamos a guardar.
	 * @return La instancia de la actividad que almacenamos dentro de la ontología.
	 */
	private Individual loadActivitiesOntology(OntModel ontology, Activity activity) {
		String className = activity.getClass().getSimpleName();
		String individualName = className + "_" + activity.getId();

		// Creamos la clase y la instancia de la actividad.
		OntClass activityClass = ontology.createClass(AssessmentOntologyImpl.NAMESPACE + className);
		Individual activityIndividual = activityClass.createIndividual(AssessmentOntologyImpl.NAMESPACE + individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ACTIVITY_HAVE_DESCRIPTION);
		DatatypeProperty haveReactive = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_ACTIVITY_HAVE_REACTIVES);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(activity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(activityIndividual, haveDescription, description));

		for (Reactive reactive : activity.getReactives()) {
			statements.add(ontology.createLiteralStatement(activityIndividual, haveReactive, this.loadReactivesOntology(ontology, reactive)));
		}

		ontology.add(statements);

		return activityIndividual;
	}

	/**
	 * La función encargada de cargar un reactivo dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a guardar el reactivo.
	 * @param reactive
	 *            El reactivo que vamos a guardar.
	 * @return La instancia del reactivo que almacenamos dentro de la ontología.
	 */
	private Individual loadReactivesOntology(OntModel ontology, Reactive reactive) {
		String className = reactive.getClass().getSimpleName();
		String individualName = className + "_" + reactive.getId();

		// Creamos la clase y la instancia del reactivo.
		OntClass reactiveClass = ontology.createClass(AssessmentOntologyImpl.NAMESPACE + className);
		Individual reactiveIndividual = reactiveClass.createIndividual(AssessmentOntologyImpl.NAMESPACE + individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_REACTIVE_HAVE_DESCRIPTION);
		DatatypeProperty haveReactive = ontology.createDatatypeProperty(AssessmentOntologyImpl.PROPERTY_REACTIVE_HAVE_INSTRUMENT);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(reactive.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(reactiveIndividual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(reactiveIndividual, haveReactive,
				this.loadInstrumentOntology(ontology, reactive.getInstrument())));

		ontology.add(statements);

		return reactiveIndividual;
	}

	private Object loadInstrumentOntology(OntModel ontology, Instrument instrument) {
		// TODO Auto-generated method stub
		return "INSTRUMENT";
	}

	/**
	 * La función encargada de cargar dentro de la ontología un instrumento de ensayo.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a guardar el instrumento y sus respuestas.
	 * @param instrument
	 *            El instrumento que vamos a almacenar-
	 * @return La instancia de la clase de instrumento de ensayo que almacenamos.
	 */
	private Individual loadEssayInstrumentToOntology(OntModel ontology, EssayInstrument instrument) {
		return null;
	}
}
