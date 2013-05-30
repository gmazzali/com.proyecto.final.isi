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

	private final JPanel contentPanel = new JPanel();

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
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 11, 184, 194);
		contentPanel.add(list);
		
		JButton newRuleButton = new JButton("Nueva Regla");
		newRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		newRuleButton.setBounds(256, 19, 129, 23);
		contentPanel.add(newRuleButton);
		
		JButton deleteRuleButton = new JButton("Eliminar Regla");
		deleteRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRuleButton.setBounds(256, 53, 129, 23);
		contentPanel.add(deleteRuleButton);
		
		JButton updateRuleButton = new JButton("Editar Regla");
		updateRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateRuleButton.setBounds(256, 87, 129, 23);
		contentPanel.add(updateRuleButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
