package com.proyecto.ontology.rdf.option;

import com.proyecto.model.option.Option;
import com.proyecto.ontology.rdf.ProyectoRdf;

/**
 * La interfaz que define el comportamiento de las opciones que tenemos dentro de una ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <O>
 *            La clase de opción que vamos a manejar dentro de la ontología.
 */
public interface OptionRdf<O extends Option> extends ProyectoRdf<O> {
}