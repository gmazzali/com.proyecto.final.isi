package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.rule.RuleService;
import com.proyecto.service.rule.RuleSetService;

/**
 * La clase que despliega una ventana con los listados de conjuntos y de reglas, detallando las que tenemos activas dentro del conjunto que activamos.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class RuleManagerDialog extends JDialog {

	private static final long serialVersionUID = -5483954371881234128L;

	/**
	 * Las ventanas de edición de reglas y de conjuntos.
	 */
	@Autowired
	private RuleSetFormDialog ruleSetFormDialog;
	@Autowired
	private RuleFormDialog ruleFormDialog;

	/**
	 * Los servicios de reglas y de conjuntos.
	 */
	@Autowired
	private RuleService ruleService;
	@Autowired
	private RuleSetService ruleSetService;

	/**
	 * Los listado de los conjuntos y las reglas.
	 */
	private List<RuleSet> ruleSetList;
	private List<Rule> ruleList;

	/**
	 * El modelo y la tabla donde desplegamos las reglas.
	 */
	private DefaultTableModel ruleTableModel;
	private JTable ruleTable;

	/**
	 * El combo de los conjuntos de las reglas.
	 */
	private JComboBox<RuleSet> comboRuleSet;

	/**
	 * Constructor de una ventana de edición de reglas y conjuntos.
	 */
	public RuleManagerDialog() {
		super();
		this.init();
	}

	/**
	 * La función para inicializar la ventana.
	 */
	@SuppressWarnings("serial")
	private void init() {
		this.setTitle("Administracion de Conjuntos de Reglas\r\n");
		this.setResizable(false);
		this.setBounds(100, 100, 542, 442);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		this.comboRuleSet = new JComboBox<RuleSet>();
		this.comboRuleSet.setBounds(10, 40, 396, 29);
		contentPanel.add(this.comboRuleSet);

		JLabel setRuleLabel = new JLabel("Conjuntos");
		setRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		setRuleLabel.setBounds(10, 23, 76, 14);
		contentPanel.add(setRuleLabel);

		JButton newRuleSetButton = new JButton("Nuevo");
		newRuleSetButton.setFont(new Font("Arial", Font.BOLD, 11));
		newRuleSetButton.setBounds(10, 98, 89, 23);
		newRuleSetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.newRuleSet();
			}
		});
		contentPanel.add(newRuleSetButton);

		JButton modifiyRuleSetButton = new JButton("Modificar");
		modifiyRuleSetButton.setFont(new Font("Arial", Font.BOLD, 11));
		modifiyRuleSetButton.setBounds(214, 98, 89, 23);
		modifiyRuleSetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.modifyRuleSet();
			}
		});
		contentPanel.add(modifiyRuleSetButton);

		JButton deleteRuleSetButton = new JButton("Eliminar");
		deleteRuleSetButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRuleSetButton.setBounds(114, 98, 89, 23);
		deleteRuleSetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.deleteRuleSet();
			}
		});
		contentPanel.add(deleteRuleSetButton);

		JButton applySetRuleButton = new JButton("Aplicar");
		applySetRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		applySetRuleButton.setBounds(317, 98, 89, 23);
		contentPanel.add(applySetRuleButton);

		this.ruleTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		this.ruleTableModel.addColumn("Descripción");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 181, 320, 179);
		contentPanel.add(scrollPane);

		this.ruleTable = new JTable();
		this.ruleTable.setModel(this.ruleTableModel);
		scrollPane.add(this.ruleTable);
		scrollPane.setViewportView(this.ruleTable);

		JPanel panel = new JPanel();
		panel.setBounds(366, 181, 150, 112);
		contentPanel.add(panel);

		panel.setLayout(null);

		JButton newRuleButton = new JButton("Nueva Regla");
		newRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		newRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.newRule();
			}
		});
		newRuleButton.setBounds(11, 5, 129, 23);
		panel.add(newRuleButton);

		JButton modifyRuleButton = new JButton("Editar Regla");
		modifyRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		modifyRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.modifyRule();
			}
		});
		modifyRuleButton.setBounds(11, 73, 129, 23);
		panel.add(modifyRuleButton);

		JButton deleteRuleButton = new JButton("Eliminar Regla");
		deleteRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.deleteRule();
			}
		});
		deleteRuleButton.setBounds(11, 39, 129, 23);
		panel.add(deleteRuleButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 143, 526, 2);
		contentPanel.add(separator);

		JLabel setRulesList = new JLabel("Reglas");
		setRulesList.setFont(new Font("Arial", Font.BOLD, 11));
		setRulesList.setBounds(10, 156, 76, 14);
		contentPanel.add(setRulesList);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Aceptar");
		okButton.setFont(new Font("Arial", Font.BOLD, 12));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		this.getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleManagerDialog.this.dispose();
			}
		});
		cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}

	/**
	 * La función que nos permite cargar el combo de conjuntos de reglas.
	 */
	private void loadRuleSetsInComboBox() {
		this.comboRuleSet.removeAllItems();
		try {
			this.ruleSetList = this.ruleSetService.findAll();
			for (RuleSet rs : this.ruleSetList) {
				this.comboRuleSet.addItem(rs);
			}
			// Seleccionamos el conjunto que tenemos activo.
			Boolean active = false;
			for (int i = 0; i < this.comboRuleSet.getItemCount(); i++) {
				RuleSet ruleSet = this.comboRuleSet.getItemAt(i);
				if (ruleSet.getActive()) {
					this.comboRuleSet.setSelectedItem(ruleSet);
					active = true;
					break;
				}
			}
			if (!active) {
				this.comboRuleSet.setSelectedIndex(-1);
			}
		} catch (CheckedException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de cargar la tabla de las reglas.
	 */
	private void loadRulesInTable() {
		this.ruleTableModel.getDataVector().clear();
		try {
			this.ruleList = this.ruleService.findAll();
			for (Rule r : this.ruleList) {
				String[] row = new String[1];
				row[0] = r.getDescription();
				this.ruleTableModel.addRow(row);
			}
		} catch (CheckedException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función encargada de actualizar el contenido de la ventana.
	 */
	private void refresh() {
		this.loadRuleSetsInComboBox();
		this.loadRulesInTable();
	}

	/**
	 * La función para dar de alta un nuevo conjunto de reglas.
	 */
	private void newRuleSet() {
		RuleSetFormDialog dialog = this.ruleSetFormDialog.createNewDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setModal(true);
		dialog.setVisible(true);
		this.refresh();
	}

	/**
	 * La función para modificar un conjunto de reglas.
	 */
	private void modifyRuleSet() {
		if (this.comboRuleSet.getSelectedItem() != null) {
			RuleSet ruleSet = (RuleSet) this.comboRuleSet.getSelectedItem();
			RuleSetFormDialog dialog = this.ruleSetFormDialog.createEditDialog(ruleSet);
			dialog.setLocationRelativeTo(this);
			dialog.setModal(true);
			dialog.setVisible(true);
			this.refresh();
		}
	}

	/**
	 * La función para eliminar un conjunto de reglas.
	 */
	private void deleteRuleSet() {
		if (this.comboRuleSet.getSelectedItem() != null) {
			RuleSet ruleSet = (RuleSet) this.comboRuleSet.getSelectedItem();
			if (JOptionPane.showConfirmDialog(this, "Está seguro de borrar el conjunto \"" + ruleSet.getDescription() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					this.ruleSetService.delete(ruleSet);
					this.refresh();
				} catch (CheckedException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * La función para dar de alta una nueva regla.
	 */
	private void newRule() {
		RuleFormDialog dialog = this.ruleFormDialog.createNewDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setModal(true);
		dialog.setVisible(true);
		this.refresh();
	}

	/**
	 * La función para modificar una regla.
	 */
	private void modifyRule() {
		if (this.ruleTable.getSelectedRow() != -1) {
			Rule rule = this.ruleList.get(this.ruleTable.convertRowIndexToModel(this.ruleTable.getSelectedRow()));
			RuleFormDialog dialog = this.ruleFormDialog.createEditDialog(rule);
			dialog.setLocationRelativeTo(this);
			dialog.setModal(true);
			dialog.setVisible(true);
			this.refresh();
		}
	}

	/**
	 * La función para eliminar una regla.
	 */
	private void deleteRule() {
		if (this.ruleTable.getSelectedRow() != -1) {
			Rule rule = this.ruleList.get(this.ruleTable.convertRowIndexToModel(this.ruleTable.getSelectedRow()));
			if (JOptionPane.showConfirmDialog(this, "Está seguro de borrar la regla \"" + rule.getDescription() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					this.ruleService.delete(rule);
					this.refresh();
				} catch (CheckedException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * La función que nos configura la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar.
	 */
	public RuleManagerDialog createRuleManagerDialog() {
		this.refresh();
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

			RuleManagerDialog dialog = HolderApplicationContext.getContext().getBean(RuleManagerDialog.class).createRuleManagerDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
