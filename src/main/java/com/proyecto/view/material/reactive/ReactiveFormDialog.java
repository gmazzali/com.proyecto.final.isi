package com.proyecto.view.material.reactive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.converter.ReactiveTypeToInstrumentTypeConverter;
import com.proyecto.model.material.activity.type.impl.ActivityTypeImpl;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.type.InstrumentType;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.material.reactive.type.ReactiveType;
import com.proyecto.model.material.reactive.type.impl.ReactiveTypeImpl;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.reactive.ReactiveService;
import com.proyecto.view.Resources;
import com.proyecto.view.material.instrument.InstrumentListDialog;

/**
 * La clase que representa el formulario donde vamos a poder editar un reactivo dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ReactiveFormDialog extends JDialog {

	private static final long serialVersionUID = -3007425576330803557L;

	/**
	 * El acceso dentro del sistema.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * La ventana de selección de reactivo.
	 */
	@Autowired
	private InstrumentListDialog instrumentListDialog;

	/**
	 * El servicio para el reactivo.
	 */
	@Autowired
	private ReactiveService reactiveService;

	/**
	 * El reactivo que estamos editando, su instrumento asociado.
	 */
	private Reactive reactive;
	private Instrument instrument;

	/**
	 * Los tipos validos de reactivos que vamos a poder editar dentro de esta ventana.
	 */
	private List<ReactiveType> reactiveTypes;

	/**
	 * El combo con los tipos de instrumentos que vamos a usar dentro de las evaluaciones.
	 */
	private JComboBox<ReactiveType> assessementTypeComboBox;
	/**
	 * Las areas donde vamos a desplegar las descripciones.
	 */
	private JTextArea descriptionTextArea;
	private JTextArea instrumentTextArea;
	/**
	 * Los botones de acción.
	 */
	private JButton commitButton;
	private JButton rejectButton;
	private JButton addInstrumentbutton;
	private JButton removeInstrumentButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de una ventana de edición de reactivos.
	 */
	public ReactiveFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el contenido de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		this.setBounds(100, 100, 723, 451);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel selectionTypeEvaluationLabel = new JLabel(HolderMessage.getMessage("reactive.form.label.type"));
		selectionTypeEvaluationLabel.setFont(new Font("Arial", Font.BOLD, 11));
		selectionTypeEvaluationLabel.setBounds(10, 10, 701, 16);
		contentPanel.add(selectionTypeEvaluationLabel);

		this.assessementTypeComboBox = new JComboBox<ReactiveType>();
		this.assessementTypeComboBox.setFont(this.getContentPane().getFont());
		this.assessementTypeComboBox.setBounds(6, 27, 705, 30);
		contentPanel.add(this.assessementTypeComboBox);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("reactive.form.label.description"));
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 69, 701, 16);
		contentPanel.add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(6, 86, 705, 120);
		contentPanel.add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setBorder(new LineBorder(Color.GRAY));
		this.descriptionTextArea.setBackground(Color.WHITE);
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel instrumentLabel = new JLabel(HolderMessage.getMessage("reactive.form.label.instrument"));
		instrumentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		instrumentLabel.setBounds(10, 218, 654, 16);
		contentPanel.add(instrumentLabel);

		JScrollPane instrumentScrollPane = new JScrollPane();
		instrumentScrollPane.setBounds(6, 235, 664, 120);
		contentPanel.add(instrumentScrollPane);

		this.instrumentTextArea = new JTextArea();
		this.instrumentTextArea.setWrapStyleWord(true);
		this.instrumentTextArea.setLineWrap(true);
		this.instrumentTextArea.setEnabled(false);
		this.instrumentTextArea.setFont(this.getContentPane().getFont());
		this.instrumentTextArea.setBorder(new LineBorder(Color.GRAY));
		instrumentScrollPane.setViewportView(this.instrumentTextArea);

		this.addInstrumentbutton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.addInstrumentbutton.setBounds(676, 235, 35, 35);
		this.addInstrumentbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveFormDialog.this.selectInstrument();
			}
		});
		contentPanel.add(this.addInstrumentbutton);

		this.removeInstrumentButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeInstrumentButton.setBounds(676, 282, 35, 35);
		this.removeInstrumentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveFormDialog.this.removeInstrument();
			}
		});
		contentPanel.add(this.removeInstrumentButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 367, 701, 2);
		contentPanel.add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(582, 381, 35, 35);
		contentPanel.add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(629, 381, 35, 35);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveFormDialog.this.saveReactive();
			}
		});
		contentPanel.add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(676, 381, 35, 35);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveFormDialog.this.dispose();
			}
		});
		contentPanel.add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.descriptionTextArea.setEnabled(enabled);

		this.addInstrumentbutton.setEnabled(enabled);
		this.removeInstrumentButton.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de administrar el combo de tipos de instrumentos de acuerdo al tipo de reactivo que estamos editando.
	 */
	private void reloadReactiveTypeInComboBox() {
		// Quitamos todos los elementos del combo.
		this.assessementTypeComboBox.removeAllItems();

		// Cargamos solo los que el tipo de actividad requiere.
		if (this.reactiveTypes != null) {
			for (ReactiveType type : this.reactiveTypes) {
				this.assessementTypeComboBox.addItem(type);
			}
		}
		// Cargamos todos los tipos.
		else {
			for (ReactiveType type : ReactiveTypeImpl.values()) {
				this.assessementTypeComboBox.addItem(type);
			}
		}
		this.assessementTypeComboBox.setSelectedIndex(-1);
	}

	/**
	 * La función encargada de cargar el tipo de reactivo en el combo de selección de tipo de acuerdo el instrumento que tenemos dentro del reactivo.
	 */
	private void setReactiveTypeComboBoxFromInstrument() {
		ReactiveType reactiveType = null;

		// Solo si el instrumento no es nulo, procesamos.
		if (this.instrument != null) {

			// Recorremos el combo para ver que tipo cargar.
			for (int i = 0; i < this.assessementTypeComboBox.getItemCount(); i++) {

				ReactiveType temp = this.assessementTypeComboBox.getItemAt(i);

				for (InstrumentType instrumentType : temp.getInstrumentsTypesAllowed()) {

					// Verificamos el tipo de reactivo.
					if (instrumentType.getInstrumentClass().isAssignableFrom(this.instrument.getClass())) {
						reactiveType = temp;
						break;
					}
				}
			}
		}

		// Si tenemos un tipo de reactivo.
		if (reactiveType != null) {
			this.assessementTypeComboBox.setSelectedItem(reactiveType);
			this.assessementTypeComboBox.setEnabled(false);
		} else {
			this.assessementTypeComboBox.setSelectedIndex(-1);
			this.assessementTypeComboBox.setEnabled(true);
		}
	}

	/**
	 * La función encargada de seleccionar el instrumento que vamos a asociar a este reactivo.
	 */
	private void selectInstrument() {
		// Recuperamos la clase de instrumento que queremos cargar dentro de este reactivo.
		ReactiveType reactiveType = (ReactiveType) this.assessementTypeComboBox.getSelectedItem();

		// Cargamos un listado de instrumentos permitidos si tenemos algo seleccionado o no en el combo de tipo.
		List<InstrumentType> instrumentTypes = null;
		if (reactiveType != null) {
			instrumentTypes = reactiveType.getInstrumentsTypesAllowed();
		} else {
			instrumentTypes = ReactiveTypeToInstrumentTypeConverter.converter(this.reactiveTypes);
		}

		// Abrimos la ventana de selección de instrumento con la clase que queremos.
		InstrumentListDialog dialog = this.instrumentListDialog.createSelectDialog(instrumentTypes);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// Si seleccionamos un instrumento, lo cargamos dentro de esta ventana.
		if (dialog.getSelectedInstrument() != null) {
			this.instrument = dialog.getSelectedInstrument();
			this.instrumentTextArea.setText(this.instrument.getDescription());

			this.setReactiveTypeComboBoxFromInstrument();
		}
	}

	/**
	 * LA función que permite quitar un instrumento de el reactivo que estamos editando.
	 */
	private void removeInstrument() {
		// Quitamos el instrumento.
		this.instrument = null;
		this.instrumentTextArea.setText("");
		this.setReactiveTypeComboBoxFromInstrument();
	}

	/**
	 * La función encargada de guardar el reactivo dentro de la base de datos.
	 */
	private void saveReactive() {
		new Thread() {
			@Override
			public void run() {
				try {
					ReactiveFormDialog.this.beforeProccessReactive();
					ReactiveFormDialog.this.fromDialogToReactive();
					ReactiveFormDialog.this.reactiveService.saveOrUpdate(ReactiveFormDialog.this.reactive);
					ReactiveFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(ReactiveFormDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
							JOptionPane.ERROR_MESSAGE);
				} finally {
					ReactiveFormDialog.this.afterProccessReactive();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar el reactivo.
	 */
	private void beforeProccessReactive() {
		this.setEnabled(false);
		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de procesar el reactivo.
	 */
	private void afterProccessReactive() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de cargar los datos que tenemos dentro de la ventana dentro del reactivo que estamos editando.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un fallo a la hora de cargar el reactivo desde la ventana.
	 */
	private void fromDialogToReactive() throws CheckedException {
		// Cargamos la asignatura del reactivo.
		this.reactive.setSubject(this.accessControl.getSubjectSelected());

		// Verificamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("reactive.form.error.description");
		} else {
			this.reactive.setDescription(this.descriptionTextArea.getText().trim());
		}

		// Cargamos el instrumento.
		if (this.instrument == null) {
			throw new CheckedException("reactive.form.error.instrument");
		} else {
			this.reactive.setInstrument(this.instrument);
		}
	}

	/**
	 * La función encargada de cargar la ventana de edición con los datos que tenemos dentro del reactivo.
	 */
	private void fromReactiveToDialog() {
		// Cargamos la descripción.
		this.descriptionTextArea.setText(this.reactive.getDescription());

		// Si tenemos instrumento, lo cargamos y seteamos el tipo de evaluación.
		if (this.reactive.getInstrument() != null) {
			this.instrument = this.reactive.getInstrument();

			// Cargamos el instrumento dentro de la ventana.
			this.instrumentTextArea.setText(this.instrument.getDescription());

			// Seteamos el tipo de combo por el instrumento dado.
			this.setReactiveTypeComboBoxFromInstrument();
		}
	}

	/**
	 * La función encargada de vaciar el contenido de la ventana.
	 */
	private void emptyFields() {
		this.descriptionTextArea.setText("");
		this.instrumentTextArea.setText("");
	}

	/**
	 * La función encargada de configurar la ventana para dar de alta un nuevo reactivo.
	 * 
	 * @param reactiveTypes
	 *            El listado de los tipos de reactivos que podemos seleccionar dentro de la ventana de edición.
	 * @return La ventana con el formulario configurado para dar de alta un reactivo.
	 */
	public ReactiveFormDialog createNewDialog(List<ReactiveType> reactiveTypes) {
		this.setTitle(HolderMessage.getMessage("reactive.form.title.new"));

		this.reactiveTypes = reactiveTypes;
		this.reactive = new Reactive();
		this.instrument = null;

		this.emptyFields();
		this.reloadReactiveTypeInComboBox();

		return this;
	}

	/**
	 * La función encargada de configurar la ventana para la edición de un reactivo.
	 * 
	 * @param reactive
	 *            El reactivo que vamos a editar.
	 * @param reactiveTypes
	 *            El listado de los tipos de reactivos que podemos seleccionar dentro de la ventana de edición.
	 * @return La ventana del formulario ya configurado para la edición del reactivo.
	 */
	public ReactiveFormDialog createEditDialog(Reactive reactive, List<ReactiveType> reactiveTypes) {
		this.setTitle(HolderMessage.getMessage("reactive.form.title.edit"));

		this.reactiveTypes = reactiveTypes;
		this.reactive = reactive;
		this.instrument = null;

		this.emptyFields();
		this.fromReactiveToDialog();
		this.reloadReactiveTypeInComboBox();

		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };

			HolderApplicationContext.initApplicationContext(files);

			Reactive reactive = HolderApplicationContext.getContext().getBean(ReactiveService.class).findById(8);

			// ReactiveFormDialog dialog = HolderApplicationContext.getContext().getBean(ReactiveFormDialog.class)
			// .createNewDialog(ActivityTypeImpl.FORMAL);
			ReactiveFormDialog dialog = HolderApplicationContext.getContext().getBean(ReactiveFormDialog.class)
					.createEditDialog(reactive, Arrays.asList(ActivityTypeImpl.FORMAL.getReactiveTypesAllowed()));
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}