package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.model.option.Option;
import com.proyecto.ontology.rdf.material.instrument.ChoiceInstrumentRdf;
import com.proyecto.ontology.rdf.option.factory.OptionFactoryRdf;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de selecci�n dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos de selecci�n que vamos a manejar dentro de la ontolog�a.
 */
public abstract class ChoiceInstrumentRdfImpl<I extends ChoiceInstrument> extends ObjectiveActivityInstrumentRdfImpl<I> implements
		ChoiceInstrumentRdf<I> {

	private static final long serialVersionUID = -5609545469326584651L;

	/**
	 * Los servicios de las opciones.
	 */
	@Autowired
	private OptionFactoryRdf optionFactoryRdf;

	/**
	 * La clase del instrumento formal objetivo de selecci�n.
	 */
	private OntClass choiceInstrumentClass;
	/**
	 * Las relaciones del instrumento formal objetivo de selecci�n.
	 */
	private ObjectProperty haveOption;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String choiceInstrumentClassName = this.namespace + ChoiceInstrument.class.getSimpleName();
		if (this.choiceInstrumentClass == null) {
			this.choiceInstrumentClass = ontology.getOntClass(choiceInstrumentClassName);
			if (this.choiceInstrumentClass == null) {
				this.choiceInstrumentClass = ontology.createClass(choiceInstrumentClassName);
			}

			// Creamos la clase padre.
			OntClass superClass = super.initClass(ontology);
			superClass.addSubClass(this.choiceInstrumentClass);
		}

		// Cargamos las relaciones.
		String option = this.namespace + Constants.Ontology.PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION;
		if (this.haveOption == null) {
			this.haveOption = ontology.getObjectProperty(option);
			if (this.haveOption == null) {
				this.haveOption = ontology.createObjectProperty(option);
				this.haveOption.addDomain(this.choiceInstrumentClass);
				this.haveOption.addRange(this.optionFactoryRdf.topClassHierachy(ontology));
			}
		}

		return this.choiceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();
		for (Option option : entity.getOptions()) {
			statements.add(ontology.createLiteralStatement(individual, this.haveOption, this.optionFactoryRdf.loadEntityToOntology(ontology, option)));
		}
		ontology.add(statements);

		return individual;
	}
}