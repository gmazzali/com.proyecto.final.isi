package com.proyecto.view.rule;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class ClassNameSelectDialog extends JDialog {

	private static final long serialVersionUID = -3600502749588025504L;

	private JList<String> classNameJList;

	/**
	 * Create the dialog.
	 */
	public ClassNameSelectDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el contenido de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setBounds(100, 100, 250, 312);
		this.getContentPane().setLayout(null);

		JScrollPane classNameScrollPane = new JScrollPane();
		classNameScrollPane.setBounds(12, 11, 220, 262);
		classNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(classNameScrollPane);

		this.classNameJList = new JList<String>();
		this.classNameJList.setFont(classNameScrollPane.getFont());
		classNameScrollPane.setViewportView(this.classNameJList);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ClassNameSelectDialog dialog = new ClassNameSelectDialog();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
