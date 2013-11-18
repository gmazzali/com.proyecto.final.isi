package com.proyecto.report;

import java.io.File;
import java.io.Serializable;

import com.proyecto.model.material.assessment.Assessment;

/**
 * La interfaz que define el comportamiento para crear un reporte con la evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentReport extends Serializable {

	/**
	 * La extensión del archivo de reporte.
	 */
	public static final String EXTENSION = ".pdf";

	/**
	 * La función encargada de crear el reporte y guardarlo dentro de un archivo dentro del sistema.
	 * 
	 * @param path
	 *            El path donde vamos a almacenar el archivo del reporte.
	 * @param assessment
	 *            La evaluación que vamos a almacenar dentro de ese reporte.
	 * @return El archivo que creamos con la evaluación que recibimos.
	 * @throws Exception
	 *             En caso de una falla durante la creación del reporte de la evaluación.
	 */
	public File createAssessmentReport(String path, Assessment assessment) throws Exception;
}