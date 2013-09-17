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
	 * Los botones de acción.
	 */
	private JButton newButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton ruleManagerButton;
	private JButton closeButton;
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
		this.setBounds(100, 100, 562, 560);

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel ruleSetLabel = new JLabel(HolderMessage.getMessage("ruleset.manager.label.sets"));
		ruleSetLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleSetLabel.setBounds(10, 10, 499, 16);
		contentPanel.add(ruleSetLabel);

		JScrollPane ruleSetScrollPane = new JScrollPane();
		ruleSetScrollPane.setBounds(6, 27, 503, 278);
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

		JLabel ruleLabel = new JLabel(HolderMessage.getMessage("ruleset.manager.label.rules"));
		ruleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		ruleLabel.setBounds(10, 317, 499, 16);
		contentPanel.add(ruleLabel);

		JScrollPane ruleScrollPane = new JScrollPane();
		ruleScrollPane.setBounds(6, 334, 503, 191);
		contentPanel.add(ruleScrollPane);

		this.ruleList = new JList<Rule>();
		this.ruleList.setBorder(new LineBorder(Color.GRAY));
		this.ruleList.setModel(new DefaultListModel<Rule>());
		this.ruleList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.ruleList.setFont(new Font("Arial", Font.BOLD, 11));
		ruleScrollPane.setViewportView(this.ruleList);

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setBounds(515, 27, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.newRuleSet();
			}
		});
		contentPanel.add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setBounds(515, 74, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.modifyRuleSet();
			}
		});
		contentPanel.add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteButton.setBounds(515, 121, 35, 35);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.deleteRuleSet();
			}
		});
		contentPanel.add(this.deleteButton);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setBounds(515, 490, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RuleSetListDialog.this.dispose();
			}
		});

		this.ruleManagerButton = new JButton(Resources.CRUD_ICON);
		this.ruleManagerButton.setBounds(515, 334, 35, 35);
		this.ruleManagerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				RuleSetListDialog.this.managerRule();
			}
		});
		contentPanel.add(this.ruleManagerButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(515, 443, 35, 35);
		contentPanel.add(this.progressLabel);
		contentPanel.add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.ruleSetList.setEnabled(enabled);
		this.ruleList.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
		this.ruleManagerButton.setEnabled(enabled);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de cargar los conjuntos de reglas.
	 */
	private void loadRuleSets() {
		new Thread() {
			@Override
			public void run() {
				RuleSetListDialog.this.beforeExecuteProccess();
				DefaultListModel<RuleSet> model = new DefaultListModel<RuleSet>();

				try {
					for (RuleSet ruleSet : RuleSetListDialog.this.ruleSetService.findBySubject(RuleSetListDialog.this.accessControl
							.getSubjectSelected())) {
						model.addElement(ruleSet);
					}

				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.load.ruleset.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					RuleSetListDialog.this.ruleSetList.setModel(model);
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
				RuleSetListDialog.this.beforeExecuteProccess();

				DefaultListModel<RuleSet> ruleSetModel = (DefaultListModel<RuleSet>) RuleSetListDialog.this.ruleSetList.getModel();
				DefaultListModel<Rule> ruleModel = new DefaultListModel<Rule>();

				try {
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
					JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.load.rules.failed"),
							HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} finally {
					RuleSetListDialog.this.ruleList.setModel(ruleModel);
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

			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("ruleset.manager.delete.confirm"),
					HolderMessage.getMessage("dialog.message.confirm.title"), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new Thread() {
					@Override
					public void run() {
						try {
							RuleSetListDialog.this.beforeExecuteProccess();
							RuleSetListDialog.this.ruleSetService.delete(ruleSet);
							RuleSetListDialog.this.loadRuleSets();
						} catch (CheckedException ex) {
							JOptionPane.showMessageDialog(RuleSetListDialog.this, HolderMessage.getMessage("ruleset.manager.delete.failed"),
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
		RuleListDialog dialog = this.ruleListDialog.createCrudDialog();
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
		this.setTitle(HolderMessage.getMessage("ruleset.manager.title"));
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
