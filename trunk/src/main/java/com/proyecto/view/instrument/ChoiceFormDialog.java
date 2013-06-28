package com.proyecto.view.instrument;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChoiceFormDialog extends JDialog {

	private static final long serialVersionUID = 3339143561900518060L;

	private final JTextField textField;

	/**
	 * Create the dialog.
	 */
	public ChoiceFormDialog() {
		super();
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 10, 65, 14);
		this.getContentPane().add(descriptionLabel);

		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setBounds(10, 36, 884, 63);
		this.getContentPane().add(descriptionTextArea);

		JLabel choiceLabel = new JLabel("Opciones");
		choiceLabel.setFont(new Font("Arial", Font.BOLD, 11));
		choiceLabel.setBounds(10, 111, 52, 14);
		this.getContentPane().add(choiceLabel);

		JScrollPane choiceScrollPane = new JScrollPane();
		choiceScrollPane.setBounds(10, 137, 884, 122);
		this.getContentPane().add(choiceScrollPane);

		JList<String> choiceList = new JList<>();
		choiceScrollPane.setViewportView(choiceList);

		this.textField = new JTextField();
		this.textField.setBounds(87, 271, 807, 28);
		this.getContentPane().add(this.textField);
		this.textField.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(10, 272, 65, 26);
		this.getContentPane().add(comboBox);

		JButton button = new JButton("Aceptar");
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setBounds(684, 311, 100, 30);
		this.getContentPane().add(button);

		JButton button_1 = new JButton("Cancelar");
		button_1.setFont(new Font("Arial", Font.BOLD, 12));
		button_1.setBounds(794, 311, 100, 30);
		this.getContentPane().add(button_1);
		this.init();
	}

	private void init() {
		this.setBounds(100, 100, 906, 379);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChoiceFormDialog dialog = new ChoiceFormDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
