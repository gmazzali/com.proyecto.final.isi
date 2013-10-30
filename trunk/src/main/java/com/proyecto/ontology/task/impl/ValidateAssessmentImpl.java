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
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");

			// Comenzamos la carga de la ontolog�a a la salida.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.begin") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");

			// Creamos la ontolog�a y la cargamos con la evaluaci�n.
			OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
			ontology.setNsPrefix(this.namespacePrefix, this.namespace);
			ValidateAssessmentImpl.this.assessmentFactoryRdf.loadEntityToOntology(ontology, ValidateAssessmentImpl.this.assessment);

			// Cargamos la ontolog�a a la salida.
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ontology.write(out, this.printMode);
			stringBuffer.append(out.toString());

			// Guardamos la ontolog�a dentro de un archivo.
			this.saveOntology(ontology);

			// Finalizamos la carga de la ontolog�a.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.load.finish") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");

			// Cargamos las reglas solo si las mismas no son nulas.
			if (this.ruleSet != null && !this.ruleSet.getRules().isEmpty()) {
				for (Rule rule : this.ruleSet.getRules()) {
					this.evaluateRule(stringBuffer, ontology, rule);
				}
			}
			// Cuando terminamos de validar la evaluaci�n.
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
			stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.finish") + "\n");
			stringBuffer.append(Constants.SEPARATOR_LINE + "\n");
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
		// Comenzamos con la evaluaci�n de la regla.
		stringBuffer.append("\n" + this.ruleService.convertRuleToString(rule) + "\n");
		stringBuffer.append(Constants.UNDERLINE + "\n");

		try {
			// Convertimos la regla a partir del String.
			com.hp.hpl.jena.reasoner.rulesys.Rule jenaRule = this.ruleService.parseRule(rule);
			List<com.hp.hpl.jena.reasoner.rulesys.Rule> rules = new ArrayList<com.hp.hpl.jena.reasoner.rulesys.Rule>();
			rules.add(jenaRule);

			// Imprimimos la regla.

			// Creamos el modelo de inferencia.
			Reasoner reasoner = new GenericRuleReasoner(rules);
			InfModel infOntology = ModelFactory.createInfModel(reasoner, ontology);

			// Guardamos el modelo inferido.
			this.saveInfOntology(infOntology);
			infOntology.write(System.out, "TTL");

			// Comparamos los modelos para su evaluaci�n.
			// TODO gmazzali Hacer lo de la ejecuci�n de la validaci�n de la evaluaci�n y el conjunto de reglas dentro de la ontolog�a.
			if (ontology.difference(infOntology).isEmpty()) {
				// TODO gmazzali Hacer lo que corresponda cuando los modelos sean iguales.
			} else {
				// TODO gmazzali Hacer lo que corresponda cuando los modelos sean diferentes.
			}

			// Validamos el modelo.
			ValidityReport reports = infOntology.validate();
			if (reports.isValid()) {
				stringBuffer.append(HolderMessage.getMessage("evaluate.ontology.rule.pass") + "\n");
			} else {
				// Mostramos todos los errores que nos surgieron.
				Iterator<Report> iterableReport = reports.getReports();
				while (iterableReport.hasNext()) {
					Report report = iterableReport.next();
					stringBuffer.append(report.getDescription() + "\n");
				}
			}
		} catch (Exception e) {
			stringBuffer.append("\n" + HolderMessage.getMessage("evaluate.ontology.parse.failed") + "\n");
			e.printStackTrace();
		}
	}

	/**
	 * La funci�n encargada de guardar el modelo de inferencia de la ontolog�a.
	 * 
	 * @param ontology
	 *            El model que vamos a almacenar.
	 */
	private void saveOntology(InfModel ontology) {
		this.save(ontology, "ontology.rdf");
	}

	/**
	 * La funci�n encargada de guardar el modelo de inferencia de la ontolog�a.
	 * 
	 * @param ontology
	 *            El model que vamos a almacenar.
	 */
	private void saveInfOntology(InfModel ontology) {
		this.save(ontology, "inf_ontology.rdf");
	}

	/**
	 * La funci�n encargada de guardar dentro de un archivo temporal la ontolog�a creada.
	 * 
	 * @param ontology
	 *            La ontolog�a que vamos a almacenar dentro del archivo.
	 * @param name
	 *            El nombre del archivo que vamos a guardar.
	 */
	private void save(Model ontology, String name) {
		// Guardamos temporalmente la ontolog�a dentro de un archivo.
		try {
			String path = System.getProperty("proyecto.configuration.dir") + "\\" + name;
			FileOutputStream salida = new FileOutputStream(path);
			ontology.write(salida);
			salida.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}