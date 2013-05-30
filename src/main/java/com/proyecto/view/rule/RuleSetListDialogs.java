package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RuleSetListDialogs extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RuleSetListDialogs dialog = new RuleSetListDialogs();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RuleSetListDialogs() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Lista de Conjutos");
		setBounds(100, 100, 486, 405);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton backButton = new JButton("Volver");
			backButton.setFont(new Font("Arial", Font.BOLD, 12));
			backButton.setBounds(371, 333, 89, 23);
			contentPanel.add(backButton);
		}
		{
			JList list = new JList();
			list.setBounds(24, 32, 252, 153);
			contentPanel.add(list);
		}
		
		JLabel lblConjuntos = new JLabel("Conjuntos");
		lblConjuntos.setFont(new Font("Arial", Font.BOLD, 11));
		lblConjuntos.setBounds(24, 11, 71, 14);
		contentPanel.add(lblConjuntos);
		
		JLabel lblReglas = new JLabel("Reglas");
		lblReglas.setFont(new Font("Arial", Font.BOLD, 11));
		lblReglas.setBounds(24, 195, 52, 14);
		contentPanel.add(lblReglas);
		
		JList list = new JList();
		list.setBounds(24, 220, 252, 101);
		contentPanel.add(list);
		
		JButton btnGestinarReglas = new JButton("Gestionar Reglas");
		btnGestinarReglas.setFont(new Font("Arial", Font.BOLD, 12));
		btnGestinarReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGestinarReglas.setBounds(24, 333, 145, 23);
		contentPanel.add(btnGestinarReglas);
	}
}
