package com.proyecto.ontology.rdf;

import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.Constants;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.rdf.material.reactive.ReactiveRdf;

/**
 * La clase de prueba para los reactivos en la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveRdfTestUnit {

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
	 * Las pruebas sobre los reactivos.
	 */
	@Test
	public void pruebaDeLosReactivos() {
		
		System.out.println("######################################################################");
		System.out.println("####################### REACTIVOS EN ONTOLOGÍA #######################");
		System.out.println("######################################################################");

		Reactive reactive1 = CreateExampleMaterial.createReactive(10, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(10));
		Reactive reactive2 = CreateExampleMaterial.createReactive(20, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(20));
		Reactive reactive3 = CreateExampleMaterial.createReactive(30, CreateExampleMaterial.createInstrumentSingleChoice(30));
		Reactive reactive4 = CreateExampleMaterial.createReactive(40, CreateExampleMaterial.createInstrumentMultipleChoice(40));
		Reactive reactive5 = CreateExampleMaterial.createReactive(50, CreateExampleMaterial.createInstrumentCompletion(50));
		Reactive reactive6 = CreateExampleMaterial.createReactive(60, CreateExampleMaterial.createInstrumentCorrespondence(60));

		OntModel ontology = ModelFactory.createOntologyModel();
		ontology.setNsPrefix(Constants.PREFIX, Constants.NS);

		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive1);
		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive2);
		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive3);
		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive4);
		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive5);
		HolderApplicationContext.getBean(ReactiveRdf.class).createIndividual(ontology, reactive6);

		ontology.write(System.out, Constants.MODE);

		try {
			String archivo = System.getProperty("proyecto.configuration.dir") + "/ontology.rdf";
			FileOutputStream salida = new FileOutputStream(archivo);
			ontology.write(salida, Constants.MODE);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
