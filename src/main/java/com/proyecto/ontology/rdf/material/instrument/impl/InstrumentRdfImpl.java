package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.ontology.rdf.material.MaterialRdfImpl;
import com.proyecto.ontology.rdf.material.instrument.InstrumentRdf;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos que vamos a manejar dentro de la ontología.
 */
public abstract class InstrumentRdfImpl<I extends Instrument> extends MaterialRdfImpl<I> implements InstrumentRdf<I> {

	/**
	 * La clase de instrumento.
	 */
	private OntClass instrumentClass;
	/**
	 * Las relaciones de los instrumentos.
	 */
	private DatatypeProperty haveDescription;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		if (this.instrumentClass == null) {

			String instrumentClassName = ConstantsOntology.NAMESPACE + Instrument.class.getSimpleName();
			this.instrumentClass = ontology.getOntClass(instrumentClassName);

			if (this.instrumentClass == null) {
				this.instrumentClass = ontology.createClass(instrumentClassName);
			}
		}

		// Creamos las relaciones.
		if (this.haveDescription == null) {
			this.haveDescription = ontology.getDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);
			if (this.haveDescription == null) {
				this.haveDescription = ontology.createDatatypeProperty(ConstantsOntology.PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);
			}
		}

		return this.instrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos los literales.
		Literal description = ontology.createTypedLiteral(entity.getDescription(), XSDDatatype.XSDstring);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		statements.add(ontology.createLiteralStatement(individual, this.haveDescription, description));

		ontology.add(statements);

		return individual;
	}
}