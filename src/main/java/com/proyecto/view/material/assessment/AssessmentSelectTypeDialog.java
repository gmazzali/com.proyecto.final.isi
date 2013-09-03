package com.proyecto.view.material.assessment;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.assessment.type.AssessmentType;
import com.proyecto.model.material.assessment.type.impl.AssessmentTypeImpl;
import com.proyecto.view.Resources;

/**
 * La clase que nos permite crear una ventana de selección de un tipo de evaluación que vamos a crear de acuerdo al contenido de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentSelectTypeDialog extends JDialog {

	private static final long serialVersionUID = -6387450303106849940L;

	/**
	 * La ventana de edición de una evaluación.
	 */
	@Autowired
	private AssessmentFormDialog assessmentFormDialog;

	/**
	 * La evaluación que vamos a editar y los tipos de evaluaciones.
	 */
	private Assessment assessment;
	private AssessmentType assessmentType;

	/**
	 * El combo de tipo de evaluación.
	 */
	// private JComboBox<AssessmentType> assessmentTypeComboBox;
	private JComboBox<AssessmentType> assessmentTypeComboBox;

	/**
	 * Constructor de la ventana de selección de un tipo de evaluación.
	 */
	public AssessmentSelectTypeDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar los componentes de la ventana.
	 */
	private void init() {
		this.setBounds(100, 100, 450, 155);
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));

		JLabel assessmentTypeLabel = new JLabel(HolderMessage.getMessage("assessment.form.select.type.label"));
		assessmentTypeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentTypeLabel.setBounds(10, 11, 424, 15);
		this.getContentPane().add(assessmentTypeLabel);

		this.assessmentTypeComboBox = new JComboBox<AssessmentType>();
		this.assessmentTypeComboBox.setBounds(10, 37, 424, 30);
		this.getContentPane().add(this.assessmentTypeComboBox);
		this.initAssessmentTypeComboBox();

		JButton commitButton = new JButton(Resources.COMMIT_ICON);
		commitButton.setBounds(354, 79, 35, 35);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentSelectTypeDialog.this.createNewAssessment();
			}
		});
		this.getContentPane().add(commitButton);

		JButton rejectButton = new JButton(Resources.CLOSE_ICON);
		rejectButton.setBounds(399, 79, 35, 35);
		rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentSelectTypeDialog.this.dispose();
			}
		});
		this.getContentPane().add(rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.assessmentTypeComboBox.setEnabled(enabled);
	}

	/**
	 * La función de carga del combo de tipo de evaluación.
	 */
	private void initAssessmentTypeComboBox() {
		this.assessmentTypeComboBox.removeAllItems();

		for (AssessmentType type : AssessmentTypeImpl.values()) {
			this.assessmentTypeComboBox.addItem(type);
		}
	}

	/**
	 * La función encargada de dar de alta una nueva evaluación de acuerdo a lo que tenemos seleccionado dentro del combo de tipo.
	 */
	private void createNewAssessment() {
		// Si tenemos algo seleccionado en el combo, lo creamos.
		if (this.assessmentTypeComboBox.getSelectedItem() != null) {

			// Creamos el listado de los tipos de evaluaciones.
			this.assessmentType = (AssessmentType) this.assessmentTypeComboBox.getSelectedItem();

			// Abrimos una ventana de alta de una evaluación.
			AssessmentFormDialog dialog = this.assessmentFormDialog.createNewDialog(this.assessmentType);
			dialog.setLocationRelativeTo(this);
			this.dispose();
			dialog.setVisible(true);
		}
	}

	/**
	 * La función encargada de inicializar la ventana de selección del tipo de evaluación para dar de alta una nueva.
	 * 
	 * @return La ventana de selección del tipo de evaluación.
	 */
	public AssessmentSelectTypeDialog createNewDialog() {
		this.setTitle(HolderMessage.getMessage("assessment.form.select.title"));

		this.assessmentTypeComboBox.setSelectedIndex(-1);

		return this;
	}

	/**
	 * La función encargada de crear una ventana de edición de una evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que queremos editar.
	 */
	public void createEditDialog(Assessment assessment) {
		if (assessment != null) {
			this.assessment = assessment;
			this.assessmentType = assessment.getAssessmentyType();

			JDialog dialog = this.assessmentFormDialog.createEditDialog(this.assessment, this.assessmentType);
			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
		}
	}
}