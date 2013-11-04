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
import com.proyecto.security.AccessControl;
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
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

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
		ruleLabel.setBounds(10, 149, 574, 16);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 166, 578, 129);
		ruleScrollPane.setFont(this.getFont());
		contentPanel.add(ruleScrollPane);

		this.ruleTextArea = new JTextArea();
		this.ruleTextArea.setFont(ruleScrollPane.getFont());
		this.ruleTextArea.setLineWrap(true);
		this.ruleTextArea.setWrapStyleWord(true);
		ruleScrollPane.setViewportView(this.ruleTextArea);
		this.ruleTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));

		this.classNameButton = new JButton("[ ]");
		this.classNameButton.setFont(new Font("Arial", Font.PLAIN, 11));
		this.classNameButton.setBounds(596, 166, 35, 35);
		this.classNameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int pos = RuleFormDialog.this.ruleTextArea.getCaretPosition();
				RuleFormDialog.this.ruleTextArea.insert("=>", pos);
			}
		});
		contentPanel.add(this.classNameButton);

		this.propertyNameButton = new JButton("[ ]");
		this.propertyNameButton.setFont(new Font("Arial", Font.PLAIN, 11));
		this.propertyNameButton.setBounds(643, 166, 35, 35);
		contentPanel.add(this.propertyNameButton);

		this.parentesisButton = new JButton("[ ]");
		this.parentesisButton.setFont(new Font("Arial", Font.PLAIN, 11));
		this.parentesisButton.setBounds(596, 213, 35, 35);
		contentPanel.add(this.parentesisButton);

		this.corchetesButton = new JButton("[ ]");
		this.corchetesButton.setFont(new Font("Arial", Font.PLAIN, 11));
		this.corchetesButton.setBounds(643, 213, 35, 35);
		contentPanel.add(this.corchetesButton);

		this.errorButton = new JButton("[ ]");
		this.errorButton.setFont(new Font("Arial", Font.PLAIN, 11));
		this.errorButton.setBounds(596, 260, 82, 35);
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
		this.errorButton.setEnabled(enabled);

		this.commitButton.setEnabled(enabled);
		this.cancelButton.setEnabled(enabled);
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
		this.rule.setRule(this.ruleTextArea.getText().trim());
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