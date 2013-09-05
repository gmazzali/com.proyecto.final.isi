package com.proyecto;

import java.util.Arrays;
import java.util.Date;

import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.answer.TrueFalseAnswer;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.assessment.type.impl.AssessmentMomentImpl;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.TrueOption;

/**
 * La clase que nos permite crear una evaluación o cualquier elemento que tenga dentro.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CreateExampleMaterial {

	/**
	 * La función encargada de crear una nueva evaluación.
	 * 
	 * @param id
	 *            El identificador de la evaluación.
	 * @param activities
	 *            El listado de las actividades.
	 * @return La evaluación creada.
	 */
	public static Assessment createAssessment(Integer id, Activity... activities) {
		Assessment assessment = new Assessment();

		assessment.setId(id);
		assessment.setAssessmentDate(new Date());
		assessment.setAssessmentMoment(AssessmentMomentImpl.DIAGNOSTIC);
		assessment.setDescription("assessment");
		assessment.setActivities(Arrays.asList(activities));

		return assessment;
	}

	/**
	 * La función encargada de crear una actividad.
	 * 
	 * @param id
	 *            El identificador de la actividad.
	 * @param reactives
	 *            El listado de los reactivos.
	 * @return La actividad creada.
	 */
	public static Activity createActivity(Integer id, Reactive... reactives) {
		Activity activity = new Activity();

		activity.setId(id);
		activity.setDescription("activity");
		activity.addAllReactives(Arrays.asList(reactives));

		return activity;
	}

	/**
	 * La función encargada de crear un reactivo.
	 * 
	 * @param id
	 *            El identificador del reactivo.
	 * @param instrument
	 *            El instrumento que vamos a cargar dentro del reactivo.
	 * @return El reactivo creado.
	 */
	public static Reactive createReactive(Integer id, Instrument instrument) {
		Reactive reactive = new Reactive();

		reactive.setId(id);
		reactive.setDescription("reactive");
		reactive.setInstrument(instrument);

		return reactive;
	}

	/**
	 * La función encargada de crear un instrumento de ensayo irrestricto.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de ensayo irrestricto.
	 */
	public static Instrument createInstrumentUnrestrictedEssayActivity(Integer id) {
		UnrestrictedEssayActivityInstrument unrestrictedEssayActivityInstrument = new UnrestrictedEssayActivityInstrument();

		unrestrictedEssayActivityInstrument.setId(id);
		unrestrictedEssayActivityInstrument.setDescription("unrestricted essay instrument");

		EssayActivityAnswer essayActivityAnswer = new EssayActivityAnswer();
		essayActivityAnswer.setId(id);
		essayActivityAnswer.setAnswer("unrestricted essay answer");
		unrestrictedEssayActivityInstrument.setAnswer(essayActivityAnswer);

		return unrestrictedEssayActivityInstrument;
	}

	/**
	 * La función encargada de crear un instrumento de ensayo restricto.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de ensayo restricto.
	 */
	public static Instrument createInstrumentRestrictedEssayActivity(Integer id) {
		RestrictedEssayActivityInstrument restrictedEssayActivityInstrument = new RestrictedEssayActivityInstrument();

		restrictedEssayActivityInstrument.setId(id);
		restrictedEssayActivityInstrument.setDescription("restricted essay instrument");

		EssayActivityAnswer essayActivityAnswer = new EssayActivityAnswer();
		essayActivityAnswer.setId(id);
		essayActivityAnswer.setAnswer("restricted essay answer");
		restrictedEssayActivityInstrument.setAnswer(essayActivityAnswer);

		return restrictedEssayActivityInstrument;
	}

	/**
	 * La función encargada de crear un instrumento de selección simple.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de selección simple.
	 */
	public static Instrument createInstrumentSingleChoice(Integer id) {
		SingleChoiceInstrument singleChoiceInstrument = new SingleChoiceInstrument();

		singleChoiceInstrument.setId(id);
		singleChoiceInstrument.setDescription("single choice instrument");

		TrueFalseAnswer trueFalseAnswer = new TrueFalseAnswer();
		TrueOption trueOption = new TrueOption();
		Distractor distractor = new Distractor();

		// True
		trueFalseAnswer.setId(id);
		trueFalseAnswer.setValue(true);

		trueOption = new TrueOption();
		trueOption.setId(id);
		trueOption.setDescription("single option t");
		trueOption.setTrueFalseAnswer(trueFalseAnswer);

		singleChoiceInstrument.addOption(trueOption);

		// False
		trueFalseAnswer.setId(id + 1);
		trueFalseAnswer.setValue(true);

		distractor.setId(id + 1);
		distractor.setDescription("single option f");
		distractor.setTrueFalseAnswer(trueFalseAnswer);

		singleChoiceInstrument.addOption(distractor);

		return singleChoiceInstrument;
	}

	/**
	 * La función encargada de crear un instrumento de selección multiple.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de selección multiple.
	 */
	public static Instrument createInstrumentMultipleChoice(Integer id) {
		MultipleChoiceInstrument multipleChoiceInstrument = new MultipleChoiceInstrument();

		multipleChoiceInstrument.setId(id);
		multipleChoiceInstrument.setDescription("multiple choice instrument");

		TrueFalseAnswer trueFalseAnswer = new TrueFalseAnswer();
		TrueOption trueOption = new TrueOption();
		Distractor distractor = new Distractor();

		// True
		trueFalseAnswer.setId(id);
		trueFalseAnswer.setValue(true);

		trueOption = new TrueOption();
		trueOption.setId(id);
		trueOption.setDescription("multiple option t");
		trueOption.setTrueFalseAnswer(trueFalseAnswer);

		multipleChoiceInstrument.addOption(trueOption);

		// False
		trueFalseAnswer.setId(id + 1);
		trueFalseAnswer.setValue(true);

		distractor.setId(id + 1);
		distractor.setDescription("multiple option f");
		distractor.setTrueFalseAnswer(trueFalseAnswer);

		multipleChoiceInstrument.addOption(distractor);

		return multipleChoiceInstrument;
	}

	/**
	 * La función encargada de crear un instrumento de correspondencia.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de correspondencia.
	 */
	public static Instrument createInstrumentCorrespondence(Integer id) {
		CorrespondenceInstrument correspondenceInstrument = new CorrespondenceInstrument();

		correspondenceInstrument.setId(id);
		correspondenceInstrument.setDescription("correspondence instrument");

		RelationAnswer relationAnswer = new RelationAnswer();
		relationAnswer.setId(id);
		relationAnswer.setLeftSide("left side 1");
		relationAnswer.setRightSide("right side 1");
		correspondenceInstrument.addRelation(relationAnswer);

		relationAnswer = new RelationAnswer();
		relationAnswer.setId(id + 1);
		relationAnswer.setLeftSide("left side 2");
		correspondenceInstrument.addRelation(relationAnswer);

		relationAnswer = new RelationAnswer();
		relationAnswer.setId(id + 2);
		relationAnswer.setRightSide("right side 3");
		correspondenceInstrument.addRelation(relationAnswer);

		return correspondenceInstrument;
	}

	/**
	 * La función encargada de crear un instrumento de completar.
	 * 
	 * @param id
	 *            El id del instrumento a crear.
	 * @return El instrumento de completar.
	 */
	public static Instrument createInstrumentCompletion(Integer id) {
		CompletionInstrument completionInstrument = new CompletionInstrument();

		completionInstrument.setId(id);
		completionInstrument.setDescription("completion instrument");

		CompletionAnswer completionAnswer = new CompletionAnswer();
		completionAnswer.setId(id);
		completionAnswer.setIndex(1);
		completionAnswer.setPhrase("complete answer 1");
		completionInstrument.addComplete(completionAnswer);

		completionAnswer = new CompletionAnswer();
		completionAnswer.setId(id + 1);
		completionAnswer.setIndex(2);
		completionAnswer.setPhrase("complete answer 2");
		completionInstrument.addComplete(completionAnswer);

		return completionInstrument;
	}

}
