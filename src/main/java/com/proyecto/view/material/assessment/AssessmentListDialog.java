package com.proyecto.view.material.assessment;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.assessment.AssessmentService;
import com.proyecto.view.Resources;

/**
 * La clase que crea la ventana del listado de las evaluaciones que tiene asignada la materia que se seleccionó.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentListDialog extends JDialog {

	private static final long serialVersionUID = 1077719995099120763L;

	/**
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * La ventana de selección de tipo de evaluación para dar de alta una nueva o modificar una.
	 */
	@Autowired
	private AssessmentSelectTypeDialog assessmentSelectTypeDialog;

	/**
	 * El servicio de las evaluaciones.
	 */
	@Autowired
	private AssessmentService assessmentService;

	/**
	 * El listado de las evaluaciones.
	 */
	private final List<Assessment> assessments;
	/**
	 * La tabla de las evaluaciones.
	 */
	private JTable assessmentTable;
	/**
	 * Los botones de acciones.
	 */
	private JButton newButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton closeButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana de administración de evaluaciones.
	 */
	public AssessmentListDialog() {
		super();
		this.init();
		this.assessments = new ArrayList<Assessment>();
	}

	/**
	 * La ventana de inicialización de la ventana de administración de evaluaciones.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 683, 465);

		JLabel assessmentListLabel = new JLabel(HolderMessage.getMessage("assessment.manager.label.assessments"));
		assessmentListLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentListLabel.setBounds(10, 10, 620, 16);
		this.getContentPane().add(assessmentListLabel);

		// El contenido de la ventana.
		JScrollPane assessmentScrollPane = new JScrollPane();
		assessmentScrollPane.setBounds(6, 25, 624, 406);
		this.getContentPane().add(assessmentScrollPane);

		this.assessmentTable = new JTable();
		this.assessmentTable.setBorder(new LineBorder(Color.GRAY));
		this.assessmentTable.setShowHorizontalLines(true);
		this.assessmentTable.setFillsViewportHeight(true);
		assessmentScrollPane.setViewportView(this.assessmentTable);
		this.initTableModel();

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(636, 25, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentListDialog.this.newAssessment();
			}
		});
		this.getContentPane().add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(636, 67, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentListDialog.this.modifyAssessment();
			}
		});
		this.getContentPane().add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteButton.setBounds(636, 109, 35, 35);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentListDialog.this.deleteAssessment();
			}
		});
		this.getContentPane().add(this.deleteButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(636, 349, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setBounds(636, 396, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentListDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.assessmentTable.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cargar el modelo de la tabla
	 */
	private void initTableModel() {
		DefaultTableModel tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -890706019332687145L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn(HolderMessage.getMessage("assessment.manager.table.column.description"));
		tableModel.addColumn(HolderMessage.getMessage("assessment.manager.table.column.date"));

		// Seteamos el modelo a la tabla.
		this.assessmentTable.setModel(tableModel);

		this.assessmentTable.getColumnModel().getColumn(0).setPreferredWidth(400);
		this.assessmentTable.getColumnModel().getColumn(1).setPreferredWidth(200);
	}

	/**
	 * La función encargada de actualizar el listado de las evaluaciones que tenemos dentro de la ventana.
	 */
	private void updateAssessments() {
		new Thread() {
			@Override
			public void run() {
				try {
					// Ejecutamos las acciones antes de procesar.
					AssessmentListDialog.this.beforeExecuteProccess();

					// Vaciamos la lista de evaluaciones.
					DefaultTableModel tableModel = (DefaultTableModel) AssessmentListDialog.this.assessmentTable.getModel();
					tableModel.getDataVector().clear();

					// Vaciamos el listado.
					AssessmentListDialog.this.assessments.clear();

					// Cargamos el listado de las evaluaciones.
					AssessmentListDialog.this.assessments.addAll(AssessmentListDialog.this.assessmentService
							.findBySubject(AssessmentListDialog.this.accessControl.getSubjectSelected()));

					// Cargamos el listado dentro de la tabla.
					AssessmentListDialog.this.loadAssessmentTable();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(AssessmentListDialog.this, HolderMessage.getMessage("assessment.manager.load.assessments.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
				} finally {
					AssessmentListDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar las evaluaciones.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(gif);
	}

	/*
	 * La función después de procesar las evaluaciones.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de cargar dentro de la tabla el listado de las evaluaciones.
	 */
	private void loadAssessmentTable() {
		// Recuperamos el modelo de la tabla.
		DefaultTableModel tableModel = (DefaultTableModel) this.assessmentTable.getModel();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

		// Volvemos a cargar el modelo.
		for (Assessment assessment : this.assessments) {
			String[] row = new String[2];

			row[0] = assessment.getDescription();
			row[1] = dateFormatter.format(assessment.getAssessmentDate());

			tableModel.addRow(row);
		}
	}

	/**
	 * La función encargada de abrir una ventana de selección de tipo de evaluación.
	 */
	private void newAssessment() {
		// Abrimos la ventana de selección de tipo de evaluación.
		JDialog dialog = this.assessmentSelectTypeDialog.createNewDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// Actualizamos el listado de las evaluaciones.
		this.updateAssessments();
	}

	/**
	 * La función encargada de modificar una evaluación seleccionada dentro de la tabla.
	 */
	private void modifyAssessment() {
		// Tomamos el índice de la tabla que tenemos seleccionado.
		Integer assessmentIndex = this.assessmentTable.getSelectedRow();

		// Si tenemos algo seleccionado.
		if (assessmentIndex >= 0) {
			// Creamos la ventana para modificar instrumento.
			this.assessmentSelectTypeDialog.createEditDialog(this.assessments.get(this.assessmentTable.convertRowIndexToModel(assessmentIndex)));
			this.updateAssessments();
		}
	}

	/**
	 * La función encargada de eliminar una evaluación seleccionada dentro de la tabla.
	 */
	private void deleteAssessment() {
		// Tomamos el índice de la tabla que tenemos seleccionado.
		Integer assessmentIndex = this.assessmentTable.getSelectedRow();

		// Si tenemos algo seleccionado.
		if (assessmentIndex >= 0) {
			final Assessment deletedAssessment = this.assessments.get(this.assessmentTable.convertRowIndexToModel(assessmentIndex));

			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("assessment.manager.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				new Thread() {
					@Override
					public void run() {
						try {
							AssessmentListDialog.this.beforeExecuteProccess();
							AssessmentListDialog.this.assessmentService.delete(deletedAssessment);
							AssessmentListDialog.this.updateAssessments();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(AssessmentListDialog.this, HolderMessage.getMessage("assessment.manager.delete.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							AssessmentListDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de inicializar la ventana de administración de evaluaciones.
	 * 
	 * @return La ventana de administración de evaluaciones.
	 */
	public AssessmentListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("assessment.manager.title"));

		this.updateAssessments();

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

			AssessmentListDialog dialog = HolderApplicationContext.getContext().getBean(AssessmentListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
