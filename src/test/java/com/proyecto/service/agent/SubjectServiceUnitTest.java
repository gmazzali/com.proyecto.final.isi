package com.proyecto.service.agent;

import org.junit.AfterClass;
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
	 * Al finalizar dejamos un espacio en blanco en la consola.
	 */
	@AfterClass
	public static void afterClass() {
		System.out.println();
	}

	/**
	 * Pruebas de creación de una materia.
	 */
	@Test
	public void pruebaDeCreacionDeMaterias() {
		// Creamos una nueva materia.
		Subject subject = new Subject();

		System.out.println("######################################################################");
		System.out.println("############################## MATERIAS ##############################");
		System.out.println("######################################################################");

		subject.setName("Materia de prueba");

		SubjectService service = HolderApplicationContext.getContext().getBean(SubjectService.class);

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< GUARDADO DE MATERIA >>>>>>>>>>>>>>>>>>>>>>>>>");
			service.save(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la materia.
		subject.setName("Otra materia de prueba");

		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<< ACTUALIZADO DE LA MATERIA >>>>>>>>>>>>>>>>>>>>>>");
			service.update(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<< BORRADO DE LA MATERIA >>>>>>>>>>>>>>>>>>>>>>>>");
			service.delete(subject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
