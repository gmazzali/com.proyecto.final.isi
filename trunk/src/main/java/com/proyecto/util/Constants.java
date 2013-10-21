package com.proyecto.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.common.util.holder.HolderMessage;

/**
 * La clase que va a contener los valores constantes de las relaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Constants {

	/**
	 * La línea de separación de la salida en pantalla.
	 */
	public static final String SEPARATOR_LINE = "############################################################";

	/**
	 * La clase que contiene las constantes propias de la ontología.
	 * 
	 * @author Guillermo Mazzali
	 * @version 1.0
	 */
	public static class Ontology {

		/**
		 * Las cadenas que vamos a utilizar.
		 */
		public static final String NAMESPACE = "http://www.assessments.com/#";

		/**
		 * Las propiedades de las evaluaciones.
		 */
		public static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.assessment.description");
		public static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.assessment.moment");
		public static final String PROPERTY_ASSESSMENT_HAVE_DATE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.assessment.date");
		public static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.assessment.activity");

		/**
		 * Las propiedades de las actividades.
		 */
		public static final String PROPERTY_ACTIVITY_BELONG_TO_ASSESSMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.activity.assessment");
		public static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.activity.description");
		public static final String PROPERTY_ACTIVITY_HAVE_REACTIVE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.activity.reactive");

		/**
		 * Las propiedades de los reactivos.
		 */
		public static final String PROPERTY_REACTIVE_BELONG_TO_ACTIVITY = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.reactive.activity");
		public static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.reactive.description");
		public static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.reactive.instrument");

		/**
		 * Las propiedades de los instrumentos.
		 */
		public static final String PROPERTY_INSTRUMENT_BELONG_TO_REACTIVE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.reactive");
		public static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.description");
		public static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.essay.answer");
		public static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.choice.option");
		public static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.correspondence.relation");
		public static final String PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.instrument.completion.complete");

		/*
		 * Las propiedades de las opciones.
		 */
		public static final String PROPERTY_OPTION_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.option.instrument");
		public static final String PROPERTY_OPTION_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.option.description");
		public static final String PROPERTY_OPTION_HAVE_ANSWER = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.option.answer");

		/**
		 * Las propiedades de las respuestas.
		 */
		public static final String PROPERTY_ANSWER_ESSAY_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.essay.instrument");
		public static final String PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.essay.description");

		public static final String PROPERTY_ANSWER_TRUEFALSE_BELONG_TO_OPTION = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.truefalse.option");
		public static final String PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.truefalse.value");

		public static final String PROPERTY_ANSWER_RELATION_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.relation.instrument");
		public static final String PROPERTY_ANSWER_RELATION_LEFT_SIDE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.relation.leftside");
		public static final String PROPERTY_ANSWER_RELATION_RIGHT_SIDE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.relation.rightside");

		public static final String PROPERTY_ANSWER_COMPLETE_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.complete.instrument");
		public static final String PROPERTY_ANSWER_COMPLETE_HAVE_INDEX = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.complete.index");
		public static final String PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE = Constants.Ontology.NAMESPACE
				+ HolderMessage.getMessage("ontology.relations.answer.complete.phrase");

		/**
		 * El mapa de las relaciones en base al nombre de los elementos. Dentro tenemos el nombre del elemento y todas las relaciones de este.
		 */
		public static final Map<String, Set<String>> ELEMENTS;

		static {
			ELEMENTS = new HashMap<String, Set<String>>();
			
			Set<String> relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_ASSESSMENT_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_ASSESSMENT_HAVE_MOMENT);
			relaciones.add(PROPERTY_ASSESSMENT_HAVE_DATE);
			relaciones.add(PROPERTY_ASSESSMENT_HAVE_ACTIVITY);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.assessment"), relaciones);
			
			relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_ACTIVITY_BELONG_TO_ASSESSMENT);
			relaciones.add(PROPERTY_ACTIVITY_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_ACTIVITY_HAVE_REACTIVE);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.activity"), relaciones);
			
			relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_REACTIVE_BELONG_TO_ACTIVITY);
			relaciones.add(PROPERTY_REACTIVE_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_REACTIVE_HAVE_INSTRUMENT);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.reactive"), relaciones);
			
			relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_INSTRUMENT_BELONG_TO_REACTIVE);
			relaciones.add(PROPERTY_INSTRUMENT_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER);
			relaciones.add(PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION);
			relaciones.add(PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION);
			relaciones.add(PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.instrument"), relaciones);

			relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_OPTION_BELONG_TO_INSTRUMENT);
			relaciones.add(PROPERTY_OPTION_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_OPTION_HAVE_ANSWER);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.option"), relaciones);

			relaciones = new HashSet<String>();
			relaciones.add(PROPERTY_ANSWER_ESSAY_BELONG_TO_INSTRUMENT);
			relaciones.add(PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION);
			relaciones.add(PROPERTY_ANSWER_TRUEFALSE_BELONG_TO_OPTION);
			relaciones.add(PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE);
			relaciones.add(PROPERTY_ANSWER_RELATION_BELONG_TO_INSTRUMENT);
			relaciones.add(PROPERTY_ANSWER_RELATION_LEFT_SIDE);
			relaciones.add(PROPERTY_ANSWER_RELATION_RIGHT_SIDE);
			relaciones.add(PROPERTY_ANSWER_COMPLETE_BELONG_TO_INSTRUMENT);
			relaciones.add(PROPERTY_ANSWER_COMPLETE_HAVE_INDEX);
			relaciones.add(PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE);
			ELEMENTS.put(HolderMessage.getMessage("ontology.relations.answer"), relaciones);
		}
	}
}