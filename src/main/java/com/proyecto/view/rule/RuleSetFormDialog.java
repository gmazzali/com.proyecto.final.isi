package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
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
	private JTextPane descriptionTextPane;
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
		this.setBounds(100, 100, 832, 446);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("ruleset.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 11, 807, 14);
		contentPanel.add(descriptionLabel);

		this.descriptionTextPane = new JTextPane();
		this.descriptionTextPane.setBorder(new LineBorder(Color.GRAY, 2));
		this.descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 12));
		this.descriptionTextPane.setBounds(10, 36, 807, 58);
		contentPanel.add(this.descriptionTextPane);

		JLabel enableRuleLabel = new JLabel(HolderMessage.getMessage("ruleset.form.label.rules.active"));
		enableRuleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleLabel.setBounds(10, 106, 375, 14);
		contentPanel.add(enableRuleLabel);

		JScrollPane enableRulesScrollPane = new JScrollPane();
		enableRulesScrollPane.setBounds(10, 130, 375, 216);
		contentPanel.add(enableRulesScrollPane);

		this.activeRulesList = new JList<Rule>();
		this.activeRulesList.setBorder(new LineBorder(new Color(128, 128, 128)));
		this.activeRulesList.setSize(388, 214);
		this.activeRulesList.setModel(new DefaultListModel<Rule>());
		this.activeRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		enableRulesScrollPane.setViewportView(this.activeRulesList);

		this.enableRuleButton = new JButton(Resources.ADD_RULE_ICON);
		this.enableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.enableRuleButton.setBounds(397, 130, 35, 35);
		this.enableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.enableRule();
			}
		});
		contentPanel.add(this.enableRuleButton);

		this.disableRuleButton = new JButton(Resources.REMOVE_RULE_ICON);
		this.disableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		this.disableRuleButton.setBounds(397, 177, 35, 35);
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
		disableRuleLabel.setBounds(442, 106, 375, 14);
		contentPanel.add(disableRuleLabel);

		JScrollPane disableRulesScrollPane = new JScrollPane();
		disableRulesScrollPane.setBounds(442, 130, 375, 216);
		contentPanel.add(disableRulesScrollPane);

		this.deactiveRulesList = new JList<Rule>();
		this.deactiveRulesList.setBorder(new LineBorder(new Color(128, 128, 128)));
		this.deactiveRulesList.setLocation(441, 0);
		this.deactiveRulesList.setModel(new DefaultListModel<Rule>());
		this.deactiveRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		disableRulesScrollPane.setViewportView(this.deactiveRulesList);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 358, 807, 2);
		contentPanel.add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(688, 372, 35, 35);
		contentPanel.add(this.progressLabel);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setBounds(782, 372, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.dispose();
			}
		});
		contentPanel.add(this.rejectButton);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setBounds(735, 372, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.saveRuleSet();
			}
		});
		contentPanel.add(this.commitButton);
	}

	/**
	 * La función encargada de recuperar todas las reglas que tenemos dentro del sistema y cargarlas dentro de la lista de reglas desactivadas.
	 */
	private void loadDisableRules() {
		try {
			DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

			for (Rule r : this.ruleService.findAll()) {
				deactiveRuleModel.addElement(r);
			}
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, HolderMessage.getMessage("ruleset.form.error.load.rule"),
					HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de filtrar las reglas que tenemos activadas dentro del conjunto para pasarlas a la otra lista.
	 */
	private void loadEnableRules() {
		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

		for (Rule r : this.ruleSet.getRules()) {
			if (deactiveRuleModel.contains(r)) {
				deactiveRuleModel.removeElement(r);
				activeRuleModel.addElement(r);
			}
		}
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
				Rule rule = activeRuleModel.remove(indexs[i]);
				deactiveRuleModel.addElement(rule);
			}
			this.activeRulesList.clearSelection();
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
			for (int i = 0; i < indexs.length; i++) {
				Rule rule = deactiveRuleModel.remove(indexs[i]);
				activeRuleModel.addElement(rule);
			}
			this.deactiveRulesList.clearSelection();
		}
	}

	/**
	 * La función encargada de guardar un conjunto de reglas.
	 */
	private void saveRuleSet() {
		try {
			this.fromDialogToRuleSet();
			try {
				this.ruleSetService.saveOrUpdate(this.ruleSet);
				this.dispose();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, HolderMessage.getMessage("ruleset.form.error.save"),
						HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
			}
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextPane.setText("");

		DefaultListModel<Rule> activeRuleModel = (DefaultListModel<Rule>) this.activeRulesList.getModel();
		DefaultListModel<Rule> deactiveRuleModel = (DefaultListModel<Rule>) this.deactiveRulesList.getModel();

		activeRuleModel.clear();
		deactiveRuleModel.clear();

		this.loadDisableRules();
	}

	/**
	 * La función que toma los datos del conjunto de reglas y los carga a la ventana.
	 */
	private void fromRuleSetToDialog() {
		this.descriptionTextPane.setText(this.ruleSet.getDescription());
		this.loadDisableRules();
		this.loadEnableRules();
	}

	/**
	 * La función que toma los datos de la ventana y los carga al conjunto de reglas.
	 * 
	 * @throws CheckedException
	 *             En caso de que el conjunto de reglas tenga algún parámetro fuera de lugar
	 */
	private void fromDialogToRuleSet() throws CheckedException {
		// La descripción del conjunto de reglas.
		if (Validator.descriptionValidator(this.descriptionTextPane.getText())) {
			this.ruleSet.setDescription(this.descriptionTextPane.getText());
		} else {
			throw new CheckedException("ruleset.form.error.description");
		}

		// El conjunto de las reglas.
		if (this.enableRuleModelList.getSize() > 0) {
			this.ruleSet.getRules().clear();
			for (Object o : this.enableRuleModelList.toArray()) {
				this.ruleSet.getRules().add((Rule) o);
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
		this.setTitle("Nuevo conjunto");
		this.ruleSet = new RuleSet();
		this.emptyField();
		return this;
	}

	/**
	 * La función que carga la ventana para modificar un conjunto de reglas que ya tenemos dentro de la base de datos.
	 * 
	 * @param editRuleSet
	 *            El conjunto de reglas que queremos editar.
	 * @return La ventana cargada con los datos para modificar un conjunto de reglas.
	 */
	public RuleSetFormDialog createEditDialog(RuleSet editRuleSet) {
		this.setTitle("Editar conjunto");
		this.ruleSet = editRuleSet;
		this.fromRuleSetToDialog();
		return this;
	}
}
