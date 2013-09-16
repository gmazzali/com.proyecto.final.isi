package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.rule.RuleService;
import com.proyecto.view.Resources;

/**
 * La ventana donde vamos a desplegar el listado de las reglas que vamos a tener dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class RuleListDialog extends JDialog {

	private static final long serialVersionUID = 4956186132310235753L;

	/**
	 * El servicio de reglas.
	 */
	@Autowired
	private RuleService ruleService;

	/**
	 * La ventana de edición de reglas.
	 */
	@Autowired
	private RuleFormDialog ruleFormDialog;

	/**
	 * Los modelos de las listas de reglas y sus listas.
	 */
	private JList<Rule> ruleList;

	/**
	 * Constructor de la ventana de listado de reglas.
	 */
	public RuleListDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana.
	 */
	public void init() {
		this.setBounds(100, 100, 601, 395);
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane rulesScrollPane = new JScrollPane();
		rulesScrollPane.setBounds(10, 11, 457, 340);
		contentPanel.add(rulesScrollPane);

		this.ruleList = new JList<Rule>();
		this.ruleList.setModel(new DefaultListModel<Rule>());
		this.ruleList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleList.setFont(new Font("Arial", Font.PLAIN, 12));
		this.ruleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					RuleListDialog.this.modifyRule();
				}
			}
		});
		rulesScrollPane.setViewportView(this.ruleList);

		JButton newRuleButton = new JButton(Resources.ADD_ELEMENT_ICON);
		newRuleButton.setBounds(479, 11, 35, 35);
		newRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.newRule();
			}
		});
		contentPanel.add(newRuleButton);

		JButton modifyRuleButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		modifyRuleButton.setBounds(479, 53, 35, 35);
		modifyRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.modifyRule();
			}
		});
		contentPanel.add(modifyRuleButton);

		JButton deleteRuleButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		deleteRuleButton.setBounds(479, 95, 35, 35);
		contentPanel.add(deleteRuleButton);
		deleteRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.deleteRule();
			}
		});

		JButton backButton = new JButton(Resources.CLOSE_ICON);
		backButton.setBounds(479, 321, 35, 35);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.dispose();
			}
		});
		contentPanel.add(backButton);
	}

	/**
	 * La función encargada de recuperar todas las reglas que tenemos dentro del sistema y cargarlas dentro de la lista de reglas.
	 */
	private void loadRules() {
		this.ruleModelList.clear();
		try {
			for (Rule r : this.ruleService.findAll()) {
				this.ruleModelList.addElement(r);
			}
		} catch (CheckedException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		this.loadRules();
	}

	/**
	 * La función para modificar una regla.
	 */
	private void modifyRule() {
		if (this.ruleList.getSelectedIndex() != -1) {
			Rule rule = this.ruleList.getSelectedValue();
			RuleFormDialog dialog = this.ruleFormDialog.createEditDialog(rule);
			dialog.setLocationRelativeTo(this);
			dialog.setModal(true);
			dialog.setVisible(true);
			this.loadRules();
		}
	}

	/**
	 * La función para eliminar una regla.
	 */
	private void deleteRule() {
		if (this.ruleList.getSelectedIndex() != -1) {
			Rule rule = this.ruleList.getSelectedValue();
			if (JOptionPane.showConfirmDialog(this, "Está seguro de borrar la regla \"" + rule.getDescription() + "\"?", "Confirmación",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					this.ruleService.delete(rule);
					this.loadRules();
				} catch (CheckedException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * La función encargada de inicializar la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar el listado de las reglas.
	 */
	public RuleListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("rule.manager.dialog.title"));
		
		this.loadRules();
		
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

			RuleListDialog dialog = HolderApplicationContext.getContext().getBean(RuleListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
