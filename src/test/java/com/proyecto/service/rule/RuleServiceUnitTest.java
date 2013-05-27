package com.proyecto.service.rule;

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

	@Test
	public void pruebaDeCreacionDeRegla() {
		// Creamos una nueva regla.
		Rule rule = new Rule();

		rule.setDescription("descripcion de prueba");
		rule.setRule("regla de prueba");

		RuleService service = HolderApplicationContext.getContext().getBean(RuleService.class);

		try {
			service.save(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la regla.
		rule.setDescription("otra descripcion");

		try {
			service.update(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			service.delete(rule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
