package com.proyecto.view.parts;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ReactiveManagerDialog extends JDialog {

	private static final long serialVersionUID = -5794558641133190585L;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReactiveManagerDialog dialog = new ReactiveManagerDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReactiveManagerDialog() {
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setTitle("Menu Reactivos");
		this.setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(null);
	}

}
