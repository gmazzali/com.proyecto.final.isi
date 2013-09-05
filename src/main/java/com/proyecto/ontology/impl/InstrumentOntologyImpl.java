package com.proyecto.ontology.impl;

import com.common.util.annotations.Service;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.InstrumentOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de la carga de instrumentos dentro de una ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class InstrumentOntologyImpl implements InstrumentOntology {

	@Override
	public <I extends Instrument> Individual loadInstrumentToOntology(OntModel ontology, Reactive reactive, I instrument) {
		// TODO gmazzali Hacer el m�todo de carga de un instrumento en una ontolog�a.
		return ontology.createIndividual("Instrument", null);
	}
}