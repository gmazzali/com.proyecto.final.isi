package com.proyecto.report.impl;

import java.awt.Desktop;
import java.io.File;

import com.common.util.annotations.Service;
import com.proyecto.report.Printer;

/**
 * La clase que implementa la interfaz de la impresora para manipular los archivos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class PrinterImpl implements Printer {

	@Override
	public void openFile(File report) throws Exception {
		// Verifica que el ambiente del SO soporte los procedimientos
		if (Desktop.isDesktopSupported()) {
			// abre el archivo
			Desktop.getDesktop().open(report);
		}
	}
}