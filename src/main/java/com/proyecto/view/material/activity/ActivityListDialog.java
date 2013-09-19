package com.proyecto.view.material.activity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.activity.ActivityService;
import com.proyecto.view.Resources;

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
	 * El acceso dentro del sistema.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * La ventana de edición de actividades.
	 */
	@Autowired
	private ActivityFormDialog activityFormDialog;

	/**
	 * El servicio para las actividades.
	 */
	@Autowired
	private ActivityService activityService;

	/**
	 * El valor booleano que nos determina si la ventana es de selección o de administración y las actividades seleccionadas.
	 */
	private Boolean isSelectDialog;
	private List<Activity> activitiesSelected;

	/**
	 * Los tipos validos de actividades que vamos a poder editar dentro de esta ventana.
	 */
	private List<ActivityType> activityTypes;

	/**
	 * El listado de las actividades.
	 */
	private JList<Activity> activityList;
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
	private JLabel activitiesListLabel;

	/**
	 * Constructor de la ventana de listado.
	 */
	public ActivityListDialog() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización de los componentes de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);

		this.setBounds(100, 100, 690, 402);
		this.getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		this.activitiesListLabel = new JLabel(HolderMessage.getMessage("activity.manager.label.activities"));
		this.activitiesListLabel.setBounds(10, 10, 623, 16);
		contentPanel.add(this.activitiesListLabel);
		this.activitiesListLabel.setFont(new Font("Arial", Font.BOLD, 11));

		JScrollPane activitiesScrollPane = new JScrollPane();
		activitiesScrollPane.setBounds(6, 27, 627, 341);
		contentPanel.add(activitiesScrollPane);

		this.activityList = new JList<Activity>();
		this.activityList.setBorder(new LineBorder(Color.GRAY));
		this.activityList.setModel(new DefaultListModel<Activity>());
		this.activityList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		this.activityList.setFont(new Font("Arial", Font.PLAIN, 12));
		activitiesScrollPane.setViewportView(this.activityList);

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(640, 27, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityListDialog.this.newActivity();
			}
		});
		contentPanel.add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(640, 73, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityListDialog.this.modifyActivity();
			}
		});
		contentPanel.add(this.modifyButton);

		this.removeButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeButton.setBounds(640, 119, 35, 35);
		this.removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityListDialog.this.removeActivity();
			}
		});
		contentPanel.add(this.removeButton);

		this.selectButton = new JButton(Resources.SELECT_ELEMENT_ICON);
		this.selectButton.setBounds(640, 165, 35, 35);
		this.selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityListDialog.this.selectActivities();
			}
		});
		contentPanel.add(this.selectButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(640, 286, 35, 35);
		contentPanel.add(this.progressLabel);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setBounds(640, 333, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityListDialog.this.dispose();
			}
		});
		contentPanel.add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.activityList.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.removeButton.setEnabled(enabled);
		this.selectButton.setEnabled(enabled && this.isSelectDialog);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de actualizar el listado de las actividades que tenemos dentro de la ventana.
	 */
	private void updateActivities() {
		// Vaciamos la lista.
		DefaultListModel<Activity> model = (DefaultListModel<Activity>) ActivityListDialog.this.activityList.getModel();
		model.clear();

		new Thread() {
			@Override
			public void run() {
				// Ejecutamos las acciones antes de procesar.
				ActivityListDialog.this.beforeExecuteProccess();

				// La volvemos a cargar.
				new Thread() {
					@Override
					public void run() {
						try {
							// Cargamos el listado de actividades.
							ActivityListDialog.this.loadReactiveList();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(ActivityListDialog.this,
									HolderMessage.getMessage("activity.manager.load.activities.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						} finally {
							ActivityListDialog.this.afterExecuteProccess();
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
		DefaultListModel<Activity> model = new DefaultListModel<Activity>();

		// El listado de las actividades.
		List<Activity> activities = this.activityService.findBySubject(this.accessControl.getSubjectSelected(), this.activityTypes);

		// Cargamos el listado de las actividades que filtramos.
		for (Activity activity : activities) {
			model.addElement(activity);
		}
		this.activityList.setModel(model);
	}

	/**
	 * La función antes de procesar las actividades.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(gif);
	}

	/*
	 * La función después de procesar las actividades.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de crear una ventana para dar de alta una nueva instancia de una actividad dentro del sistema.
	 */
	private void newActivity() {
		// Creamos la ventana para dar de alta una nueva actividad.
		JDialog dialog = this.activityFormDialog.createNewDialog(this.activityTypes);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// Recargamos la tabla de actividades.
		this.updateActivities();
	}

	/**
	 * La función encargada de modificar una actividad seleccionado dentro del listado.
	 */
	private void modifyActivity() {
		// Si tenemos algo seleccionado.
		if (this.activityList.getSelectedValue() != null) {

			Activity activity = this.activityList.getSelectedValue();
			JDialog dialog = this.activityFormDialog.createEditDialog(activity, this.activityTypes);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);

			this.updateActivities();
		}
	}

	/**
	 * La función encargada de eliminar una actividad seleccionada dentro del listado de las mismas.
	 */
	private void removeActivity() {
		// Si tenemos algo seleccionado.
		if (this.activityList.getSelectedValue() != null) {

			// Pedimos confirmación de borrado.
			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("activity.manager.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
					@Override
					public void run() {
						try {
							ActivityListDialog.this.beforeExecuteProccess();
							ActivityListDialog.this.activityService.delete(ActivityListDialog.this.activityList.getSelectedValue());
							ActivityListDialog.this.updateActivities();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(ActivityListDialog.this, HolderMessage.getMessage("activity.manager.delete.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							ActivityListDialog.this.afterExecuteProccess();
						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de tomar las actividades seleccionada y mantenerlas para retornarlas mas adelante en el sistema.
	 */
	private void selectActivities() {
		// Si tenemos algo seleccionado.
		if (this.activityList.getSelectedIndices().length > 0) {

			this.activitiesSelected = this.activityList.getSelectedValuesList();

			this.dispose();
		}
	}

	/**
	 * La función encargada de retornar el listado de actividades que seleccionamos dentro de la ventana.
	 * 
	 * @return El listado de actividades que seleccionamos dentro de la ventana.
	 */
	public List<Activity> getSelectedActivities() {
		return this.activitiesSelected;
	}

	/**
	 * La función encargada de inicializar la ventana de administración de actividades.
	 * 
	 * @return La ventana de administración de actividades.
	 */
	public ActivityListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("activity.manager.title.crud"));

		this.activityTypes = null;

		this.isSelectDialog = false;
		this.activitiesSelected = null;

		this.updateActivities();

		return this;
	}

	/**
	 * La función encargada de inicializar la ventana de selección de actividades.
	 * 
	 * @param activityTypes
	 *            El listado de las actividades que podemos seleccionar dentro de esta ventana de listado.
	 * @return La ventana de selección de actividades.
	 */
	public ActivityListDialog createSelectDialog(List<ActivityType> activityTypes) {
		this.setTitle(HolderMessage.getMessage("activity.manager.title.select"));

		this.activityTypes = activityTypes;

		this.isSelectDialog = true;
		this.activitiesSelected = null;

		this.updateActivities();

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

			ActivityListDialog dialog = HolderApplicationContext.getContext().getBean(ActivityListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
