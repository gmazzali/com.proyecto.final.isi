package com.proyecto.view.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;

@View
public class LoginDialog extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private JTextField userNameTextField;
	private JPasswordField userPassField;
	
	@Autowired
	private SubjectDialog SubjectDialog;

	/**
	 * Launch the application.
	 */
	@SuppressWarnings("restriction")
	public static void main(String[] args) {
		try {
			
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

		    LoginDialog dialog = HolderApplicationContext.getContext().getBean(LoginDialog.class);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginDialog() {
		setTitle("Ingreso al Sistema");
		setBounds(100, 100, 450, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			userNameTextField = new JTextField();
			userNameTextField.setBounds(154, 48, 233, 20);
			contentPanel.add(userNameTextField);
			userNameTextField.setColumns(10);
		}
		
		JLabel userNameLabel = new JLabel("Usuario");
		userNameLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userNameLabel.setBounds(70, 51, 66, 16);
		contentPanel.add(userNameLabel);
		
		JLabel userPassLabel = new JLabel("Contrase\u00F1a");
		userPassLabel.setFont(new Font("Arial", Font.BOLD, 11));
		userPassLabel.setBounds(70, 92, 78, 16);
		contentPanel.add(userPassLabel);
		
		userPassField = new JPasswordField();
		userPassField.setBounds(154, 89, 233, 20);
		contentPanel.add(userPassField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Ingresar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						SubjectDialog.setVisible(true);
						
					}
				});
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
