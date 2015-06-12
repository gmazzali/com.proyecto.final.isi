package com.proyecto.view.rule;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.common.util.annotations.View;
import com.common.util.holder.HolderMessage;
import com.proyecto.ontology.OntologyConstants;

/**
 * La ventana que nos permite seleccionar un nombre de una propiedad que tengamos dentro de la ontología para la creación de las reglas.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class PropertyNameSelectDialog extends JDialog {

	private static final long serialVersionUID = -2312760597467093929L;

	/**
	 * La lista de los nombre de las clases.
	 */
	private JList<String> classNameJList;
	/**
	 * La lista de los nombre de las propiedades.
	 */
	private JList<String> propertyNameJList;
	/**
	 * El nombre de la propiedad seleccionada.
	 */
	private String propertySelected;

	/**
	 * Constructor del dialogo de selección.
	 */
	public PropertyNameSelectDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar el contenido de la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 610, 300);
		this.setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		JScrollPane classNameScrollPane = new JScrollPane();
		classNameScrollPane.setBounds(12, 11, 233, 250);
		classNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(classNameScrollPane);

		this.classNameJList = new JList<String>();
		this.classNameJList.setFont(classNameScrollPane.getFont());
		this.classNameJList.setModel(new DefaultListModel<String>());
		this.classNameJList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				PropertyNameSelectDialog.this.initPropertyNameList();
			}
		});
		classNameScrollPane.setViewportView(this.classNameJList);

		JScrollPane propertyNameScrollPane = new JScrollPane();
		propertyNameScrollPane.setBounds(257, 11, 337, 250);
		propertyNameScrollPane.setFont(this.getFont());
		this.getContentPane().add(propertyNameScrollPane);

		this.propertyNameJList = new JList<String>();
		this.propertyNameJList.setFont(propertyNameScrollPane.getFont());
		this.propertyNameJList.setModel(new DefaultListModel<String>());
		this.propertyNameJList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					PropertyNameSelectDialog.this.selectPropertyName();
				}
			}
		});
		propertyNameScrollPane.setViewportView(this.propertyNameJList);
	}

	/**
	 * La función que carga todos los nombres de las clases dentro de la lista.
	 */
	private void initClassNameList() {
		DefaultListModel<String> classModel = new DefaultListModel<String>();

		for (String className : OntologyConstants.PROPERTIES.keySet()) {
			classModel.addElement(className);
		}

		this.classNameJList.setModel(classModel);
	}

	/**
	 * La función que carga todos los nombres de las propiedades dentro de la lista.
	 */
	private void initPropertyNameList() {
		Integer index = this.classNameJList.getSelectedIndex();

		if (index != -1) {
			DefaultListModel<String> classModel = (DefaultListModel<String>) this.classNameJList.getModel();
			DefaultListModel<String> propertyModel = new DefaultListModel<String>();
			String className = classModel.get(index);

			for (String propertyName : OntologyConstants.PROPERTIES.get(className)) {
				propertyModel.addElement(propertyName);
			}

			this.propertyNameJList.setModel(propertyModel);
		}
	}

	/**
	 * La función encargada de seleccionar el nombre de una propiedad dentro de la lista y cerrar la ventana.
	 */
	private void selectPropertyName() {
		// Si tenemos algún nombre de una propiedad seleccionada.
		Integer index = this.propertyNameJList.getSelectedIndex();

		if (index != -1) {
			// Tomamos la propiedad seleccionada.
			DefaultListModel<String> classModel = (DefaultListModel<String>) this.propertyNameJList.getModel();
			this.propertySelected = classModel.get(index);

			// Cerramos la ventana.
			this.dispose();
		}
	}

	/**
	 * La función encargada de retornar el nombre de la propiedad seleccionada.
	 * 
	 * @return El nombre de la propiedad seleccionada.
	 */
	public String getPropertySelected() {
		return this.propertySelected != null ? OntologyConstants.NAMESPACE + this.propertySelected : null;
	}

	/**
	 * La función encargada de cargar la ventana para seleccionar el nombre de una clase.
	 * 
	 * @return La ventana lista para seleccionar el nombre de una clase
	 */
	public PropertyNameSelectDialog createSelectDialog() {
		this.setTitle(HolderMessage.getMessage("rule.select.property.title"));

		this.initClassNameList();
		this.propertySelected = null;

		return this;
	}
}