package com.proyecto.ontology.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.ontology.ActivityOntology;
import com.proyecto.ontology.ReactiveOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de actividades dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActivityOntologyImpl implements ActivityOntology {

	/**
	 * El servicio de carga de los reactivos a la ontología.
	 */
	@Autowired
	private ReactiveOntology reactiveOntology;

	@Override
	public <A extends Activity> Individual loadActivityToOntology(OntModel ontology, Individual assessment, A activity) {
		// String className = Constants.NAMESPACE + activity.getClass().getSimpleName();
		// String individualName = className + "_" + activity.getId();
		//
		// // Creamos la clase y la instancia de la actividad.
		// OntClass activityClass = ontology.createClass(className);
		// Individual activityIndividual = activityClass.createIndividual(individualName);
		//
		// // Creamos las relaciones.
		// DatatypeProperty haveDescription = ontology.createDatatypeProperty(Constants.PROPERTY_ACTIVITY_HAVE_DESCRIPTION);
		// DatatypeProperty haveReactive = ontology.createDatatypeProperty(Constants.PROPERTY_ACTIVITY_HAVE_REACTIVES);
		//
		// // Creamos los literales.
		// Literal description = ontology.createTypedLiteral(activity.getDescription(), XSDDatatype.XSDstring);
		//
		// // Creamos las carga de los datos.
		// List<Statement> statements = new ArrayList<Statement>();
		// statements.add(ontology.createLiteralStatement(activityIndividual, haveDescription, description));
		//
		// for (Reactive reactive : activity.getReactives()) {
		// statements.add(ontology.createLiteralStatement(activityIndividual, haveReactive,
		// this.reactiveOntology.loadReactiveToOntology(ontology, activityIndividual, reactive)));
		// }
		//
		// ontology.add(statements);

		return assessment;
	}
}