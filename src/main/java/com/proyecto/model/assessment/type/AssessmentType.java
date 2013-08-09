package com.proyecto.model.assessment.type;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.impl.InstrumentType;

/**
 * La enumeración que nos permite definir los posibles tipos de evaluaciones que tenemos dentro del sistema.
 * @author Guilermo Mazzali
 * @version 1.0
 */
public enum AssessmentType {

	/**
	 * El tipo de evaluación formal.
	 */
	FORMAL(new InstrumentType[]{ InstrumentType.FORMAL }), 
	/**
	 * El tipo de evaluación semiformal.
	 */
	SEMIFORMAL(new InstrumentType[]{ InstrumentType.SEMIFORMAL });
	
	/**
	 * Los tipos de instrumentos que tenemos permitidos dentro de cada tipo de evaluación.
	 */
	private InstrumentType[] instrumentsTypeAllowed;
	
	/**
	 * El constructor de un tipo de evaluación dentro del sistema.
	 * @param instrumentsTypeAllowed El tipo de instrumento que permitimos dentro de esta evaluaciones.
	 */
	private AssessmentType(InstrumentType[] instrumentsTypeAllowed) {
		this.instrumentsTypeAllowed = instrumentsTypeAllowed;
	}
	
	/**
	 * La función que retorna el listado de los tipos de instrumentos que tenemos permitidos dentro de este tipo de evaluación.
	 * @return El listado de los tipos de instrumentos permitidos dentro de este tipo de evaluación.
	 */
	public InstrumentType[] getInstrumentsTypeAllowed() {
		return instrumentsTypeAllowed;
	}
	
	public Boolean isInstrumentClassAllowed(Class<? extends Instrument> instrumentClass) {
		for (InstrumentType instrumentType : instrumentsTypeAllowed) {
			if(instrumentType.getInstrumentClass().isAssignableFrom(instrumentClass)) {
				return true;
			}
		}
		return false;
	}
}
