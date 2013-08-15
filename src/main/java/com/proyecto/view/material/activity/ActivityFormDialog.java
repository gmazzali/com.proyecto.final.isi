package com.proyecto.view.material.activity;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 * La clase que nos permite crear una ventana de edición de actividades que vamos a utilizar dentro del sistema para las evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ActivityFormDialog extends JDialog {

	private static final long serialVersionUID = -1516446490165543963L;

	/**
	 * Constructor de la ventana de edición de actividades.
	 */
	public ActivityFormDialog() {
		super();
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 11, 96, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 2, 2);
		this.getContentPane().add(scrollPane);
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana.
	 */
	private void init() {
		this.setBounds(100, 100, 701, 493);
	}
}