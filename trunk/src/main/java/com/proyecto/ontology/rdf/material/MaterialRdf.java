package com.proyecto.ontology.rdf.material;

import com.proyecto.model.material.Material;
import com.proyecto.ontology.rdf.ProyectoRdf;

/**
 * La interfaz que define el comportamiento de los materiales dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <M>
 *            La clase de materiales que vamos a manejar dentro de la ontología.
 */
public interface MaterialRdf<M extends Material<?>> extends ProyectoRdf<M> {
}