package com.proyecto.ontology.rdf.material.instrument.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.model.option.Option;
import com.proyecto.ontology.OntologyConstants;
import com.proyecto.ontology.factory.option.OptionFactoryRdf;
import com.proyecto.ontology.rdf.material.instrument.ChoiceInstrumentRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales objetivos de selección dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase de instrumentos formales objetivos de selección que vamos a manejar dentro de la ontología.
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
	 * La clase del instrumento formal objetivo de selección.
	 */
	private OntClass choiceInstrumentClass;
	/**
	 * Las relaciones del instrumento formal objetivo de selección.
	 */
	private ObjectProperty haveOption;

	@Override
	public OntClass initClass(OntModel ontology) {
		// Creamos la clase si es nula.
		String choiceInstrumentClassName = this.namespace + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE;
		this.choiceInstrumentClass = ontology.getOntClass(choiceInstrumentClassName);
		if (this.choiceInstrumentClass == null) {
			this.choiceInstrumentClass = ontology.createClass(choiceInstrumentClassName);
		}

		// Creamos la clase padre.
		OntClass superClass = super.initClass(ontology);
		superClass.addSubClass(this.choiceInstrumentClass);

		// Cargamos las relaciones.
		String option = this.namespace + OntologyConstants.PropertyName.INSTRUMENT_CHOICE_HAS_OPTION;
		this.haveOption = ontology.getObjectProperty(option);
		if (this.haveOption == null) {
			this.haveOption = ontology.createObjectProperty(option);
			this.haveOption.addDomain(this.choiceInstrumentClass);
			this.haveOption.addRange(this.optionFactoryRdf.topClassHierachy(ontology));
		}

		return this.choiceInstrumentClass;
	}

	@Override
	public Individual loadEntityData(OntModel ontology, Individual individual, I entity) {
		// Cargamos el padre.
		individual = super.loadEntityData(ontology, individual, entity);

		// Creamos las carga de los datos.
		List<Statement> statements = new ArrayList<Statement>();

		if (entity.getOptions() != null && !entity.getOptions().isEmpty()) {
			RDFNode[] optionsNodes = new RDFNode[entity.getOptions().size()];
			int index = 0;
			for (Option option : entity.getOptions()) {
				optionsNodes[index++] = this.optionFactoryRdf.loadEntityToOntology(ontology, option);
			}
			statements.add(ontology.createLiteralStatement(individual, this.haveOption, ontology.createList(optionsNodes)));
		}

		ontology.add(statements);

		return individual;
	}
}