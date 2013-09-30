package com.proyecto.ontology.task.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
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
 * La clase que implementa la interfaz que define el comportamiento del validador de una evaluación a partir de un conjunto de reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@RdfService
@SuppressWarnings("unused")
public class ValidateAssessmentTaskImpl implements ValidateAssessmentTask {

	private static final long serialVersionUID = -322789772715142375L;

	/**
	 * El servicio para la ontología de las evaluaciones.
	 */
	@Autowired
	private AssessmentFactoryRdf assessmentFactoryRdf;

	/**
	 * La evaluación que vamos a validar.
	 */
	private Assessment assessment;
	/**
	 * El conjunto de reglas que vamos a utilizar para validar la evaluación.
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
			@Override
			public void run() {
				// Comenzamos la carga de la ontología a la salida.
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");
				stringBuffer.append(HolderMessage.getMessage("ontology.phase.assessment.start"));
				stringBuffer.append("\n");
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");

				// Creamos la ontología y la cargamos con la evaluación.
				OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.DAML_MEM_RDFS_INF);
				ontology.setNsPrefix("NS", Constants.Ontology.NAMESPACE);
				ValidateAssessmentTaskImpl.this.assessmentFactoryRdf.loadEntityToOntology(ontology, ValidateAssessmentTaskImpl.this.assessment);

				// Cargamos la ontología a la salida.
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				ontology.write(out);
				stringBuffer.append(out.toString());

				// Finalizamos la carga de la ontología.
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");
				stringBuffer.append(HolderMessage.getMessage("ontology.phase.assessment.finish"));
				stringBuffer.append("\n");
				stringBuffer.append(Constants.SEPARATOR_LINE);
				stringBuffer.append("\n");

				try {
					ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
					
					OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
					OWLOntology owlOntology = manager.loadOntologyFromOntologyDocument(in);
					
					for (OWLNamedIndividual individual : owlOntology.getIndividualsInSignature()) {
						System.out.println(individual.getIRI());
					}
					PelletReasoner reasoner = PelletReasonerFactory.getInstance().createReasoner(owlOntology);
				} catch (OWLOntologyCreationException e) {
					e.printStackTrace();
				}

				// TODO gmazzali Hacer lo de la ejecución de la validación de la evaluación y el conjunto de reglas dentro de la ontología.
			};
		}.start();
	}
}