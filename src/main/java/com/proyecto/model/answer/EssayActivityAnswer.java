package com.proyecto.model.answer;

import javax.persistence.Column;
import javax.persistence.Table;

import com.common.util.annotations.Model;
import com.common.util.model.Entity;
import com.proyecto.model.instrument.EssayActivityInstrument;


@Model
@Table(name = "ESSAY_ANSWERS")
@javax.persistence.Entity(name = "EssayActivityAnswer")
public class EssayActivityAnswer extends Answer {
	
	private static final long serialVersionUID = 2732402132346858133L;

	/**
	 * @see Entity.Attributes
	 */
	public interface Attributes extends Answer.Attributes {
		static final String ANSWER = "answer";
		
	}
	protected String answer;
	protected EssayActivityInstrument essayActivityInstrument;
	
	public EssayActivityAnswer() {
		super();
	}

	@Override
	public String toString() {
		return  this.answer;
	}

	public EssayActivityInstrument getEssayActivityInstrument() {
		return essayActivityInstrument;
	}

	public void setEssayActivityInstrument(EssayActivityInstrument essayActivityInstrument) {
		this.essayActivityInstrument = essayActivityInstrument;
	}

	@Column(name = "ANSWER", columnDefinition = "text", nullable = false)
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
