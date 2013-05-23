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
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.rule.Rule;
import com.proyecto.service.rule.RuleService;

@View
public class EditRuleFormDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	@Autowired
	private RuleService ruleService;

	private DefaultListModel<Rule> listModelRuleOff = new DefaultListModel<Rule>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			EditRuleFormDialog dialog = HolderApplicationContext.getContext()
					.getBean(EditRuleFormDialog.class);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public EditRuleFormDialog() {
		super();
		this.init();}
	
	public void init(){
		setTitle("Grupos de Reglas");
		setBounds(100, 100, 832, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel groupDescriptionLabel = new JLabel(
					"Descripci\u00F3n del Grupo");
			groupDescriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
			groupDescriptionLabel.setBounds(10, 11, 133, 14);
			contentPanel.add(groupDescriptionLabel);
		}
		{
			JTextPane textPane = new JTextPane();
			textPane.setBounds(10, 36, 794, 58);
			contentPanel.add(textPane);
		}

		JList activeRulelist = new JList();
		activeRulelist.setFont(new Font("Arial", Font.BOLD, 11));
		activeRulelist.setBounds(10, 130, 341, 216);
		contentPanel.add(activeRulelist);
		{
			JList ruleOfflist = new JList();
			ruleOfflist.setModel(this.listModelRuleOff);
			ruleOfflist.setFont(new Font("Arial", Font.BOLD, 11));
			ruleOfflist.setBounds(463, 130, 341, 216);
			contentPanel.add(ruleOfflist);
		}
		{
			JLabel activeRuleLabel = new JLabel("Reglas Activas");
			activeRuleLabel.setFont(new Font("Arial", Font.BOLD, 11));
			activeRuleLabel.setBounds(134, 106, 92, 14);
			contentPanel.add(activeRuleLabel);
		}
		{
			JLabel ruleOffLabel = new JLabel("Reglas Desactivadas");
			ruleOffLabel.setFont(new Font("Arial", Font.BOLD, 11));
			ruleOffLabel.setBounds(571, 105, 125, 14);
			contentPanel.add(ruleOffLabel);
		}

		JButton addRuleButton = new JButton("Agregar >>");
		addRuleButton.setFont(new Font("Arial", Font.BOLD, 11));
		addRuleButton.setBounds(361, 130, 92, 38);
		contentPanel.add(addRuleButton);

		JButton deleteRulebutton = new JButton("Eliminar <<");
		deleteRulebutton.setFont(new Font("Arial", Font.BOLD, 11));
		deleteRulebutton.setBounds(361, 179, 92, 38);
		contentPanel.add(deleteRulebutton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setFont(new Font("Arial", Font.BOLD, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Arial", Font.BOLD, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void fillList() {
		this.listModelRuleOff.clear();
		try {
			for (Rule r : this.ruleService.findAll()) {
				this.listModelRuleOff.addElement(r);
			}
		} catch (CheckedException e) {
			e.printStackTrace();
		}
	}
}
