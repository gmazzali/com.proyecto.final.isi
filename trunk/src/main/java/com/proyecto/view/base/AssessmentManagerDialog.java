package com.proyecto.view.base;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.common.util.annotations.View;
@View
public class AssessmentManagerDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AssessmentManagerDialog dialog = new AssessmentManagerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AssessmentManagerDialog() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Menu Principal\r\n");
		setBounds(100, 100, 487, 321);
		getContentPane().setLayout(null);
		contentPanel.setBounds(470, 0, 1, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		table_1 = new JTable();

		contentPanel.add(table_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 261, 471, 1);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(312, 5, 47, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(364, 5, 65, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JButton btnNewButton = new JButton("Crear Evaluaci\u00F3n");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton.setBounds(290, 52, 154, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar Evaluaci\u00F3n");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_1.setBounds(290, 86, 154, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton updateAssessmentButton = new JButton("Modificar Evaluaci\u00F3n");
		updateAssessmentButton.setFont(new Font("Arial", Font.BOLD, 11));
		updateAssessmentButton.setBounds(290, 120, 154, 23);
		getContentPane().add(updateAssessmentButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 228, 460, 33);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton okButton = new JButton("Aceptar");
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		panel.add(okButton);
		
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setActionCommand("Cancel");
		panel.add(cancelButton);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu systemMenu = new JMenu("Sistema");
		systemMenu.setFont(new Font("Arial", Font.BOLD, 12));
		systemMenu.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(systemMenu);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
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
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
