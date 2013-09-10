package com.proyecto.ontology.material;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.proyecto.model.material.Material;

/**
 * La interfaz que define el comportamiento de los materiales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase que extiende a los materiales.
 */
public interface MaterialOntology<M extends Material<?>> {

	/**
	 * La función encargada de crear la clase de instrumento dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a crear la clase de instrumento.
	 * @return La clase de instrumento que creamos dentro de la ontología.
	 */
	public OntClass createClass(OntModel ontology);

	/**
	 * La función encargada de cargar los datos desde un material a un individuo dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a cargar los datos.
	 * @param individual
	 *            El individuo donde vamos a cargar los datos.
	 * @param material
	 *            El material desde el cual vamos a sacar los datos.
	 * @return El individuo con los datos cargados.
	 */
	public Individual loadMaterialData(OntModel ontology, Individual individual, M material);
}