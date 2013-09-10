package com.proyecto.ontology.material;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.Material;
import com.proyecto.util.ConstantsOntology;

/**
 * La clase que implementa la interfaz para la creaci�n de materiales dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * @param <M>
 *            La clase de materiales que vamos a manejar dentro de la ontolog�a.
 */
public class MaterialOntologyImpl<M extends Material<?>> implements MaterialOntology<M> {

	@Override
	public OntClass createClass(OntModel ontology) {
		String materialClassName = ConstantsOntology.NAMESPACE + Material.class.getSimpleName();
		OntClass materialClass = ontology.getOntClass(materialClassName);

		if (materialClass == null) {
			materialClass = ontology.createClass(materialClassName);
		}

		return materialClass;
	}

	@Override
	public Individual loadMaterialData(OntModel ontology, Individual individual, M material) {
		return individual;
	}
}