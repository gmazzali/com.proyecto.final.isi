package com.proyecto.service.instrument;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.instrument.CorrespondenceInstrument;

/**
 * La clase que usamos para probar los instrumentos de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class CorrespondenceInstrumentTest {

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
	 * La prueba de creación del instrumento.
	 */
	@Test
	public void pruebaDeCreacionDeCorrespondencias() {
		// Creamos un nuevo instrumento.
		RelationAnswer relation = null;
		List<RelationAnswer> relations = null;
		CorrespondenceInstrument correspondence = new CorrespondenceInstrument();

		correspondence.setDescription("Descripcion de correspondencia");

		CorrespondenceInstrumentService service = HolderApplicationContext.getContext().getBean(CorrespondenceInstrumentService.class);

		relation = new RelationAnswer();
		relations = new ArrayList<>();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 1");
		relation.setRightSide("derecha 1");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 2");
		relation.setRightSide("derecha 2");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 3");
		relation.setRightSide("derecha 3");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 4");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 5");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setRightSide("derecha 6");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setRightSide("derecha 7");
		relations.add(relation);

		correspondence.getRelations().addAll(relations);

		try {
			service.save(correspondence);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// Modificamos la regla.
		correspondence.setDescription("Otra descripcion de correspondencia 1");
		correspondence.getRelations().remove(relation);

		try {
			service.update(correspondence);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		relations = new ArrayList<>();
		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 11");
		relation.setRightSide("derecha 11");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 12");
		relation.setRightSide("derecha 12");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 13");
		relation.setRightSide("derecha 13");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 14");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setLeftSide("izquierda 15");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setRightSide("derecha 16");
		relations.add(relation);

		relation = new RelationAnswer();
		relation.setCorrespondenceInstrument(correspondence);
		relation.setRightSide("derecha 17");
		relations.add(relation);

		correspondence.setDescription("Otra descripcion de correspondencia 2");
		correspondence.getRelations().clear();
		correspondence.getRelations().addAll(relations);

		try {
			service.update(correspondence);
		} catch (CheckedException e) {
			e.printStackTrace();
		}

		// La borramos.
		try {
			service.delete(correspondence);
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
