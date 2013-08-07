package com.proyecto.converter;

import javax.xml.ws.Holder;

import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CompositeInstrument;
import com.proyecto.model.instrument.ConceptualMapInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.model.instrument.EssayInstrument;
import com.proyecto.model.instrument.ExerciseInstrument;
import com.proyecto.model.instrument.FormalInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.model.instrument.ObjectiveActivityInstrument;
import com.proyecto.model.instrument.PortfolioInstrument;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.SemiFormalInstrument;
import com.proyecto.model.instrument.SimpleInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;

/**
 * La clase de conversión entre el nombre de la clase de un instrumento al nombre que vamos a desplegar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class InstrumentClassToName {

	/**
	 * El listado de las clases de los instrumentos.
	 */
	private static final Class<? extends Instrument>[] INSTRUMENT_CLASS = new Class[] {
			FormalInstrument.class,
			EssayActivityInstrument.class,
			UnrestrictedEssayActivityInstrument.class,
			RestrictedEssayActivityInstrument.class,
			ObjectiveActivityInstrument.class, 
			ChoiceInstrument.class,
			SingleChoiceInstrument.class, 
			MultipleChoiceInstrument.class,
			CorrespondenceInstrument.class, 
			CompletionInstrument.class,
			SemiFormalInstrument.class, 
			SimpleInstrument.class,
			EssayInstrument.class, 
			ExerciseInstrument.class,
			ConceptualMapInstrument.class, 
			CompositeInstrument.class,
			PortfolioInstrument.class 
		};
	/**
	 * El listado de los nombre que tenemos para las clases.
	 */
	private static final String[] INSTRUMENT_NAME = new String[] {
			"instrument.type.formal", 
			"instrument.type.formal.essay",
			"instrument.type.formal.essay.unrestricted",
			"instrument.type.formal.essay.restricted",
			"instrument.type.formal.objective",
			"instrument.type.formal.objective.choice",
			"instrument.type.formal.objective.choice.single",
			"instrument.type.formal.objective.choice.multiple",
			"instrument.type.formal.objective.correspondence",
			"instrument.type.formal.objective.completion",
			"instrument.type.semiformal", 
			"instrument.type.semiformal.simple",
			"instrument.type.semiformal.simple.essay",
			"instrument.type.semiformal.simple.exercise",
			"instrument.type.semiformal.simple.conceptual",
			"instrument.type.semiformal.composite",
			"instrument.type.semiformal.composite.portfolio"
		};

	/**
	 * La función de conversión entre el nombre de una clase de instrumento y el nombre del instrumento en si.
	 * 
	 * @param instrumentClass
	 *            La clase de instrumento.
	 * @return El nombre de la clase de instrumento que corresponde con el parámetro.
	 */
	public static String converter(Class<? extends Instrument> instrumentClass) {
		for (int index = 0; index < InstrumentClassToName.INSTRUMENT_CLASS.length; index++) {
			if(InstrumentClassToName.INSTRUMENT_CLASS[index].equals(instrumentClass)) {
				return HolderMessage.getMessage(InstrumentClassToName.INSTRUMENT_NAME[index]);
			}
		}
	}
}
