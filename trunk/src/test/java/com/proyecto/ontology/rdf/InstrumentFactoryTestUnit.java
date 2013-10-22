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
import com.proyecto.ontology.factory.material.instrument.InstrumentFactoryRdf;

/**
 * La clase de prueba para la factor�a de los instrumentos en la ontolog�a.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class InstrumentFactoryTestUnit {

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
	 * Las pruebas sobre la factor�a de los instrumentos.
	 */
	@Test
	public void pruebaDeLaFactoriaInstrumentos() {

		System.out.println("######################################################################");
		System.out.println("################ FACTOR�A DE INSTRUMENTO EN ONTOLOG�A ################");
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

		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, restrictedEssayActivityInstrument);
		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, unrestrictedEssayActivityInstrument);
		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, singleChoiceInstrument);
		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, multipleChoiceInstrument);
		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, completionInstrument);
		HolderApplicationContext.getBean(InstrumentFactoryRdf.class).loadEntityToOntology(ontology, correspondenceInstrument);

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
