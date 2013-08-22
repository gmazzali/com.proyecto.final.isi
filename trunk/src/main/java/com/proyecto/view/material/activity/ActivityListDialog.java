package com.proyecto.view.material.activity;

import javax.swing.JDialog;

import com.common.util.annotations.View;

/**
 * La clase que nos permite crear una ventana de listado de actividades para poder administrarlas o seleccionarlas para colocarlas dentro de una
 * evaluación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ActivityListDialog extends JDialog {

	private static final long serialVersionUID = 2298555004862377049L;

	/**
	 * Constructor de la ventana de listado.
	 */
	public ActivityListDialog() {
		this.setResizable(false);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 700, 400);
	}
}
