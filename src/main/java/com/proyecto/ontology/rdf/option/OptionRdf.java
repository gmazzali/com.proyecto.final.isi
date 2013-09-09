package com.proyecto.ontology.rdf.option;

import com.proyecto.model.option.Option;
import com.proyecto.ontology.rdf.ProyectoRdf;

/**
 * La interfaz que define el comportamiento de las opciones que tenemos dentro de una ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <O>
 *            La clase de opci�n que vamos a manejar dentro de la ontolog�a.
 */
public interface OptionRdf<O extends Option> extends ProyectoRdf<O> {
}