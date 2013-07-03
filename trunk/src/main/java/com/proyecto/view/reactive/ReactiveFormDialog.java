package com.proyecto.view.reactive;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import com.proyecto.model.agent.Subject;

public class ReactiveFormDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

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

	/**
	 * Create the dialog.
	 */
	public ReactiveFormDialog() {
		setBounds(100, 100, 727, 396);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Descripci\u00F3n");
		label.setFont(new Font("Arial", Font.BOLD, 11));
		label.setBounds(10, 98, 162, 14);
		contentPanel.add(label);
		
		JLabel lblInstrumento = new JLabel("Instrumento");
		lblInstrumento.setFont(new Font("Arial", Font.BOLD, 11));
		lblInstrumento.setBounds(10, 203, 162, 14);
		contentPanel.add(lblInstrumento);
		
		JTextPane DescriptiontextPane = new JTextPane();
		DescriptiontextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		DescriptiontextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		DescriptiontextPane.setBackground(Color.WHITE);
		DescriptiontextPane.setBounds(10, 124, 668, 60);
		contentPanel.add(DescriptiontextPane);
		
		JTextPane descriptionInstrumentextPane = new JTextPane();
		descriptionInstrumentextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descriptionInstrumentextPane.setBounds(10, 228, 535, 65);
		contentPanel.add(descriptionInstrumentextPane);
		
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
}
