package com.proyecto.view.base;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.agent.Subject;
import com.proyecto.security.AccessControl;

/**
 * La clase que crea la ventana donde van a desplegarse todas las materias a las que se encuentra inscrito el agente que esta logueado en el sistema
 * para que pueda seleccionar la materia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class SelectSubjectDialog extends JDialog {

	private static final long serialVersionUID = 1264943776318050914L;

	@Autowired
	private AssessmentManagerDialog assessmentManagerDialog;

	@Autowired
	private AccessControl accessControl;

	/**
	 * El combo de las materias.
	 */
	private JComboBox<Subject> subjectComboBox;

	/**
	 * Constructor del dialogo de selección de materia.
	 */
	public SelectSubjectDialog() {
		super();
		this.setResizable(false);
		this.init();
	}

	/**
	 * La función encargada de inicializar el dialogo de selección de materia.
	 */
	private void init() {
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setTitle("Materia a Seleccionar");
		this.setBounds(100, 100, 393, 146);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel seleccionLabel = new JLabel("Seleccionar Una Materia");
		seleccionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		seleccionLabel.setBounds(10, 11, 131, 14);
		contentPanel.add(seleccionLabel);

		this.subjectComboBox = new JComboBox<Subject>();
		this.subjectComboBox.setBounds(10, 36, 367, 29);
		contentPanel.add(this.subjectComboBox);

		JButton commitButton = new JButton("Aceptar");
		commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		commitButton.setBounds(167, 76, 100, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectSubjectDialog.this.selectSubject();
			}
		});
		contentPanel.add(commitButton);
		this.getRootPane().setDefaultButton(commitButton);

		JButton rejectButton = new JButton("Cancelar");
		rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		rejectButton.setBounds(277, 76, 100, 30);
		rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SelectSubjectDialog.this.dispose();
			}
		});
		contentPanel.add(rejectButton);
	}

	/**
	 * La función encargada de inicializar el combo de materias.
	 */
	private void loadSubjects() {
		this.subjectComboBox.removeAllItems();
		for (Subject s : this.accessControl.getAgentLogged().getSubjects()) {
			this.subjectComboBox.addItem(s);
		}
		this.subjectComboBox.setSelectedIndex(-1);
	}

	/**
	 * La función encargada se tomar la materia seleccionada y arrancar la aplicación.
	 */
	private void selectSubject() {
		if (this.subjectComboBox.getSelectedItem() != null) {
			Subject subject = (Subject) this.subjectComboBox.getSelectedItem();
			this.accessControl.setSubjectSelected(subject);
			this.dispose();

			// Abrimos la ventana de control de evaluaciones.
			JFrame frame = this.assessmentManagerDialog.createFrame();
			frame.setLocationRelativeTo(this);
			frame.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(this, HolderMessage.getMessage("subject.select.required"), "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * La función encargada de crear la ventana de dialogo para seleccionar la materia.
	 * 
	 * @return La ventana para seleccionar la materia a la que vamos conectarnos.
	 */
	public SelectSubjectDialog createDialog() {
		this.loadSubjects();
		return this;
	}
}
