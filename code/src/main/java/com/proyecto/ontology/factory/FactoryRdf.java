package com.proyecto.ontology.factory;

import java.io.Serializable;

import com.common.util.model.Persistence;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;

/**
 * La interfaz que define el comportamiento de la manipulaci�n de las entidades dentro de una ontolog�a. Esta factor�a sirve para el caso en que una
 * entidad tenga un conjunto de entidades hijas y que las mismas tengan un comportamiento propio de acuerdo al tipo recibido, por lo que verifica el
 * tipo de entidad hija recibida y ejecuta las acciones correspondiente.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface FactoryRdf<E extends Persistence<?>> extends Serializable {

	/**
	 * La funci�n encargada de inicializar la clase superior de la jerarqu�a de la entidad.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la clase superior de la jerarqu�a de la entidad.
	 * @return La clase que corresponde a la cima de la jerarqu�a de entidades dentro de la ontolog�a.
	 */
	public OntClass topClassHierachy(OntModel ontology);

	/**
	 * La funci�n encargada de cargar una entidad recibida dentro de la ontolog�a.
	 * 
	 * @param ontology
	 *            La ontolog�a donde vamos a cargar la entidad.
	 * @param entity
	 *            La entidad que vamos a cargar dentro de la ontolog�a.
	 * @return El individuo que acabamos de cargar dentro de la ontolog�a con los datos propios de la entidad recibida.
	 */
	public Individual loadEntityToOntology(OntModel ontology, E entity);
}
