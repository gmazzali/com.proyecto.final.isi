package com.proyecto.view.base;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.proyecto.security.AccessControl;
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
	 * La ventana de selección de materias.
	 */
	@Autowired
	private SelectSubjectDialog selectSubjectDialog;

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
	 * La ventana de adminitración de conjuntos de reglas.
	 */
	@Autowired
	private RuleSetListDialog ruleSetListDialog;

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
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setTitle("Menu Principal\r\n");
		this.setBounds(100, 100, 743, 487);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 737, 23);

		JMenu systemMenu = new JMenu("Sistema");
		systemMenu.setFont(new Font("Arial", Font.BOLD, 12));
		systemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(systemMenu);

		JMenuItem exitItemMenu = new JMenuItem("Salir");
		exitItemMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.dispose();
			}
		});

		JMenuItem changeSubjectItemMenu = new JMenuItem("Cambio de Materia");
		changeSubjectItemMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		changeSubjectItemMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.changeSubject();
			}
		});
		systemMenu.add(changeSubjectItemMenu);
		exitItemMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		exitItemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		systemMenu.add(exitItemMenu);

		JMenu ruleMenu = new JMenu("Reglas");
		ruleMenu.setFont(new Font("Arial", Font.BOLD, 12));
		ruleMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(ruleMenu);

		JMenuItem managerRulesItemMenu = new JMenuItem("Administrar");
		managerRulesItemMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AssessmentManagerDialog.this.managerRules();
			}
		});
		managerRulesItemMenu.setFont(new Font("Arial", Font.PLAIN, 12));
		managerRulesItemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		ruleMenu.add(managerRulesItemMenu);

		JMenu resourceMenu = new JMenu("Recursos");
		resourceMenu.setFont(new Font("Arial", Font.BOLD, 12));
		resourceMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(resourceMenu);

		JMenuItem mntmActividades = new JMenuItem("Actividades");
		mntmActividades.setFont(new Font("Arial", Font.PLAIN, 12));
		resourceMenu.add(mntmActividades);

		JMenuItem mntmReactivos = new JMenuItem("Reactivos");
		mntmReactivos.setFont(new Font("Arial", Font.PLAIN, 12));
		resourceMenu.add(mntmReactivos);

		JMenuItem mntmInstrumentros = new JMenuItem("Instrumentros");
		mntmInstrumentros.setFont(new Font("Arial", Font.PLAIN, 12));
		resourceMenu.add(mntmInstrumentros);

		JButton newButton = new JButton("Crear Evaluaci\u00F3n");
		newButton.setBounds(518, 35, 207, 30);
		newButton.setFont(new Font("Arial", Font.BOLD, 12));

		JButton deleteButton = new JButton("Eliminar Evaluaci\u00F3n");
		deleteButton.setBounds(518, 119, 207, 30);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		deleteButton.setFont(new Font("Arial", Font.BOLD, 12));

		JButton modifyButton = new JButton("Modificar Evaluaci\u00F3n");
		modifyButton.setBounds(518, 77, 207, 30);
		modifyButton.setFont(new Font("Arial", Font.BOLD, 12));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 496, 411);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel.setBounds(518, 356, 207, 90);

		JLabel agentLabel = new JLabel("Agente:");
		agentLabel.setBounds(6, 3, 54, 17);
		agentLabel.setHorizontalAlignment(SwingConstants.LEFT);
		agentLabel.setFont(new Font("Arial", Font.BOLD, 11));

		this.subjectNameLabel = new JLabel("NOMBRE MATERIA");
		this.subjectNameLabel.setForeground(Color.BLUE);
		this.subjectNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.subjectNameLabel.setBounds(6, 68, 200, 16);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(scrollPane);
		this.getContentPane().add(newButton);
		this.getContentPane().add(modifyButton);
		this.getContentPane().add(deleteButton);
		this.getContentPane().add(menuBar);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		panel.add(agentLabel);

		this.agentNameLabel = new JLabel("NOMBRE AGENTE");
		this.agentNameLabel.setForeground(Color.BLUE);
		this.agentNameLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		this.agentNameLabel.setBounds(6, 23, 200, 16);
		panel.add(this.agentNameLabel);

		JLabel subjectLabel = new JLabel("Materia:");
		subjectLabel.setBounds(6, 51, 54, 14);
		subjectLabel.setHorizontalAlignment(SwingConstants.LEFT);
		subjectLabel.setFont(new Font("Arial", Font.BOLD, 11));
		panel.add(subjectLabel);
		panel.add(this.subjectNameLabel);
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
}
