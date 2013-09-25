package com.proyecto.ontology.task.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.holder.HolderMessage;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.ontology.rdf.material.assessment.factory.AssessmentFactoryRdf;
import com.proyecto.ontology.task.ValidateAssessmentTask;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento del validador de una evaluaci�n a partir de un conjunto de reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
@SuppressWarnings("unused")
public class ValidateAssessmentTaskImpl implements ValidateAssessmentTask {

	private static final long serialVersionUID = -322789772715142375L;

	/**
	 * El servicio para la ontolog�a de las evaluaciones.
	 */
	@Autowired
	private AssessmentFactoryRdf assessmentFactoryRdf;

	/**
	 * La evaluaci�n que vamos a validar.
	 */
	private Assessment assessment;
	/**
	 * El conjunto de reglas que vamos a utilizar para validar la evaluaci�n.
	 */
	private RuleSet ruleSet;

	@Override
	public void initValidateTask(Assessment assessment, RuleSet ruleSet) {
		this.assessment = assessment;
		this.ruleSet = ruleSet;
	}

	@Override
	public void startTask(final StringBuffer stringBuffer) {
		new Thread() {
			public void run() {
				// Comenzamos la carga de la ontolog�a a la salida.
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");
				stringBuffer.append(HolderMessage.getMessage("ontology.phase.assessment.start"));
				stringBuffer.append("\n");
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");

				// Creamos la ontolog�a y la cargamos con la evaluaci�n.
				OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.DAML_MEM_RDFS_INF);
				ontology.setNsPrefix("NS", Constants.Ontology.NAMESPACE);
				assessmentFactoryRdf.loadEntityToOntology(ontology, assessment);

				// Cargamos la ontolog�a a la salida.
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ontology.write(out);
				stringBuffer.append(out.toByteArray());

				// Finalizamos la carga de la ontolog�a.
				stringBuffer.append("\n");
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");
				stringBuffer.append(HolderMessage.getMessage("ontology.phase.assessment.finish"));
				stringBuffer.append("\n");
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");

				// TODO gmazzali Hacer lo de la ejecuci�n de la validaci�n de la evaluaci�n y el conjunto de reglas dentro de la ontolog�a.

				for (int i = 0; i < 20; i++) {
					stringBuffer.append("SALIDA!!! \n");
				}
			};
		}.start();
	}
}