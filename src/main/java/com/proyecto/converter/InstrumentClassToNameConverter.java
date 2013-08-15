package com.proyecto.converter;

import java.util.HashMap;
import java.util.Map;

import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CompositeInstrument;
import com.proyecto.model.material.instrument.ConceptualMapInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.EssayActivityInstrument;
import com.proyecto.model.material.instrument.EssayInstrument;
import com.proyecto.model.material.instrument.ExerciseInstrument;
import com.proyecto.model.material.instrument.FormalInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.ObjectiveActivityInstrument;
import com.proyecto.model.material.instrument.PortfolioInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SemiFormalInstrument;
import com.proyecto.model.material.instrument.SimpleInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;

/**
 * La clase de conversión entre el nombre de la clase de un instrumento al nombre que vamos a desplegar.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class InstrumentClassToNameConverter {

	/**
	 * El mapa de los nombre.
	 */
	private static Map<Class<? extends Instrument>, String> MAPPING_ISTRUMENT_CLASS;

	/**
	 * La función de conversión entre el nombre de una clase de instrumento y el nombre del instrumento en si.
	 * 
	 * @param instrumentClass
	 *            La clase de instrumento.
	 * @return El nombre de la clase de instrumento que corresponde con el parámetro.
	 */
	public static String converter(Class<? extends Instrument> instrumentClass) {

		if (InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS == null) {
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS = new HashMap<>();

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(FormalInstrument.class, "instrument.type.formal");

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(EssayActivityInstrument.class, "instrument.type.formal.essay");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(UnrestrictedEssayActivityInstrument.class,
					"instrument.type.formal.essay.unrestricted");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(RestrictedEssayActivityInstrument.class,
					"instrument.type.formal.essay.restricted");

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(ObjectiveActivityInstrument.class, "instrument.type.formal.objective");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(ChoiceInstrument.class, "instrument.type.formal.objective.choice");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS
					.put(SingleChoiceInstrument.class, "instrument.type.formal.objective.choice.single");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(MultipleChoiceInstrument.class,
					"instrument.type.formal.objective.choice.multiple");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(CorrespondenceInstrument.class,
					"instrument.type.formal.objective.correspondence");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(CompletionInstrument.class, "instrument.type.formal.objective.completion");

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(SemiFormalInstrument.class, "instrument.type.semiformal");

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(SimpleInstrument.class, "instrument.type.semiformal.simple");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(EssayInstrument.class, "instrument.type.semiformal.simple.essay");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(ExerciseInstrument.class, "instrument.type.semiformal.simple.exercise");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(ConceptualMapInstrument.class, "instrument.type.semiformal.simple.conceptual");

			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(CompositeInstrument.class, "instrument.type.semiformal.composite");
			InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.put(PortfolioInstrument.class, "instrument.type.semiformal.composite.portfolio");
		}
		return HolderMessage.getMessage(InstrumentClassToNameConverter.MAPPING_ISTRUMENT_CLASS.get(instrumentClass));
	}
}