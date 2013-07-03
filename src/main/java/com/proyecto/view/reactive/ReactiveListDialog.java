package com.proyecto.view.reactive;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import com.proyecto.model.rule.Rule;
import java.awt.Font;
import javax.swing.ListSelectionModel;

public class ReactiveListDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReactiveListDialog dialog = new ReactiveListDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReactiveListDialog() {
		setBounds(100, 100, 617, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JList<Rule> reactivelist = new JList<Rule>();
		reactivelist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		reactivelist.setFont(new Font("Arial", Font.PLAIN, 12));
		reactivelist.setBounds(10, 14, 451, 334);
		contentPanel.add(reactivelist);
		
		JButton createbutton = new JButton("Crear");
		createbutton.setFont(new Font("Arial", Font.BOLD, 12));
		createbutton.setBounds(476, 11, 100, 30);
		contentPanel.add(createbutton);
		
		JButton editbutton = new JButton("Editar");
		editbutton.setFont(new Font("Arial", Font.BOLD, 12));
		editbutton.setBounds(476, 53, 100, 30);
		contentPanel.add(editbutton);
		
		JButton deletebutton = new JButton("Eliminar");
		deletebutton.setFont(new Font("Arial", Font.BOLD, 12));
		deletebutton.setBounds(476, 95, 100, 30);
		contentPanel.add(deletebutton);
		
		JButton returnbutton = new JButton("Volver");
		returnbutton.setFont(new Font("Arial", Font.BOLD, 12));
		returnbutton.setBounds(476, 321, 100, 30);
		contentPanel.add(returnbutton);
		
		JButton Selectionbutton = new JButton("Seleccionar");
		Selectionbutton.setFont(new Font("Arial", Font.BOLD, 12));
		Selectionbutton.setBounds(476, 260, 100, 30);
		contentPanel.add(Selectionbutton);
	}
}
