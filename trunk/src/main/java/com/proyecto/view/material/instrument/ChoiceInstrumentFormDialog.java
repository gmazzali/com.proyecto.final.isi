package com.proyecto.view.material.instrument;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

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
 * La clase que nos permite editar un instrumento de selecci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class ChoiceInstrumentFormDialog extends InstrumentFormDialog {

	private static final long serialVersionUID = 3339143561900518060L;

	/*
	 * El instrumento que vamos a manipular y la opci�n dentro de la ventana.
	 */
	protected ChoiceInstrument choiceInstrument;
	protected Option option;

	/**
	 * El listado de las opciones y la opci�n que estamos editando en la ventana.
	 */
	protected JList<Option> optionsList;

	/**
	 * El campo de descripci�n del instrumento.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * Los el grupo de radioButtons de tipo de respuesta (TRUE o FALSE), dichos radioButtons y el area de descripci�n de la opci�n.
	 */
	private ButtonGroup answerTypeGroup;
	private JRadioButton falseAnswerRadioButton;
	private JRadioButton trueAnswerRadioButton;
	private JTextArea optionTextArea;
	/**
	 * El grupo de los radioButtons de "todas las anteriores" y "ninguna de las anteriores"y dichos radioButton.
	 */
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
	 * Constructor de una ventana de edici�n de instrumentos de selecci�n.
	 */
	public ChoiceInstrumentFormDialog() {
		super();
		this.init();
	}

	/**
	 * La funci�n encargada de inicializar la ventana de edici�n de un instrumento de correspondencia.
	 */
	private void init() {
		this.setBounds(100, 100, 750, 562);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 10, 728, 16);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(6, 27, 732, 98);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setBorder(new LineBorder(Color.GRAY));
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
		choiceLabel.setBounds(10, 137, 728, 16);
		this.getContentPane().add(choiceLabel);

		JScrollPane choiceScrollPane = new JScrollPane();
		choiceScrollPane.setBounds(6, 154, 732, 124);
		this.getContentPane().add(choiceScrollPane);

		this.optionsList = new JList<Option>();
		this.optionsList.setBorder(new LineBorder(Color.GRAY));
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

		this.trueAnswerRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.true"));
		this.trueAnswerRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.trueAnswerRadioButton.setBounds(10, 301, 728, 18);
		this.trueAnswerRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(6, 287, 732, 2);
		this.getContentPane().add(separator1);
		this.getContentPane().add(this.trueAnswerRadioButton);

		this.falseAnswerRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.false"));
		this.falseAnswerRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.falseAnswerRadioButton.setBounds(10, 321, 728, 18);
		this.falseAnswerRadioButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ChoiceInstrumentFormDialog.this.addOption();
				}
			}
		});
		this.getContentPane().add(this.falseAnswerRadioButton);

		this.answerTypeGroup = new ButtonGroup();
		this.answerTypeGroup.add(this.trueAnswerRadioButton);
		this.answerTypeGroup.add(this.falseAnswerRadioButton);
		this.answerTypeGroup.clearSelection();

		this.allOptionRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));

		this.allOptionRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.allOptionRadioButton.setBounds(10, 353, 728, 18);
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
				ChoiceInstrumentFormDialog.this.managerOptionRadioButtons(ChoiceInstrumentFormDialog.this.allOptionRadioButton);
			}
		});
		this.getContentPane().add(this.allOptionRadioButton);

		this.noneOptionRadioButton = new JRadioButton(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));

		this.noneOptionRadioButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.noneOptionRadioButton.setBounds(10, 373, 728, 18);
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
				ChoiceInstrumentFormDialog.this.managerOptionRadioButtons(ChoiceInstrumentFormDialog.this.noneOptionRadioButton);
			}
		});
		this.getContentPane().add(this.noneOptionRadioButton);

		JScrollPane optionScrollPane = new JScrollPane();
		optionScrollPane.setBounds(6, 403, 732, 66);
		this.getContentPane().add(optionScrollPane);

		this.optionTextArea = new JTextArea();
		this.optionTextArea.setBorder(new LineBorder(Color.GRAY));
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

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 477, 728, 2);
		this.getContentPane().add(separator2);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(609, 491, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.setBounds(656, 491, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.setBounds(703, 491, 35, 35);
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

		Boolean isOptionSelected = this.allOptionRadioButton.isSelected() || this.noneOptionRadioButton.isSelected();
		this.optionTextArea.setEnabled(enabled && !isOptionSelected);

		this.trueAnswerRadioButton.setEnabled(enabled);
		this.falseAnswerRadioButton.setEnabled(enabled);
		this.allOptionRadioButton.setEnabled(enabled);
		this.noneOptionRadioButton.setEnabled(enabled);

		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La funci�n que administra los cambios sobre los radioButtons para las respuestas "Todas las anteriores" o "ninguna de las anteriores".
	 * 
	 * @param selectButton
	 *            El radioButton que fue activado.
	 */
	private void managerOptionRadioButtons(JRadioButton selectButton) {
		// Si el que presionamos es igual al que tenemos activo, limpiamos la selecci�n y habilitamos la carga de la descripci�n.
		if (!selectButton.isSelected()) {
			this.allOptionRadioButton.setSelected(false);
			this.noneOptionRadioButton.setSelected(false);
			this.optionTextArea.setText("");
			this.optionTextArea.setEnabled(true);
		}
		// Si lo que presionamos no estaba seleccionado, cargamos la descripci�n y deshabilitarla.
		else {
			this.optionTextArea.setEnabled(false);

			if (selectButton == this.allOptionRadioButton) {
				this.noneOptionRadioButton.setSelected(false);
				this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));
			} else if (selectButton == this.noneOptionRadioButton) {
				this.allOptionRadioButton.setSelected(false);
				this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));
			}
		}
	}

	/**
	 * La funci�n utilizada para agregar una nueva opci�n al listado ya cargado.
	 */
	private void addOption() {
		try {
			// Cargamos la opci�n desde la ventana.
			this.fromDialogToOption();

			// Cargamos la opci�n a la lista.
			if (this.option != null) {
				// Agregamos la opci�n al listado.
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
	 * La funci�n utilizada para modificar una opci�n dentro del listado y que ya estaba cargada.
	 */
	private void modifyOption() {
		// Obtenemos la opci�n para editar.
		Integer optionIndex = this.optionsList.getSelectedIndex();

		if (optionIndex >= 0) {
			// Vemos si tenemos alguna opci�n anterior, si la tenemos, la volvemos a guardar.
			if (this.option != null) {
				// Agregamos la opci�n al listado.
				DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
				optionModel.addElement(this.option);
				this.emptyOptionFields();
			}

			// Volvemos a tomar la opci�n para la edici�n.
			DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
			this.option = optionModel.get(optionIndex);
			optionModel.remove(optionIndex);

			// Cargamos la opci�n dentro de la ventana para editarla.
			this.fromOptionToDialog();
		}
	}

	/**
	 * La funci�n utilizada para eliminar una nueva opci�n seleccionada.
	 * 
	 * @return TRUE en caso de que haya quitado la opci�n, en caso contrario retorna FALSE.
	 */
	private boolean removeOption() {
		// Obtenemos la opci�n para borrar.
		Integer optionIndex = this.optionsList.getSelectedIndex();

		if (optionIndex >= 0) {
			// Borramos la opci�n seleccionada.
			DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
			optionModel.remove(optionIndex);
			return true;
		}
		return false;
	}

	/**
	 * La funci�n encargada de tomar una opci�n y cargarla dentro de la ventana de edici�n.
	 */
	private void fromOptionToDialog() {
		// Cargamos la opci�n para editarla.
		switch (this.option.getAnswerType()) {
			case TRUE:
				this.answerTypeGroup.setSelected(this.trueAnswerRadioButton.getModel(), true);
				break;

			case FALSE:
				this.answerTypeGroup.setSelected(this.falseAnswerRadioButton.getModel(), true);
				break;
		}

		this.optionTextArea.setText(this.option.getDescription());
		this.optionTextArea.requestFocus();

		// Cargamos el tipo de opci�n.
		if (this.option.getDescription().equals(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"))) {
			this.allOptionRadioButton.setSelected(true);
			this.optionTextArea.setEnabled(false);
			this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.true"));
		} else if (this.option.getDescription().equals(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"))) {
			this.noneOptionRadioButton.setSelected(true);
			this.optionTextArea.setEnabled(false);
			this.optionTextArea.setText(HolderMessage.getMessage("instrument.formal.objective.choice.form.label.all.false"));
		}
	}

	/**
	 * La funci�n encargada de cargar una opci�n en base a lo que tenemos dentro de la ventana.
	 * 
	 * @throws CheckedException
	 *             En caso de que la opci�n tenga valores inv�lidos.
	 */
	private void fromDialogToOption() throws CheckedException {
		// Obtenemos su descripci�n y el tipo de opci�n.
		String optionDescription = this.optionTextArea.getText().trim();

		// Corroboramos que se haya seleccionado un tipo de respuesta (T o F) y la descripci�n tenga algo cargado.
		if (this.answerTypeGroup.getSelection() != null && !optionDescription.isEmpty()) {

			// Si hay una opci�n cargada, verificamos que no se haya cambiado el tipo y modificamos esa.
			TrueFalseAnswerTypeEnum answerType = null;
			if (this.answerTypeGroup.getSelection().equals(this.trueAnswerRadioButton.getModel())) {
				answerType = TrueFalseAnswerTypeEnum.TRUE;
			} else if (this.answerTypeGroup.getSelection().equals(this.falseAnswerRadioButton.getModel())) {
				answerType = TrueFalseAnswerTypeEnum.FALSE;
			}
			if (this.option != null && answerType != null && this.option.getAnswerType().equals(answerType)) {
				this.option.setDescription(optionDescription);
			}
			// Sino, creamos una opci�n nueva.
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
	 * La funci�n de vaciado de los campos de carga de las opciones.
	 */
	private void emptyOptionFields() {
		// Borramos el campo de tipo de respuesta, su descripci�n y la opci�n.
		this.allOptionRadioButton.setSelected(false);
		this.noneOptionRadioButton.setSelected(false);

		this.answerTypeGroup.clearSelection();

		this.optionTextArea.setText("");
		this.optionTextArea.requestFocus();
		this.optionTextArea.setEnabled(true);

		this.option = null;
	}

	@Override
	protected void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripci�n.
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
		// Seteamos la descripci�n.
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