package com.proyecto.view.material.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.common.util.exception.CheckedException;
import com.proyecto.model.answer.type.TrueFalseAnswerTypeEnum;
import com.proyecto.model.material.instrument.ChoiceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.Option;
import com.proyecto.model.option.TrueOption;
import com.proyecto.view.Resources;

/**
 * La clase que nos permite editar un instrumento de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class ChoiceInstrumentFormDialog extends InstrumentFormDialog {

	private static final long serialVersionUID = 3339143561900518060L;

	/*
	 * El instrumento que vamos a manipular.
	 */
	protected ChoiceInstrument choiceInstrument;

	/**
	 * El listado de las opciones y la opción que estamos editando en la ventana.
	 */
	protected JList<Option> optionsList;

	/**
	 * El campo de descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * El combo del tipo de opción, el campo para la opción que estamos editando y los botones de todos los anteriores.
	 */
	private JComboBox<TrueFalseAnswerTypeEnum> optionAnswerComboBox;
	private JTextField optionTextField;
	private JRadioButton allChoiceRadioButton;
	private JRadioButton noneChoiceRadioButton;
	/**
	 * Los botones de aceptar y cancelar.
	 */
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de una ventana de edición de instrumentos de selección.
	 */
	public ChoiceInstrumentFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana de edición de un instrumento de correspondencia.
	 */
	private void init() {
		this.setBounds(100, 100, 906, 384);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 16, 884, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 33, 884, 63);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ChoiceInstrumentFormDialog.this.descriptionTextArea.selectAll();
			}
		});
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel choiceLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.options"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		choiceLabel.setFont(new Font("Arial", Font.BOLD, 11));
		choiceLabel.setBounds(10, 108, 884, 14);
		this.getContentPane().add(choiceLabel);

		JScrollPane choiceScrollPane = new JScrollPane();
		choiceScrollPane.setBounds(10, 124, 884, 122);
		this.getContentPane().add(choiceScrollPane);

		this.optionsList = new JList<Option>();
		this.optionsList.setFont(this.getContentPane().getFont());
		this.optionsList.setModel(new DefaultListModel<Option>());
		choiceScrollPane.setViewportView(this.optionsList);
		this.optionsList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					ChoiceInstrumentFormDialog.this.removeOption();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.modifyOption();
				}
			}
		});
		this.optionsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ChoiceInstrumentFormDialog.this.modifyOption();
				}
			}
		});

		this.optionAnswerComboBox = new JComboBox<TrueFalseAnswerTypeEnum>();
		this.optionAnswerComboBox.setFont(this.getContentPane().getFont());
		this.optionAnswerComboBox.setBounds(10, 253, 137, 30);
		for (TrueFalseAnswerTypeEnum item : TrueFalseAnswerTypeEnum.values()) {
			this.optionAnswerComboBox.addItem(item);
		}
		this.optionAnswerComboBox.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		this.optionAnswerComboBox.setSelectedIndex(-1);
		this.getContentPane().add(this.optionAnswerComboBox);

		this.optionTextField = new JTextField();
		this.optionTextField.setFont(this.getContentPane().getFont());
		this.optionTextField.setBounds(159, 253, 735, 30);
		this.optionTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		this.optionTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				ChoiceInstrumentFormDialog.this.optionTextField.selectAll();
			}
		});
		this.getContentPane().add(this.optionTextField);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 295, 884, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(765, 311, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.setBounds(812, 311, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.setBounds(859, 311, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}
	
	/**
	 * La función que administra los cambios sobre los radioButtons para las respuestas "Todas las anteriores" o "ninguna de las anteriores".
	 */
	private void managerAnswerRadioButtons() {
		// Si está el botón de "Todas las anteriores" habilitado.
		if(this.allChoiceRadioButton.isSelected()) {
			
		}
		
		// Si está el botón de "Ninguna de las anteriores" habilitado.
		if (this.noneChoiceRadioButton.isSelected()) {

		}
		// TODO gmazzali Hacer toda la administración de los radioButtons de la ventana.
	}

	/**
	 * La función utilizada para agregar una nueva opción al listado ya cargado.
	 */
	private void addOption() {
		// Obtenemos su descripción y el tipo de opción.
		String optionDescription = this.optionTextField.getText().trim();
		TrueFalseAnswerTypeEnum answerType = (TrueFalseAnswerTypeEnum) this.optionAnswerComboBox.getSelectedItem();

		// Corroboramos que haya algo en la descripción de la opción.
		if (answerType != null) {

			Option option = null;
			switch (answerType) {
				case TRUE:
					option = new TrueOption();
					break;
				case FALSE:
					option = new Distractor();
					break;
			}

			// Si hay una opción creada, la completamos.
			if (option != null) {
				option.setDescription(optionDescription);

				// Agregamos la opción al listado.
				DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
				optionModel.addElement(option);

				// Borramos el campo de tipo de respuesta y de su descripción.
				this.optionAnswerComboBox.setSelectedIndex(-1);
				this.optionTextField.setText("");
				this.optionTextField.requestFocus();
			}
		}
	}

	/**
	 * La función utilizada para modificar una opción dentro del listado y que ya estaba cargada.
	 */
	private void modifyOption() {
		// Obtenemos la opción para editar.
		Integer optionIndex = this.optionsList.getSelectedIndex();

		if (optionIndex >= 0) {
			// Agregamos la opción que estamos editando.
			this.addOption();

			// Modificamos la opción seleccionada.
			DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
			Option option = optionModel.get(optionIndex);
			optionModel.remove(optionIndex);

			// Cargamos la opción para editarla.
			this.optionAnswerComboBox.setSelectedItem(option.getAnswerType());
			this.optionTextField.setText(option.getDescription());
			this.optionTextField.requestFocus();
		}
	}

	/**
	 * La función utilizada para eliminar una nueva opción seleccionada.
	 * 
	 * @return TRUE en caso de que haya quitado la opción, en caso contrario retorna FALSE.
	 */
	private boolean removeOption() {
		// Obtenemos la opción para borrar.
		Integer optionIndex = this.optionsList.getSelectedIndex();

		if (optionIndex >= 0) {
			// Borramos la opción seleccionada.
			DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
			optionModel.remove(optionIndex);
			return true;
		}
		return false;
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);

		this.descriptionTextArea.setEnabled(b);

		this.optionsList.setEnabled(b);

		this.optionAnswerComboBox.setEnabled(b);
		this.optionTextField.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	@Override
	protected void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.objective.choice.form.error.description");
		} else {
			this.choiceInstrument.setDescription(this.descriptionTextArea.getText());
		}

		// Cargamos las opciones.
		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		List<Option> options = new ArrayList<Option>();

		// Obtenemos el listado de las opciones.
		for (Integer index = 0; index < optionModel.getSize(); index++) {
			Option option = optionModel.get(index);

			if (option.getDescription().isEmpty()) {
				throw new CheckedException("instrument.formal.objective.choice.form.error.empty.option");
			}

			option.setInstrument(this.choiceInstrument);
			options.add(option);
		}

		if (options.isEmpty()) {
			throw new CheckedException("instrument.formal.objective.choice.form.error.options");
		}

		// Agregamos las opciones al listado del instrumento.
		this.choiceInstrument.getOptions().clear();
		this.choiceInstrument.getOptions().addAll(options);
	}

	@Override
	protected void fromInstrumentToDialog() {
		// Seteamos la descripción.
		this.descriptionTextArea.setText(this.choiceInstrument.getDescription());

		// Cargamos el listado de las opciones.
		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		for (Option o : this.choiceInstrument.getOptions()) {
			optionModel.addElement(o);
		}
	}

	@Override
	protected void emptyFields() {
		this.descriptionTextArea.setText("");

		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		optionModel.clear();

		this.optionTextField.setText("");
	}

	@Override
	protected <E extends Instrument> void setEditInstrument(E instrument) {
		this.choiceInstrument = (ChoiceInstrument) instrument;
	}

	@Override
	protected ChoiceInstrument getInstrument() {
		return this.choiceInstrument;
	}

	@Override
	protected JLabel getProgressLabel() {
		return this.progressLabel;
	}
}