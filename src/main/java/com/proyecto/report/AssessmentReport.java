package com.proyecto.report;

import java.io.File;
import java.io.Serializable;

import com.proyecto.model.material.assessment.Assessment;

/**
 * La interfaz que define el comportamiento para crear un reporte con la evaluaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface AssessmentReport extends Serializable {

	/**
	 * La extensi�n del archivo de reporte.
	 */
	public static final String EXTENSION = ".pdf";

	/**
	 * La funci�n encargada de crear el reporte y guardarlo dentro de un archivo dentro del sistema.
	 * 
	 * @param path
	 *            El path donde vamos a almacenar el archivo del reporte.
	 * @param assessment
	 *            La evaluaci�n que vamos a almacenar dentro de ese reporte.
	 * @return El archivo que creamos con la evaluaci�n que recibimos.
	 * @throws Exception
	 *             En caso de una falla durante la creaci�n del reporte de la evaluaci�n.
	 */
	public File createAssessmentReport(String path, Assessment assessment) throws Exception;
}