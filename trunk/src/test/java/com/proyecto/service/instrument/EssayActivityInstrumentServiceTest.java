package com.proyecto.service.instrument;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.service.material.instrument.RestrictedEssayActivityInstrumentService;
import com.proyecto.service.material.instrument.UnrestrictedEssayActivityInstrumentService;

/**
 * La clase que usamos para probar los instrumentos de ensayos formales.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class EssayActivityInstrumentServiceTest {
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
	public void pruebaDeCreacionDeEnsayosRestringidos() {
		// Creamos un nuevo instrumento.
		EssayActivityAnswer answer = null;
		RestrictedEssayActivityInstrument instrument = new RestrictedEssayActivityInstrument();

		System.out.println("######################################################################");
		System.out.println("################# INSTRUMENTOS DE ENSAYO RESTRINGIDO #################");
		System.out.println("######################################################################");

		RestrictedEssayActivityInstrumentService service = HolderApplicationContext.getContext().getBean(
				RestrictedEssayActivityInstrumentService.class);

		instrument.setDescription("Descripcion de ensayo formal restringido");

		answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta restringida 1");

		instrument.setAnswer(answer);

		try {
			System.out.println("<<<<<<<<<<<< GUARDADO DE INSTRUMENTO DE ENSAYO RESTRINGIDO >>>>>>>>>>>>");
			service.save(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el instrumento.
		instrument.setDescription("Otra descripcion de ensayo formal restringido 1");
		instrument.setAnswer(null);

		try {
			System.out.println("<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE ENSAYO RESTRINGIDO >>>>>>>>>>");
			service.update(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta restringida 2");
		instrument.setAnswer(answer);

		try {
			System.out.println("<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE ENSAYO RESTRINGIDO >>>>>>>>>>");
			service.update(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Lo borramos.
		try {
			System.out.println("<<<<<<<<<<<< BORRADO DE INSTRUMENTOS DE ENSAYO RESTRINGIDO >>>>>>>>>>>>");
			service.delete(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La prueba de creación del instrumento.
	 */
	@Test
	public void pruebaDeCreacionDeEnsayoNorestringido() {
		// Creamos un nuevo instrumento.
		EssayActivityAnswer answer = null;
		UnrestrictedEssayActivityInstrument instrument = new UnrestrictedEssayActivityInstrument();

		System.out.println("######################################################################");
		System.out.println("############### INSTRUMENTOS DE ENSAYOS NO RESTRINGIDO ###############");
		System.out.println("######################################################################");

		UnrestrictedEssayActivityInstrumentService service = HolderApplicationContext.getContext().getBean(
				UnrestrictedEssayActivityInstrumentService.class);

		instrument.setDescription("Descripcion de ensayo formal no restringido");

		answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta no restringida 1");

		instrument.setAnswer(answer);

		try {
			System.out.println("<<<<<<<<<< GUARDADO DE INSTRUMENTO DE ENSAYOS NO RESTRINGIDO >>>>>>>>>>");
			service.save(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el instrumento.
		instrument.setDescription("Otra descripcion de ensayo formal no restringido 1");
		instrument.setAnswer(null);

		try {
			System.out.println("<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE ENSAYOS NO RESTRINGIDO >>>>>>>>");
			service.update(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta no restringida 2");
		instrument.setAnswer(answer);

		try {
			System.out.println("<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE ENSAYO NO RESTRINGIDO >>>>>>>>");
			service.update(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Lo borramos.
		try {
			System.out.println("<<<<<<<<<< BORRADO DE INSTRUMENTOS DE ENSAYO NO RESTRINGIDO >>>>>>>>>>");
			service.delete(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}