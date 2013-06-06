package com.proyecto.view.base;

import java.awt.Dialog.ModalExclusionType;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.common.util.annotations.View;

@View
public class AssessmentManagerDialog extends JFrame {

	private static final long serialVersionUID = 1077719995099120763L;

	private final JPanel contentPanel = new JPanel();

	private JTable table_1;

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
		this.setModalExclusionType(ModalExclusionType.NO_EXCLUDE);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setTitle("Menu Principal\r\n");
		this.setBounds(100, 100, 487, 321);
		this.getContentPane().setLayout(null);
		this.contentPanel.setBounds(470, 0, 1, 261);
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel);
		this.contentPanel.setLayout(null);
		this.table_1 = new JTable();

		this.contentPanel.add(this.table_1);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 261, 471, 1);
		this.getContentPane().add(buttonPane);
		buttonPane.setLayout(null);

		JButton okButton = new JButton("OK");
		okButton.setBounds(312, 5, 47, 23);
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(364, 5, 65, 23);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JButton btnNewButton = new JButton("Crear Evaluaci\u00F3n");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setBounds(290, 52, 154, 23);
		this.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Eliminar Evaluaci\u00F3n");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_1.setBounds(290, 86, 154, 23);
		this.getContentPane().add(btnNewButton_1);

		JButton updateAssessmentButton = new JButton("Modificar Evaluaci\u00F3n");
		updateAssessmentButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateAssessmentButton.setBounds(290, 120, 154, 23);
		this.getContentPane().add(updateAssessmentButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 228, 460, 33);
		this.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		JButton ok1Button = new JButton("Aceptar");
		ok1Button.setFont(new Font("Arial", Font.BOLD, 12));
		ok1Button.setActionCommand("OK");
		panel.add(ok1Button);

		JButton cancel1Button = new JButton("Cancelar");
		cancel1Button.setFont(new Font("Arial", Font.BOLD, 12));
		cancel1Button.setActionCommand("Cancel");
		panel.add(cancel1Button);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu systemMenu = new JMenu("Sistema");
		systemMenu.setFont(new Font("Arial", Font.BOLD, 12));
		systemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(systemMenu);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AssessmentManagerDialog.this.dispose();
			}
		});
		mntmSalir.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmSalir.setHorizontalAlignment(SwingConstants.LEFT);
		systemMenu.add(mntmSalir);

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

		JMenu ruleMenu = new JMenu("Reglas");
		ruleMenu.setFont(new Font("Arial", Font.BOLD, 12));
		ruleMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(ruleMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Crear");
		mntmNewMenuItem.setFont(new Font("Arial", Font.PLAIN, 12));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		ruleMenu.add(mntmNewMenuItem);

		JMenu mnRazonar = new JMenu("Razonar");
		mnRazonar.setHorizontalAlignment(SwingConstants.LEFT);
		mnRazonar.setFont(new Font("Arial", Font.BOLD, 12));
		menuBar.add(mnRazonar);

		JMenuItem mntmIniciar = new JMenuItem("Iniciar");
		mntmIniciar.setFont(new Font("Arial", Font.PLAIN, 12));
		mnRazonar.add(mntmIniciar);
	}

	/**
	 * La función encargada de crear la ventana principal de la aplicación.
	 * 
	 * @return La ventana principal de la aplicación.
	 */
	public JFrame createFrame() {
		return this;
	}
}
