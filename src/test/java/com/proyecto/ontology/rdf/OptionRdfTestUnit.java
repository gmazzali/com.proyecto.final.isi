package com.proyecto.ontology.rdf;

import java.io.FileOutputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.holder.HolderApplicationContext;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.TrueOption;
import com.proyecto.ontology.rdf.option.DistractorRdf;
import com.proyecto.ontology.rdf.option.TrueOptionRdf;

/**
 * La clase de prueba para las opciones en la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class OptionRdfTestUnit {

	/**
	 * Antes de que arranque la ejecuci�n de la clase, cargamos el dao.
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
	 * Las pruebas sobre las opciones.
	 */
	@Test
	public void pruebaDeLasOpciones() {

		TrueFalseAnswer trueFalseAnswer1 = new TrueFalseAnswer();
		trueFalseAnswer1.setId(1);
		trueFalseAnswer1.setValue(true);

		// Creamos un par de opciones.
		TrueOption trueOption1 = new TrueOption();
		trueOption1.setId(1);
		trueOption1.setDescription("true option 1");
		trueOption1.setTrueFalseAnswer(trueFalseAnswer1);

		TrueFalseAnswer trueFalseAnswer2 = new TrueFalseAnswer();
		trueFalseAnswer2.setId(2);
		trueFalseAnswer2.setValue(false);

		// Creamos un par de opciones.
		Distractor distractor1 = new Distractor();
		distractor1.setId(2);
		distractor1.setDescription("distractor option 1");
		distractor1.setTrueFalseAnswer(trueFalseAnswer2);

		OntModel ontology = ModelFactory.createOntologyModel();

		TrueOptionRdf trueOptionRdf = HolderApplicationContext.getBean(TrueOptionRdf.class);
		trueOptionRdf.createIndividual(ontology, trueOption1);

		DistractorRdf distractorRdf = HolderApplicationContext.getBean(DistractorRdf.class);
		distractorRdf.createIndividual(ontology, distractor1);

		ontology.write(System.out);

		try {
			FileOutputStream salida = new FileOutputStream("C:/Users/Guillermo Mazzali/Desktop/salida.rdf");
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}