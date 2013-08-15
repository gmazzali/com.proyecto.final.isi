package com.proyecto.service.material.instrument;

import com.common.util.exception.CheckedException;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.service.material.MaterialService;

/**
 * La interfaz que define los servicios que van a ofrecerse a los instrumentos que tenemos en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de instrumento que vamos a manipular dentro del servicio.
 */
public interface InstrumentService<E extends Instrument> extends MaterialService<E, Integer> {

	/**
	 * La función encargada de verificar que el instrumento que queremos eliminar no se encuentre apuntado por un reactivo.
	 * 
	 * @param instrument
	 *            El instrumento que queremos verificar.
	 * @return TRUE en caso de que el instrumento no se encuentre apuntado por ningún reactivo, en caso de que este apuntado por al menos 1 retornará
	 *         FALSE.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla en la base de datos a la hora de verificar que existan reactivos que hacen uso de este instrumento.
	 */
	public Boolean isValidDeleteInstrument(E instrument) throws CheckedException;
}