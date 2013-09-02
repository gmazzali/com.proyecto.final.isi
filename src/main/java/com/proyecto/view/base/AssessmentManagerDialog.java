package com.proyecto.view.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.security.AccessControl;
import com.proyecto.view.Resources;
import com.proyecto.view.login.SelectSubjectDialog;
import com.proyecto.view.material.activity.ActivityListDialog;
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
	 * La barra de menu.
	 */
	private JMenuBar menuBar;
	/**
	 * La lista de evaluaciones.
	 */
	private JList<Assessment> assessmentList;
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
	 * Constructor de la ventana de administración de evaluaciones.
	 */
	public AssessmentManagerDialog() {
		super();
		this.init();
	}

	/**
	 * La ventana de inicialización de la ventana de administración de evaluaciones.
	 */
	private void init() {
		this.setResizable(false);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.setBounds(100, 100, 695, 534);

		this.menuBar = new JMenuBar();
		this.menuBar.setFont(this.getContentPane().getFont());
		this.menuBar.setBounds(0, 0, 689, 23);

		// Los menu de sistemas.
		JMenu menuSistemas = new JMenu(HolderMessage.getMessage("main.menu.system"));
		menuSistemas.setFont(this.menuBar.getFont());
		menuSistemas.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuSistemas);

		JMenuItem itemMenuSalir = new JMenuItem(HolderMessage.getMessage("main.menu.system.exit"));
		itemMenuSalir.setFont(this.menuBar.getFont());
		itemMenuSalir.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.dispose();
			}
		});
		menuSistemas.add(itemMenuSalir);

		JMenuItem itemMenuCambioMateria = new JMenuItem(HolderMessage.getMessage("main.menu.system.subject.change"));
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
		JMenu menuReglas = new JMenu(HolderMessage.getMessage("main.menu.rules"));
		menuReglas.setFont(this.menuBar.getFont());
		menuReglas.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuReglas);

		JMenuItem itemMenuReglas = new JMenuItem(HolderMessage.getMessage("main.menu.rules.set.manager"));
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
		JMenu menuRecursos = new JMenu(HolderMessage.getMessage("main.menu.material"));
		menuRecursos.setFont(this.menuBar.getFont());
		menuRecursos.setHorizontalAlignment(SwingConstants.LEFT);
		this.menuBar.add(menuRecursos);

		JMenuItem itemMenuActividades = new JMenuItem(HolderMessage.getMessage("main.menu.material.activity.manager"));
		itemMenuActividades.setFont(this.menuBar.getFont());
		itemMenuActividades.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuActividades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = AssessmentManagerDialog.this.activityListDialog.createCrudDialog();
				dialog.setLocationRelativeTo(AssessmentManagerDialog.this);
				dialog.setVisible(true);
			}
		});
		menuRecursos.add(itemMenuActividades);

		JMenuItem itemMenuReactivos = new JMenuItem(HolderMessage.getMessage("main.menu.material.reactive.manager"));
		itemMenuReactivos.setFont(this.menuBar.getFont());
		itemMenuReactivos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuReactivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = AssessmentManagerDialog.this.reactiveListDialog.createCrudDialog();
				dialog.setLocationRelativeTo(AssessmentManagerDialog.this);
				dialog.setVisible(true);
			}
		});
		menuRecursos.add(itemMenuReactivos);

		JMenuItem itemMenuInstrumentos = new JMenuItem(HolderMessage.getMessage("main.menu.material.instrument.manager"));
		itemMenuInstrumentos.setFont(this.menuBar.getFont());
		itemMenuInstrumentos.setHorizontalAlignment(SwingConstants.LEFT);
		itemMenuInstrumentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = AssessmentManagerDialog.this.instrumentListDialog.createCrudDialog();
				dialog.setLocationRelativeTo(AssessmentManagerDialog.this);
				dialog.setVisible(true);
			}
		});
		menuRecursos.add(itemMenuInstrumentos);
		this.getContentPane().add(this.menuBar);

		JScrollPane assessmentScrollPane = new JScrollPane();
		assessmentScrollPane.setBounds(10, 35, 624, 425);
		this.getContentPane().add(assessmentScrollPane);

		this.assessmentList = new JList<Assessment>();
		this.assessmentList.setModel(new DefaultListModel<Assessment>());
		assessmentScrollPane.setViewportView(this.assessmentList);

		// El contenido de la ventana.
		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(644, 35, 35, 35);
		this.getContentPane().add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(644, 77, 35, 35);
		this.getContentPane().add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteButton.setBounds(644, 119, 35, 35);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		this.getContentPane().add(this.deleteButton);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(null);
		dataPanel.setBounds(0, 471, 689, 35);
		this.getContentPane().add(dataPanel);

		JLabel agentLabel = new JLabel("Agente:");
		agentLabel.setBounds(6, 3, 284, 14);
		agentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentLabel.setFont(new Font("Arial", Font.BOLD, 11));

		this.subjectNameLabel = new JLabel("NOMBRE MATERIA");
		this.subjectNameLabel.setForeground(Color.BLUE);
		this.subjectNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.subjectNameLabel.setBounds(300, 20, 284, 14);
		this.getContentPane().setLayout(null);
		dataPanel.setLayout(null);
		dataPanel.add(agentLabel);

		this.agentNameLabel = new JLabel("NOMBRE AGENTE");
		this.agentNameLabel.setForeground(Color.BLUE);
		this.agentNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.agentNameLabel.setBounds(6, 20, 284, 14);
		dataPanel.add(this.agentNameLabel);

		JLabel subjectLabel = new JLabel("Materia:");
		subjectLabel.setBounds(300, 3, 284, 14);
		subjectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 11));
		dataPanel.add(subjectLabel);
		dataPanel.add(this.subjectNameLabel);
	}

	/**
	 * La función que carga el nombre del agente y de la materia.
	 */
	private void loadAgentData() {
		if (this.accessControl.getAgentLogged() != null) {
			this.agentNameLabel.setText(this.accessControl.getAgentLogged().getName());
		}
		if (this.accessControl.getSubjectSelected() != null) {
			this.subjectNameLabel.setText(this.accessControl.getSubjectSelected().getName());
		}
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
	protected void managerRules() {
		JDialog dialog = this.ruleSetListDialog.createDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	/**
	 * La función encargada de crear la ventana principal de la aplicación.
	 * 
	 * @return La ventana principal de la aplicación.
	 */
	public JFrame createFrame() {
		this.loadAgentData();
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
