package com.proyecto.view.material.assessment;

import java.awt.Font;

import javax.swing.JDialog;

/**
 * La clase que nos permite crear una ventana de selección de un tipo de evaluación que vamos a crear de acuerdo al contenido de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class AssessmentSelectTypeDialog extends JDialog {

	private static final long serialVersionUID = -6387450303106849940L;

	/**
	 * Constructor de la ventana de selección de un tipo de evaluación.
	 */
	public AssessmentSelectTypeDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar los componentes de la ventana.
	 */
	private void init() {
		this.setBounds(100, 100, 701, 459);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
	}
}