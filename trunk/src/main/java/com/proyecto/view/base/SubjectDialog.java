package com.proyecto.view.base;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;

@View
public class SubjectDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	@Autowired
	private AssessmentManagerDialog AssessmentManagerDialog;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			
			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			SubjectDialog dialog = HolderApplicationContext.getContext().getBean(SubjectDialog.class);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SubjectDialog() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setTitle("Materia a Seleccionar");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox subjectComboBox = new JComboBox();
		subjectComboBox.setBounds(99, 113, 227, 29);
		contentPanel.add(subjectComboBox);
		subjectComboBox.addItem("Inteligencia Artificial");
		subjectComboBox.addItem("Modelo E Negocios");
		
		JLabel lblSeleccionarMateria = new JLabel("Seleccionar Una Materia");
		lblSeleccionarMateria.setFont(new Font("Arial", Font.BOLD, 11));
		lblSeleccionarMateria.setBounds(97, 83, 143, 14);
		contentPanel.add(lblSeleccionarMateria);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AssessmentManagerDialog.setVisible(true);
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
					public void actionPerformed(ActionEvent arg0) {
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
