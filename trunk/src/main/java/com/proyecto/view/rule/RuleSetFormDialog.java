package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

	public void init() {
		this.setTitle("Grupos de Reglas");
		this.setBounds(100, 100, 832, 428);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel groupDescriptionLabel = new JLabel("Descripci\u00F3n del Grupo");
		groupDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		groupDescriptionLabel.setBounds(10, 11, 133, 14);
		contentPanel.add(groupDescriptionLabel);

		this.descriptionTextPane = new JTextPane();
		this.descriptionTextPane.setBounds(10, 36, 794, 58);
		contentPanel.add(this.descriptionTextPane);

		this.enableRuleModelList = new DefaultListModel<Rule>();
		this.enableRulesList = new JList<Rule>();
		this.enableRulesList.setModel(this.enableRuleModelList);
		this.enableRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		this.enableRulesList.setBounds(10, 130, 341, 216);
		contentPanel.add(this.enableRulesList);

		this.disableRuleModelList = new DefaultListModel<Rule>();
		this.disableRulesList = new JList<Rule>();
		this.disableRulesList.setModel(this.disableRuleModelList);
		this.disableRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		this.disableRulesList.setBounds(463, 130, 341, 216);
		contentPanel.add(this.disableRulesList);

		JLabel enableRuleLabel = new JLabel("Reglas Activas");
		enableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleLabel.setBounds(134, 106, 92, 14);
		contentPanel.add(enableRuleLabel);

		JLabel disableRuleLabel = new JLabel("Reglas Desactivadas");
		disableRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		disableRuleLabel.setBounds(571, 105, 125, 14);
		contentPanel.add(disableRuleLabel);

		JButton enableRuleButton = new JButton("Agregar >>");
		enableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		enableRuleButton.setBounds(361, 130, 92, 38);
		enableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.enableRule();
			}
		});
		contentPanel.add(enableRuleButton);

		JButton disableRuleButton = new JButton("Eliminar <<");
		disableRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		disableRuleButton.setBounds(361, 179, 92, 38);
		disableRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.disableRule();
			}
		});
		contentPanel.add(disableRuleButton);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aceptar");
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.saveRuleSet();
			}
		});
		this.getRootPane().setDefaultButton(okButton);
		buttonPane.add(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetFormDialog.this.dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	/**
	 * La función encargada de recuperar todas las reglas que tenemos dentro del sistema y cargarlas dentro de la lista de reglas desactivadas.
	 */
	private void loadDisableRules() {
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
		Integer index = this.enableRulesList.getSelectedIndex();
		if (index != -1) {
			Rule rule = this.enableRuleModelList.getElementAt(index);
			this.enableRuleModelList.removeElement(rule);
			this.disableRuleModelList.addElement(rule);
		}
	}

	/**
	 * La función que pasa una regla de desactivada a activada.
	 */
	private void enableRule() {
		Integer index = this.disableRulesList.getSelectedIndex();
		if (index != -1) {
			Rule rule = this.disableRuleModelList.getElementAt(index);
			this.disableRuleModelList.removeElement(rule);
			this.enableRuleModelList.addElement(rule);
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
		this.enableRuleModelList.clear();
		this.disableRuleModelList.clear();
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			RuleSetFormDialog dialog = HolderApplicationContext.getContext().getBean(RuleSetFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
