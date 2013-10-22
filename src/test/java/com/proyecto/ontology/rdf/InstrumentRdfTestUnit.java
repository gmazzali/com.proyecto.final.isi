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
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.ontology.rdf.material.instrument.CompletionInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.CorrespondenceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.MultipleChoiceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.RestrictedEssayActivityInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.SingleChoiceInstrumentRdf;
import com.proyecto.ontology.rdf.material.instrument.UnrestrictedEssayActivityInstrumentRdf;

/**
 * La clase de prueba para los instrumentos en la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class InstrumentRdfTestUnit {

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
	 * Las pruebas sobre los instrumentos.
	 */
	@Test
	public void pruebaDeLosInstrumentos() {
		
		System.out.println("######################################################################");
		System.out.println("##################### INSTRUMENTOS EN ONTOLOGÍAS #####################");
		System.out.println("######################################################################");

		// Creamos los instrumentos.
		RestrictedEssayActivityInstrument restrictedEssayActivityInstrument = CreateExampleMaterial.createInstrumentRestrictedEssayActivity(10);
		UnrestrictedEssayActivityInstrument unrestrictedEssayActivityInstrument = CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(20);
		SingleChoiceInstrument singleChoiceInstrument = CreateExampleMaterial.createInstrumentSingleChoice(30);
		MultipleChoiceInstrument multipleChoiceInstrument = CreateExampleMaterial.createInstrumentMultipleChoice(40);
		CompletionInstrument completionInstrument = CreateExampleMaterial.createInstrumentCompletion(50);
		CorrespondenceInstrument correspondenceInstrument = CreateExampleMaterial.createInstrumentCorrespondence(60);

		OntModel ontology = ModelFactory.createOntologyModel();
		ontology.setNsPrefix(Constants.PREFIX, Constants.NS);

		HolderApplicationContext.getBean(RestrictedEssayActivityInstrumentRdf.class).createIndividual(ontology, restrictedEssayActivityInstrument);

		HolderApplicationContext.getBean(UnrestrictedEssayActivityInstrumentRdf.class)
				.createIndividual(ontology, unrestrictedEssayActivityInstrument);

		HolderApplicationContext.getBean(SingleChoiceInstrumentRdf.class).createIndividual(ontology, singleChoiceInstrument);

		HolderApplicationContext.getBean(MultipleChoiceInstrumentRdf.class).createIndividual(ontology, multipleChoiceInstrument);

		HolderApplicationContext.getBean(CompletionInstrumentRdf.class).createIndividual(ontology, completionInstrument);

		HolderApplicationContext.getBean(CorrespondenceInstrumentRdf.class).createIndividual(ontology, correspondenceInstrument);

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