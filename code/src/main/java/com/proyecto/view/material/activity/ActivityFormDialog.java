package com.proyecto.view.material.activity;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.converter.ActivityTypeToReactiveTypeConverter;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.activity.type.ActivityType;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.activity.ActivityService;
import com.proyecto.service.material.reactive.ReactiveService;
import com.proyecto.view.Resources;
import com.proyecto.view.base.BaseFormDialog;
import com.proyecto.view.material.reactive.ReactiveListDialog;

/**
 * La clase que nos permite crear una ventana de edición de actividades que vamos a utilizar dentro del sistema para las evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ActivityFormDialog extends BaseFormDialog<Activity> {

	private static final long serialVersionUID = -1516446490165543963L;

	/**
	 * El acceso dentro del sistema.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * La ventana de selección de reactivos.
	 */
	@Autowired
	private ReactiveListDialog reactiveListDialog;

	/**
	 * El servicio de las actividades y los reactivos.
	 */
	@Autowired
	private ActivityService activityService;

	@Autowired
	private ReactiveService reactiveService;

	/**
	 * Los tipos validos de actividades que vamos a poder editar dentro de esta ventana.
	 */
	private List<ActivityType> activityTypes;

	/**
	 * La actividad que vamos a editar.
	 */
	private Activity activity;

	/**
	 * El area de la descripción de la actividad.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * La lista donde colocamos los reactivos de la actividad y su listado correspondiente.
	 */
	private JList<Reactive> reactivesList;
	/**
	 * Los botones de acciones.
	 */
	private JButton addReactiveButton;
	private JButton removeReactiveButton;
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana de edición de actividades.
	 */
	public ActivityFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana.
	 */
	private void init() {
		this.setBounds(100, 100, 701, 444);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("activity.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 10, 679, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(6, 25, 683, 135);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setBorder(new LineBorder(Color.GRAY));
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel activitiesLabel = new JLabel(HolderMessage.getMessage("activity.form.label.reactives"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		activitiesLabel.setFont(new Font("Arial", Font.BOLD, 11));
		activitiesLabel.setBounds(10, 172, 630, 14);
		this.getContentPane().add(activitiesLabel);

		JScrollPane reactivesScrollPane = new JScrollPane();
		reactivesScrollPane.setBounds(6, 187, 642, 160);
		this.getContentPane().add(reactivesScrollPane);

		this.reactivesList = new JList<Reactive>();
		this.reactivesList.setBorder(new LineBorder(Color.GRAY));
		this.reactivesList.setModel(new DefaultListModel<Reactive>());
		reactivesScrollPane.setViewportView(this.reactivesList);

		this.addReactiveButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.addReactiveButton.setBounds(654, 187, 35, 35);
		this.addReactiveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityFormDialog.this.addReactives();
			}
		});
		this.getContentPane().add(this.addReactiveButton);

		this.removeReactiveButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeReactiveButton.setBounds(654, 233, 35, 35);
		this.removeReactiveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityFormDialog.this.removeReactives();
			}
		});
		this.getContentPane().add(this.removeReactiveButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 359, 679, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(560, 373, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(607, 373, 35, 35);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityFormDialog.this.saveActivity();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(654, 373, 35, 35);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActivityFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.descriptionTextArea.setEnabled(enabled);

		this.reactivesList.setEnabled(enabled);

		this.addReactiveButton.setEnabled(enabled);
		this.removeReactiveButton.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cargar un nuevo reactivo dentro de la actividad.
	 */
	private void addReactives() {
		new Thread() {
			@Override
			public void run() {
				try {
					// Abrimos la ventana de selección de reactivos.
					ReactiveListDialog dialog = ActivityFormDialog.this.reactiveListDialog.createSelectDialog(ActivityTypeToReactiveTypeConverter
							.converter(ActivityFormDialog.this.activityTypes));
					dialog.setLocationRelativeTo(ActivityFormDialog.this);
					dialog.setVisible(true);

					// Deshabilitamos la ventana y actualizamos el listado de actividades.
					ActivityFormDialog.this.beforeProccess();

					// Actualizamos el listado de reactivos anteriores.
					ActivityFormDialog.this.updateReactives();

					// Cargamos el listado de los nuevos reactivos.
					if (dialog.getSelectedReactives() != null && !dialog.getSelectedReactives().isEmpty()) {
						// Cargamos la lista de reactivos a los que ya tenemos.
						DefaultListModel<Reactive> reactiveModel = (DefaultListModel<Reactive>) ActivityFormDialog.this.reactivesList.getModel();
						for (Reactive reactive : dialog.getSelectedReactives()) {
							if (!reactiveModel.contains(reactive)) {
								reactiveModel.addElement(reactive);
							}
						}
					}
				} catch (CheckedException e) {
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(ActivityFormDialog.this, HolderMessage.getMessage("activity.form.error.update"),
								HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					}
					e.printStackTrace();
				} finally {
					ActivityFormDialog.this.afterProccess();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de actualizar el listado de los reactivos que tenemos dentro de la actividad y que se vea reflejado el cambio en la
	 * ventana de edición.
	 * 
	 * @throws CheckedException
	 *             En caso de falla en la actualización de los reactivos.
	 */
	private void updateReactives() throws CheckedException {
		DefaultListModel<Reactive> oldReactiveModel = (DefaultListModel<Reactive>) this.reactivesList.getModel();
		DefaultListModel<Reactive> newReactiveModel = new DefaultListModel<Reactive>();
		Reactive oldReactive = null;
		Reactive newReactive = null;
		for (Integer index = 0; index < oldReactiveModel.getSize(); index++) {
			oldReactive = oldReactiveModel.get(index);
			newReactive = this.reactiveService.findById(oldReactive.getId());
			newReactiveModel.addElement(newReactive);
		}
		this.reactivesList.setModel(newReactiveModel);
	}

	/**
	 * La función encargada de quitar un reactivo desde de la actividad.
	 */
	private void removeReactives() {
		new Thread() {
			@Override
			public void run() {
				try {
					// Deshabilitamos la ventana y actualizamos el listado de reactivos.
					ActivityFormDialog.this.beforeProccess();

					// Vemos si tenemos algo seleccionado de la lista y lo quitamos.
					if (!ActivityFormDialog.this.reactivesList.getSelectedValuesList().isEmpty()) {
						List<Reactive> reactives = ActivityFormDialog.this.reactivesList.getSelectedValuesList();

						DefaultListModel<Reactive> reactiveModel = (DefaultListModel<Reactive>) ActivityFormDialog.this.reactivesList.getModel();
						for (Reactive reactive : reactives) {
							reactiveModel.removeElement(reactive);
						}

						// Actualizamos el listado de reactivos anteriores.
						ActivityFormDialog.this.updateReactives();
					}
				} catch (CheckedException e) {
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(ActivityFormDialog.this, HolderMessage.getMessage("activity.form.error.update"),
								HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					}
					e.printStackTrace();
				} finally {
					ActivityFormDialog.this.afterProccess();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de guardar la actividad dentro de la base de datos.
	 */
	private void saveActivity() {
		this.startProccess();
	}

	@Override
	protected void proccess() throws CheckedException {
		try {
			this.fromDialogToActivity();
			try {
				this.activityService.saveOrUpdate(ActivityFormDialog.this.activity);
				this.dispose();
			} catch (CheckedException e) {
				throw new CheckedException("activity.form.error.save");
			}
		} catch (CheckedException e) {
			throw e;
		}
	}

	@Override
	protected void beforeProccess() {
		this.setEnabled(false);
		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(gif);
	}

	@Override
	protected void afterProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de cargar los datos que tenemos dentro de la ventana dentro de la actividad que estamos editando.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un fallo a la hora de cargar la actividad desde la ventana.
	 */
	private void fromDialogToActivity() throws CheckedException {
		// Cargamos la asignatura de la actividad.
		this.activity.setSubject(this.accessControl.getSubjectSelected());

		// Verificamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("activity.form.error.description");
		} else {
			this.activity.setDescription(this.descriptionTextArea.getText().trim());
		}

		// Cargamos los reactivos.
		DefaultListModel<Reactive> reactiveModel = (DefaultListModel<Reactive>) this.reactivesList.getModel();
		List<Reactive> reactives = new ArrayList<Reactive>();
		for (Integer index = 0; index < reactiveModel.getSize(); index++) {
			reactives.add(reactiveModel.get(index));
		}

		if (reactives.isEmpty()) {
			throw new CheckedException("activity.form.error.reactives");
		} else {
			this.activity.clearReactive();
			this.activity.addAllReactives(reactives);
		}
	}

	/**
	 * La función encargada de cargar la ventana de edición con los datos que tenemos dentro de la actividad.
	 */
	private void fromActivityToDialog() {
		// Cargamos la descripción.
		this.descriptionTextArea.setText(this.activity.getDescription());

		// Si tenemos reactivos, los cargamos.
		if (this.activity.getReactives() != null && !this.activity.getReactives().isEmpty()) {

			List<Reactive> oldReactives = this.activity.getReactives();
			List<Reactive> newReactives = new ArrayList<Reactive>();

			// Actualizamos los reactivos.
			try {
				for (Reactive reactive : oldReactives) {
					newReactives.add(this.reactiveService.findById(reactive.getId()));
				}
			} catch (CheckedException e) {
				e.printStackTrace();
			}

			// Cargamos la lista de reactivos.
			DefaultListModel<Reactive> reactiveModel = (DefaultListModel<Reactive>) this.reactivesList.getModel();
			for (Reactive reactive : newReactives) {
				reactiveModel.addElement(reactive);
			}
		}
	}

	/**
	 * La función encargada de vaciar el contenido de la ventana.
	 */
	private void emptyFields() {
		this.descriptionTextArea.setText("");

		DefaultListModel<Reactive> reactiveModel = (DefaultListModel<Reactive>) this.reactivesList.getModel();
		reactiveModel.clear();
	}

	/**
	 * La función encargada de configurar la ventana para dar de alta una nueva actividad.
	 * 
	 * @param activityTypes
	 *            El listado de los tipos de actividades que podemos editar dentro de la ventana de edición.
	 * @return La ventana con el formulario configurado para dar de alta una actividad.
	 */
	public ActivityFormDialog createNewDialog(List<ActivityType> activityTypes) {
		this.setTitle(HolderMessage.getMessage("activity.form.title.new"));

		this.activityTypes = activityTypes;
		this.activity = new Activity();

		this.emptyFields();

		return this;
	}

	/**
	 * La función encargada de configurar la ventana para la edición de una actividad.
	 * 
	 * @param activity
	 *            La actividad que vamos a editar.
	 * @param activityTypes
	 *            El listado de los tipos de actividades que podemos seleccionar dentro de la ventana de edición.
	 * @return La ventana del formulario ya configurado para la edición del reactivo.
	 */
	public ActivityFormDialog createEditDialog(Activity activity, List<ActivityType> activityTypes) {
		this.setTitle(HolderMessage.getMessage("activity.form.title.edit"));

		this.activityTypes = activityTypes;
		this.activity = activity;

		this.emptyFields();
		this.fromActivityToDialog();

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

			// Activity activity = HolderApplicationContext.getContext().getBean(ActivityService.class).findById(1);
			// ActivityFormDialog dialog = HolderApplicationContext.getContext().getBean(ActivityFormDialog.class).createEditDialog(activity, null);

			ActivityFormDialog dialog = HolderApplicationContext.getContext().getBean(ActivityFormDialog.class)
					.createNewDialog(Arrays.asList(AssessmentTypeImpl.FORMAL.getActivityTypesAllowed()));
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}