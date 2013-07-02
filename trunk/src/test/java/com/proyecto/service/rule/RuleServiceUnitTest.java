package com.proyecto.service.rule;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;

/**
 * La clase de prueba de los servicios de las reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class RuleServiceUnitTest {

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
	 * La prueba de creación de reglas.
	 */
	@Test
	public void pruebaDeCreacionDeRegla() {
		// Creamos una nueva regla.
		Rule rule = new Rule();

		System.out.println("######################################################################");
		System.out.println("############################### REGLAS ###############################");
		System.out.println("######################################################################");

		rule.setDescription("descripcion de prueba");
		rule.setRule("regla de prueba");

		RuleService service = HolderApplicationContext.getContext().getBean(RuleService.class);

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< GUARDADO DE REGLA >>>>>>>>>>>>>>>>>>>>>>>>>>");
			service.save(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la regla.
		rule.setDescription("otra descripcion");

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< ACTUALIZADO DE REGLAS >>>>>>>>>>>>>>>>>>>>>>>>");
			service.update(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<< BORRADO DE REGLAS >>>>>>>>>>>>>>>>>>>>>>>>>>");
			service.delete(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
