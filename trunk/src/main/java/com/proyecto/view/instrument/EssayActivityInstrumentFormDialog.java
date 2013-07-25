package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;

import com.common.util.exception.CheckedException;
import com.proyecto.model.answer.EssayActivityAnswer;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.service.instrument.EssayActivityInstrumentService;
import com.proyecto.view.Resources;

/**
 * La clase que permite desplegar una ventana de edición de instrumentos de ensayos formales.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
public abstract class EssayActivityInstrumentFormDialog extends JDialog {

	private static final long serialVersionUID = -5335279786295006491L;

	/**
	 * El instrumento que vamos a manipular.
	 */
	private EssayActivityInstrument essayActivityInstrument;

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
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.setModal(true);
		this.setResizable(false);
		this.init();
	}

	/**
	 * La función de inicialización de componentes.
	 */
	private void init() {
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
				EssayActivityInstrumentFormDialog.this.SaveInstrument();
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
	public void setEnabled(boolean b) {
		super.setEnabled(b);

		this.descriptionTextArea.setEnabled(b);
		this.answerTextArea.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	/**
	 * La función encargada de crear un nuevo instrumento de ensayo formal para manipularlo dentro de esta ventana.
	 * 
	 * @return El nuevo instrumento de ensayo formal.
	 */
	protected abstract EssayActivityInstrument newEssayActivityInstrument();

	/**
	 * La función encargada de retornar el servicio para los instrumentos de ensayo formal.
	 * 
	 * @return El servicio para los instrumentos de ensayo formal.
	 */
	protected abstract <E extends EssayActivityInstrument> EssayActivityInstrumentService<E> getEssayActivityInstrumentService();

	/*
	 * La función encargada de guardar el instrumento dentro de la base de datos.
	 */
	private void SaveInstrument() {
		new Thread() {
			@Override
			public void run() {
				try {
					EssayActivityInstrumentFormDialog.this.beforeSave();
					EssayActivityInstrumentFormDialog.this.fromDialogToInstrument();
					EssayActivityInstrumentFormDialog.this.getEssayActivityInstrumentService().saveOrUpdate(
							EssayActivityInstrumentFormDialog.this.essayActivityInstrument);
					EssayActivityInstrumentFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(EssayActivityInstrumentFormDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					EssayActivityInstrumentFormDialog.this.afterSave();
				}
			}
		}.start();
	}

	/**
	 * La función antes de guardar.
	 */
	private void beforeSave() {
		this.setEnabled(false);
		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de guardar.
	 */
	private void afterSave() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/*
	 * La función encargada de vaciar los campos de la ventana de edición del instrumento.
	 */
	private void emptyFields() {
		this.descriptionTextArea.setText("");
		this.answerTextArea.setText("");
	}

	/**
	 * La función encargada de cargar dentro de la ventana, el instrumento que estamos editando.
	 */
	private void fromDialogToInstrument() throws CheckedException {
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

	/**
	 * La función encargada de cargar el instrumento dentro del dialogo para su edición.
	 */
	private void fromInstrumentToDialog() {
		// Seteamos la descripción.
		this.descriptionTextArea.setText(this.essayActivityInstrument.getDescription());

		// Seteamos la respuesta.
		if (this.essayActivityInstrument.getAnswer() != null) {
			this.answerTextArea.setText(this.essayActivityInstrument.getAnswer().getAnswer());
		} else {
			this.answerTextArea.setText("");
		}
	}

	/**
	 * La función encargada de crear una ventana para crear un nuevo instrumento de ensayo formal.
	 * 
	 * @return La ventana para crear un nuevo instrumento de ensayo formal.
	 */
	public EssayActivityInstrumentFormDialog createNewDialog() {
		this.setTitle("Nuevo instrumento de ensayo formal");
		this.essayActivityInstrument = this.newEssayActivityInstrument();
		this.emptyFields();
		this.descriptionTextArea.requestFocus();
		return this;
	}

	/**
	 * La función encargada de crear una ventana para editar un instrumento de ensayo formal.
	 * 
	 * @param essayActivityInstrument
	 *            El instrumento de ensayo formal que vamos a editar.
	 * @return La ventana para editar un instrumento de ensayo formal.
	 */
	public EssayActivityInstrumentFormDialog createEditDialog(EssayActivityInstrument essayActivityInstrument) {
		this.setTitle("Edición de instrumento de ensayo formal");
		this.essayActivityInstrument = essayActivityInstrument;
		this.fromInstrumentToDialog();
		this.descriptionTextArea.requestFocus();
		return this;
	}
}