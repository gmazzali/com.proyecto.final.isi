package com.proyecto.view.base;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.agent.Agent;
import com.proyecto.security.AccessControl;
import com.proyecto.service.agent.AgentService;

/**
 * La ventana de login que vamos a ocupar dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 923206517734687294L;

	/**
	 * El servicio de los agentes.
	 */
	@Autowired
	private AgentService agentService;
	/**
	 * El control de acceso donde vamos a poner el agente que ingresa a este sistema.
	 */
	@Autowired
	private AccessControl accessControl;
	/**
	 * La ventana de selección de materia.
	 */
	@Autowired
	private SelectSubjectDialog selectSubjectDialog;

	/**
	 * Los campos de nombre de agente y password.
	 */
	private JTextField userNameTextField;
	private JPasswordField userPassField;

	/**
	 * Constructor del dialogo de login.
	 */
	public LoginDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el dialogo de login.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setTitle("Ingreso al Sistema");
		this.setBounds(100, 100, 350, 153);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel userNameLabel = new JLabel("Usuario:");
		userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userNameLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userNameLabel.setBounds(10, 15, 74, 16);
		contentPanel.add(userNameLabel);

		this.userNameTextField = new JTextField();
		this.userNameTextField.setBounds(94, 11, 240, 25);
		contentPanel.add(this.userNameTextField);

		JLabel userPassLabel = new JLabel("Contrase\u00F1a:");
		userPassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userPassLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userPassLabel.setBounds(10, 51, 74, 16);
		contentPanel.add(userPassLabel);

		this.userPassField = new JPasswordField();
		this.userPassField.setBounds(94, 47, 240, 25);
		contentPanel.add(this.userPassField);

		JButton okButton = new JButton("Ingresar");
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setBounds(124, 83, 100, 30);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginDialog.this.loggin();
			}
		});
		contentPanel.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setBounds(234, 83, 100, 30);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.dispose();
			}
		});
		contentPanel.add(cancelButton);
	}

	/**
	 * La función encargada de verificar la existencia del agente y su password para luego guardarlo dentro del sistema para control de acceso al
	 * mismo.
	 */
	protected void loggin() {
		// Recuperamos el agente de acuerdo al nombre.
		String agentName = this.userNameTextField.getText();

		Agent agent = this.agentService.findByName(agentName);

		// Verificamos si recuperamos un agente.
		if (agent != null) {
			// Verificamos la password.
			String agentPassword = new String(this.userPassField.getPassword());

			if (agentPassword.equals(agent.getPassword())) {
				this.accessControl.setAgentLogged(agent);
				this.dispose();

				// Abrimos la ventana de selección de materia.
				JDialog dialog = this.selectSubjectDialog.createDialog();
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(this, HolderMessage.getMessage("login.password.invalid"), "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, HolderMessage.getMessage("login.name.invalid"), "Advertencia", JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			LoginDialog dialog = HolderApplicationContext.getContext().getBean(LoginDialog.class);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
