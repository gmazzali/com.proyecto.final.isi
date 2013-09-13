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

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
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
	 * El instrumento que vamos a manipular y la opción dentro de la ventana.
	 */
	protected ChoiceInstrument choiceInstrument;
	protected Option option;

	/**
	 * El listado de las opciones y la opción que estamos editando en la ventana.
	 */
	protected JList<Option> optionsList;

	/**
	 * El campo de descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * Los radioButtons de tipo de respuesta de opción y los botones de todos los anteriores.
	 */
	private ButtonGroup answerTypeGroup;
	private JRadioButton falseAnswerRadioButton;
	private JRadioButton trueAnswerRadioButton;
	private JTextArea optionTextArea;
	/**
	 * Los radioButtons de "todas las anteriores" y "ninguna de las anteriores".
	 */
	private ButtonGroup optionTypeGroup;
	private JRadioButton allOptionRadioButton;
	private JRadioButton noneOptionRadioButton;
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
		this.setBounds(100, 100, 750, 498);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 16, 728, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 33, 728, 92);
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
		choiceLabel.setBounds(10, 137, 728, 14);
		this.getContentPane().add(choiceLabel);

		JScrollPane choiceScrollPane = new JScrollPane();
		choiceScrollPane.setBounds(10, 153, 728, 122);
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

		JScrollPane optionScrollPane = new JScrollPane();
		optionScrollPane.setBounds(10, 287, 563, 62);
		this.getContentPane().add(optionScrollPane);

		this.optionTextArea = new JTextArea();
		this.optionTextArea.setWrapStyleWord(true);
		this.optionTextArea.setLineWrap(true);
		this.optionTextArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		optionScrollPane.setViewportView(this.optionTextArea);

		this.allOptionRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));
		this.allOptionRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.allOptionRadioButton.setBounds(10, 361, 728, 18);
		this.allOptionRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		this.allOptionRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.managerOptionRadioButtons(e);
			}
		});

		this.trueAnswerRadioButton = new JRadioButton("");
		this.trueAnswerRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.trueAnswerRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.trueAnswerRadioButton.setBounds(585, 297, 153, 18);
		this.getContentPane().add(this.trueAnswerRadioButton);

		this.falseAnswerRadioButton = new JRadioButton("");
		this.falseAnswerRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.falseAnswerRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.falseAnswerRadioButton.setBounds(585, 319, 153, 18);
		this.getContentPane().add(this.falseAnswerRadioButton);
		this.getContentPane().add(this.allOptionRadioButton);

		this.answerTypeGroup = new ButtonGroup();
		this.answerTypeGroup.add(this.trueAnswerRadioButton);
		this.answerTypeGroup.add(this.falseAnswerRadioButton);
		this.answerTypeGroup.clearSelection();

		this.noneOptionRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));
		this.noneOptionRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.noneOptionRadioButton.setBounds(10, 381, 728, 18);
		this.noneOptionRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		this.noneOptionRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.managerOptionRadioButtons(e);
			}
		});
		this.getContentPane().add(this.noneOptionRadioButton);

		this.optionTypeGroup = new ButtonGroup();
		this.optionTypeGroup.add(this.allOptionRadioButton);
		this.optionTypeGroup.add(this.noneOptionRadioButton);
		this.optionTypeGroup.clearSelection();

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 410, 728, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(609, 424, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.setBounds(656, 424, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.setBounds(703, 424, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.descriptionTextArea.setEnabled(enabled);

		this.optionsList.setEnabled(enabled);

		this.optionTextArea.setEnabled(enabled);

		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La función que administra los cambios sobre los radioButtons para las respuestas "Todas las anteriores" o "ninguna de las anteriores".
	 */
	private void managerOptionRadioButtons(ActionEvent event) {
		// El radioBotton que lanzo el evento.
		JRadioButton button = (JRadioButton) event.getSource();

		// Si el botón "Todas las anteriores" activo el método.
		if (button == this.allOptionRadioButton) {

			// Si no estaba seleccionada, la seleccionamos y cargamos la descripción de la opción.
			if (!this.allOptionRadioButton.isSelected()) {
				this.allOptionRadioButton.setSelected(false);
				this.noneOptionRadioButton.setSelected(false);

				this.optionTextArea.setText("");
				this.optionTextArea.setEnabled(true);
			}
			// Sino, deshabilitamos la carga de datos en el cuadro de descripción de la opción.
			else {
				this.allOptionRadioButton.setSelected(true);
				this.noneOptionRadioButton.setSelected(false);

				this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));
				this.optionTextArea.setEnabled(false);
			}
		} else if (button == this.noneOptionRadioButton) {

			// Si estaba seleccionado anteriormente, le quitamos la marca y habilitamos la carga de la descripción.
			if (!this.noneOptionRadioButton.isSelected()) {
				this.allOptionRadioButton.setSelected(false);
				this.noneOptionRadioButton.setSelected(false);

				this.optionTextArea.setText("");
				this.optionTextArea.setEnabled(true);
			}
			// Sino, deshabilitamos la carga de datos en el cuadro de descripción de la opción.
			else {
				this.allOptionRadioButton.setSelected(false);
				this.noneOptionRadioButton.setSelected(true);

				this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));
				this.optionTextArea.setEnabled(false);
			}
		}
	}

	/**
	 * La función utilizada para agregar una nueva opción al listado ya cargado.
	 */
	private void addOption() {
		try {
			// Cargamos la opción desde la ventana.
			this.fromDialogToOption();

			// Cargamos la opción a la lista.
			if (this.option != null) {
				// Agregamos la opción al listado.
				DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
				optionModel.addElement(this.option);
			}

			// Vaciamos los campos de opciones.
			this.emptyOptionFields();
		} catch (CheckedException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * La función utilizada para modificar una opción dentro del listado y que ya estaba cargada.
	 */
	private void modifyOption() {
		// Obtenemos la opción para editar.
		Integer optionIndex = this.optionsList.getSelectedIndex();

		if (optionIndex >= 0) {
			// Vemos si tenemos alguna opción anterior, si la tenemos, la volvemos a guardar.
			if (this.option != null) {
				// Agregamos la opción al listado.
				DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
				optionModel.addElement(this.option);
				this.emptyOptionFields();
			}

			// Volvemos a tomar la opción para la edición.
			DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
			this.option = optionModel.get(optionIndex);
			optionModel.remove(optionIndex);

			// Cargamos la opción dentro de la ventana para editarla.
			this.fromOptionToDialog();
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

	/**
	 * La función encargada de tomar una opción y cargarla dentro de la ventana de edición.
	 */
	// TODO gmazzali Hacer lo del pase de la opción que estamos editando a la ventana.
	private void fromOptionToDialog() {
		// Cargamos la opción para editarla.
		this.optionAnswerComboBox.setSelectedItem(this.option.getAnswerType());

		this.optionTextArea.setText(this.option.getDescription());
		this.optionTextArea.requestFocus();

		// Cargamos el tipo de opción.
		if (this.option.getDescription().equals(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"))) {
			this.allOptionRadioButton.setSelected(true);
			this.noneOptionRadioButton.setSelected(false);
			this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));
			this.optionTextArea.setEnabled(false);
		} else if (this.option.getDescription().equals(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"))) {
			this.allOptionRadioButton.setSelected(false);
			this.noneOptionRadioButton.setSelected(true);
			this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));
			this.optionTextArea.setEnabled(false);
		}
	}

	/**
	 * La función encargada de cargar una opción en base a lo que tenemos dentro de la ventana.
	 * 
	 * @throws CheckedException
	 *             En caso de que la opción tenga valores inválidos.
	 */
	// TODO gmazzali Hacer lo del pase de los campos de la ventana a la opción para guardarla.
	private void fromDialogToOption() throws CheckedException {
		// Obtenemos su descripción y el tipo de opción.
		String optionDescription = this.optionTextArea.getText().trim();
		TrueFalseAnswerTypeEnum answerType = (TrueFalseAnswerTypeEnum) this.optionAnswerComboBox.getSelectedItem();

		// Corroboramos que se haya seleccionado un tipo de opción y la descripción tenga algo cargado.
		if (answerType != null && !optionDescription.isEmpty()) {

			// Si hay una opción cargada, verificamos que no se haya cambiado el tipo y modificamos esa.
			if (this.option != null && this.option.getAnswerType().equals(answerType)) {
				this.option.setDescription(optionDescription);
			}
			// Sino, creamos una opción nueva.
			else {
				switch (answerType) {
					case TRUE:
						this.option = new TrueOption();
						break;
					case FALSE:
						this.option = new Distractor();
						break;
				}

				this.option.setDescription(optionDescription);
			}
		} else {
			throw new CheckedException("error.option.load");
		}
	}

	/**
	 * La función de vaciado de los campos de carga de las opciones.
	 */
	// TODO gmazzali Hacer lo del vaciado de los campos de las opciones.
	private void emptyOptionFields() {
		// Borramos el campo de tipo de respuesta, su descripción y la opción.
		this.optionAnswerComboBox.setSelectedIndex(-1);

		this.allOptionRadioButton.setSelected(false);
		this.noneOptionRadioButton.setSelected(false);

		this.optionTextArea.setText("");
		this.optionTextArea.requestFocus();
		this.optionTextArea.setEnabled(true);

		this.option = null;
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

		this.emptyOptionFields();
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