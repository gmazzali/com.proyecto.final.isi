package com.proyecto.ontology.task;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.reactive.Reactive;

/**
 * La clase de pruebas del proceso de validación de las evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ValidateAssessmentTaskTestUnit {

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
	 * Las pruebas sobre la validación de evaluaciones.
	 */
	@Test
	public void pruebaDeLaFactoriaDeLasActividades() {

		System.out.println("######################################################################");
		System.out.println("#################### EL VALIDADOR DE EVALUACIONES ####################");
		System.out.println("######################################################################");

		// Creamos la evaluación a validar.
		Reactive reactive1 = CreateExampleMaterial.createReactive(10, CreateExampleMaterial.createInstrumentRestrictedEssayActivity(10));
		Reactive reactive2 = CreateExampleMaterial.createReactive(20, CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(20));
		Activity activity1 = CreateExampleMaterial.createActivity(10, reactive1, reactive2);
		activity1.setDescription("essay activity");

		Reactive reactive3 = CreateExampleMaterial.createReactive(30, CreateExampleMaterial.createInstrumentSingleChoice(30));
		Reactive reactive4 = CreateExampleMaterial.createReactive(40, CreateExampleMaterial.createInstrumentMultipleChoice(40));
		Reactive reactive5 = CreateExampleMaterial.createReactive(50, CreateExampleMaterial.createInstrumentCompletion(50));
		Reactive reactive6 = CreateExampleMaterial.createReactive(60, CreateExampleMaterial.createInstrumentCorrespondence(60));
		Activity activity2 = CreateExampleMaterial.createActivity(20, reactive3, reactive4, reactive5, reactive6);
		activity2.setDescription("objective activity");

		Assessment assessment = CreateExampleMaterial.createAssessment(10, activity1, activity2);

		// Cargamos la evaluación en el validador y lo ejecutamos.
		ValidateAssessmentTask task = HolderApplicationContext.getBean(ValidateAssessmentTask.class);
		task.initValidateTask(assessment, null);

		StringBuffer output = new StringBuffer();
		task.startTask(output);

		System.out.println(output.toString());
	}
}