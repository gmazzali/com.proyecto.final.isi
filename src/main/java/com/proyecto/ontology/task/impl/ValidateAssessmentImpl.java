package com.proyecto.ontology.task.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.common.util.holder.HolderMessage;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.vocabulary.ReasonerVocabulary;
import com.proyecto.annotation.RdfService;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.ontology.factory.material.assessment.AssessmentFactoryRdf;
import com.proyecto.ontology.task.ValidateAssessment;
import com.proyecto.service.rule.RuleService;
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
	 * El valor que indica si va a imprimirse la ontolog�a dentro de la salida.
	 */
	@Value("${ontology.print}")
	private Boolean printOntology;
	/**
	 * El modo en el que se va a imprimir la salida de la ontolog�a.
	 */
	@Value("${ontology.print.mode}")
	private String printMode;
	/**
	 * El nombre de la ontolog�a.
	 */
	@Value("${ontology.namespace}")
	private String namespace;
	/**
	 * El prefijo del nombre de la ontolog�a.
	 */
	@Value("${ontology.namespace.prefix}")
	private String namespacePrefix;
	/**
	 * El valor de debug para el razonador.
	 */
	@Value("${ontology.reasoner.trace}")
	private Boolean reasonerTrace;
	/**
	 * El valor booleano que nos indica si va a detallarse el error al momento de validar la regla.
	 */
	@Value("${ontology.reasoner.fail.detail}")
	private Boolean detailFailedValidation;
	/**
	 * El valor que indica si va a guardarse la ontolog�a dentro de un archivo.
	 */
	@Value("${ontology.save}")
	private Boolean saveOntology;

	/**
	 * El servicio para la ontolog�a de las evaluaciones.
	 */
	@Autowired
	private AssessmentFactoryRdf assessmentFactoryRdf;

	/**
	 * El servicio de las reglas.
	 */
	@Autowired
	private RuleService ruleService;

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
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.begin") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");

			// Comenzamos la carga de la ontolog�a a la salida.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.begin") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");

			// Creamos la ontolog�a y la cargamos con la evaluaci�n.
			OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
			ontology.setNsPrefix(this.namespacePrefix, this.namespace);
			ontology.removeAll();

			ValidateAssessmentImpl.this.assessmentFactoryRdf.loadEntityToOntology(ontology, ValidateAssessmentImpl.this.assessment);

			// Si queremos cargar la ontolog�a a la salida, la cargamos.
			if (this.printOntology) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ontology.write(out, this.printMode);
				stringBuffer.append(out.toString() + "\n");
			}

			// Guardamos la ontolog�a dentro de un archivo.
			if (this.saveOntology) {
				this.saveOntology(ontology);
			}

			// Finalizamos la carga de la ontolog�a.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.finish") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");

			// Cargamos las reglas solo si las mismas no son nulas.
			if (this.ruleSet != null && !this.ruleSet.getRules().isEmpty()) {
				// Agregamos los mensajes de carga de reglas.
				stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
				stringBuffer.append(HolderMessage.getMessage("evaluate.rules.begin") + "\n");
				stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");

				// Evaluamos cada una de las reglas del conjunto.
				for (Rule rule : this.ruleSet.getRules()) {
					this.evaluateRule(stringBuffer, ontology, rule);
				}

				// Agregamos los mensajes de finalizaci�n de reglas.
				stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
				stringBuffer.append(HolderMessage.getMessage("evaluate.rules.finish") + "\n");
				stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");
			}
			// Cuando terminamos de validar la evaluaci�n.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.finish") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n\n");
		}
	}

	/**
	 * La funci�n encargada de tomar una regla y poder validarla dentro de la ontolog�a que recibimos.
	 * 
	 * @param stringBuffer
	 *            El buffer donde vamos a escribir los resultados.
	 * @param ontology
	 *            La ontolog�a sobre la que vamos a validar la regla.
	 * @param rule
	 *            La regla en si misma para validarse.
	 */
	private void evaluateRule(StringBuffer stringBuffer, OntModel ontology, Rule rule) {
		try {
			// Comenzamos con la evaluaci�n de la regla.
			stringBuffer.append(Constants.UNDERLINE + "\n");
			stringBuffer.append(rule.getDescription() + ":\n");
			stringBuffer.append(rule.getRule() + "\n\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.rule.title") + ": ");

			// Convertimos la regla a partir del String.
			com.hp.hpl.jena.reasoner.rulesys.Rule jenaRule = this.ruleService.parseRule(rule);
			List<com.hp.hpl.jena.reasoner.rulesys.Rule> rules = new ArrayList<com.hp.hpl.jena.reasoner.rulesys.Rule>();
			rules.add(jenaRule);

			// Creamos el razonador.
			Reasoner reasoner = new GenericRuleReasoner(rules);
			reasoner.setParameter(ReasonerVocabulary.PROPtraceOn, this.reasonerTrace.booleanValue());

			// Creamos el modelo de inferencia y preparamos las inferencia dentro de la misma.
			InfModel infOntology = ModelFactory.createInfModel(reasoner, ontology);
			infOntology.prepare();

			// Validamos el modelo.
			ValidityReport reports = infOntology.validate();
			if (reports.isValid()) {
				stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.rule.pass") + "\n");
			} else {
				stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.rule.failed") + "\n");

				// Mostramos todos los errores que nos surgieron si es necesario.
				if (this.detailFailedValidation) {
					Iterator<Report> iterableReport = reports.getReports();
					while (iterableReport.hasNext()) {
						Report report = iterableReport.next();
						stringBuffer.append(report.getDescription() + "\n");
					}
				}
			}
		} catch (Exception e) {
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.parse.failed") + "\n");
			e.printStackTrace();
		} finally {
			stringBuffer.append("\n");
		}
	}

	/**
	 * La funci�n encargada de guardar dentro de un archivo temporal la ontolog�a creada.
	 * 
	 * @param ontology
	 *            La ontolog�a que vamos a almacenar dentro del archivo.
	 */
	private void saveOntology(Model ontology) {
		try {
			// Guardamos temporalmente la ontolog�a dentro de un archivo.
			String path = System.getProperty("proyecto.configuration.dir") + "\\ontology.rdf";
			FileOutputStream salida = new FileOutputStream(path);
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}