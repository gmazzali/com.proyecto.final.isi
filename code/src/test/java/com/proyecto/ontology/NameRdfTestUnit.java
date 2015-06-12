package com.proyecto.ontology;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;

/**
 * La clase que nos permite probar los nombre que vamos a usar dentro de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class NameRdfTestUnit {

	/**
	 * Antes de que arranque la ejecución de la clase.
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
	 * Las pruebas sobre los nombre de las clases.
	 */
	@Test
	public void pruebaDeLosNombreDeLasClases() {
		System.out.println("######################################################################");
		System.out.println("############### LOS NOMBRES DE LAS CLASES DE ONTOLOGÍA ###############");
		System.out.println("######################################################################");

		System.out.println("EVALUACION: " + OntologyConstants.ClassName.ASSESSMENT);
		System.out.println("ACTIVIDAD: " + OntologyConstants.ClassName.ACTIVITY);
		System.out.println("REACTIVO: " + OntologyConstants.ClassName.REACTIVE);
		System.out.println("INSTRUMENTO: " + OntologyConstants.ClassName.INSTRUMENT);
		System.out.println("INSTRUMENTO FORMAL: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL);
		System.out.println("INSTRUMENTO FORMAL ENSAYO: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY);
		System.out.println("INSTRUMENTO FORMAL ENSAYO RESTRICTO: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY_RESTRICTED);
		System.out.println("INSTRUMENTO FORMAL ENSAYO NO RESTRICTO: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY_UNRESTRICTED);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO SELECCION: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO SELECCION SIMPLE: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_SINGLE);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO SELECCION MULTIPLE: "
				+ OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_MULTIPLE);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO COMPLETAR: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_COMPLETION);
		System.out.println("INSTRUMENTO FORMAL OBJECTIVO CORRESPONDENCIA: " + OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CORRESPONDENCE);
		System.out.println("INSTRUMENTO SEMIFORMAL: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL);
		System.out.println("INSTRUMENTO SEMIFORMAL SIMPLE: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE);
		System.out.println("INSTRUMENTO SEMIFORMAL SIMPLE MAPA: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_CONCEPTUAL_MAP);
		System.out.println("INSTRUMENTO SEMIFORMAL SIMPLE ENSAYO: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_ESSAY);
		System.out.println("INSTRUMENTO SEMIFORMAL SIMPLE EJERCICIO: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_EXERCISE);
		System.out.println("INSTRUMENTO SEMIFORMAL COMPUESTO: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_COMPOSITE);
		System.out.println("INSTRUMENTO SEMIFORMAL COMPUESTO PORTOLIO: " + OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_COMPOSITE_PORTFOLIO);

		System.out.println("OPCION: " + OntologyConstants.ClassName.OPTION);
		System.out.println("OPCION VERDADERA: " + OntologyConstants.ClassName.OPTION_TRUE);
		System.out.println("OPCION FALSA: " + OntologyConstants.ClassName.OPTION_DISTRACTOR);

		System.out.println("RESPUESTA: " + OntologyConstants.ClassName.ANSWER);
		System.out.println("RESPUESTA BOOLEANA: " + OntologyConstants.ClassName.ANSWER_TRUEFALSE);
		System.out.println("RESPUESTA COMPLETAR: " + OntologyConstants.ClassName.ANSWER_COMPLETE);
		System.out.println("RESPUESTA ENSAYO: " + OntologyConstants.ClassName.ANSWER_TEXT);
		System.out.println("RESPUESTA RELACION: " + OntologyConstants.ClassName.ANSWER_RELATION);
	}

	/**
	 * Las pruebas sobre los nombre de las propiedades.
	 */
	@Test
	public void pruebaDeLosNombreDeLasPropiedades() {
		System.out.println("######################################################################");
		System.out.println("########### LOS NOMBRES DE LAS PROPIEDADES DE LA ONTOLOGÍA ###########");
		System.out.println("######################################################################");

		System.out.println("EVALUACION DESCRIPCION: " + OntologyConstants.PropertyName.ASSESSMENT_HAS_DESCRIPTION);
		System.out.println("EVALUACION FECHA: " + OntologyConstants.PropertyName.ASSESSMENT_HAS_DATE);
		System.out.println("EVALUACION MOMENTO: " + OntologyConstants.PropertyName.ASSESSMENT_HAS_MOMENT);
		System.out.println("EVALUACION ACTIVIDADES: " + OntologyConstants.PropertyName.ASSESSMENT_HAS_ACTIVITY);

		System.out.println("ACTIVIDAD EVALUACION: " + OntologyConstants.PropertyName.ACTIVITY_BELONG_TO_ASSESSMENT);
		System.out.println("ACTIVIDAD DESCRIPCION: " + OntologyConstants.PropertyName.ACTIVITY_HAS_DESCRIPTION);
		System.out.println("ACTIVIDAD REACTIVO: " + OntologyConstants.PropertyName.ACTIVITY_HAS_REACTIVE);

		System.out.println("REACTIVO ACTIVIDAD: " + OntologyConstants.PropertyName.REACTIVE_BELONG_TO_ACTIVITY);
		System.out.println("REACTIVO DESCRIPCION: " + OntologyConstants.PropertyName.REACTIVE_HAS_DESCRIPTION);
		System.out.println("REACTIVO INSTRUMENTO: " + OntologyConstants.PropertyName.REACTIVE_HAS_INSTRUMENT);

		System.out.println("INSTRUMENTO REACTIVO: " + OntologyConstants.PropertyName.INSTRUMENT_BELONG_TO_REACTIVE);
		System.out.println("INSTRUMENTO DESCRIPCION: " + OntologyConstants.PropertyName.INSTRUMENT_HAS_DESCRIPTION);
		System.out.println("INSTRUMENTO SELECCION OPCIONES: " + OntologyConstants.PropertyName.INSTRUMENT_CHOICE_HAS_OPTION);
		System.out.println("INSTRUMENTO COMPLETAR COMPLETO: " + OntologyConstants.PropertyName.INSTRUMENT_COMPLETION_HAS_COMPLETE);
		System.out.println("INSTRUMENTO CORRESPONDENCIA RELACION: " + OntologyConstants.PropertyName.INSTRUMENT_CORRESPONDENCE_HAS_RELATION);
		System.out.println("INSTRUMENTO ENSAYO RESPUESTA: " + OntologyConstants.PropertyName.INSTRUMENT_ESSAY_HAS_ANSWER);

		System.out.println("OPCION INSTRUMENTO: " + OntologyConstants.PropertyName.OPTION_BELONG_TO_INSTRUMENT);
		System.out.println("OPCION DESCRIPCION: " + OntologyConstants.PropertyName.OPTION_HAS_DESCRIPTION);
		System.out.println("OPCION ANSWER: " + OntologyConstants.PropertyName.OPTION_HAS_ANSWER);

		System.out.println("RESPUESTA COMPLETAR INSTRUMENTO: " + OntologyConstants.PropertyName.ANSWER_COMPLETE_BELONG_TO_INSTRUMENT);
		System.out.println("RESPUESTA COMPLETAR INDICE: " + OntologyConstants.PropertyName.ANSWER_COMPLETE_HAS_INDEX);
		System.out.println("RESPUESTA COMPLETAR FRASE: " + OntologyConstants.PropertyName.ANSWER_COMPLETE_HAS_PHRASE);

		System.out.println("RESPUESTA ENSAYO INSTRUMENTO: " + OntologyConstants.PropertyName.ANSWER_ESSAY_BELONG_TO_INSTRUMENT);
		System.out.println("RESPUESTA ENSAYO DESCRIPCION: " + OntologyConstants.PropertyName.ANSWER_ESSAY_HAS_DESCRIPTION);

		System.out.println("RESPUESTA RELACION INSTRUMENTO: " + OntologyConstants.PropertyName.ANSWER_RELATION_BELONG_TO_INSTRUMENT);
		System.out.println("RESPUESTA RELACION LADO IZQUIERDO: " + OntologyConstants.PropertyName.ANSWER_RELATION_HAS_LEFT_SIDE);
		System.out.println("RESPUESTA RELACION LADO DERECHO: " + OntologyConstants.PropertyName.ANSWER_RELATION_HAS_RIGHT_SIDE);

		System.out.println("RESPUESTA BOOLEANA INSTRUMENTO: " + OntologyConstants.PropertyName.ANSWER_TRUEFALSE_BELONG_TO_OPTION);
		System.out.println("RESPUESTA BOOLEANA VALOR: " + OntologyConstants.PropertyName.ANSWER_TRUEFALSE_HAS_VALUE);
	}
}
