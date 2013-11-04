package com.proyecto.view.rule;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class PropertyNameSelectDialog extends JDialog {

	private static final long serialVersionUID = -2312760597467093929L;
	private JList<String> classNameJList;
	private JList<String> propertyNameJList;

	/**
	 * Create the dialog.
	 */
	public PropertyNameSelectDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el contenido de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 569, 300);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		JScrollPane classNameScrollPane = new JScrollPane();
		classNameScrollPane.setBounds(12, 11, 233, 250);
		classNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(classNameScrollPane);

		this.classNameJList = new JList<String>();
		this.classNameJList.setFont(classNameScrollPane.getFont());
		classNameScrollPane.setViewportView(this.classNameJList);

		JScrollPane propertyNameScrollPane = new JScrollPane();
		propertyNameScrollPane.setBounds(257, 11, 294, 250);
		propertyNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(propertyNameScrollPane);

		this.propertyNameJList = new JList<String>();
		this.propertyNameJList.setFont(propertyNameScrollPane.getFont());
		propertyNameScrollPane.setViewportView(this.propertyNameJList);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PropertyNameSelectDialog dialog = new PropertyNameSelectDialog();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}