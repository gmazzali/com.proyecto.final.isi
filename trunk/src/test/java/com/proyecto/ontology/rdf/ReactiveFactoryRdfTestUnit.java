package com.proyecto.ontology.rdf;

import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.rdf.material.reactive.factory.ReactiveFactoryRdf;

/**
 * La clase de prueba para la factoría de los reactivos en la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveFactoryRdfTestUnit {

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
	 * Las pruebas sobre la factoria de los reactivos.
	 */
	@Test
	public void pruebaDeLaFactoriaDeLosReactivos() {
		
		System.out.println("######################################################################");
		System.out.println("################# FACTORÍA DE REACTIVO EN ONTOLOGÍAS #################");
		System.out.println("######################################################################");

		Reactive reactive1 = CreateExampleMaterial.createReactive(10, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(10));
		Reactive reactive2 = CreateExampleMaterial.createReactive(20, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(20));
		Reactive reactive3 = CreateExampleMaterial.createReactive(30, CreateExampleMaterial.createInstrumentSingleChoice(30));
		Reactive reactive4 = CreateExampleMaterial.createReactive(40, CreateExampleMaterial.createInstrumentMultipleChoice(40));
		Reactive reactive5 = CreateExampleMaterial.createReactive(50, CreateExampleMaterial.createInstrumentCompletion(50));
		Reactive reactive6 = CreateExampleMaterial.createReactive(60, CreateExampleMaterial.createInstrumentCorrespondence(60));

		OntModel ontology = ModelFactory.createOntologyModel();

		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive1);
		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive2);
		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive3);
		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive4);
		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive5);
		HolderApplicationContext.getBean(ReactiveFactoryRdf.class).loadInstrumentToOntology(ontology, reactive6);

		ontology.write(System.out);

		try {
			String archivo = System.getProperty("proyecto.configuration.dir") + "/ontology.rdf";
			FileOutputStream salida = new FileOutputStream(archivo);
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
