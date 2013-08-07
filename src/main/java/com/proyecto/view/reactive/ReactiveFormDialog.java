package com.proyecto.view.reactive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;
import com.proyecto.model.instrument.type.impl.InstrumentType;
import com.proyecto.model.reactive.Reactive;
import com.proyecto.service.reactive.ReactiveService;
import com.proyecto.view.Resources;
import com.proyecto.view.instrument.InstrumentListDialog;

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
	 * El reactivo que estamos editando.
	 */
	private Reactive reactive;

	/**
	 * El combo con los tipos de instrumentos que vamos a usar dentro de las evaluaciones.
	 */
	private JComboBox<InstrumentTypeInterface> assessementTypeComboBox;
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
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		this.setBounds(100, 100, 727, 387);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel selectionTypeEvaluationLabel = new JLabel("Tipo de Evaluaci\u00F3n");
		selectionTypeEvaluationLabel.setFont(new Font("Arial", Font.BOLD, 11));
		selectionTypeEvaluationLabel.setBounds(10, 11, 104, 14);
		contentPanel.add(selectionTypeEvaluationLabel);

		this.assessementTypeComboBox = new JComboBox<InstrumentTypeInterface>();
		this.assessementTypeComboBox.setBounds(10, 27, 222, 30);
		contentPanel.add(this.assessementTypeComboBox);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 68, 65, 14);
		contentPanel.add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 85, 701, 88);
		contentPanel.add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		descriptionScrollPane.setViewportView(this.descriptionTextArea);
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 11));
		this.descriptionTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.descriptionTextArea.setBackground(Color.WHITE);

		JLabel instrumentLabel = new JLabel("Instrumento");
		instrumentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		instrumentLabel.setBounds(10, 185, 69, 14);
		contentPanel.add(instrumentLabel);

		JScrollPane instrumentScrollPane = new JScrollPane();
		instrumentScrollPane.setBounds(10, 202, 654, 88);
		contentPanel.add(instrumentScrollPane);

		this.instrumentTextArea = new JTextArea();
		instrumentScrollPane.setViewportView(this.instrumentTextArea);
		this.instrumentTextArea.setEnabled(false);
		this.instrumentTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));

		this.addInstrumentbutton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.addInstrumentbutton.setBounds(676, 202, 35, 35);
		contentPanel.add(this.addInstrumentbutton);

		JButton removeInstrumentButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		removeInstrumentButton.setBounds(676, 255, 35, 35);
		contentPanel.add(removeInstrumentButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 302, 701, 2);
		contentPanel.add(separator);

		this.commitButton = new JButton("Aceptar");
		this.commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.commitButton.setBounds(501, 316, 100, 30);
		contentPanel.add(this.commitButton);

		this.rejectButton = new JButton("Cancelar");
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveFormDialog.this.dispose();
			}
		});
		this.rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.rejectButton.setBounds(611, 316, 100, 30);
		contentPanel.add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.descriptionTextArea.setEnabled(enabled);

		this.addInstrumentbutton.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La función de carga del combo box de tipo de evaluación.
	 */
	private void loadInstrumentLevelOneComboBox() {
		this.assessementTypeComboBox.removeAllItems();

		// Volvemos a cargar el combo.
		for (InstrumentTypeInterface item : InstrumentType.values()) {
			this.assessementTypeComboBox.addItem(item);
		}
		this.assessementTypeComboBox.setSelectedIndex(-1);
		this.assessementTypeComboBox.setEnabled(true);
	}

	/**
	 * La función encargada de cargar el combo de tipos de instrumentos a cargar dentro de este reactivo para poder clasificarlo como formal,
	 * semiformal o no formal.
	 * 
	 * @param instrumentClass
	 *            La clase de los instrumentos que podemos cargar.
	 */
	private void loadInstrumentClassInCombo(Class<? extends Instrument> instrumentClass) {
		if (instrumentClass != null) {
			for (InstrumentTypeInterface item : InstrumentType.values()) {
				if (item.getInstrumentClass().isAssignableFrom(instrumentClass)) {
					this.assessementTypeComboBox.setSelectedItem(item);
					this.assessementTypeComboBox.setEnabled(false);
					break;
				}
			}
		} else {
			this.assessementTypeComboBox.setEnabled(true);
		}
	}

	/**
	 * La función encargada de seleccionar el instrumento que vamos a asociar a este reactivo.
	 */
	private void selectInstrument() {
		// TODO gmazzali hacer lo de la carga del instrumento dentro del reactivo.
	}

	/**
	 * La función encargada de cargar los datos que tenemos dentro de la ventana dentro del reactivo que estamos editando.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un fallo a la hora de cargar el reactivo desde la ventana.
	 */
	private void fromDialogToReactive() throws CheckedException {
		// TODO gmazzali hacer lo de la carga del reactivo desde la ventana.
	}

	/**
	 * La función encargada de cargar la ventana de edición con los datos que tenemos dentro del reactivo.
	 */
	private void fromReactiveToDialog() {
		// TODO gmazzali hacer lo de la carga de la ventana desde el reactivo.
	}

	/**
	 * La función encargada de vaciar el contenido de la ventana.
	 */
	private void emptyFields() {
		// TODO gmazzali hacer lo del vaciado de los campos de la ventana.
	}

	/**
	 * La función encargada de configurar la ventana para dar de alta un nuevo reactivo.
	 * 
	 * @param instrumenClass
	 *            La clase de los instrumentos que vamos a poder asignarle al reactivo.
	 * @return La ventana con el formulario configurado para dar de alta un reactivo.
	 */
	public ReactiveFormDialog createNewDialog(Class<? extends Instrument> instrumentClass) {
		this.setTitle(HolderMessage.getMessage("reactive.form.dialog.title.new"));

		this.loadInstrumentLevelOneComboBox();
		this.loadInstrumentClassInCombo(instrumentClass);

		this.reactive = new Reactive();
		this.emptyFields();

		return this;
	}

	/**
	 * La función encargada de configurar la ventana para la edición de un reactivo.
	 * 
	 * @param reactive
	 *            El reactivo que vamos a editar.
	 * @return La ventana del formulario ya configurado para la edición del reactivo.
	 */
	public ReactiveFormDialog createEditDialog(Reactive reactive) {
		this.setTitle(HolderMessage.getMessage("reactive.form.dialog.title.edit"));

		this.loadInstrumentLevelOneComboBox();

		this.reactive = reactive;
		this.fromReactiveToDialog();

		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			ReactiveFormDialog dialog = HolderApplicationContext.getContext().getBean(ReactiveFormDialog.class)
					.createNewDialog(ChoiceInstrument.class);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}