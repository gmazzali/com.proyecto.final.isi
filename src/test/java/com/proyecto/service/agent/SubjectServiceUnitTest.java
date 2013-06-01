package com.proyecto.service.agent;

import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.agent.Subject;

/**
 * La clase de prueba de los servicios de las materias.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class SubjectServiceUnitTest {

	/**
	 * Antes de que arranque la ejecución de la clase, cargamos el dao.
	 */
	@BeforeClass
	public static void beforeClass() {
		String[] files =
			{ "/com/proyecto/spring/general-application-context.xml" };
		HolderApplicationContext.initApplicationContext(files);
	}

	/**
	 * Pruebas de creación.
	 */
	@Test
	public void pruebaDeCreacionDeMaterias() {
		// Creamos una nueva materia.
		Subject subject = new Subject();

		subject.setName("Materia de prueba");

		SubjectService service = HolderApplicationContext.getContext().getBean(SubjectService.class);

		try {
			service.save(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la materia.
		subject.setName("Otra materia de prueba");

		try {
			service.update(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			service.delete(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
