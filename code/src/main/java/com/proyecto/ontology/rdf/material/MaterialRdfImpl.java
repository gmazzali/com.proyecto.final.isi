package com.proyecto.ontology.rdf.material;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.Material;
import com.proyecto.ontology.rdf.ProyectoRdfImpl;

/**
 * La clase que implementa la interfaz que define el comportamiento de los materiales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase de materiales que vamos a manejar dentro de la ontología.
 */
public abstract class MaterialRdfImpl<M extends Material<?>> extends ProyectoRdfImpl<M> implements MaterialRdf<M> {

	private static final long serialVersionUID = 6920907403789436984L;

	public Individual loadEntityData(OntModel ontology, Individual individual, M entity) {
		return individual;
	};
}