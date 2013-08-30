package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.rule.RuleService;
import com.proyecto.util.Validator;

/**
 * La clase que despliega el formulario para edición de reglas dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class RuleFormDialog extends JDialog {

	private static final long serialVersionUID = 1250114119899890805L;

	/**
	 * El servicio que vamos a ocupar.
	 */
	@Autowired
	private RuleService ruleService;

	/**
	 * La regla que vamos a editar.
	 */
	private Rule rule;

	/**
	 * Los componentes de la ventana.
	 */
	private JTextPane ruleTextField;
	private JTextPane descriptionTextPane;

	/**
	 * El constructor de una ventana de edición de reglas.
	 */
	public RuleFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana de edición de reglas.
	 */
	private void init() {
		this.setResizable(false);
		this.setBounds(100, 100, 694, 312);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setBounds(10, 11, 162, 14);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		contentPanel.add(descriptionLabel);

		this.descriptionTextPane = new JTextPane();
		this.descriptionTextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.descriptionTextPane.setBackground(Color.WHITE);
		this.descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		this.descriptionTextPane.setBounds(10, 37, 668, 60);
		contentPanel.add(this.descriptionTextPane);

		JLabel ruleLabel = new JLabel("Regla");
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 116, 75, 14);
		contentPanel.add(ruleLabel);

		this.ruleTextField = new JTextPane();
		this.ruleTextField.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.ruleTextField.setBounds(10, 141, 668, 90);
		contentPanel.add(this.ruleTextField);

		JButton commitButton = new JButton("Aceptar");
		commitButton.setLocation(468, 243);
		commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		commitButton.setSize(100, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.saveRule();
			}
		});
		contentPanel.add(commitButton);
		this.getRootPane().setDefaultButton(commitButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setLocation(578, 243);
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setSize(100, 30);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RuleFormDialog.this.dispose();
			}
		});
		contentPanel.add(cancelButton);
		
		JButton btnNewButton = new JButton("=>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String contentRule =	ruleTextField.getText();
			ruleTextField.setText(contentRule+"=>");
			
			}
		});
		btnNewButton.setBounds(95, 112, 53, 23);
		contentPanel.add(btnNewButton);
		
		JButton button = new JButton("|");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String contentRule =	ruleTextField.getText();
				ruleTextField.setText(contentRule+"|");
			}
		});
		button.setBounds(150, 112, 53, 23);
		contentPanel.add(button);
		
		JButton button_1 = new JButton("?");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contentRule =	ruleTextField.getText();
				ruleTextField.setText(contentRule+"?");
			}
		});
		button_1.setBounds(202, 112, 53, 23);
		contentPanel.add(button_1);
		
		JButton button_2 = new JButton("=");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contentRule =	ruleTextField.getText();
				ruleTextField.setText(contentRule+"=");
			}
		});
		button_2.setBounds(255, 112, 53, 23);
		contentPanel.add(button_2);
	}

	/**
	 * La función encargada de guardar la regla dentro de la base de datos.
	 */
	private void saveRule() {
		try {
			this.fromDialogToRule();
			this.ruleService.saveOrUpdate(this.rule);
			this.dispose();
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función que toma los datos de la ventana y los carga a la regla.
	 * 
	 * @throws CheckedException
	 *             En caso de que algún campo de la regla este fuera de parámetro.
	 */
	private void fromDialogToRule() throws CheckedException {
		// La descripción de la regla.
		if (Validator.descriptionValidator(this.descriptionTextPane.getText())) {
			this.rule.setDescription(this.descriptionTextPane.getText());
		} else {
			throw new CheckedException("rule.description.empty");
		}
		// La regla en si misma.
		if (Validator.ruleValidator(this.ruleTextField.getText())) {
			this.rule.setRule(this.ruleTextField.getText());
		} else {
			throw new CheckedException("rule.rule.empty");
		}
	}

	/**
	 * La función que toma los datos de la regla y los carga a la ventana.
	 */
	private void fromRuleToDialog() {
		this.descriptionTextPane.setText(this.rule.getDescription());
		this.ruleTextField.setText(this.rule.getRule());
	}

	/**
	 * La función encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextPane.setText("");
		this.ruleTextField.setText("");
	}

	/**
	 * La función que carga la ventana para dar de alta una nueva regla.
	 * 
	 * @return La ventana cargada con los datos para dar de alta una nueva regla.
	 */
	public RuleFormDialog createNewDialog() {
		this.setTitle("Nueva regla");
		this.rule = new Rule();
		this.emptyField();
		return this;
	}

	/**
	 * La función que carga la ventana para modificar una regla que ya tenemos dentro de la base de datos.
	 * 
	 * @param editRule
	 *            La regla que queremos editar.
	 * @return La ventana cargada con los datos para modificar una regla.
	 */
	public RuleFormDialog createEditDialog(Rule editRule) {
		this.setTitle("Editar regla");
		this.rule = editRule;
		this.fromRuleToDialog();
		return this;
	}
}
