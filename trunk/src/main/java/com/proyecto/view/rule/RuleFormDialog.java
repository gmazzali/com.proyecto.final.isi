package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
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
		this.setBounds(100, 100, 660, 300);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setBounds(10, 11, 65, 14);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		contentPanel.add(descriptionLabel);

		this.descriptionTextPane = new JTextPane();
		this.descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 11));
		this.descriptionTextPane.setBounds(10, 36, 624, 60);
		contentPanel.add(this.descriptionTextPane);

		JLabel ruleLabel = new JLabel("Regla");
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 116, 46, 14);
		contentPanel.add(ruleLabel);

		this.ruleTextField = new JTextPane();
		this.ruleTextField.setBounds(10, 141, 624, 60);
		contentPanel.add(this.ruleTextField);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aceptar");
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.saveRule();
			}
		});
		buttonPane.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RuleFormDialog.this.dispose();
			}
		});
		buttonPane.add(cancelButton);
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			Rule r = HolderApplicationContext.getContext().getBean(RuleService.class).findById(28);
			RuleFormDialog dialog = HolderApplicationContext.getContext().getBean(RuleFormDialog.class).createEditDialog(r);

			// RuleFormDialog dialog = HolderApplicationContext.getContext().getBean(RuleFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
