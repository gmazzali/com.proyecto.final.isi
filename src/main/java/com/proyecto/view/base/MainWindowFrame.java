package com.proyecto.view.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.security.AccessControl;
import com.proyecto.service.material.assessment.AssessmentService;
import com.proyecto.service.rule.RuleSetService;
import com.proyecto.view.Resources;
import com.proyecto.view.login.SelectSubjectDialog;
import com.proyecto.view.material.activity.ActivityListDialog;
import com.proyecto.view.material.assessment.AssessmentListDialog;
import com.proyecto.view.material.instrument.InstrumentListDialog;
import com.proyecto.view.material.reactive.ReactiveListDialog;
import com.proyecto.view.rule.RuleSetListDialog;

/**
 * La clase que nos permite crear la ventana principal de la aplicación.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class MainWindowFrame extends JFrame {

	private static final long serialVersionUID = -7170869916954032109L;

	/**
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * Las ventanas de administración de materiales.
	 */
	@Autowired
	private AssessmentListDialog assessmentListDialog;

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
	 * El servicio de las evaluaciones.
	 */
	@Autowired
	private AssessmentService assessmentService;

	/**
	 * El servicio de los conjuntos de reglas.
	 */
	@Autowired
	private RuleSetService ruleSetService;

	/**
	 * La barra de menu.
	 */
	private JMenuBar menuBar;
	/**
	 * Los labels de los datos de login.
	 */
	private JLabel agentNameLabel;
	private JLabel subjectNameLabel;
	/**
	 * Las listas de evaluaciones y de conjuntos de reglas.
	 */
	private JList<Assessment> assessmentList;
	private JList<RuleSet> ruleSetList;
	/**
	 * El area donde vamos a cargar el resultado de la validación de la evaluación.
	 */
	private JTextArea resultTextArea;
	/**
	 * Los botones de acciones.
	 */
	private JButton assessmentManagerButton;
	private JButton ruleSetManagerButton;
	private JButton evaluateButton;
	private JButton clearResultButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * El contador de procesos que corren de fondo.
	 */
	private Integer taskCount = 0;

	/**
	 * Constructor de la ventana principal.
	 */
	public MainWindowFrame() {
		super();
		this.init();
	}

	/**
	 * La función de inicialización de los componentes de la ventana.
	 */
	private void init() {
		this.setResizable(false);
		this.setBounds(100, 100, 898, 569);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		this.menuBar = new JMenuBar();
		this.menuBar.setFont(this.getContentPane().getFont());
		this.menuBar.setBounds(0, 0, 894, 23);
		this.getContentPane().add(this.menuBar);

		JMenu menuSistemas = new JMenu(HolderMessage.getMessage("menu.system"));
		menuSistemas.setHorizontalAlignment(SwingConstants.LEFT);
		menuSistemas.setFont(this.menuBar.getFont());
		this.menuBar.add(menuSistemas);

		JMenuItem itemMenuCambioMateria = new JMenuItem(HolderMessage.getMessage("menu.system.subject.change"));
		itemMenuCambioMateria.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuCambioMateria.setFont(this.menuBar.getFont());
		itemMenuCambioMateria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.changeSubject();
			}
		});
		menuSistemas.add(itemMenuCambioMateria);

		JMenuItem itemMenuSalir = new JMenuItem(HolderMessage.getMessage("menu.system.exit"));
		itemMenuSalir.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuSalir.setFont(this.menuBar.getFont());
		itemMenuSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.closeApp();
			}
		});
		menuSistemas.add(itemMenuSalir);

		JMenu menuReglas = new JMenu(HolderMessage.getMessage("menu.rules"));
		menuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		menuReglas.setFont(this.menuBar.getFont());
		this.menuBar.add(menuReglas);

		JMenuItem itemMenuReglas = new JMenuItem(HolderMessage.getMessage("menu.rules.set.manager"));
		itemMenuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReglas.setFont(this.menuBar.getFont());
		itemMenuReglas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerRules();
			}
		});
		menuReglas.add(itemMenuReglas);

		JMenu menuRecursos = new JMenu(HolderMessage.getMessage("menu.material"));
		menuRecursos.setHorizontalAlignment(SwingConstants.LEFT);
		menuRecursos.setFont(this.menuBar.getFont());
		this.menuBar.add(menuRecursos);

		JMenuItem itemMenuEvaluaciones = new JMenuItem(HolderMessage.getMessage("menu.material.assessment.manager"));
		itemMenuEvaluaciones.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuEvaluaciones.setFont(this.menuBar.getFont());
		itemMenuEvaluaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerAssessments();
			}
		});
		menuRecursos.add(itemMenuEvaluaciones);

		JMenuItem itemMenuActividades = new JMenuItem(HolderMessage.getMessage("menu.material.activity.manager"));
		itemMenuActividades.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuActividades.setFont(this.menuBar.getFont());
		itemMenuActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerActivities();
			}
		});
		menuRecursos.add(itemMenuActividades);

		JMenuItem itemMenuReactivos = new JMenuItem(HolderMessage.getMessage("menu.material.reactive.manager"));
		itemMenuReactivos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReactivos.setFont(this.menuBar.getFont());
		itemMenuReactivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerReactives();
			}
		});
		menuRecursos.add(itemMenuReactivos);

		JMenuItem itemMenuInstrumentos = new JMenuItem(HolderMessage.getMessage("menu.material.instrument.manager"));
		itemMenuInstrumentos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuInstrumentos.setFont(this.menuBar.getFont());
		itemMenuInstrumentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerInstruments();
			}
		});
		menuRecursos.add(itemMenuInstrumentos);

		JLabel assessmentListLabel = new JLabel(HolderMessage.getMessage("main.window.list.assessment.label"));
		assessmentListLabel.setFont(new Font("Arial", Font.BOLD, 11));
		assessmentListLabel.setBounds(10, 35, 400, 16);
		this.getContentPane().add(assessmentListLabel);

		JScrollPane assessmentScrollPane = new JScrollPane();
		assessmentScrollPane.setBounds(10, 52, 400, 200);
		this.getContentPane().add(assessmentScrollPane);

		this.assessmentList = new JList<Assessment>();
		this.assessmentList.setModel(new DefaultListModel<Assessment>());
		assessmentScrollPane.setViewportView(this.assessmentList);

		this.assessmentManagerButton = new JButton();
		this.assessmentManagerButton.setBounds(422, 52, 35, 35);
		this.getContentPane().add(this.assessmentManagerButton);

		JLabel ruleSetListLabel = new JLabel(HolderMessage.getMessage("main.window.list.rule.set.label"));
		ruleSetListLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetListLabel.setBounds(10, 264, 400, 16);
		this.getContentPane().add(ruleSetListLabel);

		JScrollPane ruleSetScrollPane = new JScrollPane();
		ruleSetScrollPane.setBounds(10, 285, 400, 200);
		this.getContentPane().add(ruleSetScrollPane);

		this.ruleSetList = new JList<RuleSet>();
		this.ruleSetList.setModel(new DefaultListModel<RuleSet>());
		ruleSetScrollPane.setViewportView(this.ruleSetList);

		this.ruleSetManagerButton = new JButton();
		this.ruleSetManagerButton.setBounds(422, 285, 35, 35);
		this.getContentPane().add(this.ruleSetManagerButton);

		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(469, 23, 2, 476);
		this.getContentPane().add(separator1);

		JLabel resultLabel = new JLabel(HolderMessage.getMessage("main.window.list.result.label"));
		resultLabel.setFont(new Font("Arial", Font.BOLD, 11));
		resultLabel.setBounds(483, 35, 400, 16);
		this.getContentPane().add(resultLabel);

		JScrollPane resultScrollPane = new JScrollPane();
		resultScrollPane.setBounds(483, 52, 400, 386);
		this.getContentPane().add(resultScrollPane);

		this.resultTextArea = new JTextArea();
		this.resultTextArea.setWrapStyleWord(true);
		this.resultTextArea.setLineWrap(true);
		this.resultTextArea.setEditable(false);
		resultScrollPane.setViewportView(this.resultTextArea);

		this.clearResultButton = new JButton(Resources.ERASE_ICON);
		this.clearResultButton.setBounds(530, 450, 35, 35);
		this.clearResultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.clearResults();
			}
		});
		this.getContentPane().add(this.clearResultButton);

		this.evaluateButton = new JButton(Resources.PROCCESS_ICON);
		this.evaluateButton.setBounds(483, 450, 35, 35);
		this.getContentPane().add(this.evaluateButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(848, 450, 35, 35);
		this.getContentPane().add(this.progressLabel);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(0, 497, 894, 2);
		this.getContentPane().add(separator2);

		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(null);
		dataPanel.setBounds(10, 501, 707, 35);
		this.getContentPane().add(dataPanel);

		JLabel agentLabel = new JLabel(HolderMessage.getMessage("data.agent.name"));
		agentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		agentLabel.setBounds(6, 6, 341, 14);
		dataPanel.add(agentLabel);

		this.agentNameLabel = new JLabel(HolderMessage.getMessage("main.window.data.agent.null"));
		this.agentNameLabel.setBounds(6, 23, 341, 14);
		this.agentNameLabel.setForeground(Color.BLUE);
		this.agentNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		dataPanel.add(this.agentNameLabel);

		JLabel subjectLabel = new JLabel(HolderMessage.getMessage("data.subject.name"));
		subjectLabel.setBounds(359, 6, 341, 14);
		subjectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 11));
		dataPanel.add(subjectLabel);

		this.subjectNameLabel = new JLabel(HolderMessage.getMessage("main.window.data.subject.null"));
		this.subjectNameLabel.setBounds(359, 23, 341, 14);
		this.subjectNameLabel.setForeground(Color.BLUE);
		this.subjectNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		dataPanel.add(this.subjectNameLabel);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.menuBar.setEnabled(enabled);

		this.assessmentList.setEnabled(enabled);
		this.ruleSetList.setEnabled(enabled);

		this.assessmentManagerButton.setEnabled(enabled);
		this.ruleSetManagerButton.setEnabled(enabled);
		this.evaluateButton.setEnabled(enabled);
		this.clearResultButton.setEnabled(enabled);
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
	 * La función que permite confirmar la salida del sistema.
	 */
	private void closeApp() {
		if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("main.window.confirm.exit"),
				HolderMessage.getMessage("dialog.message.comfirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * La función que permite administrar las reglas que tenemos dentro del sistema.
	 */
	private void managerRules() {
		JDialog dialog = this.ruleSetListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * La función que permite administrar las evaluaciones que tenemos dentro del sistema.
	 */
	private void managerAssessments() {
		JDialog dialog = this.assessmentListDialog.createCrudDialog();
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
	 * La función encargada de actualizar el listado de las evaluaciones que tenemos dentro de la ventana creando un proceso secundario.
	 */
	private void updateAssessmentList() {
		// Ejecutamos las acciones antes de procesar.
		this.beforeExecuteProccess();

		new Thread() {
			@Override
			public void run() {
				try {
					// Sumamos 1 la cantidad de procesos.
					MainWindowFrame.this.taskCount++;

					// Cargamos el listado dentro de la tabla.
					DefaultListModel<Assessment> model = new DefaultListModel<Assessment>();
					List<Assessment> assessments = MainWindowFrame.this.assessmentService.findBySubject(MainWindowFrame.this.accessControl
							.getSubjectSelected());

					// Cargamos el listado de las evaluaciones que filtramos.
					for (Assessment assessment : assessments) {
						model.addElement(assessment);
					}
					MainWindowFrame.this.assessmentList.setModel(model);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainWindowFrame.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					// Restamos 1 la cantidad de procesos.
					MainWindowFrame.this.taskCount--;
					MainWindowFrame.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de actualizar el listado de las reglas que tenemos dentro de la ventana.
	 */
	private void updateRuleSetList() {
		// Ejecutamos las acciones antes de procesar.
		this.beforeExecuteProccess();

		new Thread() {
			@Override
			public void run() {
				try {
					// Sumamos 1 la cantidad de procesos.
					MainWindowFrame.this.taskCount++;

					// Cargamos el listado dentro de la tabla.
					DefaultListModel<RuleSet> model = new DefaultListModel<RuleSet>();
					List<RuleSet> ruleSets = MainWindowFrame.this.ruleSetService.findAll();
					// TODO gmazzali Hacer lo de la carga de los conjuntos por materias.

					// Cargamos el listado de los conjuntos que filtramos.
					for (RuleSet ruleSet : ruleSets) {
						model.addElement(ruleSet);
					}
					MainWindowFrame.this.ruleSetList.setModel(model);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainWindowFrame.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
							JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					// Restamos 1 la cantidad de procesos.
					MainWindowFrame.this.taskCount--;
					MainWindowFrame.this.afterExecuteProccess();
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
		if (this.taskCount <= 0) {
			this.setEnabled(true);
			this.progressLabel.setIcon(null);
		}
	}

	/**
	 * La función encargada de limpiar el área donde tenemos el resultado de la ejecución de la validación de las evaluaciones.
	 */
	private void clearResults() {
		this.resultTextArea.setText("");
	}

	/**
	 * La función encargada de crear la ventana principal de la aplicación.
	 * 
	 * @return La ventana principal de la aplicación cargada.
	 */
	public MainWindowFrame createFrame() {
		this.setTitle(HolderMessage.getMessage("main.window.title"));

		this.updateAgentData();
		this.updateAssessmentList();
		this.updateRuleSetList();

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

			MainWindowFrame dialog = HolderApplicationContext.getContext().getBean(MainWindowFrame.class).createFrame();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
