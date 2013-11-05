package com.proyecto.ontology.rdf;

import com.common.util.model.Persistence;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.ontology.OntologyConstants;

/**
 * La clase que implementa la interfaz que define el comportamiento de los componentes dentro de la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de entidad que vamos a manejar dentro de la ontolog�a.
 */
public abstract class ProyectoRdfImpl<E extends Persistence<?>> implements ProyectoRdf<E> {

	private static final long serialVersionUID = -1430642317860912928L;

	/**
	 * El nombre de la ontolog�a.
	 * 
	 * @see OntologyConstants#NAMESPACE
	 */
	protected String namespace = OntologyConstants.NAMESPACE;

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