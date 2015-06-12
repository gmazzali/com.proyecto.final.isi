package com.proyecto.service.rule;

import java.util.List;

import com.common.util.exception.CheckedException;
import com.proyecto.dao.rule.RuleSetDao;
import com.proyecto.model.agent.Subject;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.ProyectoService;

/**
 * La interfaz que nos permite definir los servicios que vamos a aplicar a los conjuntos de las reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface RuleSetService extends ProyectoService<RuleSet, Integer> {

	/**
	 * La función encargada de cargar el DAO para este servicio.
	 * 
	 * @param ruleSetDao
	 *            El dao para el servicio de los conjuntos de las reglas.
	 */
	public void setRuleSetDao(RuleSetDao ruleSetDao);

	/**
	 * La función encargada de recuperar el listado de conjuntos de reglas de acuerdo a la asignatura que tenemos seleccionada dentro del sistema al
	 * momento de ingresar a la aplicación.
	 * 
	 * @param subjectSelected
	 *            La materia que tenemos ingresada dentro del sistema, puede ser nula.
	 * @return El listado de los conjuntos de reglas que corresponde con la asignatura que recibimos, en caso de ser nula se retornará todos los
	 *         conjuntos de reglas que hay detallados dentro del sistema y que no tengan una asignatura asignada.
	 * @throws CheckedException
	 *             En caso de que ocurra una falla al momento de recuperar los conjuntos de reglas desde la base de datos.
	 */
	public List<RuleSet> findBySubject(Subject subjectSelected) throws CheckedException;
}