package com.proyecto.view.rule;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Value;

import com.common.util.annotations.View;
import com.common.util.holder.HolderMessage;
import com.proyecto.ontology.OntologyConstants;

/**
 * La ventana que nos permite seleccionar un nombre de una clase que tengamos dentro de la ontología para la creación de las reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class ClassNameSelectDialog extends JDialog {

	private static final long serialVersionUID = -3600502749588025504L;

	/**
	 * La lista que va a desplegar los nombres de las clases.
	 */
	private JList<String> classNameJList;
	/**
	 * El nombre de la clase seleccionada.
	 */
	private String classSelected;

	/**
	 * Constructor del dialogo de selección.
	 */
	public ClassNameSelectDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el contenido de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.setBounds(100, 100, 320, 320);
		this.getContentPane().setLayout(null);

		JScrollPane classNameScrollPane = new JScrollPane();
		classNameScrollPane.setBounds(12, 11, 292, 270);
		classNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(classNameScrollPane);

		this.classNameJList = new JList<String>();
		this.classNameJList.setFont(classNameScrollPane.getFont());
		this.classNameJList.setModel(new DefaultListModel<String>());
		this.classNameJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ClassNameSelectDialog.this.selectClassName();
				}
			}
		});
		classNameScrollPane.setViewportView(this.classNameJList);
	}

	/**
	 * La función que carga todos los nombres de las clases dentro de la lista.
	 */
	private void initClassNameList() {
		DefaultListModel<String> classModel = new DefaultListModel<String>();

		for (String className : OntologyConstants.CLASES) {
			classModel.addElement(className);
		}

		this.classNameJList.setModel(classModel);
	}

	/**
	 * La función encargada de seleccionar el nombre de una clase dentro de la lista y cerrar la ventana.
	 */
	private void selectClassName() {
		// Si tenemos algún nombre de clase seleccionada.
		Integer index = this.classNameJList.getSelectedIndex();
		if (index != -1) {
			// Tomamos la clase seleccionada.
			DefaultListModel<String> classModel = (DefaultListModel<String>) this.classNameJList.getModel();
			this.classSelected = classModel.get(index);
			// Cerramos la ventana.
			this.dispose();
		}
	}

	/**
	 * La función encargada de retornar el nombre de la clase seleccionada.
	 * 
	 * @return El nombre de la clase seleccionada.
	 */
	public String getClassSelected() {
		return this.classSelected != null ? OntologyConstants.NAMESPACE + this.classSelected : null;
	}

	/**
	 * La función encargada de cargar la ventana para seleccionar el nombre de una clase.
	 * 
	 * @return La ventana lista para seleccionar el nombre de una clase
	 */
	public ClassNameSelectDialog createSelectDialog() {
		this.setTitle(HolderMessage.getMessage("rule.select.class.title"));

		this.initClassNameList();
		this.classSelected = null;

		return this;
	}
}