package com.proyecto.ontology;

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

	public static final String PROPERTY_ASSESSMENT_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_ASSESSMENT_HAVE_MOMENT = ConstantsOntology.NAMESPACE + "have_moment";
	public static final String PROPERTY_ASSESSMENT_HAVE_DATE = ConstantsOntology.NAMESPACE + "have_date";
	public static final String PROPERTY_ASSESSMENT_HAVE_ACTIVITY = ConstantsOntology.NAMESPACE + "have_activity";

	public static final String PROPERTY_ACTIVITY_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_ACTIVITY_HAVE_REACTIVES = ConstantsOntology.NAMESPACE + "have_reactives";
	
	public static final String PROPERTY_REACTIVE_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";
	public static final String PROPERTY_REACTIVE_HAVE_INSTRUMENT = ConstantsOntology.NAMESPACE + "have_instrument";

	public static final String PROPERTY_INSTRUMENT_HAVE_DESCRIPTION = ConstantsOntology.NAMESPACE + "have_description";

	public static final String PROPERTY_INSTRUMENT_ESSAY_HAVE_ANSWER = ConstantsOntology.NAMESPACE + "have_answer";

	public static final String PROPERTY_INSTRUMENT_CORRESPONDENCE_HAVE_RELATION = ConstantsOntology.NAMESPACE + "have_relation";

	public static final String PROPERTY_INSTRUMENT_CHOICE_HAVE_OPTION = ConstantsOntology.NAMESPACE + "have_option";

	public static final String PROPERTY_HAVE_OPTION = ConstantsOntology.NAMESPACE + "have_option";
	public static final String PROPERTY_HAVE_ANSWER = ConstantsOntology.NAMESPACE + "have_answer";
}
