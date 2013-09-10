package com.proyecto.ontology;

import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La clase que nos permite probar la creación de una ontología en base a una evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AssessmentOntologyTestUnit {

	/**
	 * Antes de que arranque la ejecución de la clase, cargamos el dao.
	 */
	@BeforeClass
	public static void beforeClass() {
		String[] files = { "/com/proyecto/spring/general-application-context.xml" };
		HolderApplicationContext.initApplicationContext(files);
	}

	/**
	 * Al finalizar dejamos un espacio en blanco en la consola.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * La función de prueba de creación de una ontología con una evaluación.
	 */
	@Test
	public void pruebaDeCreacionDeOntologia() {

		Reactive[] reactives = new Reactive[2];
		reactives[0] = CreateExampleMaterial.createReactive(1, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(1));
		reactives[1] = CreateExampleMaterial.createReactive(2, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(2));

		Activity essayActivity = CreateExampleMaterial.createActivity(1, reactives);
		essayActivity.setDescription("activity essay");

		reactives = new Reactive[4];
		reactives[0] = CreateExampleMaterial.createReactive(3, CreateExampleMaterial.createInstrumentSingleChoice(3));
		reactives[1] = CreateExampleMaterial.createReactive(4, CreateExampleMaterial.createInstrumentMultipleChoice(4));
		reactives[2] = CreateExampleMaterial.createReactive(5, CreateExampleMaterial.createInstrumentCorrespondence(5));
		reactives[3] = CreateExampleMaterial.createReactive(6, CreateExampleMaterial.createInstrumentCompletion(6));

		Activity objectiveActivity = CreateExampleMaterial.createActivity(2, reactives);
		objectiveActivity.setDescription("activity objective");

		Assessment assessment = CreateExampleMaterial.createAssessment(1, essayActivity, objectiveActivity);

		assessment.print(System.out);
		System.out.println("##################################################################################");
		System.out.println("##################################################################################");
		System.out.println("##################################################################################");

		AssessmentOntology assessmentOntology = HolderApplicationContext.getContext().getBean(AssessmentOntology.class);
		OntModel ontology = assessmentOntology.loadAssessmentToOntology(assessment);

		ontology.write(System.out);
		try {
			FileOutputStream salida = new FileOutputStream("C:/Users/Guillermo Mazzali/Desktop/salida.rdf");
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		assessment.print(System.out);
		System.out.println("##################################################################################");
		System.out.println("##################################################################################");
		System.out.println("##################################################################################");

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> PREFIX owl: <http://www.w3.org/2002/07/owl#> select ?class (count(?instance) as ?count)"
				+ "where {  ?class a rdfs:Class . ?instance a ?class . } group by ?class";

		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, ontology);
		ResultSet results = qe.execSelect();

		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		qe.close();

		System.out.println("##################################################################################");
		System.out.println("##################################################################################");
		System.out.println("##################################################################################");
	}
}