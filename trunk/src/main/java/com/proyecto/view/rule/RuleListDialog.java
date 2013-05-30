package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JList;

public class RuleListDialog extends JDialog {

	private final JPanel backButton = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleListDialog dialog = new RuleListDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RuleListDialog() {
		setTitle("Listado de Reglas");
		setFont(new Font("Arial", Font.PLAIN, 12));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		backButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(backButton, BorderLayout.CENTER);
		backButton.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 11, 184, 194);
		backButton.add(list);
		
		JButton newRuleButton = new JButton("Nueva Regla");
		newRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		newRuleButton.setBounds(256, 19, 129, 23);
		backButton.add(newRuleButton);
		
		JButton deleteRuleButton = new JButton("Eliminar Regla");
		deleteRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRuleButton.setBounds(256, 53, 129, 23);
		backButton.add(deleteRuleButton);
		
		JButton updateRuleButton = new JButton("Editar Regla");
		updateRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateRuleButton.setBounds(256, 87, 129, 23);
		backButton.add(updateRuleButton);
		
		JButton button = new JButton("Volver");
		button.setFont(new Font("Arial", Font.BOLD, 12));
		button.setBounds(339, 233, 89, 23);
		backButton.add(button);
	}
}
