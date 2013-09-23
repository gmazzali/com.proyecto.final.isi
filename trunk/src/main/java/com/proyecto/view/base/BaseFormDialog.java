package com.proyecto.view.base;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
import com.common.util.model.Persistence;

/**
 * La clase que nos permite crear una ventana de formulario donde vamos a tener un proceso corriendo en segundo plano y que en caso de que el usuario
 * decida cerrar la ventana, este proceso se interrumpa.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de entidades que vamos a manipular dentro de esta ventana.
 */
public abstract class BaseFormDialog<E extends Persistence<?>> extends JDialog {

	private static final long serialVersionUID = -1915243289115936975L;

	/**
	 * El proceso que vamos a tener dentro de esta ventana.
	 */
	protected Thread thread;

	/**
	 * El constructor de una ventana donde va a contener un proceso en segundo plano.
	 */
	public BaseFormDialog() {
		super();

		this.setModal(true);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BaseFormDialog.this.stopProccess();
			}
		});
	}

	/**
	 * La función que vamos a ejecutar antes de comenzar con el proceso en segundo plano para el guardado de la entidad.
	 */
	protected abstract void afterProccess();

	/**
	 * La función que vamos a ejecutar después de terminar con el proceso en segundo plano o después que el mismo se detuvo.
	 */
	protected abstract void beforeProccess();

	/**
	 * La función que arranca el proceso en segundo plano para el guardado de la entidad.
	 */
	protected final void startProccess() {
		this.initThread();
		this.thread.start();
	}

	/**
	 * La función que para el proceso en segundo plano para el guardado de la entidad.
	 */
	protected final void stopProccess() {
		if (this.thread != null) {
			this.thread.interrupt();
			this.afterProccess();
		}
	}

	/**
	 * La función encargada de inicializar el proceso que vamos a ejecutar en segundo plano dentro de esta ventana.
	 */
	protected void initThread() {
		this.stopProccess();
		this.thread = new Thread() {
			@Override
			public void run() {
				try {
					BaseFormDialog.this.beforeProccess();
					BaseFormDialog.this.proccess();
				} catch (CheckedException e) {
					e.printStackTrace();
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(BaseFormDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
								JOptionPane.ERROR_MESSAGE);
					}
				} finally {
					BaseFormDialog.this.afterProccess();
				}
			}
		};
	}

	/**
	 * La función que define el proceso de guardado que vamos a ejecutar en segundo plano dentro de este dialogo.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un problema al momento de ejecutar la tarea.
	 */
	protected abstract void proccess() throws CheckedException;
}
