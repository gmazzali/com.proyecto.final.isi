package com.proyecto.ontology;

import java.io.FileOutputStream;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Pruebas varias de ontologías.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class OntologyTest {
	
	protected static final String NS = "http://www.test.com/#";

	public static void main(String[] args) {

		OntModel ontology = ModelFactory.createOntologyModel();

		// Creamos las clases.
		OntClass persona = ontology.createClass(OntologyTest.NS + "Persona");
		OntClass trabajador = ontology.createClass(OntologyTest.NS + "Trabajador");
		persona.addSubClass(trabajador);

		Individual personaInd = persona.createIndividual(OntologyTest.NS + "persona_1");
		Individual trabajadorInd = trabajador.createIndividual(OntologyTest.NS + "trabajador_1");

		DatatypeProperty nombre = ontology.createDatatypeProperty(OntologyTest.NS + "have_nombre");
		DatatypeProperty trabajo = ontology.createDatatypeProperty(OntologyTest.NS + "have_trabajo");

		ontology.add(ontology.createLiteralStatement(personaInd, nombre, "nombre"));
		ontology.add(ontology.createLiteralStatement(trabajadorInd, trabajo, "trabajo"));

		try {
			FileOutputStream salida = new FileOutputStream("C:/Users/Guillermo Mazzali/Desktop/salida.rdf");
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ontology.write(System.out);
	}
}