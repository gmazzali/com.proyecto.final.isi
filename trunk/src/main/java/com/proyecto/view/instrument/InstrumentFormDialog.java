package com.proyecto.view.instrument;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.common.util.exception.CheckedException;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.service.instrument.InstrumentService;
import com.proyecto.view.Resources;

/**
 * La super clase de los formularios de edici�n de instrumentos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public abstract class InstrumentFormDialog extends JDialog {

	private static final long serialVersionUID = 7961668127751162989L;

	/**
	 * El constructor por omisi�n.
	 */
	public InstrumentFormDialog() {
		super();
	}

	/**
	 * La funci�n encargada de configurar una ventana para la creaci�n de un nuevo instrumento dentro del sistema.
	 * 
	 * @return La ventana configurada para dar de alta un nuevo instrumento dentro de la base de datos.
	 */
	protected InstrumentFormDialog createNewDialog() {
		this.setTitle(this.getNewTitle());
		this.setNewInstrument();
		this.emptyFields();
		return this;
	}

	/**
	 * La funci�n encargada de configurar una ventana para la edici�n de un instrumento dentro del sistema.
	 * 
	 * @param instrument
	 *            El instrumento que vamos a editar dentro de esta ventana.
	 * @return La ventana configurada para la edici�n del instrumento.
	 */
	protected InstrumentFormDialog createEditDialog(Instrument instrument) {
		this.setTitle(this.getEditTitle());
		this.setEditInstrument(instrument);
		this.fromInstrumentToDialog();
		return this;
	}

	/**
	 * La funci�n encargada de guardar el instrumento dentro de la base de datos.
	 */
	protected void saveInstrument() {
		new Thread() {
			@Override
			public void run() {
				try {
					InstrumentFormDialog.this.beforeSave();
					InstrumentFormDialog.this.fromDialogToInstrument();
					InstrumentFormDialog.this.getInstrumentService().saveOrUpdate(InstrumentFormDialog.this.getInstrument());
					InstrumentFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(InstrumentFormDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					InstrumentFormDialog.this.afterSave();
				}
			}
		}.start();
	}

	/**
	 * La funci�n antes de guardar el instrumento.
	 */
	protected void beforeSave() {
		this.setEnabled(false);

		if (this.getProgressLabel() != null) {
			Resources.PROGRESS_LIST_ICON.setImageObserver(this.getProgressLabel());
			this.getProgressLabel().setIcon(Resources.PROGRESS_LIST_ICON);
		}
	}

	/*
	 * La funci�n despu�s de guardar el instrumento.
	 */
	protected void afterSave() {
		this.setEnabled(true);

		if (this.getProgressLabel() != null) {
			this.getProgressLabel().setIcon(null);
		}
	}

	/**
	 * La funci�n encargada de retornar el servicio que vamos a usar para esta ventana.
	 * 
	 * @return El servicio que usamos para el instrumento dentro de la ventana.
	 */
	protected abstract <E extends Instrument> InstrumentService<E> getInstrumentService();

	/**
	 * La funci�n encargada de retornar el instrumento que tenemos dentro de esta ventana.
	 * 
	 * @return El instrumento que tenemos dentro de esta ventana.
	 */
	protected abstract <E extends Instrument> E getInstrument();

	/**
	 * La funci�n encargada de crear un nuevo instrumento para dar de alta dentro de esta ventana.
	 */
	protected abstract void setNewInstrument();

	/**
	 * La funci�n encargada de cargar el instrumento que vamos a editar dentro de esta ventana.
	 * 
	 * @param instrument
	 *            El instrumento que vamos a editar.
	 */
	protected abstract <E extends Instrument> void setEditInstrument(E instrument);

	/**
	 * La funci�n encargada de tomar los datos de la ventana y pasarlos al instrumento.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un fallo al momento de validar los datos para el instrumento.
	 */
	protected abstract void fromDialogToInstrument() throws CheckedException;

	/**
	 * La funci�n encargada de cargar los campos de la ventana con los datos del instrumento que vamos a editar.
	 */
	protected abstract void fromInstrumentToDialog();

	/**
	 * La funci�n encargada de vaciar el contenido de los campos de la ventana.
	 */
	protected abstract void emptyFields();

	/**
	 * La funci�n encargada de retornar el label de progreso del guardado del instrumento.
	 * 
	 * @return El label de guardado del instrumento.
	 */
	protected abstract JLabel getProgressLabel();

	/**
	 * La funci�n que retorna el titulo de la ventana para el alta de un nuevo instrumento.
	 * 
	 * @return El titulo de la ventana para el alta de un instrumento.
	 */
	protected abstract String getNewTitle();

	/**
	 * La funci�n que retorna el titulo de la ventana para la edici�n de un nuevo instrumento.
	 * 
	 * @return El titulo de la ventana para la edici�n de un instrumento.
	 */
	protected abstract String getEditTitle();
}
