package com.proyecto.util;

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
	public class Ontology {

		/**
		 * Las cadenas que vamos a utilizar.
		 */
		public static final String NAMESPACE = "http://www.assessments.com/#";

		/**
		 * Las propiedades de las evaluaciones.
		 */
		public static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "assessmentHasDescription";
		public static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = Constants.Ontology.NAMESPACE + "assessmentHasMoment";
		public static final String PROPERTY_ASSESSMENT_HAVE_DATE = Constants.Ontology.NAMESPACE + "assessmentHasDate";
		public static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = Constants.Ontology.NAMESPACE + "assessmentHasActivity";

		/**
		 * Las propiedades de las actividades.
		 */
		public static final String PROPERTY_ACTIVITY_BELONG_TO_ASSESSMENT = Constants.Ontology.NAMESPACE + "activityBelongToAssessment";
		public static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "activityHasDescription";
		public static final String PROPERTY_ACTIVITY_HAVE_REACTIVE = Constants.Ontology.NAMESPACE + "activityHasReactives";

		/**
		 * Las propiedades de los reactivos.
		 */
		public static final String PROPERTY_REACTIVE_BELONG_TO_ACTIVITY = Constants.Ontology.NAMESPACE + "reactiveBelongToActivity";
		public static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "reactiveHasDescription";
		public static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = Constants.Ontology.NAMESPACE + "reactiveHasInstrument";

		/**
		 * Las propiedades de los instrumentos.
		 */
		public static final String PROPERTY_INSTRUMENT_BELONG_TO_REACTIVE = Constants.Ontology.NAMESPACE + "instrumentBelongToReactive";
		public static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "instrumentHasDescription";

		public static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = Constants.Ontology.NAMESPACE + "essayInstrumentHasAnswer";
		public static final String PROPERTY_ANSWER_ESSAY_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE + "essayAnswerBelongToEssayInstrument";
		public static final String PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "essayAnswerHasDescription";

		public static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = Constants.Ontology.NAMESPACE + "choiceInstrumentHas_option";
		public static final String PROPERTY_OPTION_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE + "optionBelongToChoiceInstrument";
		public static final String PROPERTY_OPTION_HAVE_DESCRIPTION = Constants.Ontology.NAMESPACE + "optionHasDescription";
		public static final String PROPERTY_OPTION_HAVE_ANSWER = Constants.Ontology.NAMESPACE + "optionHasAnswer";
		public static final String PROPERTY_ANSWER_TRUEFALSE_BELONG_TO_OPTION = Constants.Ontology.NAMESPACE + "truefalseAnswerBelongToOption";
		public static final String PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE = Constants.Ontology.NAMESPACE + "truefalseAnswerHasValue";

		public static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = Constants.Ontology.NAMESPACE
				+ "correspondenceInstrumentHasRelation";
		public static final String PROPERTY_ANSWER_RELATION_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ "relationAnswerBelongToCorrespondenceInstrument";
		public static final String PROPERTY_ANSWER_RELATION_LEFT_SIDE = Constants.Ontology.NAMESPACE + "relationAnswerHasLeftSide";
		public static final String PROPERTY_ANSWER_RELATION_RIGHT_SIDE = Constants.Ontology.NAMESPACE + "relationAnswerHasRightSide";

		public static final String PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE = Constants.Ontology.NAMESPACE + "completionInstrumentHasComplete";
		public static final String PROPERTY_ANSWER_COMPLETE_BELONG_TO_INSTRUMENT = Constants.Ontology.NAMESPACE
				+ "completeAnswerBelongToCompletionInstrument";
		public static final String PROPERTY_ANSWER_COMPLETE_HAVE_INDEX = Constants.Ontology.NAMESPACE + "completeAnswerHasIndex";
		public static final String PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE = Constants.Ontology.NAMESPACE + "completeAnswerHasPhrase";
	}
}