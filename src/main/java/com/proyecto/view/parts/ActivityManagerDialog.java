package com.proyecto.view.parts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JList;
import com.proyecto.model.rule.Rule;
import javax.swing.ListSelectionModel;

public class ActivityManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActivityManagerDialog dialog = new ActivityManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActivityManagerDialog() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Menu Actividades");
		setBounds(100, 100, 461, 328);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JList<Rule> list = new JList<Rule>();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setBounds(6, 17, 266, 208);
		contentPanel.add(list);
		
		JButton newActivityButton = new JButton("Crear Actividad");
		newActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		newActivityButton.setBounds(285, 15, 154, 23);
		contentPanel.add(newActivityButton);
		
		JButton deleteActivityButton = new JButton("Eliminar Actividad");
		deleteActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteActivityButton.setBounds(285, 49, 154, 23);
		contentPanel.add(deleteActivityButton);
		
		JButton updateActivityButton = new JButton("Modificar Actividad");
		updateActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateActivityButton.setBounds(285, 83, 154, 23);
		contentPanel.add(updateActivityButton);
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
