package com.proyecto.service.reactive;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.service.material.instrument.RestrictedEssayActivityInstrumentService;
import com.proyecto.service.material.reactive.ReactiveService;

/**
 * La clase que usamos para probar los reactivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ReactiveServiceTest {

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
	 * La prueba de creación del reactivo.
	 */
	@Test
	public void pruebaDeCreacionDeReactivo() {

		Reactive reactive = new Reactive();
		RestrictedEssayActivityInstrument instrument = null;

		System.out.println("######################################################################");
		System.out.println("############################## REACTIVO ##############################");
		System.out.println("######################################################################");

		reactive.setDescription("Descripcion del reactivo");

		ReactiveService reactiveService = HolderApplicationContext.getContext().getBean(ReactiveService.class);
		RestrictedEssayActivityInstrumentService instrumentService = HolderApplicationContext.getContext().getBean(
				RestrictedEssayActivityInstrumentService.class);

		instrument = new RestrictedEssayActivityInstrument();
		instrument.setDescription("descripción instrumento del reactivo");
		EssayActivityAnswer answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta instrumento reactivo");
		instrument.setAnswer(answer);

		reactive.setInstrument(instrument);

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< GUARDADO DEL REACTIVO >>>>>>>>>>>>>>>>>>>>>>>>");
			instrumentService.save(instrument);
			reactiveService.save(reactive);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la descripción del reactivo.
		reactive.setDescription("Otra descripcion de reactivo 1");

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<< ACTUALIZADO DE REACTIVO >>>>>>>>>>>>>>>>>>>>>>>");
			reactiveService.update(reactive);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el instrumento.
		RestrictedEssayActivityInstrument oldistrument = instrument;

		instrument = new RestrictedEssayActivityInstrument();
		instrument.setDescription("descripción instrumento del reactivo");
		answer = new EssayActivityAnswer();
		answer.setAnswer("Respuesta instrumento reactivo");
		instrument.setAnswer(answer);

		reactive.setInstrument(instrument);

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<< ACTUALIZADO DE REACTIVO >>>>>>>>>>>>>>>>>>>>>>>");
			instrumentService.save(instrument);
			reactiveService.update(reactive);
			instrumentService.delete(oldistrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< BORRADO DE REACTIVO >>>>>>>>>>>>>>>>>>>>>>>>>");
			reactiveService.delete(reactive);
			instrumentService.delete(instrument);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}