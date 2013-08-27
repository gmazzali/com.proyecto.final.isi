package com.proyecto.view.material.assessment;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.common.util.annotations.View;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeByTimeImpl;
import com.proyecto.view.Resources;
import com.toedter.calendar.JDateChooser;

/**
 * La ventana de edición de evaluaciones.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentFormDialog extends JDialog {

	private static final long serialVersionUID = -5386008246325856550L;

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
	 * Create the dialog.
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

		this.assessmentTypeByTimeComboBox = new JComboBox<>();
		this.assessmentTypeByTimeComboBox.setBounds(10, 24, 333, 26);
		this.getContentPane().add(this.assessmentTypeByTimeComboBox);

		JLabel assessmentTimeLabel = new JLabel(HolderMessage.getMessage("assessment.form.label.time"));
		assessmentTimeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentTimeLabel.setBounds(352, 5, 333, 16);
		this.getContentPane().add(assessmentTimeLabel);

		this.assessmentTimeDateChooser = new JDateChooser();
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

		this.activitiesList = new JList<>();
		activitiesScrollPane.setViewportView(this.activitiesList);

		this.addActivityButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.addActivityButton.setBounds(650, 242, 35, 35);
		this.getContentPane().add(this.addActivityButton);

		this.removeActivityButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeActivityButton.setBounds(650, 288, 35, 35);
		this.getContentPane().add(this.removeActivityButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 372, 675, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(558, 385, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(650, 385, 35, 35);
		this.getContentPane().add(this.rejectButton);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(603, 385, 35, 35);
		this.getContentPane().add(this.commitButton);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AssessmentFormDialog dialog = new AssessmentFormDialog();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}