package com.proyecto.ontology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.util.holder.HolderMessage;

/**
 * La clase que contiene las constantes propias de la ontología.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class OntologyConstants {

	/**
	 * La clase que contiene el nombre de las clase que vamos a crear dentro de la ontología.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public static class ClassName {

		/**
		 * El nombre de las clases de evaluación.
		 */
		public static final String ASSESSMENT = HolderMessage.getMessage("ontology.class.assessment");

		/**
		 * El nombre de las clases de actividad.
		 */
		public static final String ACTIVITY = HolderMessage.getMessage("ontology.class.activity");

		/**
		 * El nombre de las clases de reactivo.
		 */
		public static final String REACTIVE = HolderMessage.getMessage("ontology.class.reactive");

		/**
		 * El nombre de las clases de instrumento.
		 */
		public static final String INSTRUMENT = HolderMessage.getMessage("ontology.class.instrument");
		public static final String INSTRUMENT_FORMAL = HolderMessage.getMessage("ontology.class.instrument.formal");
		public static final String INSTRUMENT_FORMAL_ESSAY = HolderMessage.getMessage("ontology.class.instrument.formal.essay");
		public static final String INSTRUMENT_FORMAL_ESSAY_RESTRICTED = HolderMessage.getMessage("ontology.class.instrument.formal.essay.restricted");
		public static final String INSTRUMENT_FORMAL_ESSAY_UNRESTRICTED = HolderMessage
				.getMessage("ontology.class.instrument.formal.essay.unrestricted");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE = HolderMessage.getMessage("ontology.class.instrument.formal.objective");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE_CHOICE = HolderMessage.getMessage("ontology.class.instrument.formal.objective.choice");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_MULTIPLE = HolderMessage
				.getMessage("ontology.class.instrument.formal.objective.choice.multiple");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_SINGLE = HolderMessage
				.getMessage("ontology.class.instrument.formal.objective.choice.single");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE_COMPLETION = HolderMessage
				.getMessage("ontology.class.instrument.formal.objective.completion");
		public static final String INSTRUMENT_FORMAL_OBJECTIVE_CORRESPONDENCE = HolderMessage
				.getMessage("ontology.class.instrument.formal.objective.correspondence");
		public static final String INSTRUMENT_SEMIFORMAL = HolderMessage.getMessage("ontology.class.instrument.semiformal");
		public static final String INSTRUMENT_SEMIFORMAL_SIMPLE = HolderMessage.getMessage("ontology.class.instrument.semiformal.simple");
		public static final String INSTRUMENT_SEMIFORMAL_SIMPLE_CONCEPTUAL_MAP = HolderMessage
				.getMessage("ontology.class.instrument.semiformal.simple.map");
		public static final String INSTRUMENT_SEMIFORMAL_SIMPLE_ESSAY = HolderMessage.getMessage("ontology.class.instrument.semiformal.simple.essay");
		public static final String INSTRUMENT_SEMIFORMAL_SIMPLE_EXERCISE = HolderMessage
				.getMessage("ontology.class.instrument.semiformal.simple.exercise");
		public static final String INSTRUMENT_SEMIFORMAL_COMPOSITE = HolderMessage.getMessage("ontology.class.instrument.semiformal.composite");
		public static final String INSTRUMENT_SEMIFORMAL_COMPOSITE_PORTFOLIO = HolderMessage
				.getMessage("ontology.class.instrument.semiformal.composite.portfolio");

		/**
		 * El nombre de las clases de opciones.
		 */
		public static final String OPTION = HolderMessage.getMessage("ontology.class.option");
		public static final String OPTION_TRUE = HolderMessage.getMessage("ontology.class.option.true");
		public static final String OPTION_DISTRACTOR = HolderMessage.getMessage("ontology.class.option.distractor");

		/**
		 * El nombre de las clases de respuesta.
		 */
		public static final String ANSWER = HolderMessage.getMessage("ontology.class.answer");
		public static final String ANSWER_TRUEFALSE = HolderMessage.getMessage("ontology.class.answer.truefalse");
		public static final String ANSWER_COMPLETE = HolderMessage.getMessage("ontology.class.answer.complete");
		public static final String ANSWER_TEXT = HolderMessage.getMessage("ontology.class.answer.text");
		public static final String ANSWER_RELATION = HolderMessage.getMessage("ontology.class.answer.relation");
	}

	/**
	 * La clase que contiene el nombre de las propiedades que vamos a crear dentro de la ontología.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public static class PropertyName {

		/**
		 * Las propiedades de las evaluaciones.
		 */
		public static final String ASSESSMENT_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.assessment.description");
		public static final String ASSESSMENT_HAS_MOMENT = HolderMessage.getMessage("ontology.relations.assessment.moment");
		public static final String ASSESSMENT_HAS_DATE = HolderMessage.getMessage("ontology.relations.assessment.date");
		public static final String ASSESSMENT_HAS_ACTIVITY = HolderMessage.getMessage("ontology.relations.assessment.activity");

		/**
		 * Las propiedades de las actividades.
		 */
		public static final String ACTIVITY_BELONG_TO_ASSESSMENT = HolderMessage.getMessage("ontology.relations.activity.assessment");
		public static final String ACTIVITY_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.activity.description");
		public static final String ACTIVITY_HAS_REACTIVE = HolderMessage.getMessage("ontology.relations.activity.reactive");

		/**
		 * Las propiedades de los reactivos.
		 */
		public static final String REACTIVE_BELONG_TO_ACTIVITY = HolderMessage.getMessage("ontology.relations.reactive.activity");
		public static final String REACTIVE_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.reactive.description");
		public static final String REACTIVE_HAS_INSTRUMENT = HolderMessage.getMessage("ontology.relations.reactive.instrument");

		/**
		 * Las propiedades de los instrumentos.
		 */
		public static final String INSTRUMENT_BELONG_TO_REACTIVE = HolderMessage.getMessage("ontology.relations.instrument.reactive");
		public static final String INSTRUMENT_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.instrument.description");
		public static final String INSTRUMENT_ESSAY_HAS_ANSWER = HolderMessage.getMessage("ontology.relations.instrument.essay.answer");
		public static final String INSTRUMENT_CHOICE_HAS_OPTION = HolderMessage.getMessage("ontology.relations.instrument.choice.option");
		public static final String INSTRUMENT_CORRESPONDENCE_HAS_RELATION = HolderMessage
				.getMessage("ontology.relations.instrument.correspondence.relation");
		public static final String INSTRUMENT_COMPLETION_HAS_COMPLETE = HolderMessage.getMessage("ontology.relations.instrument.completion.complete");

		/*
		 * Las propiedades de las opciones.
		 */
		public static final String OPTION_BELONG_TO_INSTRUMENT = HolderMessage.getMessage("ontology.relations.option.instrument");
		public static final String OPTION_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.option.description");
		public static final String OPTION_HAS_ANSWER = HolderMessage.getMessage("ontology.relations.option.answer");

		/**
		 * Las propiedades de las respuestas.
		 */
		public static final String ANSWER_ESSAY_BELONG_TO_INSTRUMENT = HolderMessage.getMessage("ontology.relations.answer.essay.instrument");
		public static final String ANSWER_ESSAY_HAS_DESCRIPTION = HolderMessage.getMessage("ontology.relations.answer.essay.description");

		public static final String ANSWER_TRUEFALSE_BELONG_TO_OPTION = HolderMessage.getMessage("ontology.relations.answer.truefalse.option");
		public static final String ANSWER_TRUEFALSE_HAS_VALUE = HolderMessage.getMessage("ontology.relations.answer.truefalse.value");

		public static final String ANSWER_RELATION_BELONG_TO_INSTRUMENT = HolderMessage.getMessage("ontology.relations.answer.relation.instrument");
		public static final String ANSWER_RELATION_HAS_LEFT_SIDE = HolderMessage.getMessage("ontology.relations.answer.relation.leftside");
		public static final String ANSWER_RELATION_HAS_RIGHT_SIDE = HolderMessage.getMessage("ontology.relations.answer.relation.rightside");

		public static final String ANSWER_COMPLETE_BELONG_TO_INSTRUMENT = HolderMessage.getMessage("ontology.relations.answer.complete.instrument");
		public static final String ANSWER_COMPLETE_HAS_INDEX = HolderMessage.getMessage("ontology.relations.answer.complete.index");
		public static final String ANSWER_COMPLETE_HAS_PHRASE = HolderMessage.getMessage("ontology.relations.answer.complete.phrase");
	}

	/**
	 * El namespace de la ontología. Por omisión esta es "http://www.assessments.com/#"
	 */
	public static String NAMESPACE = HolderMessage.getMessage("ontology.namespace");
	/**
	 * EL prefijo del namespace de la ontología. Por omisión esta es "ns"
	 */
	public static String NAMESPACE_PREFIX = HolderMessage.getMessage("ontology.namespace.prefix");
	
	/**
	 * El conjunto de las propiedades propias de la ontología.
	 */
	public static List<String> ONTOLOGY_PROPERTIES;
	/**
	 * El conjunto de las funciones propias de JENA.
	 */
	public static List<String> JENA_BUILTIN;
	/**
	 * El conjunto de los nombre de las clases de la ontología.
	 */
	public static List<String> CLASES;
	/**
	 * El mapa de las relaciones en base al nombre de los elementos. Dentro tenemos el nombre del elemento y todas las relaciones de este.
	 */
	public static Map<String, List<String>> PROPERTIES;

	static {
		// Cargamos las propiedades de la ontología.
		OntologyConstants.ONTOLOGY_PROPERTIES = new ArrayList<String>();
		OntologyConstants.ONTOLOGY_PROPERTIES.add("rdf:type");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("rdfs:subClassOf");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("rdfs:subPropertyOf");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("rdfs:domain");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("rdfs:range");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:intersectionOf");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:unionOf");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:disjointWith");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:sameAs");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:differentFrom");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:distinctMembers");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:distinctMembers");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:Thing");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:equivalentProperty");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:inverseOf");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:FunctionalProperty");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:InverseFunctionalProperty");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:SymmeticProperty");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:TransitiveProperty");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:someValuesFrom");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:allValuesFrom");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:minCardinality");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:maxCardinality");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:cardinality");
		OntologyConstants.ONTOLOGY_PROPERTIES.add("owl:hasValue");

		// Cargamos las funciones propias de JENA.
		OntologyConstants.JENA_BUILTIN = new ArrayList<String>();
		OntologyConstants.JENA_BUILTIN.add("isLiteral(?x)");
		OntologyConstants.JENA_BUILTIN.add("notLiteral(?x)");
		OntologyConstants.JENA_BUILTIN.add("isFunctor(?x)");
		OntologyConstants.JENA_BUILTIN.add("notFunctor(?x)");
		OntologyConstants.JENA_BUILTIN.add("isBNode(?x)");
		OntologyConstants.JENA_BUILTIN.add("notBNode(?x)");
		OntologyConstants.JENA_BUILTIN.add("bound(?x...)");
		OntologyConstants.JENA_BUILTIN.add("unbound(?x..)");
		OntologyConstants.JENA_BUILTIN.add("equal(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("notEqual(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("lessThan(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("greaterThan(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("le(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("ge(?x, ?y)");
		OntologyConstants.JENA_BUILTIN.add("sum(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("addOne(?a, ?c)");
		OntologyConstants.JENA_BUILTIN.add("difference(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("min(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("max(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("product(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("quotient(?a, ?b, ?c)");
		OntologyConstants.JENA_BUILTIN.add("strConcat(?a1, .. ?an, ?t)");
		OntologyConstants.JENA_BUILTIN.add("uriConcat(?a1, .. ?an, ?t)");
		OntologyConstants.JENA_BUILTIN.add("regex(?t, ?p)");
		OntologyConstants.JENA_BUILTIN.add("regex(?t, ?p, ?m1, .. ?mn)");
		OntologyConstants.JENA_BUILTIN.add("now(?x)");
		OntologyConstants.JENA_BUILTIN.add("makeTemp(?x)");
		OntologyConstants.JENA_BUILTIN.add("makeInstance(?x, ?p, ?v)");
		OntologyConstants.JENA_BUILTIN.add("makeInstance(?x, ?p, ?t, ?v)");
		OntologyConstants.JENA_BUILTIN.add("makeSkolem(?x, ?v1, ... ?vn)");
		OntologyConstants.JENA_BUILTIN.add("noValue(?x, ?p)");
		OntologyConstants.JENA_BUILTIN.add("noValue(?x ?p ?v)");
		OntologyConstants.JENA_BUILTIN.add("remove(n, ...)");
		OntologyConstants.JENA_BUILTIN.add("drop(n, ...)");
		OntologyConstants.JENA_BUILTIN.add("isDType(?l, ?t)");
		OntologyConstants.JENA_BUILTIN.add("notDType(?l, ?t)");
		OntologyConstants.JENA_BUILTIN.add("print(?x, ...)");
		OntologyConstants.JENA_BUILTIN.add("listContains(?l, ?x)");
		OntologyConstants.JENA_BUILTIN.add("listNotContains(?l, ?x)");
		OntologyConstants.JENA_BUILTIN.add("listEntry(?list, ?index, ?val)");
		OntologyConstants.JENA_BUILTIN.add("listLength(?l, ?len)");
		OntologyConstants.JENA_BUILTIN.add("listEqual(?la, ?lb)");
		OntologyConstants.JENA_BUILTIN.add("listNotEqual(?la, ?lb)");
		OntologyConstants.JENA_BUILTIN.add("listMapAsObject(?s, ?p ?l)");
		OntologyConstants.JENA_BUILTIN.add("listMapAsSubject(?l, ?p, ?o)");
		OntologyConstants.JENA_BUILTIN.add("table(?p)");
		OntologyConstants.JENA_BUILTIN.add("tableAll()");
		OntologyConstants.JENA_BUILTIN.add("hide(p)");

		// Cargamos las clases.
		OntologyConstants.CLASES = new ArrayList<String>();
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ASSESSMENT);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ACTIVITY);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.REACTIVE);

		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY_RESTRICTED);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_ESSAY_UNRESTRICTED);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_MULTIPLE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CHOICE_SINGLE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_COMPLETION);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_FORMAL_OBJECTIVE_CORRESPONDENCE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_CONCEPTUAL_MAP);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_ESSAY);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_SIMPLE_EXERCISE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_COMPOSITE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.INSTRUMENT_SEMIFORMAL_COMPOSITE_PORTFOLIO);

		OntologyConstants.CLASES.add(OntologyConstants.ClassName.OPTION);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.OPTION_TRUE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.OPTION_DISTRACTOR);

		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ANSWER);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ANSWER_TRUEFALSE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ANSWER_COMPLETE);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ANSWER_TEXT);
		OntologyConstants.CLASES.add(OntologyConstants.ClassName.ANSWER_RELATION);

		// Cargamos las relaciones.
		OntologyConstants.PROPERTIES = new HashMap<String, List<String>>();

		List<String> relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.ASSESSMENT_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.ASSESSMENT_HAS_MOMENT);
		relaciones.add(OntologyConstants.PropertyName.ASSESSMENT_HAS_DATE);
		relaciones.add(OntologyConstants.PropertyName.ASSESSMENT_HAS_ACTIVITY);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.ASSESSMENT, relaciones);

		relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.ACTIVITY_BELONG_TO_ASSESSMENT);
		relaciones.add(OntologyConstants.PropertyName.ACTIVITY_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.ACTIVITY_HAS_REACTIVE);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.ACTIVITY, relaciones);

		relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.REACTIVE_BELONG_TO_ACTIVITY);
		relaciones.add(OntologyConstants.PropertyName.REACTIVE_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.REACTIVE_HAS_INSTRUMENT);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.REACTIVE, relaciones);

		relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_BELONG_TO_REACTIVE);
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_ESSAY_HAS_ANSWER);
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_CHOICE_HAS_OPTION);
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_CORRESPONDENCE_HAS_RELATION);
		relaciones.add(OntologyConstants.PropertyName.INSTRUMENT_COMPLETION_HAS_COMPLETE);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.INSTRUMENT, relaciones);

		relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.OPTION_BELONG_TO_INSTRUMENT);
		relaciones.add(OntologyConstants.PropertyName.OPTION_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.OPTION_HAS_ANSWER);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.OPTION, relaciones);

		relaciones = new ArrayList<String>();
		relaciones.add(OntologyConstants.PropertyName.ANSWER_ESSAY_BELONG_TO_INSTRUMENT);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_ESSAY_HAS_DESCRIPTION);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_TRUEFALSE_BELONG_TO_OPTION);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_TRUEFALSE_HAS_VALUE);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_RELATION_BELONG_TO_INSTRUMENT);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_RELATION_HAS_LEFT_SIDE);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_RELATION_HAS_RIGHT_SIDE);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_COMPLETE_BELONG_TO_INSTRUMENT);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_COMPLETE_HAS_INDEX);
		relaciones.add(OntologyConstants.PropertyName.ANSWER_COMPLETE_HAS_PHRASE);
		OntologyConstants.PROPERTIES.put(OntologyConstants.ClassName.ANSWER, relaciones);
	}
}