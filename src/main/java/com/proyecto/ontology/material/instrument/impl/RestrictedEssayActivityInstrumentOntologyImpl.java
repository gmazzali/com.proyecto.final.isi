package com.proyecto.ontology.material.instrument.impl;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.util.ConstantsOntology;

public class RestrictedEssayActivityInstrumentOntologyImpl extends EssayActivityInstrumentOntologyImpl<RestrictedEssayActivityInstrument> {

	@Override
	public OntClass createClass(OntModel ontology) {
		// Creamos u obtenemos la clase superior.
		OntClass superClass = super.createClass(ontology);

		// Creamos u obtenemos la clase hija.
		String restrictedEssayFormalInstrumentClassName = ConstantsOntology.NAMESPACE + RestrictedEssayActivityInstrument.class.getSimpleName();
		OntClass restrictedEssayFormalInstrumentClass = ontology.getOntClass(restrictedEssayFormalInstrumentClassName);

		if (restrictedEssayFormalInstrumentClass == null) {
			restrictedEssayFormalInstrumentClass = ontology.createClass(restrictedEssayFormalInstrumentClassName);
		}

		superClass.addSubClass(restrictedEssayFormalInstrumentClass);

		return restrictedEssayFormalInstrumentClass;
	}

}
