package com.proyecto.ontology;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
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
		assessmentOntology.loadAssessmentToOntology(assessment).write(System.out);
	}
}