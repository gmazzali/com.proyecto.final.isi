package com.proyecto.service.instrument;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.service.material.instrument.CompletionInstrumentService;

/**
 * La clase que usamos para probar los instrumentos de completar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CompletionInstrumentServiceTest {

	/**
	 * Antes de que arranque la ejecución de la clase, cargamos el dao.
	 */
	@BeforeClass
	public static void beforeClass() {
		String[] files =
			{ "/com/proyecto/spring/general-application-context.xml" };
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
	 * La prueba de creación del instrumento.
	 */
	@Test
	public void pruebaDeCreacionDeCompletar() {
		// Creamos un nuevo instrumento.
		CompletionAnswer complete = null;
		CompletionInstrument completion = new CompletionInstrument();

		System.out.println("######################################################################");
		System.out.println("##################### INSTRUMENTO PARA COMPLETAR #####################");
		System.out.println("######################################################################");

		completion.setDescription("Descripcion de completar");

		CompletionInstrumentService service = HolderApplicationContext.getContext().getBean(CompletionInstrumentService.class);

		complete = new CompletionAnswer();
		complete.setPhrase("Word 1");
		complete.setIndex(1);

		completion.addComplete(complete);

		try {
			System.out.println("<<<<<<<<<<<<<<<< GUARDADO DE INSTRUMENTO DE COMPLETAR >>>>>>>>>>>>>>>>");
			service.save(completion);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		completion.removeComplete(complete);

		complete = new CompletionAnswer();
		complete.setPhrase("Word 2");
		complete.setIndex(2);

		// Modificamos el instrumento.
		completion.setDescription("Otra descripcion de completar 1");
		completion.addComplete(complete);

		try {
			System.out.println("<<<<<<<<<<<<<< ACTUALIZADO DEL INSTRUMENTO DE COMPLETAR >>>>>>>>>>>>>>");
			service.update(completion);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Lo borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<< BORRADO DEL INSTRUMENTO DE COMPLETAR >>>>>>>>>>>>>>>>");
			service.delete(completion);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
