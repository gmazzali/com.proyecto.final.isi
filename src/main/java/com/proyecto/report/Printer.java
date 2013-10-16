package com.proyecto.report;

import java.io.File;

/**
 * La interfaz que define el comportamiento para la apertura e impresión de archivos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface Printer {

	/**
	 * La función que permite abrir el archivo de reporte creado.
	 * 
	 * @param report
	 *            El archivo de reporte creado.
	 * @throws Exception
	 *             En caso de una falla durante la apertura del archivo.
	 */
	public void openFile(File report) throws Exception;
}