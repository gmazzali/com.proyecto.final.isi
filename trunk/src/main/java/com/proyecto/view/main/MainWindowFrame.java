package com.proyecto.view.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.DefaultCaret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.ontology.task.ValidateAssessmentTask;
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

	private static final Logger logger = LoggerFactory.getLogger(MainWindowFrame.class);

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
	 * El proceso de validación de las evaluaciones.
	 */
	@Autowired
	private ValidateAssessmentTask validateAssessmentTask;

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
	 * El area donde vamos a cargar el resultado de la validación de la evaluación y el buffer donde se va a cargar el resultado.
	 */
	private JTextArea resultTextArea;
	private final StringBuffer resultStringBuffer = new StringBuffer();
	/**
	 * Los botones de acciones.
	 */
	private JButton assessmentManagerButton;
	private JButton ruleSetManagerButton;
	private JButton evaluateButton;
	private JButton clearResultButton;
	/**
	 * Los labels de progreso.
	 */
	private JLabel assessmentProgressLabel;
	private JLabel ruleSetProgressLabel;
	private JLabel evaluateProgressLabel;

	/**
	 * El contador de procesos que corren de fondo.
	 */
	private Integer taskCount = 0;

	/**
	 * Los procesos en segundo plano.
	 */
	private Thread updateAssessmentsTask;
	private Thread updateRuleSetTask;
	private Thread updateResultsTask;

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
		this.setBounds(100, 100, 1000, 562);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MainWindowFrame.this.stopBackgroundTask();
			}
		});

		this.menuBar = new JMenuBar() {

			private static final long serialVersionUID = 1L;

			@Override
			public void setEnabled(boolean enabled) {
				for (int i = 0; i < this.getMenuCount(); i++) {
					JMenu menu = this.getMenu(i);
					menu.setEnabled(enabled);
				}
			};
		};
		this.menuBar.setFont(this.getContentPane().getFont());
		this.menuBar.setBounds(0, 0, 994, 23);
		this.getContentPane().add(this.menuBar);

		JMenu menuSistemas = new JMenu(HolderMessage.getMessage("main.menu.system"));
		menuSistemas.setHorizontalAlignment(SwingConstants.LEFT);
		menuSistemas.setFont(this.menuBar.getFont());
		this.menuBar.add(menuSistemas);

		JMenuItem itemMenuCambioMateria = new JMenuItem(HolderMessage.getMessage("main.menu.system.subject.change"));
		itemMenuCambioMateria.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuCambioMateria.setFont(this.menuBar.getFont());
		itemMenuCambioMateria.setMnemonic(KeyEvent.VK_F3);
		itemMenuCambioMateria.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.changeSubject();
			}
		});
		menuSistemas.add(itemMenuCambioMateria);

		JMenuItem itemMenuSalir = new JMenuItem(HolderMessage.getMessage("main.menu.system.exit"));
		itemMenuSalir.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuSalir.setFont(this.menuBar.getFont());
		itemMenuSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.closeApp();
			}
		});
		menuSistemas.add(itemMenuSalir);

		JMenu menuReglas = new JMenu(HolderMessage.getMessage("main.menu.rules"));
		menuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		menuReglas.setFont(this.menuBar.getFont());
		this.menuBar.add(menuReglas);

		JMenuItem itemMenuReglas = new JMenuItem(HolderMessage.getMessage("main.menu.rules.set.manager"));
		itemMenuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReglas.setFont(this.menuBar.getFont());
		itemMenuReglas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerRuleSets();
			}
		});
		menuReglas.add(itemMenuReglas);

		JMenu menuRecursos = new JMenu(HolderMessage.getMessage("main.menu.material"));
		menuRecursos.setHorizontalAlignment(SwingConstants.LEFT);
		menuRecursos.setFont(this.menuBar.getFont());
		this.menuBar.add(menuRecursos);

		JMenuItem itemMenuEvaluaciones = new JMenuItem(HolderMessage.getMessage("main.menu.material.assessment.manager"));
		itemMenuEvaluaciones.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuEvaluaciones.setFont(this.menuBar.getFont());
		itemMenuEvaluaciones.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerAssessments();
			}
		});
		menuRecursos.add(itemMenuEvaluaciones);

		JMenuItem itemMenuActividades = new JMenuItem(HolderMessage.getMessage("main.menu.material.activity.manager"));
		itemMenuActividades.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuActividades.setFont(this.menuBar.getFont());
		itemMenuActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerActivities();
			}
		});
		menuRecursos.add(itemMenuActividades);

		JMenuItem itemMenuReactivos = new JMenuItem(HolderMessage.getMessage("main.menu.material.reactive.manager"));
		itemMenuReactivos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReactivos.setFont(this.menuBar.getFont());
		itemMenuReactivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerReactives();
			}
		});
		menuRecursos.add(itemMenuReactivos);

		JMenuItem itemMenuInstrumentos = new JMenuItem(HolderMessage.getMessage("main.menu.material.instrument.manager"));
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
		assessmentScrollPane.setBounds(6, 52, 400, 200);
		this.getContentPane().add(assessmentScrollPane);

		this.assessmentList = new JList<Assessment>();
		this.assessmentList.setBorder(new LineBorder(Color.GRAY));
		this.assessmentList.setModel(new DefaultListModel<Assessment>());
		assessmentScrollPane.setViewportView(this.assessmentList);

		this.assessmentManagerButton = new JButton(Resources.CRUD_ICON);
		this.assessmentManagerButton.setBounds(412, 52, 35, 35);
		this.assessmentManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerAssessments();
			}
		});
		this.getContentPane().add(this.assessmentManagerButton);

		JButton printAssessmentButton = new JButton(Resources.REPORT_PDF_ICON);
		printAssessmentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO mromitti Hacer lo de la carga de la evaluación al servicio de creación del PDF.
			}
		});
		printAssessmentButton.setBounds(412, 99, 35, 35);
		this.getContentPane().add(printAssessmentButton);

		JLabel ruleSetListLabel = new JLabel(HolderMessage.getMessage("main.window.list.ruleset.label"));
		ruleSetListLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetListLabel.setBounds(10, 264, 400, 16);
		this.getContentPane().add(ruleSetListLabel);

		JScrollPane ruleSetScrollPane = new JScrollPane();
		ruleSetScrollPane.setBounds(6, 281, 400, 200);
		this.getContentPane().add(ruleSetScrollPane);

		this.ruleSetList = new JList<RuleSet>();
		this.ruleSetList.setBorder(new LineBorder(Color.GRAY));
		this.ruleSetList.setModel(new DefaultListModel<RuleSet>());
		ruleSetScrollPane.setViewportView(this.ruleSetList);

		this.ruleSetManagerButton = new JButton(Resources.CRUD_ICON);
		this.ruleSetManagerButton.setBounds(412, 281, 35, 35);
		this.ruleSetManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.managerRuleSets();
			}
		});
		this.getContentPane().add(this.ruleSetManagerButton);

		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		separator1.setBounds(455, 24, 2, 468);
		this.getContentPane().add(separator1);

		JLabel resultLabel = new JLabel(HolderMessage.getMessage("main.window.list.result.label"));
		resultLabel.setFont(new Font("Arial", Font.BOLD, 11));
		resultLabel.setBounds(470, 35, 518, 16);
		this.getContentPane().add(resultLabel);

		JScrollPane resultScrollPane = new JScrollPane();
		resultScrollPane.setBounds(467, 52, 521, 386);
		this.getContentPane().add(resultScrollPane);

		this.resultTextArea = new JTextArea();
		this.resultTextArea.setBorder(new LineBorder(Color.GRAY));
		this.resultTextArea.setEditable(false);
		DefaultCaret caret = (DefaultCaret) this.resultTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		resultScrollPane.setViewportView(this.resultTextArea);

		this.clearResultButton = new JButton(Resources.ERASE_ICON);
		this.clearResultButton.setBounds(514, 446, 35, 35);
		this.clearResultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.clearResults();
			}
		});
		this.getContentPane().add(this.clearResultButton);

		this.evaluateButton = new JButton(Resources.PROCCESS_ICON);
		this.evaluateButton.setBounds(467, 446, 35, 35);
		this.evaluateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindowFrame.this.evaluateAssessment();
			}
		});
		this.getContentPane().add(this.evaluateButton);

		this.assessmentProgressLabel = new JLabel();
		this.assessmentProgressLabel.setBounds(412, 217, 35, 35);
		this.getContentPane().add(this.assessmentProgressLabel);

		this.ruleSetProgressLabel = new JLabel();
		this.ruleSetProgressLabel.setBounds(412, 443, 35, 35);
		this.getContentPane().add(this.ruleSetProgressLabel);

		this.evaluateProgressLabel = new JLabel();
		this.evaluateProgressLabel.setBounds(561, 446, 35, 35);
		this.getContentPane().add(this.evaluateProgressLabel);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(0, 490, 994, 2);
		this.getContentPane().add(separator2);

		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(null);
		dataPanel.setBounds(0, 493, 707, 35);
		this.getContentPane().add(dataPanel);

		JLabel agentLabel = new JLabel(HolderMessage.getMessage("main.data.agent"));
		agentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentLabel.setFont(new Font("Arial", Font.BOLD, 11));
		agentLabel.setBounds(6, 6, 341, 14);
		dataPanel.add(agentLabel);

		this.agentNameLabel = new JLabel(HolderMessage.getMessage("main.data.agent.null"));
		this.agentNameLabel.setBounds(6, 23, 341, 14);
		this.agentNameLabel.setForeground(Color.BLUE);
		this.agentNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		dataPanel.add(this.agentNameLabel);

		JLabel subjectLabel = new JLabel(HolderMessage.getMessage("main.data.subject"));
		subjectLabel.setBounds(359, 6, 341, 14);
		subjectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 11));
		dataPanel.add(subjectLabel);

		this.subjectNameLabel = new JLabel(HolderMessage.getMessage("main.data.subject.null"));
		this.subjectNameLabel.setBounds(359, 23, 341, 14);
		this.subjectNameLabel.setForeground(Color.BLUE);
		this.subjectNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		dataPanel.add(this.subjectNameLabel);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.menuBar.setEnabled(enabled);

		this.assessmentList.setEnabled(enabled);
		this.assessmentManagerButton.setEnabled(enabled);

		this.ruleSetList.setEnabled(enabled);
		this.ruleSetManagerButton.setEnabled(enabled);

		this.resultTextArea.setEnabled(enabled);

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
				HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * La función que permite administrar los conjuntos de reglas que tenemos dentro del sistema.
	 */
	private void managerRuleSets() {
		JDialog dialog = this.ruleSetListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		this.updateRuleSetList();
	}

	/**
	 * La función que permite administrar las evaluaciones que tenemos dentro del sistema.
	 */
	private void managerAssessments() {
		JDialog dialog = this.assessmentListDialog.createCrudDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		this.updateAssessmentList();
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
			this.agentNameLabel.setText(HolderMessage.getMessage("main.data.agent.null"));
		}

		if (this.accessControl.getSubjectSelected() != null) {
			this.subjectNameLabel.setText(this.accessControl.getSubjectSelected().getName());
		} else {
			this.subjectNameLabel.setText(HolderMessage.getMessage("main.data.subject.null"));
		}
	}

	/**
	 * La función encargada de actualizar el listado de las evaluaciones que tenemos dentro de la ventana creando un proceso secundario.
	 */
	private void updateAssessmentList() {
		this.initUpdateAssessmentTask();
		this.updateAssessmentsTask.start();
	}

	/**
	 * La función encargada de actualizar el listado de las reglas que tenemos dentro de la ventana.
	 */
	private void updateRuleSetList() {
		this.initUpdateRuleSetTask();
		this.updateRuleSetTask.start();
	}

	/**
	 * La función encargada de actualizar el contenido del area de resultado de evaluación de acuerdo a una entrada de datos.
	 */
	private void updateResultTextArea() {
		this.initUpdateResultTask();
		this.updateResultsTask.start();
	}

	/**
	 * La función encargada de inicializar el proceso de actualización de las evaluaciones.
	 */
	private void initUpdateAssessmentTask() {
		this.updateAssessmentsTask = new Thread() {
			@Override
			public void run() {
				try {
					// Ejecutamos las acciones antes de procesar.
					MainWindowFrame.this.beforeExecuteProccess(MainWindowFrame.this.assessmentProgressLabel);

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
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(MainWindowFrame.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
								JOptionPane.ERROR_MESSAGE);
					}
					e.printStackTrace();
				} finally {
					MainWindowFrame.this.afterExecuteProccess(MainWindowFrame.this.assessmentProgressLabel);
				}
			}
		};
		this.updateAssessmentsTask.setDaemon(true);
	}

	/**
	 * La función encargada de inicializar el proceso de actualización del conjunto de reglas.
	 */
	private void initUpdateRuleSetTask() {
		this.updateRuleSetTask = new Thread() {
			@Override
			public void run() {
				try {
					// Ejecutamos las acciones antes de procesar.
					MainWindowFrame.this.beforeExecuteProccess(MainWindowFrame.this.ruleSetProgressLabel);

					// Cargamos el listado dentro de la tabla.
					DefaultListModel<RuleSet> model = new DefaultListModel<RuleSet>();

					// Cargamos el listado de los conjuntos que filtramos.
					for (RuleSet ruleSet : MainWindowFrame.this.ruleSetService.findBySubject(MainWindowFrame.this.accessControl.getSubjectSelected())) {
						model.addElement(ruleSet);
					}
					MainWindowFrame.this.ruleSetList.setModel(model);
				} catch (Exception e) {
					if (!this.isInterrupted()) {
						JOptionPane.showMessageDialog(MainWindowFrame.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
								JOptionPane.ERROR_MESSAGE);
					}
					e.printStackTrace();
				} finally {
					MainWindowFrame.this.afterExecuteProccess(MainWindowFrame.this.ruleSetProgressLabel);
				}
			}
		};
		this.updateRuleSetTask.setDaemon(true);
	}

	/**
	 * La función encargada de inicializar el proceso de actualización del panel de resultado de evaluación.
	 */
	private void initUpdateResultTask() {
		this.updateResultsTask = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						String result = new String();

						Integer size = MainWindowFrame.this.resultStringBuffer.length();
						for (int i = 0; i < size; i++) {
							result += MainWindowFrame.this.resultStringBuffer.charAt(0);
							MainWindowFrame.this.resultStringBuffer.deleteCharAt(0);
						}

						MainWindowFrame.logger.debug(result);

						// Cargamos la salida al area de resultado.
						MainWindowFrame.this.resultTextArea.setText(MainWindowFrame.this.resultTextArea.getText() + result);

						Thread.sleep(500);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		this.updateResultsTask.setDaemon(true);
	}

	/**
	 * La función encargada de detener los proceso en segundo plano que tenemos dentro de esta ventana.
	 */
	private void stopBackgroundTask() {
		if (this.updateAssessmentsTask != null) {
			this.updateAssessmentsTask.interrupt();
		}
		if (this.updateRuleSetTask != null) {
			this.updateRuleSetTask.interrupt();
		}
		if (this.updateResultsTask != null) {
			this.updateResultsTask.interrupt();
		}
	}

	/**
	 * La función antes de procesar.
	 * 
	 * @param El
	 *            label de progreso que vamos a cargar.
	 */
	private void beforeExecuteProccess(JLabel label) {
		this.taskCount++;
		this.setEnabled(false);

		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(label);
		label.setIcon(gif);
	}

	/**
	 * La función después de procesar.
	 * 
	 * @param El
	 *            label de progreso que vamos a descargar.
	 */
	private void afterExecuteProccess(JLabel label) {
		this.taskCount--;
		label.setIcon(null);
		if (this.taskCount <= 0) {
			this.setEnabled(true);
		}
	}

	/**
	 * La función encargada de validar una evaluación con el conjunto de reglas seleccionada.
	 */
	protected void evaluateAssessment() {
		// Si tenemos algo seleccionado.
		if (this.assessmentList.getSelectedValue() != null && this.ruleSetList.getSelectedValue() != null) {
			// Obtenemos los elementos.
			Assessment assessment = this.assessmentList.getSelectedValue();
			RuleSet ruleSet = this.ruleSetList.getSelectedValue();

			// Iniciamos el proceso de evaluación y lo arrancamos.
			this.validateAssessmentTask.initValidateTask(assessment, ruleSet);
			this.validateAssessmentTask.startTask(this.resultStringBuffer);
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

		this.updateResultTextArea();

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
