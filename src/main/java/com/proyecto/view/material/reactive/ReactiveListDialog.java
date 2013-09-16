package com.proyecto.view.material.reactive;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.material.reactive.type.ReactiveType;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.reactive.ReactiveService;
import com.proyecto.view.Resources;

/**
 * La clase que crea la ventana donde desplegamos el listado de los reactivos que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ReactiveListDialog extends JDialog {

	private static final long serialVersionUID = 6091009153558216022L;

	/**
	 * El acceso dentro del sistema.
	 */
	@Autowired
	private AccessControl accessControl;

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
	 * El valor booleano que nos determina si la ventana es de selección o de administración y los reactivos seleccionados.
	 */
	private Boolean isSelectDialog;
	private List<Reactive> reactivesSelected;

	/**
	 * Los tipos validos de reactivos que vamos a poder editar dentro de esta ventana.
	 */
	private List<ReactiveType> reactiveTypes;

	/**
	 * El listado de los reactivos.
	 */
	private JList<Reactive> reactiveList;
	/**
	 * Los botones de acción.
	 */
	private JButton newButton;
	private JButton modifyButton;
	private JButton removeButton;
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
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		this.setBounds(100, 100, 700, 405);
		this.getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane reactiveScrollPane = new JScrollPane();
		reactiveScrollPane.setBounds(10, 11, 627, 355);
		contentPanel.add(reactiveScrollPane);

		this.reactiveList = new JList<Reactive>();
		this.reactiveList.setModel(new DefaultListModel<Reactive>());
		this.reactiveList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.reactiveList.setFont(new Font("Arial", Font.PLAIN, 12));
		reactiveScrollPane.setViewportView(this.reactiveList);

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(649, 11, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveListDialog.this.newReactive();
			}
		});
		contentPanel.add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(649, 57, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveListDialog.this.modifyReactive();
			}
		});
		contentPanel.add(this.modifyButton);

		this.removeButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeButton.setBounds(649, 103, 35, 35);
		this.removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveListDialog.this.removeReactive();
			}
		});
		contentPanel.add(this.removeButton);

		this.selectButton = new JButton(Resources.SELECT_ELEMENT_ICON);
		this.selectButton.setBounds(649, 149, 35, 35);
		this.selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveListDialog.this.selectReactives();
			}
		});
		contentPanel.add(this.selectButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(649, 284, 35, 35);
		contentPanel.add(this.progressLabel);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setBounds(649, 331, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ReactiveListDialog.this.dispose();
			}
		});
		contentPanel.add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.reactiveList.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.removeButton.setEnabled(enabled);
		this.selectButton.setEnabled(enabled && this.isSelectDialog);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de actualizar el listado de los reactivos que tenemos dentro de la ventana.
	 */
	private void updateReactives() {
		// Vaciamos la lista.
		DefaultListModel<Reactive> model = (DefaultListModel<Reactive>) ReactiveListDialog.this.reactiveList.getModel();
		model.clear();

		new Thread() {
			@Override
			public void run() {
				// Ejecutamos las acciones antes de procesar.
				ReactiveListDialog.this.beforeExecuteProccess();

				// La volvemos a cargar.
				new Thread() {
					@Override
					public void run() {
						try {
							// Cargamos el listado de reactivos.
							ReactiveListDialog.this.loadReactiveList();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(ReactiveListDialog.this, e.getMessage(),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
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
	 * 
	 * @throws CheckedException
	 *             En caso de alguna falla cuando recuperamos los reactivos desde la base de datos.
	 */
	private void loadReactiveList() throws CheckedException {
		DefaultListModel<Reactive> model = new DefaultListModel<Reactive>();

		// El listado de los reactivos.
		List<Reactive> reactives = this.reactiveService.findBySubject(this.accessControl.getSubjectSelected(), this.reactiveTypes);

		// Cargamos el listado de los reactivos que filtramos.
		for (Reactive r : reactives) {
			model.addElement(r);
		}
		this.reactiveList.setModel(model);
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
	 * La función encargada de crear una ventana para dar de alta una nueva instancia de un reactivo dentro del sistema.
	 */
	private void newReactive() {
		// Creamos la ventana para dar de alta un nuevo reactivo.
		JDialog dialog = this.reactiveFormDialog.createNewDialog(this.reactiveTypes);
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
		if (this.reactiveList.getSelectedValue() != null) {

			Reactive reactive = this.reactiveList.getSelectedValue();
			ReactiveFormDialog dialog = this.reactiveFormDialog.createEditDialog(reactive, this.reactiveTypes);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);

			this.updateReactives();
		}
	}

	/**
	 * La función encargada de eliminar un reactivo seleccionado dentro del listado de los mismos.
	 */
	private void removeReactive() {
		// Si tenemos algo seleccionado.
		if (this.reactiveList.getSelectedValue() != null) {

			// Pedimos confirmación de borrado.
			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("reactive.manager.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
					@Override
					public void run() {
						try {
							ReactiveListDialog.this.beforeExecuteProccess();
							ReactiveListDialog.this.reactiveService.delete(ReactiveListDialog.this.reactiveList.getSelectedValue());
							ReactiveListDialog.this.updateReactives();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(ReactiveListDialog.this, HolderMessage.getMessage("reactive.manager.delete.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							ReactiveListDialog.this.afterExecuteProccess();
						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de tomar los reactivos seleccionados y mantenerlos para retornarlos mas adelante en el sistema.
	 */
	private void selectReactives() {
		// Si tenemos algo seleccionado.
		if (this.reactiveList.getSelectedIndices().length > 0) {

			this.reactivesSelected = this.reactiveList.getSelectedValuesList();

			this.dispose();
		}
	}

	/**
	 * La función encargada de retornar el listado de reactivos que seleccionamos dentro de la ventana.
	 * 
	 * @return El listado de reactivos que seleccionamos dentro de la ventana.
	 */
	public List<Reactive> getSelectedReactives() {
		return this.reactivesSelected;
	}

	/**
	 * La función encargada de inicializar la ventana de administración de reactivos.
	 * 
	 * @return La ventana de administración de reactivos.
	 */
	public ReactiveListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("reactive.manager.title.crud"));

		this.reactiveTypes = null;

		this.isSelectDialog = false;
		this.reactivesSelected = null;

		this.updateReactives();

		return this;
	}

	/**
	 * La función encargada de inicializar la ventana de selección de reactivos.
	 * 
	 * @param reactiveTypes
	 *            El listado de los reactivos que podemos seleccionar dentro de esta ventana de listado.
	 * @return La ventana de selección de reactivos.
	 */
	public ReactiveListDialog createSelectDialog(List<ReactiveType> reactiveTypes) {
		this.setTitle(HolderMessage.getMessage("reactive.manager.title.select"));

		this.reactiveTypes = reactiveTypes;

		this.isSelectDialog = true;
		this.reactivesSelected = null;

		this.updateReactives();

		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			ReactiveListDialog dialog = HolderApplicationContext.getContext().getBean(ReactiveListDialog.class).createSelectDialog(null);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
