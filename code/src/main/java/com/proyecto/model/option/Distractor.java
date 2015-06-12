package com.proyecto.model.option;

import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.answer.type.TrueFalseAnswerTypeEnum;

/**
 * La clase que representa una opción verdadera.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Model
@Table(name = "DISTRACTORS")
@javax.persistence.Entity(name = "Distractor")
public class Distractor extends Option {

	private static final long serialVersionUID = -6698104317976545039L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Option.Attributes {
	}

	/**
	 * El constructor por omisión.
	 */
	public Distractor() {
		super(TrueFalseAnswerTypeEnum.FALSE.getAnswer());
		this.setTrueFalseAnswer(this.getTrueFalseAnswer());
	}

	@Override
	@Transient
	public TrueFalseAnswerTypeEnum getAnswerType() {
		return TrueFalseAnswerTypeEnum.FALSE;
	}
}
