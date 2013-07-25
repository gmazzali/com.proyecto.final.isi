package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.common.util.exception.CheckedException;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.service.instrument.EssayActivityInstrumentService;
import com.proyecto.view.Resources;


public abstract class EssayActivityInstrumentForm extends JDialog {

	private static final long serialVersionUID = -5335279786295006491L;
	
	/**
	 * El instrumento que vamos a manipular.
	 */
	private EssayActivityInstrument essayActivityInstrument;
	
	/**
	 * El campo de descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;
	
	/**
	 * La función encargada de crear un nuevo instrumento de selección para manipularlo dentro de esta ventana.
	 * 
	 * @return El nuevo instrumento de selección.
	 */
	protected abstract EssayActivityInstrument newEssayActivityInstrument();
	
	/**
	 * La función encargada de retornar el servicio para los instrumentos de selección.
	 * 
	 * @return El servicio para los instrumentos de selección.
	 */
	protected abstract <E extends EssayActivityInstrument> EssayActivityInstrumentService<E> getEssayActivityInstrumentService();
	
	
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	
	/**
	* Variable que almacena la respuesta
	*/
	private JTextArea answerTextArea;
	
	/**
	 * La función encargada de inicializar la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar el listado de las reglas.
	 */
	public EssayActivityInstrumentForm createDialog() {
		this.setTitle("Listado de conjuntos de reglas");
		return this;
	}
	
	/**
	 * Create the dialog.
	 */
	public EssayActivityInstrumentForm() {
		super(); 
		this.init();
	}
	
	   private void init() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Descripci\u00F3n");
		label.setFont(new Font("Arial", Font.BOLD, 11));
		label.setBounds(10, 11, 162, 14);
		getContentPane().add(label);
		
		JLabel answerLabel = new JLabel("Respuesta");
		answerLabel.setFont(new Font("Arial", Font.BOLD, 11));
		answerLabel.setBounds(10, 117, 162, 14);
		getContentPane().add(answerLabel);
		
		JButton okButton = new JButton("Aceptar");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EssayActivityInstrumentForm.this.SaveInstrument();
		
			}
		});
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setBounds(214, 221, 100, 30);
		getContentPane().add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EssayActivityInstrumentForm.this.dispose();
			}
		});
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setBounds(324, 221, 100, 30);
		getContentPane().add(cancelButton);
		
		this.descriptionTextArea = new JTextArea();
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setBounds(10, 45, 414, 61);
		getContentPane().add(descriptionTextArea);
		
		answerTextArea = new JTextArea();
		answerTextArea.setWrapStyleWord(true);
		answerTextArea.setLineWrap(true);
		answerTextArea.setBounds(10, 142, 414, 61);
		getContentPane().add(answerTextArea);
		
		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(159, 221, 37, 29);
		getContentPane().add(this.progressLabel);

	}
	
	/*
	 * La función encargada de guasrdar el instrumento dentro de la base de datos.
	 */
	private void SaveInstrument() {
		new Thread() {
			@Override
			public void run() {
				try {
					EssayActivityInstrumentForm.this.beforeSave();
					EssayActivityInstrumentForm.this.fromDialogToInstrument();
					EssayActivityInstrumentForm.this.getEssayActivityInstrumentService().saveOrUpdate(EssayActivityInstrumentForm.this.essayActivityInstrument);
					EssayActivityInstrumentForm.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(EssayActivityInstrumentForm.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					EssayActivityInstrumentForm.this.afterSave();
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
	/**
	 * La función encargada de cargar dentro de la ventana, el instrumento que estamos editando.
	 */
	private void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.selection.description");
		} else {
			this.essayActivityInstrument.setDescription(this.descriptionTextArea.getText());
		}
	}
	/**
	 * La función encargada de crear una ventana para crear un nuevo instrumento de selección.
	 * 
	 * @return La ventana para crear un nuevo instrumento de selección.
	 */
	public EssayActivityInstrumentForm createNewDialog() {
		this.setTitle("Instrumento de selección");
		this.essayActivityInstrument = this.newEssayActivityInstrument();
		this.emptyFields();
		this.descriptionTextArea.requestFocus();
		return this;
	}

	/*
	 * La función encargada de vaciar los campos de la ventana de edición del instrumento.
	 */
	private void emptyFields() {
		this.descriptionTextArea.setText("");
		this.answerTextArea.setText("");
	}

	/**
	 * La función encargada de crear una ventana para editar un instrumento de selección.
	 * 
	 * @param essayActivityInstrument
	 *            El instrumento de selección que vamos a editar.
	 * @return La ventana para editar un instrumento de selección.
	 */
	public EssayActivityInstrumentForm createEditDialog(EssayActivityInstrument essayActivityInstrument) {
		this.setTitle("Instrumento de selección");
		this.essayActivityInstrument = essayActivityInstrument;
		this.fromInstrumentToDialog();
		this.descriptionTextArea.requestFocus();
		return this;
	}
	/**
	 * La función encargada de cargar el instrumento dentro del dialogo para su edición.
	 */
	private void fromInstrumentToDialog() {
		// Seteamos la descripción.
		this.descriptionTextArea.setText(this.essayActivityInstrument.getDescription());

		// Seteamos la respuesta
		this.answerTextArea.setText(this.essayActivityInstrument.getEssayActivityAnswer());
	}	
}
