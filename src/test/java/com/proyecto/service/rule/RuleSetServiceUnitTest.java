package com.proyecto.service.rule;

import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;

/**
 * La clase de prueba de los servicios de los conjuntos de las reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class RuleSetServiceUnitTest {

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
	 * Pruebas de creación.
	 */
	// @Test
	public void pruebaDeCreacionDeConjuntoDeReglas() {
		// Creamos un nuevo conjunto de reglas.
		RuleSet ruleSet = new RuleSet();

		ruleSet.setDescription("descripcion de prueba");
		ruleSet.setActive(true);

		RuleSetService service = HolderApplicationContext.getContext().getBean(RuleSetService.class);

		try {
			service.save(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el conjunto de regla.
		ruleSet.setDescription("otra descripcion");
		ruleSet.setActive(false);

		try {
			service.update(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			service.delete(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void pruebasDeGuardadoDeReglas() {
		// Creamos un nuevo conjunto de reglas.
		RuleSet ruleSet = new RuleSet();

		ruleSet.setDescription("descripcion de prueba con reglas");
		ruleSet.setActive(true);

		RuleSetService service = HolderApplicationContext.getContext().getBean(RuleSetService.class);

		// Creamos un par de reglas.
		Rule rule = new Rule();
		rule.setDescription("regla 1 de prueba");
		rule.setRule("regla 1");
		ruleSet.getRules().add(rule);

		rule = new Rule();
		rule.setDescription("regla 2 de prueba");
		rule.setRule("regla 2");
		ruleSet.getRules().add(rule);

		rule = new Rule();
		rule.setDescription("regla 3 de prueba");
		rule.setRule("regla 3");
		ruleSet.getRules().add(rule);

		rule = new Rule();
		rule.setDescription("regla 4 de prueba");
		rule.setRule("regla 4");
		ruleSet.getRules().add(rule);

		try {
			service.save(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el conjunto de regla.
		ruleSet.setDescription("otra descripcion con reglas");
		ruleSet.setActive(false);

		try {
			service.update(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			service.delete(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
