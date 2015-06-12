package com.proyecto.ontology.factory.option.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.Option;
import com.proyecto.model.option.TrueOption;
import com.proyecto.ontology.factory.option.OptionFactoryRdf;
import com.proyecto.ontology.rdf.option.DistractorRdf;
import com.proyecto.ontology.rdf.option.OptionRdf;
import com.proyecto.ontology.rdf.option.TrueOptionRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de las opciones dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class OptionFactoryRdfImpl implements OptionFactoryRdf {

	private static final long serialVersionUID = 3268962980372820126L;

	/**
	 * El servicio para la clase superior.
	 */
	@Autowired
	@Qualifier("OptionRdf")
	private OptionRdf<Option> optionRdf;

	/**
	 * Los servicios para las opciones.
	 */
	@Autowired
	private TrueOptionRdf trueOptionRdf;

	@Autowired
	private DistractorRdf distractorRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.optionRdf.initClass(ontology);
	}

	@Override
	public Individual loadEntityToOntology(OntModel ontology, Option option) {

		if (option instanceof TrueOption) {
			TrueOption entity = (TrueOption) option;
			return this.trueOptionRdf.createIndividual(ontology, entity);
		}

		if (option instanceof Distractor) {
			Distractor entity = (Distractor) option;
			return this.distractorRdf.createIndividual(ontology, entity);
		}

		return null;
	}
}