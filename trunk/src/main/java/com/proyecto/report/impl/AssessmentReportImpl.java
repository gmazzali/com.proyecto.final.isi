package com.proyecto.report.impl;

import java.io.File;

import com.common.util.annotations.Service;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.report.AssessmentReport;

/**
 * La clase que implementa la interfaz que permite la carga de una evaluación dentro de un reporte PDF.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AssessmentReportImpl implements AssessmentReport {

	@Override
	public File createAssessmentReport(String path, Assessment assessment) throws Exception {
		// TODO mromitti Hacer el método de carga de una evaluación dentro de un reporte PDF.
		return null;
	}
}