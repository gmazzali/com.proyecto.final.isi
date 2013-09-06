package com.proyecto.ontology.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.Service;
import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.ActivityOntology;
import com.proyecto.ontology.ReactiveOntology;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de actividades dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class ActivityOntologyImpl implements ActivityOntology {

	/**
	 * El servicio de carga de los reactivos a la ontología.
	 */
	@Autowired
	private ReactiveOntology reactiveOntology;

	@Override
	public <A extends Activity> Individual loadActivityToOntology(OntModel ontology, Assessment assessment, A activity) {
		String className = ConstantsOntology.NAMESPACE + activity.getClass().getSimpleName();
		String individualName = className + "_" + activity.getId();

		// Creamos la clase y la instancia de la actividad.
		OntClass activityClass = ontology.createClass(className);
		Individual activityIndividual = activityClass.createIndividual(individualName);

		// Creamos las relaciones.
		DatatypeProperty haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ACTIVITY_HAVE_DESCRIPTION);
		DatatypeProperty haveReactive = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_ACTIVITY_HAVE_REACTIVES);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(activity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(activityIndividual, haveDescription, description));

		for (Reactive reactive : activity.getReactives()) {
			statements.add(ontology.createLiteralStatement(activityIndividual, haveReactive,
					this.reactiveOntology.loadReactiveToOntology(ontology, activity, reactive)));
		}

		ontology.add(statements);

		return activityIndividual;
	}
}