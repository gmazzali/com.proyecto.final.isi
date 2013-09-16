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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.rule.Rule;
import com.proyecto.model.rule.RuleSet;
import com.proyecto.security.AccessControl;
import com.proyecto.service.rule.RuleSetService;
import com.proyecto.view.Resources;

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
	 * El control de acceso.
	 */
	@Autowired
	private AccessControl accessControl;

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
	private JList<RuleSet> ruleSetList;
	private JList<Rule> ruleList;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana que despliega el listado de los conjuntos de reglas.
	 */
	public RuleSetListDialog() {
		super();
		this.setResizable(false);
		this.init();
	}

	private void init() {
		this.setBounds(100, 100, 562, 556);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel ruleSetLabel = new JLabel(HolderMessage.getMessage("ruleset.manager.dialog.label.sets"));
		ruleSetLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetLabel.setBounds(6, 6, 503, 14);
		contentPanel.add(ruleSetLabel);

		JScrollPane ruleSetScrollPane = new JScrollPane();
		ruleSetScrollPane.setBounds(6, 21, 503, 284);
		contentPanel.add(ruleSetScrollPane);

		this.ruleSetList = new JList<RuleSet>();
		this.ruleSetList.setBorder(new LineBorder(Color.GRAY));
		this.ruleSetList.setModel(new DefaultListModel<RuleSet>());
		this.ruleSetList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				RuleSetListDialog.this.loadRules();
			}
		});
		this.ruleSetList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ruleSetScrollPane.setViewportView(this.ruleSetList);

		JLabel ruleLabel = new JLabel(HolderMessage.getMessage("ruleset.manager.dialog.label.rules"));
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(6, 317, 503, 14);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 333, 503, 189);
		contentPanel.add(ruleScrollPane);

		this.ruleList = new JList<Rule>();
		this.ruleList.setBorder(new LineBorder(Color.GRAY));
		this.ruleList.setModel(new DefaultListModel<Rule>());
		this.ruleList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleList.setFont(new Font("Arial", Font.BOLD, 11));
		ruleScrollPane.setViewportView(this.ruleList);

		JButton ruleManagerButton = new JButton("");
		ruleManagerButton.setFont(new Font("Arial", Font.BOLD, 12));
		ruleManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleSetListDialog.this.managerRule();
			}
		});
		ruleManagerButton.setBounds(515, 333, 35, 35);
		contentPanel.add(ruleManagerButton);

		JButton newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		newButton.setBounds(515, 21, 35, 35);
		newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.newRuleSet();
			}
		});
		contentPanel.add(newButton);

		JButton modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		modifyButton.setBounds(515, 68, 35, 35);
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.modifyRuleSet();
			}
		});
		contentPanel.add(modifyButton);

		JButton deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		deleteButton.setBounds(515, 115, 35, 35);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.deleteRuleSet();
			}
		});
		contentPanel.add(deleteButton);

		JButton backButton = new JButton(Resources.CLOSE_ICON);
		backButton.setBounds(515, 487, 35, 35);
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.dispose();
			}
		});
		contentPanel.add(backButton);

		this.progressLabel = new JLabel("");
		this.progressLabel.setBounds(515, 440, 35, 35);
		contentPanel.add(this.progressLabel);
	}

	/**
	 * La función encargada de cargar los conjuntos de reglas.
	 */
	private void loadRuleSets() {
		new Thread() {
			@Override
			public void run() {
				RuleSetListDialog.this.beforeExecuteProccess();

				// Vaciamos la lista de conjuntos de reglas.
				DefaultListModel<RuleSet> model = (DefaultListModel<RuleSet>) RuleSetListDialog.this.ruleSetList.getModel();
				model.clear();

				try {
					for (RuleSet ruleSet : RuleSetListDialog.this.ruleSetService.findBySubject(RuleSetListDialog.this.accessControl
							.getSubjectSelected())) {
						model.addElement(ruleSet);
					}
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.dialog.load.ruleset.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					RuleSetListDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función que despliega el listado de las reglas que tenemos dentro del conjunto que seleccionamos.
	 */
	private void loadRules() {

		new Thread() {
			@Override
			public void run() {
				try {
					RuleSetListDialog.this.beforeExecuteProccess();

					DefaultListModel<RuleSet> ruleSetModel = (DefaultListModel<RuleSet>) RuleSetListDialog.this.ruleSetList.getModel();
					DefaultListModel<Rule> ruleModel = (DefaultListModel<Rule>) RuleSetListDialog.this.ruleList.getModel();

					// Vaciamos la lista de reglas.
					ruleModel.clear();

					// Obtenemos el conjunto seleccionado.
					Integer index = RuleSetListDialog.this.ruleSetList.getSelectedIndex();
					if (index != -1) {
						RuleSet ruleSet = ruleSetModel.get(index);

						// Cargamos las reglas del conjunto seleccionado.
						for (Rule r : ruleSet.getRules()) {
							ruleModel.addElement(r);
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.dialog.load.rules.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					RuleSetListDialog.this.afterExecuteProccess();
				}
			}
		}.start();
	}

	/**
	 * La función antes de procesar los reactivos.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de procesar los reactivos.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
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
			DefaultListModel<RuleSet> ruleSetModel = (DefaultListModel<RuleSet>) RuleSetListDialog.this.ruleSetList.getModel();
			RuleSet ruleSet = ruleSetModel.get(index);

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
			DefaultListModel<RuleSet> ruleSetModel = (DefaultListModel<RuleSet>) RuleSetListDialog.this.ruleSetList.getModel();
			final RuleSet ruleSet = ruleSetModel.get(index);

			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("ruleset.manager.dialog.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
					@Override
					public void run() {
						try {
							RuleSetListDialog.this.beforeExecuteProccess();
							RuleSetListDialog.this.ruleSetService.delete(ruleSet);
							RuleSetListDialog.this.loadRuleSets();
						} catch (CheckedException ex) {
							JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.dialog.delete.failed"),
									HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
						} finally {
							RuleSetListDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La ventana que despliega la ventana de gestión de reglas.
	 */
	protected void managerRule() {
		RuleListDialog dialog = this.ruleListDialog.createDialog();
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		this.loadRuleSets();
	}

	/**
	 * La función encargada de inicializar la ventana para desplegarse.
	 * 
	 * @return La ventana para desplegar el listado de las reglas.
	 */
	public RuleSetListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("ruleset.manager.dialog.title"));
		this.loadRuleSets();
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

			RuleSetListDialog dialog = HolderApplicationContext.getContext().getBean(RuleSetListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
