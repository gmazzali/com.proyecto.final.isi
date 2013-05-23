package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RuleFormDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField ruleTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleFormDialog dialog = new RuleFormDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RuleFormDialog() {
		setTitle("REGLAS\r\n");
		setBounds(100, 100, 660, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
			descriptionLabel.setBounds(10, 11, 65, 14);
			descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
			contentPanel.add(descriptionLabel);
		}
		
		JTextPane descriptionTextPane = new JTextPane();
		descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		descriptionTextPane.setBounds(10, 36, 624, 58);
		contentPanel.add(descriptionTextPane);
		
		JLabel ruleLabel = new JLabel("Regla");
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 116, 46, 14);
		contentPanel.add(ruleLabel);
		
		ruleTextField = new JTextField();
		ruleTextField.setBounds(10, 141, 624, 20);
		contentPanel.add(ruleTextField);
		ruleTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showConfirmDialog(null, "Accion que va a cargar el grid padre");
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
