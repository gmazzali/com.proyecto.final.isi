package com.proyecto.service.rule;

import org.junit.AfterClass;
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
	 * Al finalizar dejamos un espacio en blanco en la consola.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Pruebas de creación.
	 */
	@Test
	public void pruebaDeCreacionDeConjuntoDeReglas() {
		// Creamos un nuevo conjunto de reglas.
		RuleSet ruleSet = new RuleSet();

		System.out.println("######################################################################");
		System.out.println("###################### CONJUNTO DE REGLAS VACIO ######################");
		System.out.println("######################################################################");

		ruleSet.setDescription("descripcion de prueba");
		ruleSet.setActive(true);

		RuleSetService service = HolderApplicationContext.getContext().getBean(RuleSetService.class);

		// Guardamos el conjunto vacío.
		try {
			System.out.println("<<<<<<<<<<<<<<<< GUARDADO DEL CONJUNTO DE REGLAS VACIO >>>>>>>>>>>>>>>>");
			service.save(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el conjunto de regla.
		ruleSet.setDescription("otra descripcion");
		ruleSet.setActive(false);

		try {
			System.out.println("<<<<<<<<<<<<<<< ACTUALIZADO DEL CONJUNTO DE REGLA VACIO >>>>>>>>>>>>>>>");
			service.update(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<<< BORRADO DEL CONJUNTO DE REGLA VACIO >>>>>>>>>>>>>>>>>");
			service.delete(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void pruebasDeGuardadoDeReglas() {
		// Creamos un nuevo conjunto de reglas.
		RuleSet ruleSet = new RuleSet();

		System.out.println("######################################################################");
		System.out.println("######################### CONJUNTO DE REGLAS #########################");
		System.out.println("######################################################################");

		ruleSet.setDescription("descripcion de prueba con reglas");
		ruleSet.setActive(true);

		RuleSetService ruleSetService = HolderApplicationContext.getContext().getBean(RuleSetService.class);

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

		RuleService ruleService = HolderApplicationContext.getContext().getBean(RuleService.class);
		Rule deleteRule = ruleSet.getRules().get(0);

		try {
			for (Rule r : ruleSet.getRules()) {
				ruleService.save(r);
			}
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<< GUARDADO DEL CONJUNTO DE REGLAS >>>>>>>>>>>>>>>>>>>");
			ruleSetService.save(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el conjunto de regla.
		ruleSet.setDescription("otra descripcion con reglas");
		ruleSet.getRules().remove(deleteRule);
		ruleSet.setActive(false);

		try {
			System.out.println("<<<<<<<<<<<<<<<<< ACTUALIZADO DEL CONJUNTO DE REGLAS >>>>>>>>>>>>>>>>>");
			ruleSetService.update(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<< BORRADO DEL CONJUNTO DE REGLA >>>>>>>>>>>>>>>>>>>>");
			ruleSetService.delete(ruleSet);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		try {
			for (Rule r : ruleSet.getRules()) {
				ruleService.delete(r);
			}
			ruleService.delete(deleteRule);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
