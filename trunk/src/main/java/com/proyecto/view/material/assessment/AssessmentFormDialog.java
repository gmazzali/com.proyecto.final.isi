package com.proyecto.view.material.assessment;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.converter.AssessmentTypeToActivityTypeConverter;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.assessment.type.AssessmentType;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeByTimeImpl;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.assessment.AssessmentService;
import com.proyecto.view.Resources;
import com.proyecto.view.material.activity.ActivityListDialog;
import com.toedter.calendar.JDateChooser;

/**
 * La ventana de edición de las evaluaciones que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentFormDialog extends JDialog {

	private static final long serialVersionUID = -5386008246325856550L;

	/**
	 * El acceso dentro del sistema.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * La ventana de selección de actividades.
	 */
	@Autowired
	private ActivityListDialog activityListDialog;

	/*
	 * El servicio para las evaluaciones.
	 */
	@Autowired
	private AssessmentService assessmentService;

	/**
	 * La evaluación que vamos a editar y el tipo de la misma.
	 */
	private Assessment assessment;
	private AssessmentType assessmentType;

	/**
	 * El combo con los tipos de evaluaciones de acuerdo al momento para el que se define la misma.
	 */
	private JComboBox<AssessmentTypeByTimeImpl> assessmentTypeByTimeComboBox;
	/**
	 * El campo de la fecha de la evaluación.
	 */
	private JDateChooser assessmentTimeDateChooser;
	/**
	 * El campo de descripción de la evaluación.
	 */
	private JTextArea descritionTextArea;
	/**
	 * La lista de actividades.
	 */
	private JList<Activity> activitiesList;
	/**
	 * Los botones de acciones.
	 */
	private JButton addActivityButton;
	private JButton removeActivityButton;
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana de edición de evaluaciones.
	 */
	public AssessmentFormDialog() {
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

		JLabel assessmentTypeLabel = new JLabel(HolderMessage.getMessage("assessment.form.label.type.by.time"));
		assessmentTypeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentTypeLabel.setBounds(10, 6, 333, 16);
		this.getContentPane().add(assessmentTypeLabel);

		this.assessmentTypeByTimeComboBox = new JComboBox<AssessmentTypeByTimeImpl>();
		this.assessmentTypeByTimeComboBox.setBounds(10, 24, 333, 26);
		this.getContentPane().add(this.assessmentTypeByTimeComboBox);
		this.initAssessmentTypeByTimeComboBox();

		JLabel assessmentTimeLabel = new JLabel(HolderMessage.getMessage("assessment.form.label.time"));
		assessmentTimeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentTimeLabel.setBounds(352, 5, 333, 16);
		this.getContentPane().add(assessmentTimeLabel);

		this.assessmentTimeDateChooser = new JDateChooser();
		this.assessmentTimeDateChooser.setDateFormatString("dd/MM/yyyy");
		this.assessmentTimeDateChooser.setBounds(355, 24, 330, 28);
		this.getContentPane().add(this.assessmentTimeDateChooser);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("assessment.form.label.description"));
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 62, 675, 16);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 84, 675, 124);
		this.getContentPane().add(descriptionScrollPane);

		this.descritionTextArea = new JTextArea();
		this.descritionTextArea.setWrapStyleWord(true);
		this.descritionTextArea.setLineWrap(true);
		descriptionScrollPane.setViewportView(this.descritionTextArea);

		JLabel activitiesLabel = new JLabel(HolderMessage.getMessage("assessment.form.label.activities"));
		activitiesLabel.setFont(new Font("Arial", Font.BOLD, 11));
		activitiesLabel.setBounds(10, 220, 675, 16);
		this.getContentPane().add(activitiesLabel);

		JScrollPane activitiesScrollPane = new JScrollPane();
		activitiesScrollPane.setBounds(10, 242, 628, 118);
		this.getContentPane().add(activitiesScrollPane);

		this.activitiesList = new JList<Activity>();
		this.activitiesList.setModel(new DefaultListModel<Activity>());
		activitiesScrollPane.setViewportView(this.activitiesList);

		this.addActivityButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.addActivityButton.setBounds(650, 242, 35, 35);
		this.addActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentFormDialog.this.addActivities();
			}
		});
		this.getContentPane().add(this.addActivityButton);

		this.removeActivityButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeActivityButton.setBounds(650, 288, 35, 35);
		this.removeActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentFormDialog.this.removeActivities();
			}
		});
		this.getContentPane().add(this.removeActivityButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 372, 675, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(558, 385, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(650, 385, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(603, 385, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentFormDialog.this.saveAssessment();
			}
		});
		this.getContentPane().add(this.commitButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.assessmentTypeByTimeComboBox.setEnabled(enabled);
		this.assessmentTimeDateChooser.setEnabled(enabled);

		this.descritionTextArea.setEnabled(enabled);
		this.activitiesList.setEnabled(enabled);

		this.addActivityButton.setEnabled(enabled);
		this.removeActivityButton.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
	}

	/**
	 * La función de carga del combo de tipo de evaluación por el momento.
	 */
	private void initAssessmentTypeByTimeComboBox() {
		this.assessmentTypeByTimeComboBox.removeAllItems();

		for (AssessmentTypeByTimeImpl type : AssessmentTypeByTimeImpl.values()) {
			this.assessmentTypeByTimeComboBox.addItem(type);
		}
	}

	/**
	 * La función encargada de cargar nuevas actividades desde la ventana de selección.
	 */
	private void addActivities() {
		// Abrimos la ventana de selección de actividades.
		ActivityListDialog dialog = this.activityListDialog.createSelectDialog(AssessmentTypeToActivityTypeConverter.converter(this.assessmentType));
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		if (dialog.getSelectedActivities() != null && !dialog.getSelectedActivities().isEmpty()) {

			// Cargamos la lista de actividades a las que ya tenemos.
			DefaultListModel<Activity> activityModel = (DefaultListModel<Activity>) this.activitiesList.getModel();
			for (Activity activity : dialog.getSelectedActivities()) {
				if (!activityModel.contains(activity)) {
					activityModel.addElement(activity);
				}
			}
		}
	}

	/**
	 * La función encargada de quitar las actividades que tenemos seleccionada dentro de la lista.
	 */
	private void removeActivities() {
		// Vemos si tenemos algo seleccionado de la lista y lo quitamos.
		if (!this.activitiesList.getSelectedValuesList().isEmpty()) {
			List<Activity> activities = this.activitiesList.getSelectedValuesList();

			DefaultListModel<Activity> activityModel = (DefaultListModel<Activity>) this.activitiesList.getModel();
			for (Activity activity : activities) {
				activityModel.removeElement(activity);
			}
		}
	}

	/**
	 * La función encargada de guardar la evaluación dentro de la base de datos.
	 */
	private void saveAssessment() {
		new Thread() {
			@Override
			public void run() {
				try {
					AssessmentFormDialog.this.beforeProccessAssessment();
					AssessmentFormDialog.this.fromDialogToAssessment();
					AssessmentFormDialog.this.assessmentService.saveOrUpdate(AssessmentFormDialog.this.assessment);
					AssessmentFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(AssessmentFormDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					AssessmentFormDialog.this.afterProccessAssessment();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar la evaluación.
	 */
	private void beforeProccessAssessment() {
		this.setEnabled(false);
		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de procesar la evaluación.
	 */
	private void afterProccessAssessment() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de cargar los datos que tenemos dentro de la ventana dentro de la evaluación que estamos editando.
	 * 
	 * @throws CheckedException
	 *             En caso de que ocurra un fallo a la hora de cargar la evaluación desde la ventana.
	 */
	private void fromDialogToAssessment() throws CheckedException {
		// Cargamos la asignatura de la evaluación.
		this.assessment.setSubject(this.accessControl.getSubjectSelected());

		// Cargamos el tipo de acuerdo al momento de la misma.
		if (this.assessmentTypeByTimeComboBox.getSelectedItem() == null) {
			throw new CheckedException("assessment.form.error.type.by.time");
		} else {
			this.assessment.setAssessmentTypeByTime((AssessmentTypeByTimeImpl) this.assessmentTypeByTimeComboBox.getSelectedItem());
		}

		// Cargamos la fecha de la evaluación.
		if (this.assessmentTimeDateChooser.getDate() == null) {
			throw new CheckedException("assessment.form.error.time");
		} else {
			this.assessment.setAssessmentDate(this.assessmentTimeDateChooser.getDate());
		}

		// Verificamos la descripción.
		if (this.descritionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("assessment.form.error.description");
		} else {
			this.assessment.setDescription(this.descritionTextArea.getText().trim());
		}

		// Cargamos las actividades.
		DefaultListModel<Activity> activityModel = (DefaultListModel<Activity>) this.activitiesList.getModel();
		List<Activity> activities = new ArrayList<Activity>();
		for (Integer index = 0; index < activityModel.getSize(); index++) {
			activities.add(activityModel.get(index));
		}

		if (activities.isEmpty()) {
			throw new CheckedException("assessment.form.error.activities");
		} else {
			this.assessment.clearActivities();
			this.assessment.addActivities(activities);
		}
	}

	/**
	 * La función encargada de cargar la ventana de edición con los datos que tenemos dentro de la evaluación.
	 */
	private void fromAssessmentToDialog() {
		// Cargamos el tipo.
		if (this.assessment.getAssessmentTypeByTime() != null) {
			this.assessmentTypeByTimeComboBox.setSelectedItem(this.assessment.getAssessmentTypeByTime());
		}

		// Cargamos la fecha.
		if (this.assessment.getAssessmentDate() != null) {
			this.assessmentTimeDateChooser.setDate(this.assessment.getAssessmentDate());
		}

		// Cargamos la descripción.
		this.descritionTextArea.setText(this.assessment.getDescription());

		// Si tenemos actividades, las cargamos.
		if (this.assessment.getActivities() != null && !this.assessment.getActivities().isEmpty()) {

			// Cargamos la lista de reactivos.
			DefaultListModel<Activity> activityModel = (DefaultListModel<Activity>) this.activitiesList.getModel();
			for (Activity activity : this.assessment.getActivities()) {
				activityModel.addElement(activity);
			}
		}
	}

	/**
	 * La función encargada de vaciar el contenido de la ventana.
	 */
	private void emptyFields() {
		this.assessmentTypeByTimeComboBox.setSelectedIndex(-1);
		this.assessmentTimeDateChooser.setDate(null);
		this.descritionTextArea.setText("");

		DefaultListModel<Activity> activityModel = (DefaultListModel<Activity>) this.activitiesList.getModel();
		activityModel.clear();
	}

	/**
	 * La función encargada de configurar la ventana para dar de alta una nueva evaluación.
	 * 
	 * @param assessmentType
	 *            El tipo de evaluación que podemos seleccionar dentro de la ventana de edición.
	 * @return La ventana con el formulario configurado para dar de alta una evaluación.
	 */
	public AssessmentFormDialog createNewDialog(AssessmentType assessmentType) {
		this.setTitle(HolderMessage.getMessage("assessment.form.title.new"));

		this.assessmentType = assessmentType;
		this.assessment = new Assessment();

		this.emptyFields();

		return this;
	}

	/**
	 * La función encargada de configurar la ventana para la edición de una evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que vamos a editar.
	 * @param assessmentType
	 *            El tipo de evaluación que podemos seleccionar dentro de la ventana de edición.
	 * @return La ventana del formulario ya configurado para la edición del reactivo.
	 */
	public AssessmentFormDialog createEditDialog(Assessment assessment, AssessmentType assessmentType) {
		this.setTitle(HolderMessage.getMessage("assessment.form.title.edit"));

		this.assessmentType = assessmentType;
		this.assessment = assessment;

		this.emptyFields();
		this.fromAssessmentToDialog();

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

			AssessmentFormDialog dialog = HolderApplicationContext.getContext().getBean(AssessmentFormDialog.class)
					.createNewDialog(AssessmentTypeImpl.FORMAL);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}