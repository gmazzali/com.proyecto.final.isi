package com.proyecto.ontology.material.instrument.impl;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.EssayActivityInstrument;
import com.proyecto.model.material.instrument.FormalInstrument;
import com.proyecto.ontology.material.instrument.FormalInstrumentOntology;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz que define el comportamiento de los instrumentos formales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <I>
 *            La clase que corresponde a los tipos de instrumentos formales que vamos a manipular acá adentro.
 */
public class FormalInstrumentOntologyImpl<I extends EssayActivityInstrument> extends InstrumentOntologyImpl<I> implements FormalInstrumentOntology<I> {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String formalInstrumentClassName = ConstantsOntology.NAMESPACE + FormalInstrument.class.getSimpleName();
		OntClass formalInstrumentClass = ontology.getOntClass(formalInstrumentClassName);

		if (formalInstrumentClass == null) {
			formalInstrumentClass = ontology.createClass(formalInstrumentClassName);
		}

		superClass.addSubClass(formalInstrumentClass);

		return formalInstrumentClass;
	}

	@Override
	public Individual loadMaterialData(OntModel ontology, Individual individual, I material) {
		return individual;
	}
}