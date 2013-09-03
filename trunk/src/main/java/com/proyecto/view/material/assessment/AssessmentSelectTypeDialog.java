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
 * La clase que nos permite crear una ventana de selecci�n de un tipo de evaluaci�n que vamos a crear de acuerdo al contenido de la misma.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentSelectTypeDialog extends JDialog {

	private static final long serialVersionUID = -6387450303106849940L;

	/**
	 * La ventana de edici�n de una evaluaci�n.
	 */
	@Autowired
	private AssessmentFormDialog assessmentFormDialog;

	/**
	 * La evaluaci�n que vamos a editar y los tipos de evaluaciones.
	 */
	private Assessment assessment;
	private AssessmentType assessmentType;

	/**
	 * El combo de tipo de evaluaci�n.
	 */
	// private JComboBox<AssessmentType> assessmentTypeComboBox;
	private JComboBox<AssessmentType> assessmentTypeComboBox;

	/**
	 * Constructor de la ventana de selecci�n de un tipo de evaluaci�n.
	 */
	public AssessmentSelectTypeDialog() {
		super();
		this.init();
	}

	/**
	 * La funci�n encargada de inicializar los componentes de la ventana.
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
	 * La funci�n de carga del combo de tipo de evaluaci�n.
	 */
	private void initAssessmentTypeComboBox() {
		this.assessmentTypeComboBox.removeAllItems();

		for (AssessmentType type : AssessmentTypeImpl.values()) {
			this.assessmentTypeComboBox.addItem(type);
		}
	}

	/**
	 * La funci�n encargada de dar de alta una nueva evaluaci�n de acuerdo a lo que tenemos seleccionado dentro del combo de tipo.
	 */
	private void createNewAssessment() {
		// Si tenemos algo seleccionado en el combo, lo creamos.
		if (this.assessmentTypeComboBox.getSelectedItem() != null) {

			// Creamos el listado de los tipos de evaluaciones.
			this.assessmentType = (AssessmentType) this.assessmentTypeComboBox.getSelectedItem();

			// Abrimos una ventana de alta de una evaluaci�n.
			AssessmentFormDialog dialog = this.assessmentFormDialog.createNewDialog(this.assessmentType);
			dialog.setLocationRelativeTo(this);
			this.dispose();
			dialog.setVisible(true);
		}
	}

	/**
	 * La funci�n encargada de inicializar la ventana de selecci�n del tipo de evaluaci�n para dar de alta una nueva.
	 * 
	 * @return La ventana de selecci�n del tipo de evaluaci�n.
	 */
	public AssessmentSelectTypeDialog createNewDialog() {
		this.setTitle(HolderMessage.getMessage("assessment.form.select.title"));

		this.assessmentTypeComboBox.setSelectedIndex(-1);

		return this;
	}

	/**
	 * La funci�n encargada de crear una ventana de edici�n de una evaluaci�n.
	 * 
	 * @param assessment
	 *            La evaluaci�n que queremos editar.
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