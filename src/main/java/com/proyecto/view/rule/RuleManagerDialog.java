package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class RuleManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable rulesTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleManagerDialog dialog = new RuleManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RuleManagerDialog() {
		setTitle("Administracion de Conjuntos de Reglas\r\n");
		setBounds(100, 100, 542, 442);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 40, 230, 29);
		contentPanel.add(comboBox);
		
		JLabel setRuleLabel = new JLabel("Conjuntos");
		setRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		setRuleLabel.setBounds(10, 23, 76, 14);
		contentPanel.add(setRuleLabel);
		
		JButton newRuleButton = new JButton("Nuevo");
		newRuleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EditRuleFormDialog editRuleFormDialog = new EditRuleFormDialog();
				editRuleFormDialog.setVisible(true);
			}
		});
		newRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		newRuleButton.setBounds(10, 98, 89, 23);
		contentPanel.add(newRuleButton);
		
		JButton deleteSetRuleButton = new JButton("Eliminar");
		deleteSetRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteSetRuleButton.setBounds(114, 98, 89, 23);
		contentPanel.add(deleteSetRuleButton);
		
		JButton changeSetRuleButton = new JButton("Modificar");
		changeSetRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		changeSetRuleButton.setBounds(214, 98, 89, 23);
		contentPanel.add(changeSetRuleButton);
		
		JButton applySetRuleButton = new JButton("Aplicar");
		applySetRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		applySetRuleButton.setBounds(317, 98, 89, 23);
		contentPanel.add(applySetRuleButton);
		
		rulesTable = new JTable();
		rulesTable.setBounds(10, 181, 320, 179);
		contentPanel.add(rulesTable);
		
		JPanel panel = new JPanel();
		panel.setBounds(366, 181, 150, 112);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnNuevoRegla = new JButton("Nueva Regla");
		btnNuevoRegla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog ruleFormDialog = new RuleFormDialog();
				ruleFormDialog.setVisible(true);
			}
		});
		btnNuevoRegla.setBounds(11, 5, 129, 23);
		panel.add(btnNuevoRegla);
		btnNuevoRegla.setFont(new Font("Arial", Font.BOLD, 11));
		
		JButton deleteRuleButton = new JButton("Eliminar Regla");
		deleteRuleButton.setBounds(11, 39, 129, 23);
		panel.add(deleteRuleButton);
		deleteRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		
		JButton modifyRuleButton = new JButton("Editar Regla");
		modifyRuleButton.setBounds(11, 73, 129, 23);
		panel.add(modifyRuleButton);
		modifyRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 143, 526, 2);
		contentPanel.add(separator);
		
		JLabel setRulesList = new JLabel("Reglas");
		setRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		setRulesList.setBounds(10, 156, 76, 14);
		contentPanel.add(setRulesList);
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
