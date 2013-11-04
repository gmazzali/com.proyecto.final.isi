package com.proyecto.util;

import com.hp.hpl.jena.reasoner.rulesys.Rule;

/**
 * La clase que nos permite validar distintos campos dentro de la aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Validator {

	/**
	 * La función encargada de validar una descripción de un elemento dentro del sistema.
	 * 
	 * @param description
	 *            La descripción que queremos validar.
	 * @return TRUE en caso de que la descripción este acorde a los parámetros establecidos, en caso contrario retornar FALSE.
	 */
	public static Boolean descriptionValidator(String description) {
		String pattern = "[\\sa-záéíóúñü0-9]+";
		return description == null ? false : description.toLowerCase().trim().matches(pattern);
	}

	/**
	 * La función encargada de validar una regla dentro del sistema.
	 * 
	 * @param rule
	 *            La regla que queremos validar.
	 * @return TRUE en caso de que la regla este acorde a los parámetros establecidos, en caso contrario retornar FALSE.
	 */
	public static Boolean ruleValidator(String rule) {
		Boolean valid = null;
		try {
			Rule.parseRule(rule);
			valid = true;
		} catch (Exception e) {
			e.printStackTrace();
			valid = false;
		}
		return valid;
	}
}
