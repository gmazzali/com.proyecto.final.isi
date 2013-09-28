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
 * La clase que despliega el formulario para edici�n de reglas dentro del sistema.
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
	 * Los botones de acci�n.
	 */
	private JButton btnNewButton;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton commitButton;
	private JButton cancelButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * El constructor de una ventana de edici�n de reglas.
	 */
	public RuleFormDialog() {
		super();
		this.init();
	}

	/**
	 * La funci�n encargada de inicializar la ventana de edici�n de reglas.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 690, 408);
		this.getContentPane().setLayout(new BorderLayout());

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
		contentPanel.add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 11));
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel ruleLabel = new JLabel(HolderMessage.getMessage("rule.form.label.rule"));
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 149, 668, 16);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 166, 672, 118);
		contentPanel.add(ruleScrollPane);

		this.ruleTextArea = new JTextArea();
		this.ruleTextArea.setLineWrap(true);
		this.ruleTextArea.setWrapStyleWord(true);
		ruleScrollPane.setViewportView(this.ruleTextArea);
		this.ruleTextArea.setBorder(new LineBorder(Color.LIGHT_GRAY));

		this.btnNewButton = new JButton("=>");
		this.btnNewButton.setBounds(10, 291, 53, 23);
		this.btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int pos = ruleTextArea.getCaretPosition();
				RuleFormDialog.this.ruleTextArea.insert("=>", pos);
			}
		});
		contentPanel.add(this.btnNewButton);

		this.button = new JButton("|");
		this.button.setBounds(65, 291, 53, 23);
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int pos = ruleTextArea.getCaretPosition();
				RuleFormDialog.this.ruleTextArea.insert("|", pos);
			}
		});
		contentPanel.add(this.button);

		this.button_1 = new JButton("?");
		this.button_1.setBounds(117, 291, 53, 23);
		this.button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = ruleTextArea.getCaretPosition();
				RuleFormDialog.this.ruleTextArea.insert("?", pos);
			}
		});
		contentPanel.add(this.button_1);

		this.button_2 = new JButton("=");
		this.button_2.setBounds(170, 291, 53, 23);
		this.button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pos = ruleTextArea.getCaretPosition();
				RuleFormDialog.this.ruleTextArea.insert("=", pos);
			}
		});
		contentPanel.add(this.button_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 326, 668, 2);
		contentPanel.add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(549, 340, 35, 35);
		contentPanel.add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(596, 340, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleFormDialog.this.saveRule();
			}
		});
		contentPanel.add(this.commitButton);

		this.cancelButton = new JButton(Resources.CLOSE_ICON);
		this.cancelButton.setBounds(643, 340, 35, 35);
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

		this.btnNewButton.setEnabled(enabled);
		this.button.setEnabled(enabled);
		this.button_1.setEnabled(enabled);
		this.button_2.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
		this.cancelButton.setEnabled(enabled);
	}

	/**
	 * La funci�n encargada de guardar la regla dentro de la base de datos.
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
	 * La funci�n antes de procesar.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		ImageIcon gif = new ImageIcon(Resources.PROGRESS_LIST_ICON.getImage());
		gif.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(gif);
	}

	/*
	 * La funci�n despu�s de procesar.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La funci�n que toma los datos de la ventana y los carga a la regla.
	 * 
	 * @throws CheckedException
	 *             En caso de que alg�n campo de la regla este fuera de par�metro.
	 */
	private void fromDialogToRule() throws CheckedException {
		// La descripci�n de la regla.
		if (Validator.descriptionValidator(this.descriptionTextArea.getText())) {
			this.rule.setDescription(this.descriptionTextArea.getText().trim());
		} else {
			throw new CheckedException("rule.form.error.description");
		}
		// La regla en si misma.
		this.rule.setRule(this.ruleTextArea.getText().trim());
	}

	/**
	 * La funci�n que toma los datos de la regla y los carga a la ventana.
	 */
	private void fromRuleToDialog() {
		this.descriptionTextArea.setText(this.rule.getDescription());
		this.ruleTextArea.setText(this.rule.getRule());
	}

	/**
	 * La funci�n encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextArea.setText("");
		this.ruleTextArea.setText("");
	}

	/**
	 * La funci�n que carga la ventana para dar de alta una nueva regla.
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
	 * La funci�n que carga la ventana para modificar una regla que ya tenemos dentro de la base de datos.
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