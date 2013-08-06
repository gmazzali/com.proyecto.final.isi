package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import com.common.util.exception.CheckedException;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.model.instrument.Instrument;

/**
 * La clase que permite desplegar una ventana de edición de instrumentos de ensayos formales.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class EssayActivityInstrumentFormDialog extends InstrumentFormDialog {

	private static final long serialVersionUID = -5335279786295006491L;

	/**
	 * El instrumento que vamos a manipular.
	 */
	protected EssayActivityInstrument essayActivityInstrument;

	/**
	 * El campo de descripción del instrumento y la respuesta
	 */
	private JTextArea descriptionTextArea;
	private JTextArea answerTextArea;

	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	/**
	 * Los botones de acción.
	 */
	private JButton commitButton;
	private JButton rejectButton;

	/**
	 * Constructor de una ventana de edición de ensayos formales.
	 */
	public EssayActivityInstrumentFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización de componentes.
	 */
	private void init() {
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.setBounds(100, 100, 887, 348);
		this.getContentPane().setLayout(null);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 11, 162, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 30, 861, 95);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setLineWrap(true);
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel answerLabel = new JLabel("Respuesta");
		answerLabel.setFont(new Font("Arial", Font.BOLD, 11));
		answerLabel.setBounds(10, 140, 162, 14);
		this.getContentPane().add(answerLabel);

		JScrollPane answerScrollPane = new JScrollPane();
		answerScrollPane.setBounds(10, 159, 861, 95);
		this.getContentPane().add(answerScrollPane);

		this.answerTextArea = new JTextArea();
		this.answerTextArea.setFont(this.getContentPane().getFont());
		this.answerTextArea.setWrapStyleWord(true);
		this.answerTextArea.setLineWrap(true);
		answerScrollPane.setViewportView(this.answerTextArea);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 265, 861, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(614, 279, 37, 29);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton("Aceptar");
		this.commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.commitButton.setBounds(661, 278, 100, 30);
		this.commitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EssayActivityInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton("Cancelar");
		this.rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.rejectButton.setBounds(771, 278, 100, 30);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EssayActivityInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	@Override
	protected void emptyFields() {
		this.descriptionTextArea.setText("");
		this.answerTextArea.setText("");
	}

	@Override
	protected void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.essay.description");
		} else {
			this.essayActivityInstrument.setDescription(this.descriptionTextArea.getText().trim());
		}

		// Agregamos la respuesta.
		if (this.answerTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.essay.answer");
		} else {
			if (this.essayActivityInstrument.getAnswer() != null) {
				this.essayActivityInstrument.getAnswer().setAnswer(this.answerTextArea.getText().trim());
			} else {
				EssayActivityAnswer answer = new EssayActivityAnswer();
				answer.setAnswer(this.answerTextArea.getText().trim());
				this.essayActivityInstrument.setAnswer(answer);
			}
		}
	}

	@Override
	protected void fromInstrumentToDialog() {
		// Seteamos la descripción.
		this.descriptionTextArea.setText(this.essayActivityInstrument.getDescription());

		// Seteamos la respuesta.
		if (this.essayActivityInstrument.getAnswer() != null) {
			this.answerTextArea.setText(this.essayActivityInstrument.getAnswer().getAnswer());
		} else {
			this.answerTextArea.setText("");
		}
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);

		this.descriptionTextArea.setEnabled(b);
		this.answerTextArea.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	@Override
	protected <E extends Instrument> void setEditInstrument(E instrument) {
		this.essayActivityInstrument = (EssayActivityInstrument) instrument;
	}

	@Override
	protected EssayActivityInstrument getInstrument() {
		return this.essayActivityInstrument;
	}

	@Override
	protected JLabel getProgressLabel() {
		return this.progressLabel;
	}
}