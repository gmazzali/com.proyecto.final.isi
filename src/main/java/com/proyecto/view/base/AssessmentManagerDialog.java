package com.proyecto.view.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
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
import com.proyecto.view.login.SelectSubjectDialog;
import com.proyecto.view.material.activity.ActivityListDialog;
import com.proyecto.view.material.assessment.AssessmentSelectTypeDialog;
import com.proyecto.view.material.instrument.InstrumentListDialog;
import com.proyecto.view.material.reactive.ReactiveListDialog;
import com.proyecto.view.rule.RuleSetListDialog;

/**
 * La clase que despliega la ventana principal que despliega el listado de las evaluaciones que tiene asignada la materia que se seleccionó.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class AssessmentManagerDialog extends JFrame {

	private static final long serialVersionUID = 1077719995099120763L;

	/**
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * Las ventanas de administración de materiales.
	 */
	@Autowired
	private ActivityListDialog activityListDialog;

	@Autowired
	private ReactiveListDialog reactiveListDialog;

	@Autowired
	private InstrumentListDialog instrumentListDialog;

	/**
	 * La ventana de selección de materias.
	 */
	@Autowired
	private SelectSubjectDialog selectSubjectDialog;

	/**
	 * La ventana de edición de conjuntos de reglas.
	 */
	@Autowired
	private RuleSetListDialog ruleSetListDialog;

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
	 * La barra de menu.
	 */
	private JMenuBar menuBar;
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
	/**
	 * Los labels de los datos de acceso.
	 */
	private JLabel agentNameLabel;
	private JLabel subjectNameLabel;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana de administración de evaluaciones.
	 */
	public AssessmentManagerDialog() {
		super();
		this.init();
		this.assessments = new ArrayList<Assessment>();
	}

	/**
	 * La ventana de inicialización de la ventana de administración de evaluaciones.
	 */
	private void init() {
		this.setResizable(false);
		this.setTitle(HolderMessage.getMessage("assessment.manager.dialog.title"));
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);
		this.setBounds(100, 100, 695, 534);

		this.menuBar = new JMenuBar();
		this.menuBar.setFont(this.getContentPane().getFont());
		this.menuBar.setBounds(0, 0, 689, 23);

		// Los menu de sistemas.
		JMenu menuSistemas = new JMenu(HolderMessage.getMessage("menu.system"));
		menuSistemas.setFont(this.menuBar.getFont());
		menuSistemas.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuSistemas);

		JMenuItem itemMenuSalir = new JMenuItem(HolderMessage.getMessage("menu.system.exit"));
		itemMenuSalir.setFont(this.menuBar.getFont());
		itemMenuSalir.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.dispose();
			}
		});
		menuSistemas.add(itemMenuSalir);

		JMenuItem itemMenuCambioMateria = new JMenuItem(HolderMessage.getMessage("menu.system.subject.change"));
		itemMenuCambioMateria.setFont(this.menuBar.getFont());
		itemMenuCambioMateria.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuCambioMateria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.changeSubject();
			}
		});
		menuSistemas.add(itemMenuCambioMateria);

		// Los menu de reglas.
		JMenu menuReglas = new JMenu(HolderMessage.getMessage("menu.rules"));
		menuReglas.setFont(this.menuBar.getFont());
		menuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuReglas);

		JMenuItem itemMenuReglas = new JMenuItem(HolderMessage.getMessage("menu.rules.set.manager"));
		itemMenuReglas.setFont(this.menuBar.getFont());
		itemMenuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReglas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.managerRules();
			}
		});
		menuReglas.add(itemMenuReglas);

		// Los menu de los materiales.
		JMenu menuRecursos = new JMenu(HolderMessage.getMessage("menu.material"));
		menuRecursos.setFont(this.menuBar.getFont());
		menuRecursos.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuRecursos);

		JMenuItem itemMenuActividades = new JMenuItem(HolderMessage.getMessage("menu.material.activity.manager"));
		itemMenuActividades.setFont(this.menuBar.getFont());
		itemMenuActividades.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.managerActivities();
			}
		});
		menuRecursos.add(itemMenuActividades);

		JMenuItem itemMenuReactivos = new JMenuItem(HolderMessage.getMessage("menu.material.reactive.manager"));
		itemMenuReactivos.setFont(this.menuBar.getFont());
		itemMenuReactivos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReactivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.managerReactives();
			}
		});
		menuRecursos.add(itemMenuReactivos);

		JMenuItem itemMenuInstrumentos = new JMenuItem(HolderMessage.getMessage("menu.material.instrument.manager"));
		itemMenuInstrumentos.setFont(this.menuBar.getFont());
		itemMenuInstrumentos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuInstrumentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.managerInstruments();
			}
		});
		menuRecursos.add(itemMenuInstrumentos);
		this.getContentPane().add(this.menuBar);

		// El contenido de la ventana.
		JScrollPane assessmentScrollPane = new JScrollPane();
		assessmentScrollPane.setBounds(10, 35, 624, 425);
		this.getContentPane().add(assessmentScrollPane);

		this.assessmentTable = new JTable();
		this.assessmentTable.setShowHorizontalLines(true);
		this.assessmentTable.setFillsViewportHeight(true);
		assessmentScrollPane.setViewportView(this.assessmentTable);
		this.initTableModel();

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(644, 35, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.newAssessment();
			}
		});
		this.getContentPane().add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(644, 77, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.modifyAssessment();
			}
		});
		this.getContentPane().add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteButton.setBounds(644, 119, 35, 35);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.deleteAssessment();
			}
		});
		this.getContentPane().add(this.deleteButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(644, 166, 35, 35);
		this.getContentPane().add(this.progressLabel);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(null);
		dataPanel.setBounds(0, 471, 689, 35);
		this.getContentPane().add(dataPanel);
		dataPanel.setLayout(null);

		JLabel agentLabel = new JLabel(HolderMessage.getMessage("data.agent.name"));
		agentLabel.setBounds(6, 3, 284, 14);
		agentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		dataPanel.add(agentLabel);

		this.agentNameLabel = new JLabel();
		this.agentNameLabel.setForeground(Color.BLUE);
		this.agentNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.agentNameLabel.setBounds(6, 20, 284, 14);
		dataPanel.add(this.agentNameLabel);

		JLabel subjectLabel = new JLabel(HolderMessage.getMessage("data.subject.name"));
		subjectLabel.setBounds(300, 3, 284, 14);
		subjectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 11));
		dataPanel.add(subjectLabel);

		this.subjectNameLabel = new JLabel();
		this.subjectNameLabel.setForeground(Color.BLUE);
		this.subjectNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.subjectNameLabel.setBounds(300, 20, 284, 14);
		dataPanel.add(this.subjectNameLabel);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.menuBar.setEnabled(enabled);

		this.assessmentTable.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cambiar la materia sobre la que vamos a administrar las evaluaciones.
	 */
	private void changeSubject() {
		JDialog dialog = this.selectSubjectDialog.createDialog();
		dialog.setLocationRelativeTo(this);
		this.dispose();
		dialog.setVisible(true);
	}

	/**
	 * La función que permite administrar las reglas que tenemos dentro del sistema.
	 */
	private void managerRules() {
		JDialog dialog = this.ruleSetListDialog.createDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * La función que permite administrar las actividades que tenemos dentro del sistema.
	 */
	private void managerActivities() {
		JDialog dialog = this.activityListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * La función que permite administrar los reactivos que tenemos dentro del sistema.
	 */
	private void managerReactives() {
		JDialog dialog = this.reactiveListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * La función que permite administrar los instrumentos que tenemos dentro del sistema.
	 */
	private void managerInstruments() {
		JDialog dialog = this.instrumentListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
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
		tableModel.addColumn(HolderMessage.getMessage("assessment.manager.dialog.table.column.decription"));
		tableModel.addColumn(HolderMessage.getMessage("assessment.manager.dialog.table.column.date"));

		// Seteamos el modelo a la tabla.
		this.assessmentTable.setModel(tableModel);

		this.assessmentTable.getColumnModel().getColumn(0).setPreferredWidth(400);
		this.assessmentTable.getColumnModel().getColumn(1).setPreferredWidth(200);
	}

	/**
	 * La función encargada de actualizar el listado de las evaluaciones que tenemos dentro de la ventana.
	 */
	private void updateAssessments() {
		// Ejecutamos las acciones antes de procesar.
		this.beforeExecuteProccess();

		// Vaciamos la lista de evaluaciones.
		DefaultTableModel tableModel = (DefaultTableModel) this.assessmentTable.getModel();
		tableModel.getDataVector().clear();

		new Thread() {
			@Override
			public void run() {
				try {
					// Vaciamos el listado.
					AssessmentManagerDialog.this.assessments.clear();
					// Cargamos el listado de las evaluaciones.
					AssessmentManagerDialog.this.assessments.addAll(AssessmentManagerDialog.this.assessmentService
							.findBySubject(AssessmentManagerDialog.this.accessControl.getSubjectSelected()));
					// Cargamos el listado dentro de la tabla.
					AssessmentManagerDialog.this.loadAssessmentTable();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(AssessmentManagerDialog.this, e.getMessage(),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
				} finally {
					AssessmentManagerDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar las evaluaciones.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
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
	 * La función que carga el nombre del agente y de la materia.
	 */
	private void updateAgentData() {
		if (this.accessControl.getAgentLogged() != null) {
			this.agentNameLabel.setText(this.accessControl.getAgentLogged().getName());
		} else {
			this.agentNameLabel.setText(HolderMessage.getMessage("data.agent.null"));
		}

		if (this.accessControl.getSubjectSelected() != null) {
			this.subjectNameLabel.setText(this.accessControl.getSubjectSelected().getName());
		} else {
			this.subjectNameLabel.setText(HolderMessage.getMessage("data.subject.null"));
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

			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("assessment.manager.dialog.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				new Thread() {
					@Override
					public void run() {
						try {
							AssessmentManagerDialog.this.beforeExecuteProccess();
							AssessmentManagerDialog.this.assessmentService.delete(deletedAssessment);
							AssessmentManagerDialog.this.updateAssessments();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(AssessmentManagerDialog.this, e.getMessage(),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							AssessmentManagerDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de crear la ventana principal de la aplicación.
	 * 
	 * @return La ventana principal de la aplicación.
	 */
	public JFrame createFrame() {
		this.updateAssessments();
		this.updateAgentData();
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

			// MultipleChoiceInstrument instrument =
			// HolderApplicationContext.getContext().getBean(MultipleChoiceInstrumentService.class).findById(37);
			// MultipleChoiceInstrumentFormDialog dialog = (MultipleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(MultipleChoiceInstrumentFormDialog.class).createEditDialog(instrument);
			AssessmentManagerDialog dialog = (AssessmentManagerDialog) HolderApplicationContext.getContext().getBean(AssessmentManagerDialog.class)
					.createFrame();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
