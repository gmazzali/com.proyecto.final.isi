package com.proyecto.view.base;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;

/**
 * La clase que nos permite crear una ventana donde vamos a tener un proceso corriendo en segundo plano y que en caso de que el usuario decida cerrar
 * la ventana, este proceso se interrumpa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class SingleProccessDialog extends JDialog {

	private static final long serialVersionUID = -1915243289115936975L;

	/**
	 * El proceso que vamos a tener dentro de esta ventana.
	 */
	protected Thread thread;

	/**
	 * El constructor de una ventana donde va a contener un proceso en segundo plano.
	 */
	public SingleProccessDialog() {
		super();
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				// Si la ventana se cierra, interrumpimos el proceso.
				if (thread != null) {
					thread.interrupt();
				}
			}
		});
	}

	/**
	 * La función encargada de inicializar el proceso que vamos a ejecutar en segundo plano dentro de esta ventana.
	 */
	protected abstract void initThread();
}
