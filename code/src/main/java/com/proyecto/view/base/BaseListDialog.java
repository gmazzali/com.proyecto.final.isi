package com.proyecto.view.base;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
import com.common.util.model.Persistence;

/**
 * La clase que nos permite definir una ventana base para las ventanas de listado de entidades.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 * 
 * @param <E>
 *            La clase de entidades que vamos a manipular dentro de esta ventana.
 */
public abstract class BaseListDialog<E extends Persistence<?>> extends JDialog {

	private static final long serialVersionUID = -4465168276147748677L;

	/**
	 * El proceso de carga del listado.
	 */
	private Thread fillListThread;
	/**
	 * El proceso de borrado de una entidad.
	 */
	private Thread deleteThread;

	/**
	 * El constructor de la ventana de base para los listados.
	 */
	public BaseListDialog() {
		super();

		this.setModal(true);
		this.setResizable(false);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				BaseListDialog.this.stopFillListProccess();
				BaseListDialog.this.stopDeleteProccess();
			}
		});
	}

	/**
	 * La función que vamos a ejecutar antes de comenzar con el proceso en segundo plano.
	 */
	protected abstract void afterProccess();

	/**
	 * La función que vamos a ejecutar despuós de terminar con el proceso en segundo plano o después que el mismo se detuvo.
	 */
	protected abstract void beforeProccess();

	/**
	 * La función que arranca el proceso de llenado de la lista en segundo plano.
	 */
	protected final void startFillListProccess() {
		this.initFillListThread();
		this.fillListThread.start();
	}

	/**
	 * La función que para el proceso de llenado de la lista en segundo plano.
	 */
	protected final void stopFillListProccess() {
		if (this.fillListThread != null) {
			this.fillListThread.interrupt();
			this.afterProccess();
		}
	}

	/**
	 * La función encargada de inicializar el proceso de llenado de entidades dentro de la lista de la ventana.
	 */
	protected void initFillListThread() {
		this.stopFillListProccess();

		// Creamos el proceso de llenado.
		this.fillListThread = new Thread() {
			@Override
			public void run() {
				try {
					BaseListDialog.this.beforeProccess();
					BaseListDialog.this.fillListProccess();
				} catch (CheckedException e) {
					e.printStackTrace();
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(BaseListDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
								JOptionPane.ERROR_MESSAGE);
					}
				} finally {
					BaseListDialog.this.afterProccess();
				}
			}
		};
	}

	/**
	 * La función que define el proceso de carga de la lista que vamos a ejecutar en segundo plano dentro de este dialogo.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un problema al momento de ejecutar la tarea.
	 */
	protected abstract void fillListProccess() throws CheckedException;

	/**
	 * La función que arranca el proceso de borrado de una entidad en segundo plano.
	 * 
	 * @param entity
	 *            La entidad que vamos a borrar.
	 */
	protected final void startDeleteProccess(E entity) {
		this.initDeleteThread(entity);
		this.deleteThread.start();
	}

	/**
	 * La función que para el proceso de borrado de la entidad en segundo plano.
	 */
	protected final void stopDeleteProccess() {
		if (this.deleteThread != null) {
			this.deleteThread.interrupt();
			this.afterProccess();
		}
	}

	/**
	 * La función encargada de inicializar el proceso de borrado de una entidad dentro de la lista de la ventana.
	 * 
	 * @param entity
	 *            La entidad que vamos a borrar.
	 */
	protected void initDeleteThread(final E entity) {
		this.stopFillListProccess();

		// Creamos el proceso de llenado.
		this.deleteThread = new Thread() {
			@Override
			public void run() {
				try {
					BaseListDialog.this.beforeProccess();
					BaseListDialog.this.deleteProccess(entity);
					BaseListDialog.this.startFillListProccess();
				} catch (CheckedException e) {
					e.printStackTrace();
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(BaseListDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
								JOptionPane.ERROR_MESSAGE);
					}
				} finally {
					BaseListDialog.this.afterProccess();
				}
			}
		};
	}

	/**
	 * La función que define el proceso de borrado de entidades que vamos a ejecutar en segundo plano dentro de este dialogo.
	 * 
	 * @param entity
	 *            La entidad que vamos a borrar.
	 * @throws CheckedException
	 *             En caso de que ocurra un problema al momento de ejecutar la tarea.
	 */
	protected abstract void deleteProccess(E entity) throws CheckedException;
}