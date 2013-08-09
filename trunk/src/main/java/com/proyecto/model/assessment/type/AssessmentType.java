package com.proyecto.model.assessment.type;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.impl.InstrumentType;

/**
 * La enumeraci�n que nos permite definir los posibles tipos de evaluaciones que tenemos dentro del sistema.
 * @author Guilermo Mazzali
 * @version 1.0
 */
public enum AssessmentType {

	/**
	 * El tipo de evaluaci�n formal.
	 */
	FORMAL(new InstrumentType[]{ InstrumentType.FORMAL }), 
	/**
	 * El tipo de evaluaci�n semiformal.
	 */
	SEMIFORMAL(new InstrumentType[]{ InstrumentType.SEMIFORMAL });
	
	/**
	 * Los tipos de instrumentos que tenemos permitidos dentro de cada tipo de evaluaci�n.
	 */
	private InstrumentType[] instrumentsTypeAllowed;
	
	/**
	 * El constructor de un tipo de evaluaci�n dentro del sistema.
	 * @param instrumentsTypeAllowed El tipo de instrumento que permitimos dentro de esta evaluaciones.
	 */
	private AssessmentType(InstrumentType[] instrumentsTypeAllowed) {
		this.instrumentsTypeAllowed = instrumentsTypeAllowed;
	}
	
	/**
	 * La funci�n que retorna el listado de los tipos de instrumentos que tenemos permitidos dentro de este tipo de evaluaci�n.
	 * @return El listado de los tipos de instrumentos permitidos dentro de este tipo de evaluaci�n.
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
