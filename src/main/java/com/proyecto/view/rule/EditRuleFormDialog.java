package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JList;
import java.awt.Font;

public class EditRuleFormDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditRuleFormDialog dialog = new EditRuleFormDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditRuleFormDialog() {
		setTitle("Grupos de Reglas");
		setBounds(100, 100, 588, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel groupDescriptionLabel = new JLabel("Descripci\u00F3n del Grupo");
			groupDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
			groupDescriptionLabel.setBounds(10, 11, 133, 14);
			contentPanel.add(groupDescriptionLabel);
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setBounds(10, 36, 546, 58);
			contentPanel.add(textPane);
		}
		
		JList activeRulelist = new JList();
		activeRulelist.setFont(new Font("Arial", Font.BOLD, 11));
		activeRulelist.setBounds(10, 149, 212, 197);
		contentPanel.add(activeRulelist);
		{
			JList ruleOfflist = new JList();
			ruleOfflist.setFont(new Font("Arial", Font.BOLD, 11));
			ruleOfflist.setBounds(344, 149, 212, 197);
			contentPanel.add(ruleOfflist);
		}
		{
			JLabel activeRuleLabel = new JLabel("Reglas Activas");
			activeRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
			activeRuleLabel.setBounds(10, 125, 92, 14);
			contentPanel.add(activeRuleLabel);
		}
		{
			JLabel ruleOffLabel = new JLabel("Reglas Desactivadas");
			ruleOffLabel.setFont(new Font("Arial", Font.BOLD, 11));
			ruleOffLabel.setBounds(344, 124, 125, 14);
			contentPanel.add(ruleOffLabel);
		}
		
		JButton addRuleButton = new JButton("Agregar >>");
		addRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		addRuleButton.setBounds(232, 163, 104, 38);
		contentPanel.add(addRuleButton);
		
		JButton deleteRulebutton = new JButton("Eliminar <<");
		deleteRulebutton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRulebutton.setBounds(232, 238, 104, 38);
		contentPanel.add(deleteRulebutton);
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
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
