package com.proyecto.view.reactive;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.reactive.Reactive;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.instrument.InstrumentService;
import com.proyecto.service.reactive.ReactiveService;
import com.proyecto.view.Resources;
import com.proyecto.view.instrument.CheckedException;
import com.proyecto.view.instrument.InstrumentListDialog;

import java.awt.Font;
import javax.swing.ListSelectionModel;

/**
 * La clase que crea la ventana donde desplegamos el listado de los reactivos que tenemos dentro del sistema.
 * @author Guillermo Mazzali 
 * @version 1.0
 */
@View
public class ReactiveListDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * La ventana de edición de reactivo.
	 */
	@Autowired
	private ReactiveFormDialog reactiveFormDialog;
	
	/**
	 * El servicio para los reactivos.
	 */
	@Autowired
	private ReactiveService reactiveService;
	
	/**
	 * El listado de los reactivos y el reactivo seleccionado.
	 */
	private JList<Reactive> reactivelist;
	private Reactive selectedReactive;
	
	/**
	 * Los botones de acción.
	 */
	private JButton newButton;
	private JButton modifyButton;
	private JButton deletebutton;
	private JButton closeButton;
	private JButton selectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	
	/**
	 * Constructor de una ventana de listado de reactivos.
	 */
	public ReactiveListDialog() {
		super();
		this.init();
	}
	
	/**
	 * La función encargada de inicializar los componentes de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		setBounds(100, 100, 617, 405);
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		reactivelist = new JList<>();
		reactivelist.setModel(new DefaultListModel());
		reactivelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reactivelist.setFont(new Font("Arial", Font.PLAIN, 12));
		reactivelist.setBounds(10, 14, 451, 334);
		contentPanel.add(reactivelist);
		
		newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		newButton.setBounds(476, 11, 35, 35);
		contentPanel.add(newButton);
		
		modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		modifyButton.setBounds(476, 53, 35, 35);
		contentPanel.add(modifyButton);
		
		deletebutton = new JButton(Resources.DELETE_ELEMENT_ICON);
		deletebutton.setBounds(476, 95, 35, 35);
		contentPanel.add(deletebutton);
		
		selectButton = new JButton(Resources.SELECT_ELEMENT_ICON);
		selectButton.setBounds(476, 260, 35, 35);
		contentPanel.add(selectButton);
		
		progressLabel = new JLabel();
		progressLabel.setBounds(476, 400, 35, 35);
		contentPanel.add(progressLabel);
		
		closeButton = new JButton(Resources.CLOSE_ICON);
		closeButton.setBounds(476, 321, 35, 35);
		contentPanel.add(closeButton);
	}
	
	/**
	 * La función encargada de actualizar el listado de los reactivos que tenemos dentro de la ventana.
	 */
	private void updateReactives() {
		new Thread() {
			@Override
			public void run() {
				// Ejecutamos las acciones antes de procesar.
				ReactiveListDialog.this.beforeExecuteProccess();
				
				// Vaciamos la lista.
				DefaultListModel model = (DefaultListModel) ReactiveListDialog.this.reactivelist.getModel();
				model.clear();
				
				// La volvemos a cargar.
				new Thread() {
					@Override
					public void run() {
						try {
							// Cargamos el listado de reactivos.
							ReactiveListDialog.this.loadReactiveList();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(ReactiveListDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							ReactiveListDialog.this.afterExecuteProccess();
						}
					}
				}.start();
			}
		}.start();
	}

	/**
	 * La función encargada de cargar el listado de los reactivos que tenemos dentro del sistema.
	 */
	private void loadReactiveList() {
		DefaultListModel model = (DefaultListModel) this.reactivelist.getModel();
		for (Reactive r : this.reactiveService.findAll()) {
			model.addElement(r);
		}
	}

	/**
	 * La función antes de procesar los reactivos.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de procesar los reactivos.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}
	
	/**
	 * La función encargada de crear una nueva instancia de un reactivo dentro del sistema.
	 */
	private void newReactive() {
		// Creamos la ventana para dar de alta un nuevo reactivo.
		JDialog dialog = this.reactiveFormDialog.createNewDialog(null);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// Recargamos la tabla de reactivos.
		this.updateReactives();
	}

	/**
	 * La función encargada de modificar un reactivo seleccionado dentro del listado.
	 */
	private void modifyReactive() {
		// Si tenemos algo seleccionado.
		if (this.reactivelist.getSelectedValue() != null) {
			
			// Obtenemos el reactivo.
			Reactive reactive = (Reactive)this.reactivelist.getSelectedValue();
			
			// Creamos la ventana para modificar reactivos.
			this.reactiveFormDialog.createEditDialog(reactive, reactive.getInstrument().getClass());

			// Recargamos la tabla de reactivos.
			this.updateReactives();
		}
	}

	/**
	 * La función encargada de eliminar un reactivo seleccionado dentro del listado de los mismos.
	 */
	private void removeReactive() {
		// Si tenemos algo seleccionado.
		if (this.reactivelist.getSelectedValue() != null) {
			
			// Pedimos confirmación de borrado.
			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("reactive.manager.dialog.delete.confirm"), "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				final Reactive deleteReactive = (Reactive) this.reactivelist.getSelectedValue();
				
				new Thread() {
					@Override
					public void run() {
						try {
							ReactiveListDialog.this.beforeExecuteProccess();
							ReactiveListDialog.this.reactiveService.delete(deleteReactive);
							ReactiveListDialog.this.updateReactives();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(ReactiveListDialog.this,
									HolderMessage.getMessage("reactive.manager.dialog.delete.failed"), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							ReactiveListDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de tomar el instrumento seleccionado y mantenerlo para retornarlo mas adelante en el sistema.
	 */
	private void selectInstrument() {
		// Si tenemos algo seleccionado.
		if (this.reactivelist.getSelectedValue() != null) {
			
			// Tomamos el reactivo seleccionado.
			this.selectedReactive = (Reactive)this.reactivelist.getSelectedValue();
			this.dispose();
		}
	}

	/**
	 * La función encargada de retornar el reactivo que seleccionamos dentro de la ventana.
	 * 
	 * @return El reactivo que seleccionamos dentro de la ventana.
	 */
	public Reactive getSelectedReactive() {
		return this.selectedReactive;
	}

	/**
	 * La función encargada de inicializar la ventana de administración de reactivos.
	 * 
	 * @return La ventana de administración de reactivos.
	 */
	public ReactiveListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("reactive.manager.dialog.title.crud"));
		
		// TODO gmazzali Hacer lo de la configuración de la ventana de reactivos para su administración.
		
		return this;
	}

	/**
	 * La función encargada de inicializar la ventana de selección de reactivos.
	 * 
	 * @param instrumentClass
	 *            La clase mínima de los instrumentos de los reactivos que vamos a listar dentro de esta ventana.
	 * @return La ventana de selección de reactivos.
	 */
	public ReactiveListDialog createSelectDialog(Class<? extends Instrument> instrumentClass) {
		this.setTitle(HolderMessage.getMessage("reactive.manager.dialog.title.select"));
		
		// TODO gmazzali Hacer lo de la configuración de la ventana de reactivos para la selección.
		
		return this;
	}
}
