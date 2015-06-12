package com.proyecto.model.answer;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;

/**
 * La clase que nos permite definir una respuesta a los instrumentos que tenemos dentro del sistema para luego agregarle un modulo que verifique las
 * mismas con las que se cargaron a estos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "ANSWERS")
@javax.persistence.Entity(name = "Answer")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Answer extends Entity<Integer> {

	private static final long serialVersionUID = 8320735236711354967L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Entity.Attributes {
	}

	/**
	 * El constructor por default de este elemento.
	 */
	public Answer() {
		super();
	}

	@Id
	@Override
	@Column(name = "ID_ANSWER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return super.getId();
	}
}
