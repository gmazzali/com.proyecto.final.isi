package com.proyecto.util;

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
		String pattern = "[\\sa-záéíóúñü]+";
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
		String pattern = "[\\sa-záéíóúñü]+";
		return rule == null ? false : rule.toLowerCase().trim().matches(pattern);
	}
}
