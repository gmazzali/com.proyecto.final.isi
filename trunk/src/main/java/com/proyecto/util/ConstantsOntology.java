package com.proyecto.util;

/**
 * La clase que va a contener los valores constantes de las relaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ConstantsOntology {

	/**
	 * Las cadenas que vamos a utilizar.
	 */
	public static final String NAMESPACE = "http://www.assessments.com/#";

	/**
	 * Las propiedades de las evaluaciones.
	 */
	public static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = ConstantsOntology.NAMESPACE + "have_moment";
	public static final String PROPERTY_ASSESSMENT_HAVE_DATE = ConstantsOntology.NAMESPACE + "have_date";
	public static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = ConstantsOntology.NAMESPACE + "have_activity";

	/**
	 * Las propiedades de las actividades.
	 */
	public static final String PROPERTY_ACTIVITY_BELONG_TO_ASSESSMENT = ConstantsOntology.NAMESPACE + "belong_to_assessment";
	public static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_ACTIVITY_HAVE_REACTIVES = ConstantsOntology.NAMESPACE + "have_reactives";

	/**
	 * Las propiedades de los reactivos.
	 */
	public static final String PROPERTY_REACTIVE_BELONG_TO_ACTIVITY = ConstantsOntology.NAMESPACE + "belong_to_activity";
	public static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = ConstantsOntology.NAMESPACE + "have_instrument";

	/**
	 * Las propiedades de los instrumentos.
	 */
	public static final String PROPERTY_INSTRUMENT_BELONG_TO_REACTIVE = ConstantsOntology.NAMESPACE + "belong_to_reactive";
	public static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";

	public static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = ConstantsOntology.NAMESPACE + "have_answer";
	public static final String PROPERTY_ANSWER_ESSAY_BELONG_TO_INSTRUMENT = ConstantsOntology.NAMESPACE + "belong_to_instrument";
	public static final String PROPERTY_ANSWER_ESSAY_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";

	public static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = ConstantsOntology.NAMESPACE + "have_option";
	public static final String PROPERTY_OPTION_BELONG_TO_INSTRUMENT = ConstantsOntology.NAMESPACE + "belong_to_instrument";
	public static final String PROPERTY_OPTION_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_OPTION_HAVE_ANSWER = ConstantsOntology.NAMESPACE + "have_answer";
	public static final String PROPERTY_ANSWER_TRUEFALSE_BELONG_TO_OPTION = ConstantsOntology.NAMESPACE + "belong_to_option";
	public static final String PROPERTY_ANSWER_TRUEFALSE_HAVE_VALUE = ConstantsOntology.NAMESPACE + "have_value";

	public static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = ConstantsOntology.NAMESPACE + "have_relation";
	public static final String PROPERTY_ANSWER_RELATION_BELONG_TO_INSTRUMENT = ConstantsOntology.NAMESPACE + "belong_to_instrument";
	public static final String PROPERTY_ANSWER_RELATION_LEFT_SIDE = ConstantsOntology.NAMESPACE + "have_left_side";
	public static final String PROPERTY_ANSWER_RELATION_RIGHT_SIDE = ConstantsOntology.NAMESPACE + "have_right_side";

	public static final String PROPERTY_INSTRUMENT_COMPLETION_HAVE_COMPLETE = ConstantsOntology.NAMESPACE + "have_complete";
	public static final String PROPERTY_ANSWER_COMPLETE_BELONG_TO_INSTRUMENT = ConstantsOntology.NAMESPACE + "belong_to_instrument";
	public static final String PROPERTY_ANSWER_COMPLETE_HAVE_INDEX = ConstantsOntology.NAMESPACE + "have_index";
	public static final String PROPERTY_ANSWER_COMPLETE_HAVE_PHRASE = ConstantsOntology.NAMESPACE + "have_phrase";
}