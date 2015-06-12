package com.proyecto.ontology.factory;

import java.io.Serializable;

import com.common.util.model.Persistence;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * La interfaz que define el comportamiento de la manipulación de las entidades dentro de una ontología. Esta factoría sirve para el caso en que una
 * entidad tenga un conjunto de entidades hijas y que las mismas tengan un comportamiento propio de acuerdo al tipo recibido, por lo que verifica el
 * tipo de entidad hija recibida y ejecuta las acciones correspondiente.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface FactoryRdf<E extends Persistence<?>> extends Serializable {

	/**
	 * La función encargada de inicializar la clase superior de la jerarquía de la entidad.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a cargar la clase superior de la jerarquía de la entidad.
	 * @return La clase que corresponde a la cima de la jerarquía de entidades dentro de la ontología.
	 */
	public OntClass topClassHierachy(OntModel ontology);

	/**
	 * La función encargada de cargar una entidad recibida dentro de la ontología.
	 * 
	 * @param ontology
	 *            La ontología donde vamos a cargar la entidad.
	 * @param entity
	 *            La entidad que vamos a cargar dentro de la ontología.
	 * @return El individuo que acabamos de cargar dentro de la ontología con los datos propios de la entidad recibida.
	 */
	public Individual loadEntityToOntology(OntModel ontology, E entity);
}
