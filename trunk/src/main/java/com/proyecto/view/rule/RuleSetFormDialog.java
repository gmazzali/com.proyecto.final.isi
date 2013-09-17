package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.security.AccessControl;
import com.proyecto.service.rule.RuleService;
import com.proyecto.service.rule.RuleSetService;
import com.proyecto.util.Validator;
import com.proyecto.view.Resources;

/**
 * La clase que define la ventana de edición de un conjunto de reglas que tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class RuleSetFormDialog extends JDialog {

	private static final long serialVersionUID = -6286788752888976971L;

	/**
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

	/**
	 * El servicio de las reglas que vamos a seleccionar.
	 */
	@Autowired
	private RuleService ruleService;

	/**
	 * El servicio del conjunto de reglas.
	 */
	@Autowired
	private RuleSetService ruleSetService;

	/**
	 * El conjunto de reglas que vamos a editar.
	 */
	private RuleSet ruleSet;

	/**
	 * La descripción del conjunto de reglas.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * Los modelos de las listas de reglas y sus listas.
	 */
	private JList<Rule> activeRulesList;
	private JList<Rule> deactiveRulesList;
	/**
	 * Los botones de acción.
	 */
	private JButton enableRuleButton;
	private JButton disableRuleButton;
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de una ventana de edición de un conjunto de regla.
	 */
	public RuleSetFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de crear la ventana de edición de conjuntos.
	 */
	public void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 814, 441);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("ruleset.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 10, 791, 16);
		contentPanel.add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(6, 27, 796, 77);
		contentPanel.add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setBorder(new LineBorder(new Color(128, 128, 128)));
		this.descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel enableRuleLabel = new JLabel(HolderMessage.getMessage("ruleset.form.label.rules.active"));
		enableRuleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleLabel.setBounds(10, 116, 367, 16);
		contentPanel.add(enableRuleLabel);

		JScrollPane enableRulesScrollPane = new JScrollPane();
		enableRulesScrollPane.setBounds(6, 133, 375, 216);
		contentPanel.add(enableRulesScrollPane);

		this.activeRulesList = new JList<Rule>();
		this.activeRulesList.setBorder(new LineBorder(new Color(128, 128, 128)));
		this.activeRulesList.setSize(388, 214);
		this.activeRulesList.setModel(new DefaultListModel<Rule>());
		this.activeRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		enableRulesScrollPane.setViewportView(this.activeRulesList);

		this.enableRuleButton = new JButton(Resources.ADD_RULE_ICON);
		this.enableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.enableRuleButton.setBounds(387, 133, 35, 35);
		this.enableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.enableRule();
			}
		});
		contentPanel.add(this.enableRuleButton);

		this.disableRuleButton = new JButton(Resources.REMOVE_RULE_ICON);
		this.disableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.disableRuleButton.setBounds(387, 180, 35, 35);
		this.disableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.disableRule();
			}
		});
		contentPanel.add(this.disableRuleButton);

		JLabel disableRuleLabel = new JLabel(HolderMessage.getMessage("ruleset.form.label.rules.deactive"));
		disableRuleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		disableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		disableRuleLabel.setBounds(433, 116, 367, 16);
		contentPanel.add(disableRuleLabel);

		JScrollPane disableRulesScrollPane = new JScrollPane();
		disableRulesScrollPane.setBounds(429, 133, 375, 216);
		contentPanel.add(disableRulesScrollPane);

		this.deactiveRulesList = new JList<Rule>();
		this.deactiveRulesList.setBorder(new LineBorder(new Color(128, 128, 128)));
		this.deactiveRulesList.setLocation(441, 0);
		this.deactiveRulesList.setModel(new DefaultListModel<Rule>());
		this.deactiveRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		disableRulesScrollPane.setViewportView(this.deactiveRulesList);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 358, 791, 2);
		contentPanel.add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(672, 372, 35, 35);
		contentPanel.add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(719, 372, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.saveRuleSet();
			}
		});
		contentPanel.add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(766, 372, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.dispose();
			}
		});
		contentPanel.add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.descriptionTextArea.setEnabled(enabled);

		this.deactiveRulesList.setEnabled(enabled);
		this.activeRulesList.setEnabled(enabled);

		this.enableRuleButton.setEnabled(enabled);
		this.disableRuleButton.setEnabled(enabled);
		this.rejectButton.setEnabled(enabled);
		this.commitButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cargar el listado de las reglas activas que tenemos dentro del sistema para poder asignarlas a un conjunto dado.
	 */
	private void loadRules() {
		new Thread() {
			@Override
			public void run() {
				try {
					RuleSetFormDialog.this.beforeExecuteProccess();

					DefaultListModel<Rule> deactiveRuleModel = new DefaultListModel<Rule>();
					DefaultListModel<Rule> activeRuleModel = new DefaultListModel<Rule>();

					// Cargamos todas las reglas activas que tenemos dentro del sistema.
					for (Rule r : RuleSetFormDialog.this.ruleService.findAll()) {
						deactiveRuleModel.addElement(r);
					}

					// Cargamos las reglas que tenemos en el conjunto dentro de la lista de seleccionada.
					if (RuleSetFormDialog.this.ruleSet != null && RuleSetFormDialog.this.ruleSet.getRules() != null) {
						for (Rule r : RuleSetFormDialog.this.ruleSet.getRules()) {
							// Si la regla esta activa, la quitamos del conjunto de selección.
							if (r.getActive()) {
								deactiveRuleModel.removeElement(r);
							}
							// Agregamos la regla dentro del grupo de reglas seleccionadas.
							activeRuleModel.addElement(r);
						}
					}

					RuleSetFormDialog.this.activeRulesList.setModel(activeRuleModel);
					RuleSetFormDialog.this.deactiveRulesList.setModel(deactiveRuleModel);

				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleSetFormDialog.this, HolderMessage.getMessage("ruleset.form.load.rules.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
				} finally {
					RuleSetFormDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de procesar.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función que pasa una regla de activada a desactivada.
	 */
	private void disableRule() {
		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

		int[] indexs = this.activeRulesList.getSelectedIndices();
		if (indexs.length > 0) {
			for (int i = 0; i < indexs.length; i++) {
				Rule rule = activeRuleModel.get(indexs[i]);
				if (rule.getActive()) {
					activeRuleModel.removeElement(rule);
					deactiveRuleModel.addElement(rule);
				} else {
					if (JOptionPane.showConfirmDialog(this,
							HolderMessage.getMessage("ruleset.form.delete.rule.confirm", new Object[] { rule.getDescription() }),
							HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						activeRuleModel.removeElement(rule);
					}
				}
			}
			this.activeRulesList.clearSelection();
			this.deactiveRulesList.clearSelection();
		}
	}

	/**
	 * La función que pasa una regla de desactivada a activada.
	 */
	private void enableRule() {
		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

		int[] indexs = this.deactiveRulesList.getSelectedIndices();
		if (indexs.length > 0) {
			List<Rule> rules = new ArrayList<Rule>();
			for (int i = 0; i < indexs.length; i++) {
				rules.add(deactiveRuleModel.get(indexs[i]));
			}

			for (Rule rule : rules) {
				deactiveRuleModel.removeElement(rule);
				activeRuleModel.addElement(rule);
			}

			this.activeRulesList.clearSelection();
			this.deactiveRulesList.clearSelection();
		}
	}

	/**
	 * La función encargada de guardar un conjunto de reglas.
	 */
	private void saveRuleSet() {
		new Thread() {
			@Override
			public void run() {
				try {
					RuleSetFormDialog.this.beforeExecuteProccess();

					RuleSetFormDialog.this.fromDialogToRuleSet();
					try {
						RuleSetFormDialog.this.ruleSetService.saveOrUpdate(RuleSetFormDialog.this.ruleSet);
						RuleSetFormDialog.this.dispose();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(RuleSetFormDialog.this, HolderMessage.getMessage("ruleset.form.error.save"),
								HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					}
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleSetFormDialog.this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"),
							JOptionPane.ERROR_MESSAGE);
				} finally {
					RuleSetFormDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextArea.setText("");

		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

		activeRuleModel.clear();
		deactiveRuleModel.clear();
	}

	/**
	 * La función que toma los datos del conjunto de reglas y los carga a la ventana.
	 */
	private void fromRuleSetToDialog() {
		this.descriptionTextArea.setText(this.ruleSet.getDescription());
		this.loadRules();
	}

	/**
	 * La función que toma los datos de la ventana y los carga al conjunto de reglas.
	 * 
	 * @throws CheckedException
	 *             En caso de que el conjunto de reglas tenga algún parámetro fuera de lugar
	 */
	private void fromDialogToRuleSet() throws CheckedException {
		// La materia del conjunto.
		this.ruleSet.setSubject(this.accessControl.getSubjectSelected());

		// La descripción del conjunto de reglas.
		if (Validator.descriptionValidator(this.descriptionTextArea.getText())) {
			this.ruleSet.setDescription(this.descriptionTextArea.getText().trim());
		} else {
			throw new CheckedException("ruleset.form.error.description");
		}

		// El conjunto de las reglas.
		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		if (activeRuleModel.getSize() > 0) {
			this.ruleSet.clearRules();
			for (Object o : activeRuleModel.toArray()) {
				this.ruleSet.addRule((Rule) o);
			}
		} else {
			throw new CheckedException("ruleset.form.error.rules");
		}
	}

	/**
	 * La función que carga la ventana para dar de alta un nuevo conjunto de reglas.
	 * 
	 * @return La ventana cargada con los datos para dar de alta un nuevo conjunto de reglas.
	 */
	public RuleSetFormDialog createNewDialog() {
		this.setTitle(HolderMessage.getMessage("ruleset.form.title.new"));

		this.ruleSet = new RuleSet();

		this.emptyField();
		this.loadRules();

		return this;
	}

	/**
	 * La función que carga la ventana para modificar un conjunto de reglas que ya tenemos dentro de la base de datos.
	 * 
	 * @param ruleSet
	 *            El conjunto de reglas que queremos editar.
	 * @return La ventana cargada con los datos para modificar un conjunto de reglas.
	 */
	public RuleSetFormDialog createEditDialog(RuleSet ruleSet) {
		this.setTitle(HolderMessage.getMessage("ruleset.form.title.edit"));

		this.ruleSet = ruleSet;

		this.emptyField();
		this.fromRuleSetToDialog();

		return this;
	}
}