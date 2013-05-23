package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.rule.RuleService;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@View
public class RuleFormDialog extends JDialog {

    @Autowired
	private RuleService ruleService;
    
    private Rule rule = new Rule();
	
	private final JPanel contentPanel = new JPanel();
	private JTextField ruleTextField;
	private JTextPane descriptionTextPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);
			
			RuleFormDialog dialog = HolderApplicationContext.getContext().getBean(RuleFormDialog.class);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RuleFormDialog() {
		setTitle("REGLAS\r\n");
		setBounds(100, 100, 660, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
			descriptionLabel.setBounds(10, 11, 65, 14);
			descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
			contentPanel.add(descriptionLabel);
		}
		
		descriptionTextPane = new JTextPane();
		descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		descriptionTextPane.setBounds(10, 36, 624, 58);
		contentPanel.add(descriptionTextPane);
		
		JLabel ruleLabel = new JLabel("Regla");
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 116, 46, 14);
		contentPanel.add(ruleLabel);
		
		ruleTextField = new JTextField();
		ruleTextField.setBounds(10, 141, 624, 20);
		contentPanel.add(ruleTextField);
		
		ruleTextField.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveRule();	
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void saveRule() {
		fromDialogToRule();
		
		try {
		ruleService.save(rule);
	} catch (CheckedException e) {
		e.printStackTrace();
	}
		dispose();
	}

	private void fromDialogToRule() {
		rule.setDescription(this.descriptionTextPane.getText());
		rule.setRule(this.ruleTextField.getText());
		rule.setActive(false);
	}
}
