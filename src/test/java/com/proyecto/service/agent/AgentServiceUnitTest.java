package com.proyecto.service.agent;

import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.agent.Agent;
import com.proyecto.model.agent.Subject;

/**
 * La clase de prueba de los servicios de los agentes.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AgentServiceUnitTest {

	/**
	 * Antes de que arranque la ejecución de la clase, cargamos el dao.
	 */
	@BeforeClass
	public static void beforeClass() {
		String[] files =
			{ "/com/proyecto/spring/general-application-context.xml" };
		HolderApplicationContext.initApplicationContext(files);
	}

	@Test
	public void pruebaDeCreacionDeAgente() {
		// Creamos el agente de prueba.
		Agent agent = new Agent();

		agent.setName("Nombre prueba");
		agent.setPassword("Password prueba");

		AgentService service = HolderApplicationContext.getContext().getBean(AgentService.class);

		try {
			service.save(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el agente.
		agent.setName("Otro nombre de prueba");
		agent.setPassword("Otro password de prueba");

		try {
			service.update(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Lo borramos.
		try {
			service.delete(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void pruebasDeGuardadoDeMaterias() {
		// Creamos un agente.
		Agent agent = new Agent();

		agent.setName("Nombre prueba con materias");
		agent.setPassword("Password prueba con materias");

		AgentService service = HolderApplicationContext.getContext().getBean(AgentService.class);

		// Creamos un par de materias.
		Subject subject = new Subject();
		subject.setName("materia 1 de prueba");
		agent.getSubjects().add(subject);

		subject = new Subject();
		subject.setName("materia 2 de prueba");
		agent.getSubjects().add(subject);

		subject = new Subject();
		subject.setName("materia 3 de prueba");
		agent.getSubjects().add(subject);

		subject = new Subject();
		subject.setName("materia 4 de prueba");
		agent.getSubjects().add(subject);

		SubjectService subjectService = HolderApplicationContext.getContext().getBean(SubjectService.class);
		Subject deleteSubject = agent.getSubjects().get(0);

		try {
			for (Subject s : agent.getSubjects()) {
				subjectService.save(s);
			}
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Guardamos el agente.
		try {
			service.save(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos el conjunto de regla.
		agent.setName("Otro nombre con materias");
		agent.getSubjects().remove(deleteSubject);

		try {
			service.update(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Lo borramos.
		try {
			service.delete(agent);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		try {
			for (Subject s : agent.getSubjects()) {
				subjectService.delete(s);
			}
			subjectService.delete(deleteSubject);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
