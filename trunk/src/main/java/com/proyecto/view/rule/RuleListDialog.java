package com.proyecto.view.rule;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.border.LineBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.security.AccessControl;
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
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

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
	 * Los botones de acción.
	 */
	private JButton newRuleButton;
	private JButton modifyRuleButton;
	private JButton deleteRuleButton;
	private JButton closeButton;
	/**
	 * El label de progreso
	 */
	private JLabel progressLabel;

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
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 541, 400);

		JPanel contentPanel = new JPanel();
		contentPanel.setFont(new Font("Arial", Font.PLAIN, 12));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel ruleListLabel = new JLabel(HolderMessage.getMessage("rule.manager.label.rule.list"));
		ruleListLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleListLabel.setBounds(10, 10, 477, 16);
		contentPanel.add(ruleListLabel);

		JScrollPane rulesScrollPane = new JScrollPane();
		rulesScrollPane.setFont(contentPanel.getFont());
		rulesScrollPane.setBounds(6, 27, 481, 340);
		contentPanel.add(rulesScrollPane);

		this.ruleList = new JList<Rule>();
		this.ruleList.setBorder(new LineBorder(Color.GRAY));
		this.ruleList.setModel(new DefaultListModel<Rule>());
		this.ruleList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleList.setFont(rulesScrollPane.getFont());
		this.ruleList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					RuleListDialog.this.modifyRule();
				}
			}
		});
		rulesScrollPane.setViewportView(this.ruleList);

		this.newRuleButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newRuleButton.setBounds(493, 27, 35, 35);
		this.newRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.newRule();
			}
		});
		contentPanel.add(this.newRuleButton);

		this.modifyRuleButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyRuleButton.setBounds(493, 69, 35, 35);
		this.modifyRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.modifyRule();
			}
		});
		contentPanel.add(this.modifyRuleButton);

		this.deleteRuleButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteRuleButton.setBounds(493, 111, 35, 35);
		contentPanel.add(this.deleteRuleButton);
		this.deleteRuleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.deleteRule();
			}
		});

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(493, 285, 35, 35);
		contentPanel.add(this.progressLabel);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setBounds(493, 332, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleListDialog.this.dispose();
			}
		});
		contentPanel.add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.ruleList.setEnabled(enabled);

		this.newRuleButton.setEnabled(enabled);
		this.modifyRuleButton.setEnabled(enabled);
		this.deleteRuleButton.setEnabled(enabled);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de recuperar todas las reglas que tenemos dentro del sistema y cargarlas dentro de la lista de reglas.
	 */
	private void loadRules() {
		new Thread() {
			@Override
			public void run() {
				try {
					RuleListDialog.this.beforeExecuteProccess();

					DefaultListModel<Rule> ruleModel = (DefaultListModel<Rule>) RuleListDialog.this.ruleList.getModel();
					ruleModel.clear();

					for (Rule r : RuleListDialog.this.ruleService.findAll()) {
						ruleModel.addElement(r);
					}
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleListDialog.this, HolderMessage.getMessage("rule.manager.load.rules.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
				} finally {
					RuleListDialog.this.afterExecuteProccess();
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
	 * La función para dar de alta una nueva regla.
	 */
	private void newRule() {
		RuleFormDialog dialog = this.ruleFormDialog.createNewDialog();
		dialog.setLocationRelativeTo(this);
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
			dialog.setVisible(true);
			this.loadRules();
		}
	}

	/**
	 * La función para eliminar una regla.
	 */
	private void deleteRule() {
		if (this.ruleList.getSelectedIndex() != -1) {
			final Rule rule = this.ruleList.getSelectedValue();
			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("rule.manager.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
					@Override
					public void run() {
						try {
							RuleListDialog.this.beforeExecuteProccess();
							RuleListDialog.this.ruleService.delete(rule);
							RuleListDialog.this.loadRules();
						} catch (CheckedException ex) {
							JOptionPane.showMessageDialog(RuleListDialog.this, HolderMessage.getMessage("rule.manager.delete.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							RuleListDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La función encargada de inicializar la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar el listado de las reglas.
	 */
	public RuleListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("rule.manager.title"));

		this.loadRules();

		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			RuleListDialog dialog = HolderApplicationContext.getContext().getBean(RuleListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
