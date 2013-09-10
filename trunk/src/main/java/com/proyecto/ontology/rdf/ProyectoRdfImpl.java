package com.proyecto.ontology.rdf;

import com.common.util.model.Persistence;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * La clase que implementa la interfaz que define el comportamiento de los componentes dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de entidad que vamos a manejar dentro de la ontología.
 */
public abstract class ProyectoRdfImpl<E extends Persistence<?>> implements ProyectoRdf<E> {

	@Override
	public Individual createIndividual(OntModel ontology, E entity) {
		// Creamos la clase.
		OntClass entityClass = this.initClass(ontology);

		// Recuperamos o creamos el individuo.
		String entityIndividualName = entityClass.getNameSpace() + entityClass.getLocalName() + "_" + entity.getId();
		Individual entityIndividual = ontology.getIndividual(entityIndividualName);

		if (entityIndividual == null) {
			entityIndividual = entityClass.createIndividual(entityIndividualName);
		}

		// Cargamos la entidad dentro del individuo.
		entityIndividual = this.loadEntityData(ontology, entityIndividual, entity);

		return entityIndividual;
	}
}