package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.rule.RuleService;
import com.proyecto.util.Validator;
import com.proyecto.view.Resources;

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
	 * El panel de selección de nombre de clase.
	 */
	@Autowired
	private ClassNameSelectDialog classNameSelectDialog;
	/**
	 * El panel de selección de nombre de propiedad.
	 */
	@Autowired
	private PropertyNameSelectDialog propertyNameSelectDialog;

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
	private JTextArea ruleTextArea;
	private JTextArea descriptionTextArea;
	/**
	 * Los botones de acción.
	 */
	private JButton commitButton;
	private JButton cancelButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	/**
	 * Los botones de acciones para la regla.
	 */
	private JButton classNameButton;
	private JButton propertyNameButton;
	private JButton corchetesButton;
	private JButton parentesisButton;
	private JButton forwardRuleButton;
	private JButton backwardRuleButton;
	private JButton errorButton;

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
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 690, 394);
		this.getContentPane().setLayout(new BorderLayout());
		this.setFont(new Font("Arial", Font.PLAIN, 12));

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("rule.form.label.description"));
		descriptionLabel.setBounds(10, 10, 668, 16);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		contentPanel.add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(6, 27, 672, 112);
		descriptionScrollPane.setFont(this.getFont());
		contentPanel.add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setFont(descriptionScrollPane.getFont());
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel ruleLabel = new JLabel(HolderMessage.getMessage("rule.form.label.rule"));
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 149, 600, 16);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 166, 604, 124);
		ruleScrollPane.setFont(this.getFont());
		contentPanel.add(ruleScrollPane);

		this.ruleTextArea = new JTextArea();
		this.ruleTextArea.setFont(ruleScrollPane.getFont());
		this.ruleTextArea.setLineWrap(true);
		this.ruleTextArea.setWrapStyleWord(true);
		ruleScrollPane.setViewportView(this.ruleTextArea);
		this.ruleTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));

		this.classNameButton = new JButton(Resources.CLASS_TO_RULE_ICON);
		this.classNameButton.setBounds(616, 166, 30, 30);
		this.classNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleFormDialog.this.addClassNameToRule();
			}
		});
		contentPanel.add(this.classNameButton);

		this.propertyNameButton = new JButton(Resources.PROPERTY_TO_RULE_ICON);
		this.propertyNameButton.setBounds(648, 166, 30, 30);
		this.propertyNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleFormDialog.this.addPropertyNameToRule();
			}
		});
		contentPanel.add(this.propertyNameButton);

		this.parentesisButton = new JButton(Resources.PARENTESIS_TO_RULE_ICON);
		this.parentesisButton.setBounds(616, 198, 30, 30);
		this.parentesisButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleFormDialog.this.addStringToRule("( )");
			}
		});
		contentPanel.add(this.parentesisButton);

		this.corchetesButton = new JButton(Resources.CORCHETES_TO_RULE_ICON);
		this.corchetesButton.setBounds(648, 198, 30, 30);
		this.corchetesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleFormDialog.this.addStringToRule("[ ]");
			}
		});
		contentPanel.add(this.corchetesButton);

		this.forwardRuleButton = new JButton(Resources.FORWARD_RULE_ICON);
		this.forwardRuleButton.setBounds(616, 229, 30, 30);
		this.forwardRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.addStringToRule(" -> ");
			}
		});
		contentPanel.add(this.forwardRuleButton);

		this.backwardRuleButton = new JButton(Resources.BACKWARD_RULE_ICON);
		this.backwardRuleButton.setBounds(648, 229, 30, 30);
		this.backwardRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.addStringToRule(" <- ");
			}
		});
		contentPanel.add(this.backwardRuleButton);

		this.errorButton = new JButton(Resources.ERROR_TO_RULE_ICON);
		this.errorButton.setBounds(616, 260, 62, 30);
		this.errorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleFormDialog.this.addErrorValidationToRule();
			}
		});
		contentPanel.add(this.errorButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 307, 668, 2);
		contentPanel.add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(549, 321, 35, 35);
		contentPanel.add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(596, 321, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.saveRule();
			}
		});
		contentPanel.add(this.commitButton);

		this.cancelButton = new JButton(Resources.CLOSE_ICON);
		this.cancelButton.setBounds(643, 321, 35, 35);
		this.cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RuleFormDialog.this.dispose();
			}
		});
		contentPanel.add(this.cancelButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.ruleTextArea.setEnabled(enabled);
		this.descriptionTextArea.setEnabled(enabled);

		this.classNameButton.setEnabled(enabled);
		this.propertyNameButton.setEnabled(enabled);
		this.parentesisButton.setEnabled(enabled);
		this.corchetesButton.setEnabled(enabled);
		this.forwardRuleButton.setEnabled(enabled);
		this.backwardRuleButton.setEnabled(enabled);
		this.errorButton.setEnabled(enabled);

		this.commitButton.setEnabled(enabled);
		this.cancelButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cargar el nombre de una clase dentro del campo de la regla.
	 */
	private void addClassNameToRule() {
		// Abrimos la ventana de selección de nombre de clase.
		ClassNameSelectDialog dialog = this.classNameSelectDialog.createSelectDialog();
		dialog.setLocationRelativeTo(this.classNameButton);
		dialog.setVisible(true);

		// Tomamos el valor devuelto.
		if (dialog.getClassSelected() != null) {
			this.addStringToRule(dialog.getClassSelected());
		}
	}

	/**
	 * La función encargada de cargar el nombre de una propiedad dentro del campo de la regla.
	 */
	private void addPropertyNameToRule() {
		// Abrimos la ventana de selección de nombre de la propiedad.
		PropertyNameSelectDialog dialog = this.propertyNameSelectDialog.createSelectDialog();
		dialog.setLocationRelativeTo(this.propertyNameButton);
		dialog.setVisible(true);

		// Tomamos el valor devuelto.
		if (dialog.getPropertySelected() != null) {
			this.addStringToRule(dialog.getPropertySelected());
		}
	}

	/**
	 * La función encargada de cargar el consecuente que describe un error para la aplicación de una regla.
	 */
	private void addErrorValidationToRule() {
		this.addStringToRule("(?x rb:violation error('summary', 'description', args))");
	}

	/**
	 * La función encargada de cargar una cadenas de caracteres dentro del campo de reglas, en la posición donde se encuentra el cursor.
	 * 
	 * @param string
	 *            La cadena que queremos agregar a la regla.
	 */
	private void addStringToRule(String string) {
		int pos = this.ruleTextArea.getCaretPosition();
		this.ruleTextArea.insert(string, pos);
	}

	/**
	 * La función encargada de guardar la regla dentro de la base de datos.
	 */
	private void saveRule() {
		new Thread() {
			@Override
			public void run() {
				try {
					RuleFormDialog.this.beforeExecuteProccess();

					RuleFormDialog.this.fromDialogToRule();
					try {
						RuleFormDialog.this.ruleService.saveOrUpdate(RuleFormDialog.this.rule);
						RuleFormDialog.this.dispose();
					} catch (CheckedException e) {
						JOptionPane.showMessageDialog(RuleFormDialog.this, HolderMessage.getMessage("rule.form.error.save"),
								HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleFormDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
							JOptionPane.ERROR_MESSAGE);
				} finally {
					RuleFormDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(gif);
	}

	/*
	 * La función después de procesar.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función que toma los datos de la ventana y los carga a la regla.
	 * 
	 * @throws CheckedException
	 *             En caso de que algún campo de la regla este fuera de parámetro.
	 */
	private void fromDialogToRule() throws CheckedException {
		// La descripción de la regla.
		if (Validator.descriptionValidator(this.descriptionTextArea.getText())) {
			this.rule.setDescription(this.descriptionTextArea.getText().trim());
		} else {
			throw new CheckedException("rule.form.error.description");
		}
		// La regla en si misma.
		if (Validator.ruleValidator(this.ruleTextArea.getText().trim())) {
			this.rule.setRule(this.ruleTextArea.getText().trim());
		} else {
			throw new CheckedException("rule.form.error.rule");
		}
	}

	/**
	 * La función que toma los datos de la regla y los carga a la ventana.
	 */
	private void fromRuleToDialog() {
		this.descriptionTextArea.setText(this.rule.getDescription());
		this.ruleTextArea.setText(this.rule.getRule());
	}

	/**
	 * La función encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextArea.setText("");
		this.ruleTextArea.setText("");
	}

	/**
	 * La función que carga la ventana para dar de alta una nueva regla.
	 * 
	 * @return La ventana cargada con los datos para dar de alta una nueva regla.
	 */
	public RuleFormDialog createNewDialog() {
		this.setTitle(HolderMessage.getMessage("rule.form.title.new"));

		this.rule = new Rule();
		this.emptyField();

		return this;
	}

	/**
	 * La función que carga la ventana para modificar una regla que ya tenemos dentro de la base de datos.
	 * 
	 * @param rule
	 *            La regla que queremos editar.
	 * @return La ventana cargada con los datos para modificar una regla.
	 */
	public RuleFormDialog createEditDialog(Rule rule) {
		this.setTitle(HolderMessage.getMessage("rule.form.title.edit"));

		this.rule = rule;
		this.emptyField();
		this.fromRuleToDialog();

		return this;
	}
}