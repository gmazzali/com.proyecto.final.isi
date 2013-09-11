package com.proyecto.util;

/**
 * La clase que va a contener los valores constantes de las relaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class Constants {

	/**
	 * Las cadenas que vamos a utilizar.
	 */
	public static final String NAMESPACE = "http://www.assessments.com/#";

	/**
	 * Las propiedades de las evaluaciones.
	 */
	public static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = Constants.NAMESPACE + "assessment_have_description";
	public static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = Constants.NAMESPACE + "assessment_have_moment";
	public static final String PROPERTY_ASSESSMENT_HAVE_DATE = Constants.NAMESPACE + "assessment_have_date";
	public static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = Constants.NAMESPACE + "assessment_have_activity";

	/**
	 * Las propiedades de las actividades.
	 */
	public static final String PROPERTY_ACTIVITY_BELONG_TO_ASSESSMENT = Constants.NAMESPACE + "activity_belong_to_assessment";
	public static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = Constants.NAMESPACE + "activity_have_description";
	public static final String PROPERTY_ACTIVITY_HAVE_REACTIVES = Constants.NAMESPACE + "activity_have_reactives";

	/**
	 * Las propiedades de los reactivos.
	 */
	public static final String PROPERTY_REACTIVE_BELONG_TO_ACTIVITY = Constants.NAMESPACE + "reactive_belong_to_activity";
	public static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = Constants.NAMESPACE + "reactive_have_description";
	public static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = Constants.NAMESPACE + "reactive_have_instrument";

	/**
	 * Las propiedades de los instrumentos.
	 */
	public static final String PROPERTY_INSTRUMENT_BELONG_TO_REACTIVE = Constants.NAMESPACE + "instrument_belong_to_reactive";
	public static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = Constants.NAMESPACE + "instrument_have_description";

	public static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = Constants.NAMESPACE + "essay_instrument_have_answer";
	public static final String PROPERTY_ANSWER_ESSAY_BELONG_TO_INSTRUMENT = Constants.NAMESPACE + "essay_answer_belong_to_essay_instrument";
	public static final String PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION = Constants.NAMESPACE + "essay_answer_have_description";

	public static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = Constants.NAMESPACE + "choice_instrument_have_option";
	public static final String PROPERTY_OPTION_BELONG_TO_INSTRUMENT = Constants.NAMESPACE + "option_belong_to_choice_instrument";
	public static final String PROPERTY_OPTION_HAVE_DESCRIPTION = Constants.NAMESPACE + "option_have_description";
	public static final String PROPERTY_OPTION_HAVE_ANSWER = Constants.NAMESPACE + "option_have_answer";
	public static final String PROPERTY_ANSWER_TRUEFALSE_BELONG_TO_OPTION = Constants.NAMESPACE + "truefalse_answer_belong_to_option";
	public static final String PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE = Constants.NAMESPACE + "truefalse_answer_have_value";

	public static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = Constants.NAMESPACE + "correspondence_instrument_have_relation";
	public static final String PROPERTY_ANSWER_RELATION_BELONG_TO_INSTRUMENT = Constants.NAMESPACE
			+ "relation_answer_belong_to_correspondence_instrument";
	public static final String PROPERTY_ANSWER_RELATION_LEFT_SIDE = Constants.NAMESPACE + "relation_answer_have_left_side";
	public static final String PROPERTY_ANSWER_RELATION_RIGHT_SIDE = Constants.NAMESPACE + "relation_answer_have_right_side";

	public static final String PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE = Constants.NAMESPACE + "completion_instrument_have_complete";
	public static final String PROPERTY_ANSWER_COMPLETE_BELONG_TO_INSTRUMENT = Constants.NAMESPACE
			+ "complete_answer_belong_to_completion_instrument";
	public static final String PROPERTY_ANSWER_COMPLETE_HAVE_INDEX = Constants.NAMESPACE + "complete_answer_have_index";
	public static final String PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE = Constants.NAMESPACE + "complete_answer_have_phrase";
}