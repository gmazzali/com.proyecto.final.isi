package com.proyecto.view.material.instrument;

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
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.answer.RelationAnswer.Side;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.service.material.instrument.CorrespondenceInstrumentService;
import com.proyecto.view.Resources;

/**
 * La clase que nos permite editar un instrumento de correspondencia.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
public class CorrespondenceInstrumentFormDialog extends InstrumentFormDialog {

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

	/**
	 * Las frases de izquierda y derecha que estamos editando.
	 */
	private RelationAnswer editingLeftPhrase;
	private RelationAnswer editingRightPhrase;

	/*
	 * El campo de la descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;
	/**
	 * Los campos de textos para agregar las frases a derecha y a izquierda.
	 */
	private JTextField leftSideTextField;
	private JTextField rigthSideTextField;
	/**
	 * Las listas que tenemos en la ventana.
	 */
	private JList<RelationAnswer> leftSideList;
	private JList<RelationAnswer> rightSideList;
	private JList<RelationAnswer> relationList;
	/**
	 * Los botones de creación y eliminación de relaciones.
	 */
	private JButton createRelationButton;
	private JButton removeRelationButton;
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
	 * Constructor de un dialogo de edición de correspondencia.
	 */
	public CorrespondenceInstrumentFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana de edición de un instrumento de correspondencia.
	 */
	private void init() {
		this.setBounds(100, 100, 837, 582);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		JLabel descripcionLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.description"));
		descripcionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descripcionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descripcionLabel.setBounds(10, 11, 810, 14);
		this.getContentPane().add(descripcionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 28, 810, 65);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceInstrumentFormDialog.this.descriptionTextArea.selectAll();
			}
		});
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel leftSideLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.side.left"));
		leftSideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftSideLabel.setFont(new Font("Arial", Font.BOLD, 11));
		leftSideLabel.setBounds(169, 105, 400, 14);
		this.getContentPane().add(leftSideLabel);

		JScrollPane leftSideScrollPane = new JScrollPane();
		leftSideScrollPane.setBounds(10, 124, 400, 141);
		this.getContentPane().add(leftSideScrollPane);

		this.leftSideList = new JList<RelationAnswer>();
		this.leftSideList.setFont(this.getContentPane().getFont());
		this.leftSideList.setModel(new DefaultListModel<RelationAnswer>());
		this.leftSideList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceInstrumentFormDialog.this.modifyPhrase(CorrespondenceInstrumentFormDialog.this.leftSideList,
							CorrespondenceInstrumentFormDialog.this.leftSideTextField, Side.LEFT);
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CorrespondenceInstrumentFormDialog.this.deletePhrase(CorrespondenceInstrumentFormDialog.this.leftSideList);
				}
			}
		});
		this.leftSideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CorrespondenceInstrumentFormDialog.this.modifyPhrase(CorrespondenceInstrumentFormDialog.this.leftSideList,
							CorrespondenceInstrumentFormDialog.this.leftSideTextField, Side.LEFT);
				}
			}
		});
		leftSideScrollPane.setViewportView(this.leftSideList);

		this.leftSideTextField = new JTextField();
		this.leftSideTextField.setFont(this.getContentPane().getFont());
		this.leftSideTextField.setBounds(10, 276, 400, 31);
		this.leftSideTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceInstrumentFormDialog.this.leftSideTextField.selectAll();
			}
		});
		this.leftSideTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceInstrumentFormDialog.this.addPhrase(CorrespondenceInstrumentFormDialog.this.leftSideList,
							CorrespondenceInstrumentFormDialog.this.leftSideTextField, Side.LEFT);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					CorrespondenceInstrumentFormDialog.this.leftSideTextField.setText("");
				}
			}
		});
		this.getContentPane().add(this.leftSideTextField);

		JLabel rightSideLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.side.right"));
		leftSideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rightSideLabel.setFont(new Font("Arial", Font.BOLD, 11));
		rightSideLabel.setBounds(579, 105, 400, 14);
		this.getContentPane().add(rightSideLabel);

		JScrollPane rightSideScrollPane = new JScrollPane();
		rightSideScrollPane.setBounds(420, 124, 400, 141);
		this.getContentPane().add(rightSideScrollPane);

		this.rightSideList = new JList<RelationAnswer>();
		this.rightSideList.setFont(this.getContentPane().getFont());
		this.rightSideList.setModel(new DefaultListModel<RelationAnswer>());
		this.rightSideList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceInstrumentFormDialog.this.modifyPhrase(CorrespondenceInstrumentFormDialog.this.rightSideList,
							CorrespondenceInstrumentFormDialog.this.rigthSideTextField, Side.RIGTH);
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CorrespondenceInstrumentFormDialog.this.deletePhrase(CorrespondenceInstrumentFormDialog.this.rightSideList);
				}
			}
		});
		this.rightSideList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					CorrespondenceInstrumentFormDialog.this.modifyPhrase(CorrespondenceInstrumentFormDialog.this.rightSideList,
							CorrespondenceInstrumentFormDialog.this.rigthSideTextField, Side.RIGTH);
				}
			}
		});
		rightSideScrollPane.setViewportView(this.rightSideList);

		this.rigthSideTextField = new JTextField();
		this.rigthSideTextField.setFont(this.getContentPane().getFont());
		this.rigthSideTextField.setBounds(420, 276, 400, 31);
		this.rigthSideTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CorrespondenceInstrumentFormDialog.this.rigthSideTextField.selectAll();
			}
		});
		this.rigthSideTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CorrespondenceInstrumentFormDialog.this.addPhrase(CorrespondenceInstrumentFormDialog.this.rightSideList,
							CorrespondenceInstrumentFormDialog.this.rigthSideTextField, Side.RIGTH);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					CorrespondenceInstrumentFormDialog.this.rigthSideTextField.setText("");
				}
			}
		});
		this.getContentPane().add(this.rigthSideTextField);

		JLabel relationsLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.relations"));
		leftSideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		relationsLabel.setFont(new Font("Arial", Font.BOLD, 11));
		relationsLabel.setBounds(361, 325, 763, 14);
		this.getContentPane().add(relationsLabel);

		JScrollPane relationsScrollPane = new JScrollPane();
		relationsScrollPane.setBounds(10, 345, 763, 139);
		this.getContentPane().add(relationsScrollPane);

		this.relationList = new JList<RelationAnswer>();
		this.relationList.setFont(this.getContentPane().getFont());
		this.relationList.setModel(new DefaultListModel<RelationAnswer>());
		this.relationList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CorrespondenceInstrumentFormDialog.this.deleteRelation();
				}
			}
		});
		relationsScrollPane.setViewportView(this.relationList);

		this.createRelationButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.createRelationButton.setToolTipText(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.button.relation.add"));
		this.createRelationButton.setBounds(785, 345, 37, 35);
		this.createRelationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceInstrumentFormDialog.this.createRelation();
			}
		});
		this.getContentPane().add(this.createRelationButton);

		this.removeRelationButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.removeRelationButton.setToolTipText(HolderMessage.getMessage("instrument.formal.objective.correspondence.form.label.button.relation.remove"));
		this.removeRelationButton.setBounds(785, 392, 35, 35);
		this.removeRelationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceInstrumentFormDialog.this.deleteRelation();
			}
		});
		this.getContentPane().add(this.removeRelationButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 496, 810, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.progressLabel.setBounds(691, 510, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.setBounds(738, 510, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.setBounds(785, 510, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CorrespondenceInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	/**
	 * La función encargada de agregar una frase a la lista del lado correspondiente.
	 * 
	 * @param list
	 *            El listado de las relaciones.
	 * @param field
	 *            El campo que vamos a usar para crear las relaciones.
	 * @param side
	 *            El lado de la relación que estamos agregando.
	 */
	private void addPhrase(JList<RelationAnswer> list, JTextField field, Side side) {
		String phrase = field.getText().trim();

		// Agregamos solo si tenemos algo en el campo de texto y tenemos un lado seleccionado.
		if (phrase.length() > 0) {
			RelationAnswer answer = null;
			switch (side) {
				case LEFT:
					if (this.editingLeftPhrase != null) {
						answer = this.editingLeftPhrase;
						this.editingLeftPhrase = null;
					}
					break;

				case RIGTH:
					if (this.editingRightPhrase != null) {
						answer = this.editingRightPhrase;
						this.editingRightPhrase = null;
					}
					break;
			}
			if (answer == null) {
				answer = new RelationAnswer();
			}
			answer.setPhrase(phrase, side);

			DefaultListModel<RelationAnswer> model = (DefaultListModel<RelationAnswer>) list.getModel();
			model.addElement(answer);

			field.setText("");
			field.requestFocus();
		}
	}

	/**
	 * La función encargada de remover la frase y cargarla dentro del campo para editarla.
	 * 
	 * @param list
	 *            El listado de las relaciones.
	 * @param field
	 *            El campo que vamos a usar para editar las relaciones.
	 * @param side
	 *            El lado de la relación que estamos editando.
	 */
	private void modifyPhrase(JList<RelationAnswer> list, JTextField field, Side side) {
		Integer index = list.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<RelationAnswer> model = (DefaultListModel<RelationAnswer>) list.getModel();
			RelationAnswer relation = model.remove(index);

			// Agregamos la frase que estábamos editando anteriormente.
			this.addPhrase(list, field, side);

			// Tomamos la nueva frase a editar.
			switch (side) {
				case LEFT:
					this.editingLeftPhrase = relation;
					break;

				case RIGTH:
					this.editingRightPhrase = relation;
					break;
			}

			field.setText(relation.getPhrase(side));
			field.requestFocus();
		}
	}

	/**
	 * La función encargada de quitar una frase del lado seleccionado.
	 * 
	 * @param list
	 *            El listado de las relaciones.
	 */
	private void deletePhrase(JList<RelationAnswer> list) {
		Integer index = list.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<RelationAnswer> model = (DefaultListModel<RelationAnswer>) list.getModel();
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
			DefaultListModel<RelationAnswer> leftModel = (DefaultListModel<RelationAnswer>) this.leftSideList.getModel();
			DefaultListModel<RelationAnswer> rightModel = (DefaultListModel<RelationAnswer>) this.rightSideList.getModel();

			// Reciclamos la relación izquierda para crear la relación completa.
			RelationAnswer answer = leftModel.get(leftIndex);
			answer.setPhrase(leftModel.get(leftIndex).getPhrase(Side.LEFT), Side.LEFT);
			answer.setPhrase(rightModel.get(rightIndex).getPhrase(Side.RIGTH), Side.RIGTH);

			relationModel.addElement(answer);

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
			// Obtenemos los modelos.
			DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
			DefaultListModel<RelationAnswer> leftModel = (DefaultListModel<RelationAnswer>) this.leftSideList.getModel();
			DefaultListModel<RelationAnswer> rightModel = (DefaultListModel<RelationAnswer>) this.rightSideList.getModel();

			RelationAnswer answer = relationModel.remove(relationIndex);

			// Reciclamos la relación que tenemos para el lado izquierdo.
			RelationAnswer newLeftRelation = answer;
			RelationAnswer newRightRelation = new RelationAnswer();

			newRightRelation.setPhrase(null, Side.LEFT);
			newRightRelation.setPhrase(answer.getPhrase(Side.RIGTH), Side.RIGTH);

			newLeftRelation.setPhrase(answer.getPhrase(Side.LEFT), Side.LEFT);
			newLeftRelation.setPhrase(null, Side.RIGTH);

			leftModel.addElement(newLeftRelation);
			rightModel.addElement(newRightRelation);
		}
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);

		this.descriptionTextArea.setEnabled(b);

		this.leftSideList.setEnabled(b);
		this.leftSideTextField.setEnabled(b);

		this.rightSideList.setEnabled(b);
		this.rigthSideTextField.setEnabled(b);

		this.relationList.setEnabled(b);

		this.removeRelationButton.setEnabled(b);
		this.createRelationButton.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	@Override
	protected void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.objective.correspondence.form.error.description");
		} else {
			this.correspondenceInstrument.setDescription(this.descriptionTextArea.getText().trim());
		}

		// Los modelos.
		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<RelationAnswer> leftModel = (DefaultListModel<RelationAnswer>) this.leftSideList.getModel();
		DefaultListModel<RelationAnswer> rightModel = (DefaultListModel<RelationAnswer>) this.rightSideList.getModel();

		List<RelationAnswer> relations = new ArrayList<RelationAnswer>();

		// Obtenemos el listado de las relaciones.
		for (Integer index = 0; index < relationModel.getSize(); index++) {
			RelationAnswer answer = relationModel.get(index);
			relations.add(answer);
		}

		if (relations.isEmpty()) {
			throw new CheckedException("instrument.formal.objective.correspondence.form.error.relations");
		}

		// Creamos las relaciones de izquierda y derecha.
		for (Integer index = 0; index < leftModel.getSize(); index++) {
			RelationAnswer answer = leftModel.get(index);
			relations.add(answer);
		}

		for (Integer index = 0; index < rightModel.getSize(); index++) {
			RelationAnswer answer = rightModel.get(index);
			relations.add(answer);
		}

		// Agregamos las relaciones al listado del instrumento.
		this.correspondenceInstrument.clearRelations();
		this.correspondenceInstrument.addAllRelations(relations);
	}

	@Override
	protected void fromInstrumentToDialog() {
		this.descriptionTextArea.setText(this.correspondenceInstrument.getDescription());

		// Los modelos.
		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<RelationAnswer> leftModel = (DefaultListModel<RelationAnswer>) this.leftSideList.getModel();
		DefaultListModel<RelationAnswer> rightModel = (DefaultListModel<RelationAnswer>) this.rightSideList.getModel();

		for (RelationAnswer relation : this.correspondenceInstrument.getRelations()) {
			if (relation.getLeftSide() == null && relation.getRightSide() != null) {
				rightModel.addElement(relation);
			} else if (relation.getLeftSide() != null && relation.getRightSide() == null) {
				leftModel.addElement(relation);
			} else if (relation.getLeftSide() != null && relation.getRightSide() != null) {
				relationModel.addElement(relation);
			}
		}
	}

	@Override
	protected void emptyFields() {
		this.descriptionTextArea.setText("");
		this.leftSideTextField.setText("");
		this.rigthSideTextField.setText("");

		DefaultListModel<RelationAnswer> relationModel = (DefaultListModel<RelationAnswer>) this.relationList.getModel();
		DefaultListModel<RelationAnswer> leftModel = (DefaultListModel<RelationAnswer>) this.leftSideList.getModel();
		DefaultListModel<RelationAnswer> rightModel = (DefaultListModel<RelationAnswer>) this.rightSideList.getModel();

		relationModel.clear();
		leftModel.clear();
		rightModel.clear();

		this.editingLeftPhrase = null;
		this.editingRightPhrase = null;
	}

	@Override
	protected void setNewInstrument() {
		this.correspondenceInstrument = new CorrespondenceInstrument();
	}

	@Override
	protected <E extends Instrument> void setEditInstrument(E instrument) {
		this.correspondenceInstrument = (CorrespondenceInstrument) instrument;
	}

	@Override
	protected CorrespondenceInstrument getInstrument() {
		return this.correspondenceInstrument;
	}

	@Override
	protected CorrespondenceInstrumentService getInstrumentService() {
		return this.correspondenceInstrumentService;
	}

	@Override
	protected String getNewTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.correspondence.form.title.new");
	}

	@Override
	protected String getEditTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.correspondence.form.title.edit");
	}

	@Override
	protected JLabel getProgressLabel() {
		return this.progressLabel;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			// CorrespondenceInstrument instrument = HolderApplicationContext.getContext().getBean(CorrespondenceInstrumentService.class).findById(4);
			// CorrespondenceInstrumentFormDialog dialog = (CorrespondenceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(CorrespondenceInstrumentFormDialog.class).createEditDialog(instrument);
			CorrespondenceInstrumentFormDialog dialog = (CorrespondenceInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(CorrespondenceInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}