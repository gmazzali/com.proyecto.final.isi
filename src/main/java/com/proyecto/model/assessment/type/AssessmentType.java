package com.proyecto.model.assessment.type;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.impl.InstrumentTypeImpl;

/**
 * La enumeraci�n que nos permite definir los posibles tipos de evaluaciones que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 *          TODO gmazzali Falta hacer bien esta enumeraci�n de tipos de evaluaciones.
 */
public enum AssessmentType {

	/**
	 * El tipo de evaluaci�n formal.
	 */
	FORMAL(new InstrumentTypeImpl[] { InstrumentTypeImpl.FORMAL }),
	/**
	 * El tipo de evaluaci�n semiformal.
	 */
	SEMIFORMAL(new InstrumentTypeImpl[] { InstrumentTypeImpl.SEMIFORMAL });

	/**
	 * Los tipos de instrumentos que tenemos permitidos dentro de cada tipo de evaluaci�n.
	 */
	private InstrumentTypeImpl[] instrumentsTypeAllowed;

	/**
	 * El constructor de un tipo de evaluaci�n dentro del sistema.
	 * 
	 * @param instrumentsTypeAllowed
	 *            El tipo de instrumento que permitimos dentro de esta evaluaciones.
	 */
	private AssessmentType(InstrumentTypeImpl[] instrumentsTypeAllowed) {
		this.instrumentsTypeAllowed = instrumentsTypeAllowed;
	}

	/**
	 * La funci�n que retorna el listado de los tipos de instrumentos que tenemos permitidos dentro de este tipo de evaluaci�n.
	 * 
	 * @return El listado de los tipos de instrumentos permitidos dentro de este tipo de evaluaci�n.
	 */
	public InstrumentTypeImpl[] getInstrumentsTypeAllowed() {
		return this.instrumentsTypeAllowed;
	}

	public Boolean isInstrumentClassAllowed(Class<? extends Instrument> instrumentClass) {
		for (InstrumentTypeImpl instrumentTypeImpl : this.instrumentsTypeAllowed) {
			if (instrumentTypeImpl.getInstrumentClass().isAssignableFrom(instrumentClass)) {
				return true;
			}
		}
		return false;
	}
}
