package com.proyecto.ontology.rdf.material.activity.impl;

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
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.factory.material.reactive.ReactiveFactoryRdf;
import com.proyecto.ontology.rdf.material.MaterialRdfImpl;
import com.proyecto.ontology.rdf.material.activity.ActivityRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de las actividades dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ActivityRdfImpl extends MaterialRdfImpl<Activity> implements ActivityRdf {

	private static final long serialVersionUID = 3560221980191352241L;

	/**
	 * La factoría de los reactivos.
	 */
	@Autowired
	private ReactiveFactoryRdf reactiveFactoryRdf;

	/**
	 * La clase de actividad.
	 */
	private OntClass activityClass;
	/**
	 * Las relaciones de las actividades.
	 */
	private DatatypeProperty haveDescription;
	private ObjectProperty haveReactive;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String activityClassName = this.namespace + Activity.class.getSimpleName();
		this.activityClass = ontology.getOntClass(activityClassName);
		if (this.activityClass == null) {
			this.activityClass = ontology.createClass(activityClassName);
		}

		// Creamos las relaciones.
		String description = this.namespace + Constants.Ontology.PROPERTY_ACTIVITY_HAVE_DESCRIPTION;
		this.haveDescription = ontology.getDatatypeProperty(description);
		if (this.haveDescription == null) {
			this.haveDescription = ontology.createDatatypeProperty(description);
		}

		String reactive = this.namespace + Constants.Ontology.PROPERTY_ACTIVITY_HAVE_REACTIVE;
		this.haveReactive = ontology.getObjectProperty(reactive);
		if (this.haveReactive == null) {
			this.haveReactive = ontology.createObjectProperty(reactive);
			this.haveReactive.addDomain(this.activityClass);
			this.haveReactive.addRange(this.reactiveFactoryRdf.topClassHierachy(ontology));
		}

		return this.activityClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, Activity entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveDescription, description));

		if (entity.getReactives() != null && !entity.getReactives().isEmpty()) {
			RDFNode[] reactivesNodes = new RDFNode[entity.getReactives().size()];
			int index = 0;
			for (Reactive reactive : entity.getReactives()) {
				reactivesNodes[index++] = this.reactiveFactoryRdf.loadEntityToOntology(ontology, reactive);
			}
			statements.add(ontology.createLiteralStatement(individual, this.haveReactive, ontology.createList(reactivesNodes)));
		}

		ontology.add(statements);

		return individual;
	}
}