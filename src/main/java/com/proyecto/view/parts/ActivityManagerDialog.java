package com.proyecto.view.parts;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.proyecto.model.rule.Rule;

public class ActivityManagerDialog extends JDialog {

	private static final long serialVersionUID = 2306563402822955601L;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ActivityManagerDialog dialog = new ActivityManagerDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ActivityManagerDialog() {
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setTitle("Menu Actividades");
		this.setBounds(100, 100, 461, 328);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(null);

		JList<Rule> list = new JList<Rule>();
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setBounds(6, 17, 266, 208);
		this.contentPanel.add(list);

		JButton newActivityButton = new JButton("Crear Actividad");
		newActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		newActivityButton.setBounds(285, 15, 154, 23);
		this.contentPanel.add(newActivityButton);

		JButton deleteActivityButton = new JButton("Eliminar Actividad");
		deleteActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteActivityButton.setBounds(285, 49, 154, 23);
		this.contentPanel.add(deleteActivityButton);

		JButton updateActivityButton = new JButton("Modificar Actividad");
		updateActivityButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateActivityButton.setBounds(285, 83, 154, 23);
		this.contentPanel.add(updateActivityButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				this.getRootPane().setDefaultButton(okButton);
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
