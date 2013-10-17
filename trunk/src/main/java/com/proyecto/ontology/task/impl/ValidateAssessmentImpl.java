package com.proyecto.ontology.task.impl;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.holder.HolderMessage;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.ontology.rdf.material.assessment.factory.AssessmentFactoryRdf;
import com.proyecto.ontology.task.ValidateAssessment;
import com.proyecto.util.Constants;

/**
 * La clase que implementa la interfaz que define el comportamiento del validador de una evaluaci�n a partir de un conjunto de reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
public class ValidateAssessmentImpl implements ValidateAssessment {

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
	public void executeTask(final StringBuffer stringBuffer) {
		// Si la evaluaci�n no es nula.
		if (this.assessment != null) {

			// Comenzamos con la evaluaci�n.
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.begin"));
			stringBuffer.append("\n");
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			
			// Comenzamos la carga de la ontolog�a a la salida.
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.begin"));
			stringBuffer.append("\n");
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");

			// Creamos la ontolog�a y la cargamos con la evaluaci�n.
			OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.DAML_MEM_RDFS_INF);
			ontology.setNsPrefix("NS", Constants.Ontology.NAMESPACE);
			ValidateAssessmentImpl.this.assessmentFactoryRdf.loadEntityToOntology(ontology, ValidateAssessmentImpl.this.assessment);

			// Cargamos la ontolog�a a la salida.
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ontology.write(out);
			stringBuffer.append(out.toString());

			// Finalizamos la carga de la ontolog�a.
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.finish"));
			stringBuffer.append("\n");
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			
			// TODO gmazzali Hacer lo de la ejecuci�n de la validaci�n de la evaluaci�n y el conjunto de reglas dentro de la ontolog�a.
			// Cargamos las reglas solo si las mismas no son nulas.
			if (this.ruleSet != null && !this.ruleSet.getRules().isEmpty()) {

				// Aplicamos cada una de las reglas.
				for (Rule rule : this.ruleSet.getRules()) {
					stringBuffer.append("\n");
					stringBuffer.append(rule.getDescription());
					stringBuffer.append("\n");
					stringBuffer.append("\n");

					String rules = rule.getRule();
					Reasoner reasoner = new GenericRuleReasoner(com.hp.hpl.jena.reasoner.rulesys.Rule.parseRules(rules));
					InfModel infOntology = ModelFactory.createInfModel(reasoner, ontology);

					// Validamos el modelo.
					ValidityReport reports = infOntology.validate();
					if (reports.isValid()) {
						stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.rule.pass"));
						stringBuffer.append("\n");
					} else {
						// Mostramos todos los errores que nos surgieron.
						Iterator<Report> iterableReport = reports.getReports();
						while (iterableReport.hasNext()) {
							Report report = iterableReport.next();
							stringBuffer.append(report.getDescription());
							stringBuffer.append("\n");
						}
					}
				}
			}
			// Cuando terminamos de validar la evaluaci�n.
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.finish"));
			stringBuffer.append("\n");
			stringBuffer.append(Constants.SEPARATOR_LINE);
			stringBuffer.append("\n");
		}
	}
}