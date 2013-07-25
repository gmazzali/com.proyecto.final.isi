package com.proyecto.view.instrument;

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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.common.util.exception.CheckedException;
import com.proyecto.model.answer.type.TrueFalseAnswerTypeEnum;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.option.Distractor;
import com.proyecto.model.option.Option;
import com.proyecto.model.option.TrueOption;
import com.proyecto.service.instrument.ChoiceInstrumentService;
import com.proyecto.view.Resources;

/**
 * La clase que nos permite editar un instrumento de selección.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class ChoiceInstrumentFormDialog extends JDialog {

	private static final long serialVersionUID = 3339143561900518060L;

	/*
	 * El instrumento que vamos a manipular.
	 */
	private ChoiceInstrument choiceInstrument;

	/**
	 * El listado de las opciones y la opción que estamos editando en la ventana.
	 */
	private JList<Option> optionsList;

	/**
	 * El campo de descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * El combo del tipo de opción y el campo para la opción que estamos editando.
	 */
	private JComboBox<TrueFalseAnswerTypeEnum> optionAnswerComboBox;
	private JTextField optionTextField;
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
		this.setBounds(100, 100, 906, 376);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 16, 65, 14);
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

		JLabel choiceLabel = new JLabel("Opciones");
		choiceLabel.setFont(new Font("Arial", Font.BOLD, 11));
		choiceLabel.setBounds(10, 108, 52, 14);
		this.getContentPane().add(choiceLabel);

		JScrollPane choiceScrollPane = new JScrollPane();
		choiceScrollPane.setBounds(10, 124, 884, 122);
		this.getContentPane().add(choiceScrollPane);

		this.optionsList = new JList<>();
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

		this.optionAnswerComboBox = new JComboBox<>();
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

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(635, 311, 37, 29);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton("Aceptar");
		this.commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.commitButton.setBounds(684, 311, 100, 30);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton("Cancelar");
		this.rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.rejectButton.setBounds(794, 311, 100, 30);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChoiceInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 295, 878, 2);
		this.getContentPane().add(separator);
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

	/**
	 * La función encargada de crear un nuevo instrumento de selección para manipularlo dentro de esta ventana.
	 * 
	 * @return El nuevo instrumento de selección.
	 */
	protected abstract ChoiceInstrument newChoiceInstrument();

	/**
	 * La función encargada de retornar el servicio para los instrumentos de selección.
	 * 
	 * @return El servicio para los instrumentos de selección.
	 */
	protected abstract <E extends ChoiceInstrument> ChoiceInstrumentService<E> getChoiceInstrumentService();

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

	/**
	 * La función encargada de cargar dentro de la ventana, el instrumento que estamos editando.
	 */
	private void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.objective.selection.description");
		} else {
			this.choiceInstrument.setDescription(this.descriptionTextArea.getText());
		}

		// Cargamos las opciones.
		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		List<Option> options = new ArrayList<>();

		// Obtenemos el listado de las opciones.
		for (Integer index = 0; index < optionModel.getSize(); index++) {
			Option option = optionModel.get(index);

			if (option.getDescription().isEmpty()) {
				throw new CheckedException("instrument.formal.objective.selection.empty.option");
			}

			option.setInstrument(this.choiceInstrument);
			options.add(option);
		}

		if (options.isEmpty()) {
			throw new CheckedException("instrument.formal.objective.selection.options");
		}

		// Agregamos las opciones al listado del instrumento.
		this.choiceInstrument.getOptions().clear();
		this.choiceInstrument.getOptions().addAll(options);
	}

	/**
	 * La función encargada de cargar el instrumento dentro del dialogo para su edición.
	 */
	private void fromInstrumentToDialog() {
		// Seteamos la descripción.
		this.descriptionTextArea.setText(this.choiceInstrument.getDescription());

		// Cargamos el listado de las opciones.
		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		for (Option o : this.choiceInstrument.getOptions()) {
			optionModel.addElement(o);
		}
	}

	/*
	 * La función encargada de guardar el instrumento dentro de la base de datos.
	 */
	private void saveInstrument() {
		new Thread() {
			@Override
			public void run() {
				try {
					ChoiceInstrumentFormDialog.this.beforeSave();
					ChoiceInstrumentFormDialog.this.fromDialogToInstrument();
					ChoiceInstrumentFormDialog.this.getChoiceInstrumentService().saveOrUpdate(ChoiceInstrumentFormDialog.this.choiceInstrument);
					ChoiceInstrumentFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(ChoiceInstrumentFormDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					ChoiceInstrumentFormDialog.this.afterSave();
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

		DefaultListModel<Option> optionModel = (DefaultListModel<Option>) this.optionsList.getModel();
		optionModel.clear();

		this.optionTextField.setText("");
	}

	/**
	 * La función encargada de crear una ventana para crear un nuevo instrumento de selección.
	 * 
	 * @return La ventana para crear un nuevo instrumento de selección.
	 */
	public ChoiceInstrumentFormDialog createNewDialog() {
		this.setTitle("Instrumento de selección");
		this.choiceInstrument = this.newChoiceInstrument();
		this.emptyFields();
		this.descriptionTextArea.requestFocus();
		return this;
	}

	/**
	 * La función encargada de crear una ventana para editar un instrumento de selección.
	 * 
	 * @param choiceInstrument
	 *            El instrumento de selección que vamos a editar.
	 * @return La ventana para editar un instrumento de selección.
	 */
	public ChoiceInstrumentFormDialog createEditDialog(ChoiceInstrument choiceInstrument) {
		this.setTitle("Instrumento de selección");
		this.choiceInstrument = choiceInstrument;
		this.fromInstrumentToDialog();
		this.descriptionTextArea.requestFocus();
		return this;
	}
}