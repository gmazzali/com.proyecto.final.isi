package com.proyecto.view.rule;

import java.awt.BorderLayout;
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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.service.rule.RuleSetService;

/**
 * La ventana donde vamos a desplegar el listado de los conjuntos de reglas que vamos a tener dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class RuleSetListDialog extends JDialog {

	private static final long serialVersionUID = 7024956097195227299L;

	/**
	 * El servicio de conjuntos.
	 */
	@Autowired
	private RuleSetService ruleSetService;

	/**
	 * La ventana del listado de las reglas y la de edición de conjuntos.
	 */
	@Autowired
	private RuleListDialog ruleListDialog;
	@Autowired
	private RuleSetFormDialog ruleSetFormDialog;

	/**
	 * Los modelos de las listas de conjuntos y sus reglas.
	 */
	private DefaultListModel<RuleSet> ruleSetModelList;
	private JList<RuleSet> ruleSetList;
	private DefaultListModel<Rule> ruleModelList;
	private JList<Rule> ruleList;

	/**
	 * Constructor de la ventana que despliega el listado de los conjuntos de reglas.
	 */
	public RuleSetListDialog() {
		super();
		this.setResizable(false);
		this.init();
	}

	private void init() {
		this.setBounds(100, 100, 568, 546);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel ruleSetLabel = new JLabel("Conjuntos");
		ruleSetLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetLabel.setBounds(6, 6, 57, 14);
		contentPanel.add(ruleSetLabel);

		JScrollPane ruleSetScrollPane = new JScrollPane();
		ruleSetScrollPane.setBounds(6, 21, 435, 284);
		contentPanel.add(ruleSetScrollPane);

		this.ruleSetModelList = new DefaultListModel<RuleSet>();
		this.ruleSetList = new JList<RuleSet>();
		this.ruleSetList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				RuleSetListDialog.this.loadRules();
			}
		});
		this.ruleSetList.setModel(this.ruleSetModelList);
		this.ruleSetList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleSetList.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetScrollPane.add(this.ruleSetList);
		ruleSetScrollPane.setViewportView(this.ruleSetList);

		JLabel ruleLabel = new JLabel("Reglas");
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(6, 317, 45, 14);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 333, 435, 134);
		contentPanel.add(ruleScrollPane);

		this.ruleModelList = new DefaultListModel<Rule>();
		this.ruleList = new JList<Rule>();
		this.ruleList.setModel(this.ruleModelList);
		this.ruleList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleList.setFont(new Font("Arial", Font.BOLD, 11));
		ruleScrollPane.add(this.ruleList);
		ruleScrollPane.setViewportView(this.ruleList);

		JButton ruleManagerButton = new JButton("Gestionar Reglas");
		ruleManagerButton.setFont(new Font("Arial", Font.BOLD, 12));
		ruleManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleSetListDialog.this.managerRule();
			}
		});
		ruleManagerButton.setBounds(6, 479, 144, 30);
		contentPanel.add(ruleManagerButton);

		JButton newButton = new JButton("Crear");
		newButton.setFont(new Font("Arial", Font.BOLD, 12));
		newButton.setBounds(453, 21, 100, 30);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.newRuleSet();
			}
		});
		contentPanel.add(newButton);

		JButton modifyButton = new JButton("Modificar");
		modifyButton.setFont(new Font("Arial", Font.BOLD, 12));
		modifyButton.setBounds(453, 63, 100, 30);
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.modifyRuleSet();
			}
		});
		contentPanel.add(modifyButton);

		JButton deleteButton = new JButton("Borrar");
		deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
		deleteButton.setBounds(453, 105, 100, 30);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.deleteRuleSet();
			}
		});
		contentPanel.add(deleteButton);

		JButton backButton = new JButton("Volver");
		backButton.setFont(new Font("Arial", Font.BOLD, 12));
		backButton.setBounds(453, 479, 100, 30);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.dispose();
			}
		});
		contentPanel.add(backButton);
	}

	/**
	 * La función encargada de cargar los conjuntos de reglas.
	 */
	private void loadRuleSets() {
		this.ruleSetModelList.clear();
		try {
			for (RuleSet rs : this.ruleSetService.findAll()) {
				this.ruleSetModelList.addElement(rs);
			}
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * La función que despliega el listado de las reglas que tenemos dentro del conjunto que seleccionamos.
	 */
	private void loadRules() {
		this.ruleModelList.clear();
		Integer index = this.ruleSetList.getSelectedIndex();
		if (index != -1) {
			RuleSet ruleSet = this.ruleSetModelList.get(index);
			for (Rule r : ruleSet.getRules()) {
				this.ruleModelList.addElement(r);
			}
		}
	}

	/**
	 * La función para dar de alta un nuevo conjunto de reglas.
	 */
	private void newRuleSet() {
		RuleSetFormDialog dialog = this.ruleSetFormDialog.createNewDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setModal(true);
		dialog.setVisible(true);
		this.loadRuleSets();
	}

	/**
	 * La función para modificar un conjunto de reglas.
	 */
	private void modifyRuleSet() {
		Integer index = this.ruleSetList.getSelectedIndex();
		if (index != -1) {
			RuleSet ruleSet = this.ruleSetModelList.get(index);
			RuleSetFormDialog dialog = this.ruleSetFormDialog.createEditDialog(ruleSet);
			dialog.setLocationRelativeTo(this);
			dialog.setModal(true);
			dialog.setVisible(true);
			this.loadRuleSets();
		}
	}

	/**
	 * La función para eliminar un conjunto de reglas.
	 */
	private void deleteRuleSet() {
		Integer index = this.ruleSetList.getSelectedIndex();
		if (index != -1) {
			RuleSet ruleSet = this.ruleSetModelList.get(index);
			if (JOptionPane.showConfirmDialog(this, "Está seguro de borrar el conjunto \"" + ruleSet.getDescription() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					this.ruleSetService.delete(ruleSet);
					this.loadRuleSets();
				} catch (CheckedException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * La ventana que despliega la ventana de gestión de reglas.
	 */
	protected void managerRule() {
		RuleListDialog dialog = this.ruleListDialog.createDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setModal(true);
		dialog.setVisible(true);
		this.loadRuleSets();
	}

	/**
	 * La función encargada de inicializar la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar el listado de las reglas.
	 */
	public RuleSetListDialog createDialog() {
		this.setTitle("Listado de conjuntos de reglas");
		this.loadRuleSets();
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

			RuleSetListDialog dialog = HolderApplicationContext.getContext().getBean(RuleSetListDialog.class).createDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
