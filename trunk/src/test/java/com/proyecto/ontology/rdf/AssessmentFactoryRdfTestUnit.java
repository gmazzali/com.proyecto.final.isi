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
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.ontology.factory.material.assessment.AssessmentFactoryRdf;

/**
 * La clase de prueba para la factoría de las evaluaciones en la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AssessmentFactoryRdfTestUnit {

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
	 * Las pruebas sobre la factoría de las actividades.
	 */
	@Test
	public void pruebaDeLaFactoriaDeLasActividades() {

		System.out.println("######################################################################");
		System.out.println("############### FACTORÍA DE EVALUACIONES EN ONTOLOGÍAS ###############");
		System.out.println("######################################################################");

		Reactive reactive1 = CreateExampleMaterial.createReactive(10, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(10));
		Reactive reactive2 = CreateExampleMaterial.createReactive(20, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(20));
		Activity activity1 = CreateExampleMaterial.createActivity(10, reactive1, reactive2);
		activity1.setDescription("1 essay activity");

		Reactive reactive3 = CreateExampleMaterial.createReactive(30, CreateExampleMaterial.createInstrumentSingleChoice(30));
		Reactive reactive4 = CreateExampleMaterial.createReactive(40, CreateExampleMaterial.createInstrumentMultipleChoice(40));
		Reactive reactive5 = CreateExampleMaterial.createReactive(50, CreateExampleMaterial.createInstrumentCompletion(50));
		Reactive reactive6 = CreateExampleMaterial.createReactive(60, CreateExampleMaterial.createInstrumentCorrespondence(60));
		Activity activity2 = CreateExampleMaterial.createActivity(20, reactive3, reactive4, reactive5, reactive6);
		activity2.setDescription("1 objective activity");

		Reactive reactive7 = CreateExampleMaterial.createReactive(70, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(70));
		Reactive reactive8 = CreateExampleMaterial.createReactive(80, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(80));
		Activity activity3 = CreateExampleMaterial.createActivity(30, reactive7, reactive8);
		activity1.setDescription("2 essay activity");

		Reactive reactive9 = CreateExampleMaterial.createReactive(90, CreateExampleMaterial.createInstrumentSingleChoice(90));
		Reactive reactive10 = CreateExampleMaterial.createReactive(100, CreateExampleMaterial.createInstrumentMultipleChoice(100));
		Reactive reactive11 = CreateExampleMaterial.createReactive(110, CreateExampleMaterial.createInstrumentCompletion(110));
		Reactive reactive12 = CreateExampleMaterial.createReactive(120, CreateExampleMaterial.createInstrumentCorrespondence(120));
		Activity activity4 = CreateExampleMaterial.createActivity(40, reactive9, reactive10, reactive11, reactive12);
		activity2.setDescription("2 objective activity");

		Assessment assessment = CreateExampleMaterial.createAssessment(10, activity1, activity2, activity3, activity4);

		OntModel ontology = ModelFactory.createOntologyModel();
		ontology.setNsPrefix(Constants.PREFIX, Constants.NS);

		HolderApplicationContext.getBean(AssessmentFactoryRdf.class).loadEntityToOntology(ontology, assessment);

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
