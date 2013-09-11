package com.proyecto.ontology.rdf.material.reactive.impl;

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
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.rdf.material.MaterialRdfImpl;
import com.proyecto.ontology.rdf.material.instrument.factory.InstrumentFactoryRdf;
import com.proyecto.ontology.rdf.material.reactive.ReactiveRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los reactivos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ReactiveRdfImpl extends MaterialRdfImpl<Reactive> implements ReactiveRdf {

	private static final long serialVersionUID = -5963204658677767957L;	

	/**
	 * La factoría de instrumentos.
	 */
	@Autowired
	private InstrumentFactoryRdf instrumentFactoryRdf;

	/**
	 * La clase de reactivo.
	 */
	private OntClass reactiveClass;
	/**
	 * Las relaciones del reactivo.
	 */
	private DatatypeProperty haveDescription;
	private ObjectProperty haveInstrument;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.reactiveClass == null) {

			String reactiveClassName = Constants.NAMESPACE + Reactive.class.getSimpleName();
			this.reactiveClass = ontology.getOntClass(reactiveClassName);

			if (this.reactiveClass == null) {
				this.reactiveClass = ontology.createClass(reactiveClassName);
			}
		}

		// Creamos las relaciones.
		if (this.haveDescription == null) {
			this.haveDescription = ontology.getDatatypeProperty(Constants.PROPERTY_REACTIVE_HAVE_DESCRIPTION);
			if (this.haveDescription == null) {
				this.haveDescription = ontology.createDatatypeProperty(Constants.PROPERTY_REACTIVE_HAVE_DESCRIPTION);
			}
		}

		if (this.haveInstrument == null) {
			this.haveInstrument = ontology.getObjectProperty(Constants.PROPERTY_REACTIVE_HAVE_INSTRUMENT);
			if (this.haveInstrument == null) {
				this.haveInstrument = ontology.createObjectProperty(Constants.PROPERTY_REACTIVE_HAVE_INSTRUMENT);
				this.haveInstrument.addDomain(this.reactiveClass);
				this.haveInstrument.addRange(this.instrumentFactoryRdf.topClassHierachy(ontology));
			}
		}

		return this.reactiveClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, Reactive entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, haveDescription, description));
		statements.add(ontology.createLiteralStatement(individual, haveInstrument,
				this.instrumentFactoryRdf.loadInstrumentToOntology(ontology, entity.getInstrument())));

		ontology.add(statements);
		
		return individual;
	}
}