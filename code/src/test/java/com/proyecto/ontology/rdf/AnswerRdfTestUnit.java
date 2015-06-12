package com.proyecto.ontology.rdf;

import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.Constants;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.ontology.rdf.answer.CompletionAnswerRdf;
import com.proyecto.ontology.rdf.answer.EssayActivityAnswerRdf;
import com.proyecto.ontology.rdf.answer.RelationAnswerRdf;
import com.proyecto.ontology.rdf.answer.TrueFalseAnswerRdf;

/**
 * La clase de prueba para las respuestas en la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AnswerRdfTestUnit {

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
	 * Las pruebas sobre las respuestas.
	 */
	@Test
	public void pruebaDeLasRespuestas() {
		
		System.out.println("######################################################################");
		System.out.println("###################### RESPUESTAS EN ONTOLOGÍAS ######################");
		System.out.println("######################################################################");
		
		// Creamos un par de respuestas.
		CompletionAnswer completionAnswer = new CompletionAnswer();
		completionAnswer.setId(1);
		completionAnswer.setIndex(1);
		completionAnswer.setPhrase("completionAnswer phrase");

		EssayActivityAnswer essayActivityAnswer = new EssayActivityAnswer();
		essayActivityAnswer.setId(2);
		essayActivityAnswer.setAnswer("essayActivityAnswer answer");

		RelationAnswer relationAnswer = new RelationAnswer();
		relationAnswer.setId(3);
		relationAnswer.setLeftSide("relationAnswer left side");
		relationAnswer.setRightSide("relationAnswer right side");

		TrueFalseAnswer trueFalseAnswer = new TrueFalseAnswer();
		trueFalseAnswer.setId(4);
		trueFalseAnswer.setValue(true);

		OntModel ontology = ModelFactory.createOntologyModel();
		ontology.setNsPrefix(Constants.PREFIX, Constants.NS);

		HolderApplicationContext.getBean(CompletionAnswerRdf.class).createIndividual(ontology, completionAnswer);

		HolderApplicationContext.getBean(EssayActivityAnswerRdf.class).createIndividual(ontology, essayActivityAnswer);

		HolderApplicationContext.getBean(RelationAnswerRdf.class).createIndividual(ontology, relationAnswer);

		HolderApplicationContext.getBean(TrueFalseAnswerRdf.class).createIndividual(ontology, trueFalseAnswer);

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