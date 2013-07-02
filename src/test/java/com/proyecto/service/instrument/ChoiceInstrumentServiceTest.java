package com.proyecto.service.instrument;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.Option;
import com.proyecto.model.option.TrueOption;

/**
 * La clase que usamos para probar los instrumentos de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ChoiceInstrumentServiceTest {

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
	public void pruebaDeCreacionDeSeleccionesSimples() {
		// Creamos un nuevo instrumento.
		Option option = null;
		List<Option> options = null;
		SingleChoiceInstrument choice = new SingleChoiceInstrument();

		System.out.println("######################################################################");
		System.out.println("################## INSTRUMENTOS DE SELECCIÓN SIMPLE ##################");
		System.out.println("######################################################################");

		choice.setDescription("Descripcion de simple seleccion");

		SingleChoiceInstrumentService service = HolderApplicationContext.getContext().getBean(SingleChoiceInstrumentService.class);

		options = new ArrayList<>();
		option = new TrueOption();
		option.setDescription("Opcion 1 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new TrueOption();
		option.setDescription("Opcion 2 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 3 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 4 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		choice.getOptions().addAll(options);

		try {
			System.out.println("<<<<<<<<<<<<< GUARDADO DE INSTRUMENTO DE SELECCIÓN SIMPLE >>>>>>>>>>>>>");
			service.save(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la regla.
		choice.setDescription("Otra descripcion de simple seleccion 1");
		choice.getOptions().remove(option);

		try {
			System.out.println("<<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE SELECCIÓN SIMPLE >>>>>>>>>>>");
			service.update(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		options = new ArrayList<>();
		option = new TrueOption();
		option.setDescription("Opcion 11 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new TrueOption();
		option.setDescription("Opcion 12 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 13 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 14 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		choice.setDescription("Otra descripcion de simple seleccion 2");
		choice.getOptions().clear();
		choice.getOptions().addAll(options);

		try {
			System.out.println("<<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE SELECCIÓN SIMPLE >>>>>>>>>>>");
			service.update(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<< BORRADO DE INSTRUMENTOS DE SELECCIÓN SIMPLE >>>>>>>>>>>>>");
			service.delete(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La prueba de creación del instrumento.
	 */
	@Test
	public void pruebaDeCreacionDeSeleccionesMultiple() {
		// Creamos un nuevo instrumento.
		Option option = null;
		List<Option> options = null;
		MultipleChoiceInstrument choice = new MultipleChoiceInstrument();

		System.out.println("######################################################################");
		System.out.println("################# INSTRUMENTOS DE SELECCIÓN MULTIPLE #################");
		System.out.println("######################################################################");

		choice.setDescription("Descripcion de multiple seleccion");

		MultipleChoiceInstrumentService service = HolderApplicationContext.getContext().getBean(MultipleChoiceInstrumentService.class);

		options = new ArrayList<>();
		option = new TrueOption();
		option.setDescription("Opcion 1 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new TrueOption();
		option.setDescription("Opcion 2 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 3 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 4 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		choice.getOptions().addAll(options);

		try {
			System.out.println("<<<<<<<<<<< GUARDADO DEL INSTRUMENTOS DE SELECCIÓN MULTIPLE >>>>>>>>>>>");
			service.save(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la regla.
		choice.setDescription("Otra descripcion de multiple seleccion 1");
		choice.getOptions().remove(option);

		try {
			System.out.println("<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE SELECCIÓN MULTIPLE >>>>>>>>>>");
			service.update(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		options = new ArrayList<>();
		option = new TrueOption();
		option.setDescription("Opcion 11 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new TrueOption();
		option.setDescription("Opcion 12 - V");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 13 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		option = new Distractor();
		option.setDescription("Opcion 14 - F");
		option.setChoiceInstrument(choice);
		options.add(option);

		choice.setDescription("Otra descripcion de multiple seleccion 2");
		choice.getOptions().clear();
		choice.getOptions().addAll(options);

		try {
			System.out.println("<<<<<<<<<< ACTUALIZADO DE INSTRUMENTOS DE SELECCIÓN MULTIPLE >>>>>>>>>>");
			service.update(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<< BORRADO DE INSTRUMENTOS DE SELECCIÓN MULTIPLE >>>>>>>>>>>>");
			service.delete(choice);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}