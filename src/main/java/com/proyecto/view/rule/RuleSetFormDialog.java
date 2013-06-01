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
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.rule.RuleService;
import com.proyecto.service.rule.RuleSetService;
import com.proyecto.util.Validator;

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
	 * Los modelos de las listas de reglas y sus listas.
	 */
	private DefaultListModel<Rule> enableRuleModelList;
	private JList<Rule> enableRulesList;
	private DefaultListModel<Rule> disableRuleModelList;
	private JList<Rule> disableRulesList;
	/**
	 * La descripción del conjunto de reglas.
	 */
	private JTextPane descriptionTextPane;

	/**
	 * Create the dialog.
	 */
	public RuleSetFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de crear la ventana de edición de conjuntos.
	 */
	public void init() {
		this.setResizable(false);
		this.setBounds(100, 100, 840, 425);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel descriptionLabel = new JLabel("Descripci\u00F3n");
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 11, 133, 14);
		contentPanel.add(descriptionLabel);

		this.descriptionTextPane = new JTextPane();
		this.descriptionTextPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
		this.descriptionTextPane.setFont(new Font("Arial", Font.PLAIN, 12));
		this.descriptionTextPane.setBounds(10, 36, 815, 58);
		contentPanel.add(this.descriptionTextPane);

		JScrollPane enableRulesScrollPane = new JScrollPane();
		enableRulesScrollPane.setBounds(10, 130, 375, 216);
		contentPanel.add(enableRulesScrollPane);

		this.enableRuleModelList = new DefaultListModel<Rule>();
		this.enableRulesList = new JList<Rule>();
		this.enableRulesList.setSize(388, 214);
		this.enableRulesList.setModel(this.enableRuleModelList);
		this.enableRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		enableRulesScrollPane.add(this.enableRulesList);
		enableRulesScrollPane.setViewportView(this.enableRulesList);

		JScrollPane disableRulesScrollPane = new JScrollPane();
		disableRulesScrollPane.setBounds(450, 130, 375, 216);
		contentPanel.add(disableRulesScrollPane);

		this.disableRuleModelList = new DefaultListModel<Rule>();
		this.disableRulesList = new JList<Rule>();
		this.disableRulesList.setLocation(441, 0);
		this.disableRulesList.setModel(this.disableRuleModelList);
		this.disableRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		disableRulesScrollPane.add(this.disableRulesList);
		disableRulesScrollPane.setViewportView(this.disableRulesList);

		JLabel enableRuleLabel = new JLabel("Reglas Activas");
		enableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleLabel.setBounds(157, 106, 80, 14);
		contentPanel.add(enableRuleLabel);

		JLabel disableRuleLabel = new JLabel("Reglas Desactivadas");
		disableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		disableRuleLabel.setBounds(581, 105, 112, 14);
		contentPanel.add(disableRuleLabel);

		JButton enableRuleButton = new JButton("<<");
		enableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleButton.setBounds(395, 130, 45, 38);
		enableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.enableRule();
			}
		});
		contentPanel.add(enableRuleButton);

		JButton disableRuleButton = new JButton(">>");
		disableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		disableRuleButton.setBounds(395, 179, 45, 38);
		disableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.disableRule();
			}
		});
		contentPanel.add(disableRuleButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setBounds(725, 357, 100, 30);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.dispose();
			}
		});
		contentPanel.add(cancelButton);

		JButton commitButton = new JButton("Aceptar");
		commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		commitButton.setBounds(615, 357, 100, 30);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.saveRuleSet();
			}
		});
		contentPanel.add(commitButton);
		this.getRootPane().setDefaultButton(commitButton);
	}

	/**
	 * La función encargada de recuperar todas las reglas que tenemos dentro del sistema y cargarlas dentro de la lista de reglas desactivadas.
	 */
	private void loadDisableRules() {
		this.enableRuleModelList.clear();
		this.disableRuleModelList.clear();
		try {
			for (Rule r : this.ruleService.findAll()) {
				this.disableRuleModelList.addElement(r);
			}
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de filtrar las reglas que tenemos activadas dentro del conjunto para pasarlas a la otra lista.
	 */
	private void loadEnableRules() {
		for (Rule r : this.ruleSet.getRules()) {
			if (this.disableRuleModelList.contains(r)) {
				this.disableRuleModelList.removeElement(r);
				this.enableRuleModelList.addElement(r);
			}
		}
	}

	/**
	 * La función que pasa una regla de activada a desactivada.
	 */
	private void disableRule() {
		int[] indexs = this.enableRulesList.getSelectedIndices();
		if (indexs.length > 0) {
			for (int i = 0; i < indexs.length; i++) {
				Rule rule = this.enableRuleModelList.getElementAt(indexs[i]);
				this.enableRuleModelList.removeElement(rule);
				this.disableRuleModelList.addElement(rule);
			}
			this.enableRulesList.clearSelection();
		}
	}

	/**
	 * La función que pasa una regla de desactivada a activada.
	 */
	private void enableRule() {
		int[] indexs = this.disableRulesList.getSelectedIndices();
		if (indexs.length > 0) {
			for (int i = 0; i < indexs.length; i++) {
				Rule rule = this.disableRuleModelList.getElementAt(indexs[i]);
				this.disableRuleModelList.removeElement(rule);
				this.enableRuleModelList.addElement(rule);
			}
			this.disableRulesList.clearSelection();
		}
	}

	/**
	 * La función encargada de guardar un conjunto de reglas.
	 */
	private void saveRuleSet() {
		try {
			this.fromDialogToRuleSet();
			this.ruleSetService.saveOrUpdate(this.ruleSet);
			this.dispose();
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de vaciar los campos de una regla.
	 */
	private void emptyField() {
		this.descriptionTextPane.setText("");
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
			throw new CheckedException("rule.set.description.empty");
		}

		// El conjunto de las reglas.
		if (this.enableRuleModelList.getSize() > 0) {
			this.ruleSet.getRules().clear();
			for (Object o : this.enableRuleModelList.toArray()) {
				this.ruleSet.getRules().add((Rule) o);
			}
		} else {
			throw new CheckedException("rule.set.rules.empty");
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
