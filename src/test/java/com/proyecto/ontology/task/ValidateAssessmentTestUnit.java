package com.proyecto.ontology.task;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;

/**
 * La clase de pruebas del proceso de validación de las evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ValidateAssessmentTestUnit {

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
	// @Test
	public void pruebaDeLaCargaDeLaEvaluacionAlValidador() {

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
		ValidateAssessment task = HolderApplicationContext.getBean(ValidateAssessment.class);

		StringBuffer output = new StringBuffer();
		task.initValidateTask(assessment, null);
		task.executeTask(output);
		System.out.println(output.toString());
	}

	/**
	 * Las pruebas sobre la validación de evaluaciones.
	 */
	@Test
	public void pruebaDeLaValidacionCompleta() {

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

		// Creamos el conjunto de reglas.
		RuleSet set = new RuleSet();
		set.setId(1);

		// Creamos una regla que falle.
		Rule rule = new Rule();
//		rule.setId(1);
//		rule.setDescription("Regla que falla por multiple choice");
//		rule.setRule("[(?v rb:validation on()), (?x rdf:type http://www.assessments.com/#MultipleChoiceInstrument) -> (?x rb:violation error('error', 'Error de multiple choice', ?x))]");
//		set.addRule(rule);
//
//		rule = new Rule();
//		rule.setId(2);
//		rule.setDescription("Regla que falla por simple choice");
//		rule.setRule("[(?v rb:validation on()), (?x rdf:type http://www.assessments.com/#SingleChoiceInstrument) -> (?x rb:violation error('error', 'Error de simple choice', ?x))]");
//		set.addRule(rule);
//
//		rule = new Rule();
//		rule.setId(3);
//		rule.setDescription("Regla que falla por correspondencia");
//		rule.setRule("[(?v rb:validation on()), (?x rdf:type http://www.assessments.com/#CorrespondenceInstrument) -> (?x rb:violation error('error', 'Error de correspondencia', ?x))]");
//		set.addRule(rule);
//
//		rule = new Rule();
//		rule.setId(4);
//		rule.setDescription("Regla que no falla por portfolio");
//		rule.setRule("[(?v rb:validation on()), (?x rdf:type http://www.assessments.com/#PortfolioInstrument) -> (?x rb:violation error('error', 'Error de portfolio', ?x))]");
//		set.addRule(rule);

		rule = new Rule();
		rule.setId(5);
		rule.setDescription("Regla compleja de conteo de reactivos en actividad");
//		rule.setRule("[(?v rb:validation on()), (?a rdf:type http://www.assessments.com/#Activity), (?a http://www.assessments.com/#activityHasReactives ?l), listLength(?l, ?len), lessThan(?len, 1) -> (?x rb:violation error('error', 'Error de conteo de reactivos en actividad', ?a))]");
		rule.setRule("[(?v rb:validation on()), (?a rdf:type http://www.assessments.com/#Activity), (?a http://www.assessments.com/#activityHasReactives ?l) -> print('Salida de la evaluación', ?a, ?l)]");
		set.addRule(rule);

		rule = new Rule();
		rule.setId(6);
		rule.setDescription("Regla que no parsea");
		rule.setRule("regla sin parseo");
		set.addRule(rule);

		// Cargamos la evaluación en el validador y lo ejecutamos.
		ValidateAssessment task = HolderApplicationContext.getBean(ValidateAssessment.class);

		StringBuffer output = new StringBuffer();
		task.initValidateTask(assessment, set);
		task.executeTask(output);
		System.out.println(output.toString());
	}
}