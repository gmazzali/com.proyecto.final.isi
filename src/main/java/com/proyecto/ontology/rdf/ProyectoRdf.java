package com.proyecto.ontology.rdf;

import com.common.util.model.Persistence;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * La interfaz que define el comportamiento base para las ontologías en RDF dentro del proyecto.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de entidad que vamos a almacenar y manipular dentro de las ontologías.
 */
public interface ProyectoRdf<E extends Persistence<?>> {

	/**
	 * La función encargada de crear la clase dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a crear la clase.
	 * @return La clase que creamos dentro de la ontología.
	 */
	public OntClass createClass(OntModel ontology);

	/**
	 * La función encargada de cargar los datos desde una entidad a un individuo dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a cargar los datos.
	 * @param individual
	 *            El individuo donde vamos a cargar los datos.
	 * @param entity
	 *            La entidad desde el cual vamos a sacar los datos.
	 * @return El individuo con los datos cargados.
	 */
	public Individual loadEntityData(OntModel ontology, Individual individual, E entity);

	/**
	 * La función encargada de crear un nuevo individuo a partir de una entidad.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a crear el individuo o vamos a editarlo.
	 * @param entity
	 *            La entidad desde donde vamos a obtener los datos a cargar al individuo.
	 * @return El individuo que cargamos dentro de loa ontología con los datos de la entidad.
	 */
	public Individual createIndividual(OntModel ontology, E entity);
}