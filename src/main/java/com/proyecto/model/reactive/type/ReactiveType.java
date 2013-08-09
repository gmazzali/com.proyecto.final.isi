package com.proyecto.model.reactive.type;

import com.proyecto.model.instrument.type.InstrumentTypeInterface;

public interface ReactiveType {
	
	/**
	 * La función que retorna el listado de los tipos de instrumentos que tenemos permitidos dentro de este tipo de reactivo.
	 * @return El listado de los tipos de instrumentos permitidos dentro de este tipo de reactivo.
	 */
	public InstrumentTypeInterface[] getInstrumentsTypeAllowed();
}
