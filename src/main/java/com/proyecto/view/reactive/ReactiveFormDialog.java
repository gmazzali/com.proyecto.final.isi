package com.proyecto.view.reactive;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.proyecto.model.agent.Subject;
import com.proyecto.model.reactive.Reactive;
import com.proyecto.service.reactive.ReactiveService;
import com.proyecto.view.instrument.InstrumentListDialog;

/**
 * La clase que representa el formulario donde vamos a poder editar un reactivo dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ReactiveFormDialog extends JDialog {

	private static final long serialVersionUID = 1L;

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
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		setBounds(100, 100, 727, 396);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 98, 162, 14);
		contentPanel.add(descriptionLabel);

		JTextPane DescriptiontextPane = new JTextPane();
		DescriptiontextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		DescriptiontextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		DescriptiontextPane.setBackground(Color.WHITE);
		DescriptiontextPane.setBounds(10, 124, 668, 60);
		contentPanel.add(DescriptiontextPane);

		JLabel instrumentLabel = new JLabel("Instrumento");
		instrumentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		instrumentLabel.setBounds(10, 203, 162, 14);
		contentPanel.add(instrumentLabel);

		JTextPane instrumentTextPane = new JTextPane();
		instrumentTextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		instrumentTextPane.setBounds(10, 228, 535, 65);
		contentPanel.add(instrumentTextPane);

		JLabel selectionLabel = new JLabel("Tipo de Evaluaci\u00F3n");
		selectionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		selectionLabel.setBounds(10, 24, 131, 14);
		contentPanel.add(selectionLabel);

		JComboBox<Subject> assessementTypcomboBox = new JComboBox<Subject>();
		assessementTypcomboBox.setBounds(10, 49, 367, 29);
		contentPanel.add(assessementTypcomboBox);

		JButton selectionbutton = new JButton("Seleccionar");
		selectionbutton.setFont(new Font("Arial", Font.BOLD, 12));
		selectionbutton.setBounds(578, 242, 100, 30);
		contentPanel.add(selectionbutton);
	}

	/**
	 * La función encargada de seleccionar el instrumento que vamos a asociar a este reactivo.
	 */
	private void selectInstrument() {
		// TODO gmazzali hacer lo de la carga del instrumento dentro del reactivo.
	}

	/**
	 * La función encargada de cargar los datos que tenemos dentro de la ventana dentro del reactivo que estamos
	 * editando.
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
	 * La función encargada de configurar la ventana para la edición de un reactivo.
	 * 
	 * @param reactive
	 *            El reactivo que vamos a editar.
	 * @return La ventana del formulario ya configurado para la edición del reactivo.
	 */
	public ReactiveFormDialog createEditDialog(Reactive reactive) {
		// TODO gmazzali hacer lo de la configuración de la ventana de reactivo para un edición.
	}

	/**
	 * La función encargada de configurar la ventana para dar de alta un nuevo reactivo.
	 * 
	 * @return La ventana con el formulario configurado para dar de alta un reactivo.
	 */
	public ReactiveFormDialog createNewDialog() {
		// TODO gmazzali hacer lo de la configuración de la ventana de reactivo para un alta.
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReactiveFormDialog dialog = new ReactiveFormDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}