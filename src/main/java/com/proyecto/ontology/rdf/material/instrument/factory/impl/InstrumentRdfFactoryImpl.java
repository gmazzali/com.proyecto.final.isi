package com.proyecto.ontology.rdf.material.instrument.factory.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.EssayInstrument;
import com.proyecto.model.material.instrument.ExerciseInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.PortfolioInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.CompletionInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.ConceptualMapInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.CorrespondenceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.EssayInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.ExerciseInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.InstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.MultipleChoiceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.PortfolioInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.RestrictedEssayActivityInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.SingleChoiceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.UnrestrictedEssayActivityInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.factory.InstrumentFactoryRdf;

/**
 * La clase que implementa la interfaz que define el comportamiento de la manipulación de los instrumentos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class InstrumentRdfFactoryImpl implements InstrumentFactoryRdf {

	private static final long serialVersionUID = 5888104376867686724L;

	/**
	 * El servicio de la clase superior de la jerarquía de instrumentos.
	 */
	@Autowired
	@Qualifier("InstrumentRdf")
	private InstrumentRdf<Instrument> instrumentRdf;

	/**
	 * Los servicios de los instrumentos dentro de la ontología.
	 */
	@Autowired
	private RestrictedEssayActivityInstrumentRdf restrictedEssayActivityInstrumentRdf;

	@Autowired
	private UnrestrictedEssayActivityInstrumentRdf unrestrictedEssayActivityInstrumentRdf;

	@Autowired
	private SingleChoiceInstrumentRdf singleChoiceInstrumentRdf;

	@Autowired
	private MultipleChoiceInstrumentRdf multipleChoiceInstrumentRdf;

	@Autowired
	private CompletionInstrumentRdf completionInstrumentRdf;

	@Autowired
	private CorrespondenceInstrumentRdf correspondenceInstrumentRdf;

	@Autowired
	private ConceptualMapInstrumentRdf conceptualMapInstrumentRdf;

	@Autowired
	private EssayInstrumentRdf essayInstrumentRdf;

	@Autowired
	private ExerciseInstrumentRdf exerciseInstrumentRdf;

	@Autowired
	private PortfolioInstrumentRdf portfolioInstrumentRdf;

	@Override
	public OntClass topClassHierachy(OntModel ontology) {
		return this.instrumentRdf.initClass(ontology);
	}

	@Override
	public Individual loadInstrumentToOntology(OntModel ontology, Instrument instrument) {

		if (instrument instanceof RestrictedEssayActivityInstrument) {
			RestrictedEssayActivityInstrument entity = (RestrictedEssayActivityInstrument) instrument;
			return this.restrictedEssayActivityInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof UnrestrictedEssayActivityInstrument) {
			UnrestrictedEssayActivityInstrument entity = (UnrestrictedEssayActivityInstrument) instrument;
			return this.unrestrictedEssayActivityInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof SingleChoiceInstrument) {
			SingleChoiceInstrument entity = (SingleChoiceInstrument) instrument;
			return this.singleChoiceInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof MultipleChoiceInstrument) {
			MultipleChoiceInstrument entity = (MultipleChoiceInstrument) instrument;
			return this.multipleChoiceInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof CorrespondenceInstrument) {
			CorrespondenceInstrument entity = (CorrespondenceInstrument) instrument;
			return this.correspondenceInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof CompletionInstrument) {
			CompletionInstrument entity = (CompletionInstrument) instrument;
			return this.completionInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof ConceptualMapInstrument) {
			ConceptualMapInstrument entity = (ConceptualMapInstrument) instrument;
			return this.conceptualMapInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof EssayInstrument) {
			EssayInstrument entity = (EssayInstrument) instrument;
			return this.essayInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof ExerciseInstrument) {
			ExerciseInstrument entity = (ExerciseInstrument) instrument;
			return this.exerciseInstrumentRdf.createIndividual(ontology, entity);
		}

		if (instrument instanceof PortfolioInstrument) {
			PortfolioInstrument entity = (PortfolioInstrument) instrument;
			return this.portfolioInstrumentRdf.createIndividual(ontology, entity);
		}

		return null;
	}
}