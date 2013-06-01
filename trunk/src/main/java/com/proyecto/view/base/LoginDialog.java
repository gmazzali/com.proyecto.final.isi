package com.proyecto.view.base;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;

/**
 * La ventana de login que vamos a ocupar dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class LoginDialog extends JFrame {

	private static final long serialVersionUID = 923206517734687294L;

	private final JPanel contentPanel = new JPanel();
	private final JTextField userNameTextField;
	private final JPasswordField userPassField;

	@Autowired
	private SubjectDialog SubjectDialog;

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		this.setResizable(false);
		this.setTitle("Ingreso al Sistema");
		this.setBounds(100, 100, 350, 153);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(null);

		JLabel userNameLabel = new JLabel("Usuario:");
		userNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userNameLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userNameLabel.setBounds(10, 15, 74, 16);
		this.contentPanel.add(userNameLabel);

		this.userNameTextField = new JTextField();
		this.userNameTextField.setBounds(94, 11, 240, 25);
		this.contentPanel.add(this.userNameTextField);
		this.userNameTextField.setColumns(10);

		JLabel userPassLabel = new JLabel("Contrase\u00F1a:");
		userPassLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userPassLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userPassLabel.setBounds(10, 51, 74, 16);
		this.contentPanel.add(userPassLabel);

		this.userPassField = new JPasswordField();
		this.userPassField.setBounds(94, 47, 240, 25);
		this.contentPanel.add(this.userPassField);

		JButton okButton = new JButton("Ingresar");
		okButton.setBounds(124, 83, 100, 30);
		this.contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginDialog.this.SubjectDialog.setVisible(true);

			}
		});
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setBounds(234, 83, 100, 30);
		this.contentPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginDialog.this.dispose();
			}
		});
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
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
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
