package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.service.instrument.CorrespondenceInstrumentService;
import com.proyecto.view.Resources;

/**
 * La clase que nos permite editar un instrumento de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class CorrespondenceFormDialog extends JDialog {

	private static final long serialVersionUID = 2889344971937517392L;

	/**
	 * El servicio a los instrumentos que vamos a utilizar.
	 */
	@Autowired
	private CorrespondenceInstrumentService correspondenceInstrumentService;

	/*
	 * El instrumento que vamos a manipular.
	 */
	private CorrespondenceInstrument correspondenceInstrument;

	/*
	 * El campo de la descripción del instrumento.
	 */
	private JTextArea descripcionTextField;
	/**
	 * Los campos de textos para agregar las frases a derecha y a izquierda.
	 */
	private JTextField leftSideTextField;
	private JTextField rigthSideTextField;
	/**
	 * Las listas que tenemos en la ventana.
	 */
	private JList<String> leftSideList;
	private JList<String> rightSideList;
	private JList<RelationAnswer> relationList;
	/*
	 * Los botones de aceptar y cancelar.
	 */
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	/**
	 * Los botones de control de frases.
	 */
	private JButton removeLeftSideButton;
	private JButton removeRightSideButton;
	private JButton createRelationButton;
	private JButton removeRelationButton;

	/**
	 * Constructor de un dialogo de edición de correspondencia.
	 */
	public CorrespondenceFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana de edición de un instrumento de correspondencia.
	 */
	private void init() {
		this.setBounds(100, 100, 832, 544);
		this.setResizable(false);
		this.getContentPane().setLayout(null);

		JLabel descripcionLabel = new JLabel("Descripci\u00F3n");
		descripcionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descripcionLabel.setBounds(10, 11, 82, 14);
		this.getContentPane().add(descripcionLabel);

		this.descripcionTextField = new JTextArea();
		this.descripcionTextField.setBounds(10, 28, 810, 65);
		this.descripcionTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceFormDialog.this.descripcionTextField.selectAll();
			}
		});
		this.getContentPane().add(this.descripcionTextField);

		JLabel leftSideLabel = new JLabel("Lado izquierdo");
		leftSideLabel.setFont(new Font("Arial", Font.BOLD, 11));
		leftSideLabel.setBounds(169, 105, 82, 14);
		this.getContentPane().add(leftSideLabel);

		JScrollPane leftSideScrollPane = new JScrollPane();
		leftSideScrollPane.setBounds(10, 124, 400, 141);
		this.getContentPane().add(leftSideScrollPane);

		this.leftSideList = new JList<>();
		this.leftSideList.setModel(new DefaultListModel<String>());
		this.leftSideList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceFormDialog.this.modifyPhrase(CorrespondenceFormDialog.this.leftSideList,
							CorrespondenceFormDialog.this.leftSideTextField);
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CorrespondenceFormDialog.this.deletePhrase(CorrespondenceFormDialog.this.leftSideList);
				}
			}
		});
		this.leftSideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CorrespondenceFormDialog.this.modifyPhrase(CorrespondenceFormDialog.this.leftSideList,
							CorrespondenceFormDialog.this.leftSideTextField);
				}
			}
		});
		leftSideScrollPane.setViewportView(this.leftSideList);

		this.leftSideTextField = new JTextField();
		this.leftSideTextField.setBounds(10, 276, 353, 31);
		this.leftSideTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceFormDialog.this.leftSideTextField.selectAll();
			}
		});
		this.leftSideTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceFormDialog.this.addPhrase(CorrespondenceFormDialog.this.leftSideList,
							CorrespondenceFormDialog.this.leftSideTextField);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					CorrespondenceFormDialog.this.leftSideTextField.setText("");
				}
			}
		});
		this.getContentPane().add(this.leftSideTextField);

		this.removeLeftSideButton = new JButton(Resources.DELETE_ICON);
		this.removeLeftSideButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.removeLeftSideButton.setBounds(373, 276, 37, 31);
		this.removeLeftSideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.deletePhrase(CorrespondenceFormDialog.this.leftSideList);
			}
		});
		this.getContentPane().add(this.removeLeftSideButton);

		JLabel rightSideLabel = new JLabel("Lado derecho");
		rightSideLabel.setFont(new Font("Arial", Font.BOLD, 11));
		rightSideLabel.setBounds(579, 105, 82, 14);
		this.getContentPane().add(rightSideLabel);

		JScrollPane rightSideScrollPane = new JScrollPane();
		rightSideScrollPane.setBounds(420, 124, 400, 141);
		this.getContentPane().add(rightSideScrollPane);

		this.rightSideList = new JList<>();
		this.rightSideList.setModel(new DefaultListModel<String>());
		this.rightSideList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceFormDialog.this.modifyPhrase(CorrespondenceFormDialog.this.rightSideList,
							CorrespondenceFormDialog.this.rigthSideTextField);
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CorrespondenceFormDialog.this.deletePhrase(CorrespondenceFormDialog.this.rightSideList);
				}
			}
		});
		this.rightSideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CorrespondenceFormDialog.this.modifyPhrase(CorrespondenceFormDialog.this.rightSideList,
							CorrespondenceFormDialog.this.rigthSideTextField);
				}
			}
		});
		rightSideScrollPane.setViewportView(this.rightSideList);

		this.rigthSideTextField = new JTextField();
		this.rigthSideTextField.setColumns(10);
		this.rigthSideTextField.setBounds(420, 276, 353, 31);
		this.rigthSideTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceFormDialog.this.rigthSideTextField.selectAll();
			}
		});
		this.rigthSideTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceFormDialog.this.addPhrase(CorrespondenceFormDialog.this.rightSideList,
							CorrespondenceFormDialog.this.rigthSideTextField);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					CorrespondenceFormDialog.this.rigthSideTextField.setText("");
				}
			}
		});
		this.getContentPane().add(this.rigthSideTextField);

		this.removeRightSideButton = new JButton(Resources.DELETE_ICON);
		this.removeRightSideButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.removeRightSideButton.setBounds(783, 276, 37, 31);
		this.removeRightSideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.deletePhrase(CorrespondenceFormDialog.this.rightSideList);
			}
		});
		this.getContentPane().add(this.removeRightSideButton);

		JLabel relationsLabel = new JLabel("Relaciones");
		relationsLabel.setFont(new Font("Arial", Font.BOLD, 11));
		relationsLabel.setBounds(361, 325, 60, 14);
		this.getContentPane().add(relationsLabel);

		JScrollPane relationsScrollPane = new JScrollPane();
		relationsScrollPane.setBounds(10, 345, 763, 123);
		this.getContentPane().add(relationsScrollPane);

		this.relationList = new JList<>();
		this.relationList.setModel(new DefaultListModel<RelationAnswer>());
		relationsScrollPane.setViewportView(this.relationList);

		this.createRelationButton = new JButton(Resources.ADD_ICON);
		this.createRelationButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.createRelationButton.setBounds(783, 345, 37, 31);
		this.createRelationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.createRelation();
			}
		});
		this.getContentPane().add(this.createRelationButton);

		this.removeRelationButton = new JButton(Resources.DELETE_ICON);
		this.removeRelationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.deleteRelation();
			}
		});
		this.removeRelationButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.removeRelationButton.setBounds(783, 387, 37, 31);
		this.getContentPane().add(this.removeRelationButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(561, 480, 37, 29);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton("Aceptar");
		this.commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.commitButton.setBounds(610, 479, 100, 30);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton("Cancelar");
		this.rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		this.rejectButton.setBounds(720, 479, 100, 30);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	@Override
	public void setEnabled(boolean b) {
		this.descripcionTextField.setEnabled(b);

		this.leftSideList.setEnabled(b);
		this.leftSideTextField.setEnabled(b);

		this.rightSideList.setEnabled(b);
		this.rigthSideTextField.setEnabled(b);

		this.relationList.setEnabled(b);

		this.removeLeftSideButton.setEnabled(b);
		this.removeRightSideButton.setEnabled(b);
		this.removeRelationButton.setEnabled(b);
		this.createRelationButton.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	/**
	 * La función encargada de agregar una frase a la lista del lado correspondiente.
	 */
	private void addPhrase(JList<String> list, JTextField field) {
		// Agregamos solo si tenemos algo en el campo de texto.
		String leftSide = field.getText().trim();

		if (leftSide.length() > 0) {
			DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
			model.addElement(leftSide);
			field.setText("");
		}
	}

	/**
	 * La función encargada de remover la frase y cargarla dentro del campo para editarla.
	 */
	private void modifyPhrase(JList<String> list, JTextField field) {
		// Vemos si hay algo seleccionado en la lista.
		Integer index = list.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
			String editLeftSide = model.get(index);
			model.remove(index);

			String oldEditLeftSide = field.getText().trim();

			if (oldEditLeftSide.length() > 0) {
				model.addElement(oldEditLeftSide);
			}

			field.setText(editLeftSide);
			field.requestFocus();
		}
	}

	/**
	 * La función encargada de quitar una frase del lado seleccionado.
	 */
	private void deletePhrase(JList<String> list) {
		// Vemos si hay algo seleccionado en la lista.
		Integer index = list.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<String> model = (DefaultListModel<String>) list.getModel();
			model.remove(index);
		}
	}

	/**
	 * La función encargada de crear una relación con las frases que tenemos seleccionadas.
	 */
	private void createRelation() {
		// Obtenemos los índices de las frases.
		Integer leftIndex = this.leftSideList.getSelectedIndex();
		Integer rightIndex = this.rightSideList.getSelectedIndex();

		if (leftIndex >= 0 && rightIndex >= 0) {
			DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
			DefaultListModel<String> leftModel = (DefaultListModel<String>) this.leftSideList.getModel();
			DefaultListModel<String> rightModel = (DefaultListModel<String>) this.rightSideList.getModel();

			// Creamos la relación y la cargamos en la lista.
			RelationAnswer answer = new RelationAnswer();
			answer.setLeftSide(leftModel.get(leftIndex));
			answer.setRightSide(rightModel.get(rightIndex));

			relationModel.addElement(answer);

			// Quitamos las frases de las listas.
			leftModel.remove(leftIndex);
			rightModel.remove(rightIndex);
		}
	}

	/**
	 * La función encargada de quitar una relación y devolver las frases que esta tenía.
	 */
	private void deleteRelation() {
		// Obtenemos el índice de la relación.
		Integer relationIndex = this.relationList.getSelectedIndex();

		if (relationIndex >= 0) {
			// Obtenemos la relación.
			DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
			RelationAnswer answer = relationModel.get(relationIndex);
			relationModel.remove(relationIndex);

			// Volvemos a cargar las listas de frases.
			DefaultListModel<String> leftModel = (DefaultListModel<String>) this.leftSideList.getModel();
			DefaultListModel<String> rightModel = (DefaultListModel<String>) this.rightSideList.getModel();
			leftModel.addElement(answer.getLeftSide());
			rightModel.addElement(answer.getRightSide());
		}
	}

	/**
	 * La función encargada de cargar dentro de la ventana, el instrumento que estamos editando.
	 */
	private void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descripcionTextField.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.correspondence.description");
		} else {
			this.correspondenceInstrument.setDescription(this.descripcionTextField.getText());
		}

		// Los modelos.
		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<String> leftModel = (DefaultListModel<String>) this.leftSideList.getModel();
		DefaultListModel<String> rightModel = (DefaultListModel<String>) this.rightSideList.getModel();

		List<RelationAnswer> relations = new ArrayList<>();

		// Obtenemos el listado de las relaciones.
		for (Integer index = 0; index < relationModel.getSize(); index++) {
			RelationAnswer answer = relationModel.get(index);
			answer.setCorrespondenceInstrument(this.correspondenceInstrument);
			relations.add(answer);
		}

		if (relations.isEmpty()) {
			throw new CheckedException("isntrument.correspondence.relations");
		}

		// Creamos las relaciones de izquierda y derecha.
		for (Integer index = 0; index < leftModel.getSize(); index++) {
			RelationAnswer answer = new RelationAnswer();
			answer.setLeftSide(leftModel.get(index));
			answer.setCorrespondenceInstrument(this.correspondenceInstrument);
			relations.add(answer);
		}

		for (Integer index = 0; index < rightModel.getSize(); index++) {
			RelationAnswer answer = new RelationAnswer();
			answer.setRightSide(rightModel.get(index));
			answer.setCorrespondenceInstrument(this.correspondenceInstrument);
			relations.add(answer);
		}

		// Agregamos las relaciones al listado del instrumento.
		this.correspondenceInstrument.getRelations().clear();
		this.correspondenceInstrument.getRelations().addAll(relations);
	}

	/**
	 * La función encargada de cargar el instrumento dentro del dialogo para su edición.
	 */
	private void fromInstrumentToDialog() {
		this.descripcionTextField.setText(this.correspondenceInstrument.getDescription());

		// Los modelos.
		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<String> leftModel = (DefaultListModel<String>) this.leftSideList.getModel();
		DefaultListModel<String> rightModel = (DefaultListModel<String>) this.rightSideList.getModel();

		for (RelationAnswer relation : this.correspondenceInstrument.getRelations()) {
			if (relation.getLeftSide() == null && relation.getRightSide() != null) {
				rightModel.addElement(relation.getRightSide());
			} else if (relation.getLeftSide() != null && relation.getRightSide() == null) {
				leftModel.addElement(relation.getLeftSide());
			} else if (relation.getLeftSide() != null && relation.getRightSide() != null) {
				relationModel.addElement(relation);
			}
		}
	}

	/*
	 * La función encargada de guardar el instrumento dentro de la base de datos.
	 */
	private void saveInstrument() {
		new Thread() {
			@Override
			public void run() {
				try {
					CorrespondenceFormDialog.this.beforeSave();
					CorrespondenceFormDialog.this.fromDialogToInstrument();
					CorrespondenceFormDialog.this.correspondenceInstrumentService
							.saveOrUpdate(CorrespondenceFormDialog.this.correspondenceInstrument);
					CorrespondenceFormDialog.this.dispose();
				} catch (CheckedException e) {
					JOptionPane.showMessageDialog(CorrespondenceFormDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					CorrespondenceFormDialog.this.afterSave();
				}
			}
		}.start();
	}

	/**
	 * La función antes de guardar.
	 */
	private void beforeSave() {
		this.setEnabled(false);
		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de guardar.
	 */
	private void afterSave() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/*
	 * La función encargada de vaciar los campos de la ventana de edición del instrumento.
	 */
	private void emptyFields() {
		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<String> leftModel = (DefaultListModel<String>) this.leftSideList.getModel();
		DefaultListModel<String> rightModel = (DefaultListModel<String>) this.rightSideList.getModel();
		relationModel.clear();
		leftModel.clear();
		rightModel.clear();
	}

	/**
	 * La función encargada de crear una ventana para crear un nuevo instrumento de correspondencia.
	 * 
	 * @return La ventana para crear un nuevo instrumento de correspondencia.
	 */
	public CorrespondenceFormDialog createNewDialog() {
		this.setTitle("Instrumento de correspondencia");
		this.correspondenceInstrument = new CorrespondenceInstrument();
		this.emptyFields();
		return this;
	}

	/**
	 * La función encargada de crear una ventana para editar un instrumento de correspondencia.
	 * 
	 * @return La ventana para editar un instrumento de correspondencia.
	 */
	public CorrespondenceFormDialog createEditDialog(CorrespondenceInstrument correspondenceInstrument) {
		this.setTitle("Instrumento de correspondencia");
		this.correspondenceInstrument = correspondenceInstrument;
		this.fromInstrumentToDialog();
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

			CorrespondenceInstrument instrument = HolderApplicationContext.getContext().getBean(CorrespondenceInstrumentService.class).findById(12);
			CorrespondenceFormDialog dialog = HolderApplicationContext.getContext().getBean(CorrespondenceFormDialog.class)
					.createEditDialog(instrument);
			// CorrespondenceFormDialog dialog = HolderApplicationContext.getContext().getBean(CorrespondenceFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
