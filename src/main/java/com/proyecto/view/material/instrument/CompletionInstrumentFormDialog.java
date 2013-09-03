package com.proyecto.view.material.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import com.proyecto.model.answer.CompletionAnswer;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.service.material.instrument.CompletionInstrumentService;
import com.proyecto.view.Resources;

/**
 * La ventana que nos permite crear un nuevo instrumento para completar o editar uno que ya tenemos dentro del sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
public class CompletionInstrumentFormDialog extends InstrumentFormDialog {

	private static final long serialVersionUID = -2524357450091491717L;

	/**
	 * El servicio que vamos a utilizar.
	 */
	@Autowired
	private CompletionInstrumentService completionInstrumentService;

	/**
	 * El instrumento que vamos a editar.
	 */
	private CompletionInstrument completionInstrument;
	/**
	 * La respuesta que vamos a editar.
	 */
	private CompletionAnswer editingAnswer;

	/**
	 * La descripción del instrumento.
	 */
	private JTextArea descriptionTextArea;

	/**
	 * El conjunto de las palabras que tenemos para agregar a los lugares faltantes de la descripción del instrumento.
	 */
	private JList<CompletionAnswer> completeList;
	private JTextField indexTextField;
	private JTextField phraseTextField;
	/**
	 * Los botones de acción.
	 */
	private JButton commitButton;
	private JButton rejectButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * El constructor por omisión de la ventana.
	 */
	public CompletionInstrumentFormDialog() {
		super();
		this.init();
	}

	/**
	 * La función que inicializa la ventana.
	 */
	private void init() {
		this.setBounds(100, 100, 850, 305);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		JLabel descriptionLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.completion.form.label.description"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel.setFont(new Font("Arial", Font.BOLD, 11));
		descriptionLabel.setBounds(10, 11, 472, 14);
		this.getContentPane().add(descriptionLabel);

		JScrollPane descriptionScrollPane = new JScrollPane();
		descriptionScrollPane.setBounds(10, 30, 472, 179);
		this.getContentPane().add(descriptionScrollPane);

		this.descriptionTextArea = new JTextArea();
		this.descriptionTextArea.setFont(this.getContentPane().getFont());
		this.descriptionTextArea.setLineWrap(true);
		this.descriptionTextArea.setWrapStyleWord(true);
		this.descriptionTextArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CompletionInstrumentFormDialog.this.descriptionTextArea.selectAll();
			}
		});
		descriptionScrollPane.setViewportView(this.descriptionTextArea);

		JLabel completeLabel = new JLabel(HolderMessage.getMessage("instrument.formal.objective.completion.form.label.completes"));
		descriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		completeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		completeLabel.setBounds(486, 11, 352, 14);
		this.getContentPane().add(completeLabel);

		JScrollPane completeScrollPane = new JScrollPane();
		completeScrollPane.setBounds(486, 30, 352, 142);
		this.getContentPane().add(completeScrollPane);

		this.completeList = new JList<CompletionAnswer>();
		this.completeList.setFont(this.getContentPane().getFont());
		this.completeList.setModel(new DefaultListModel<CompletionAnswer>());
		this.completeList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CompletionInstrumentFormDialog.this.modifyCompletePhrase();
				} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					CompletionInstrumentFormDialog.this.deleteCompletePhrase();
				}
			}
		});
		completeScrollPane.setViewportView(this.completeList);

		this.indexTextField = new JTextField();
		this.indexTextField.setBounds(486, 179, 65, 30);
		this.indexTextField.setFont(this.getContentPane().getFont());
		this.indexTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CompletionInstrumentFormDialog.this.addCompletePhrase();
				}
			}
		});
		this.indexTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CompletionInstrumentFormDialog.this.indexTextField.selectAll();
			}
		});
		this.getContentPane().add(this.indexTextField);

		this.phraseTextField = new JTextField();
		this.phraseTextField.setBounds(555, 179, 283, 30);
		this.phraseTextField.setFont(this.getContentPane().getFont());
		this.phraseTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CompletionInstrumentFormDialog.this.addCompletePhrase();
				}
			}
		});
		this.phraseTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CompletionInstrumentFormDialog.this.phraseTextField.selectAll();
			}
		});
		this.getContentPane().add(this.phraseTextField);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 221, 828, 2);
		this.getContentPane().add(separator);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(709, 235, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.commitButton = new JButton(Resources.COMMIT_ICON);
		this.commitButton.setToolTipText(HolderMessage.getMessage("button.action.commit"));
		this.commitButton.setBounds(756, 235, 35, 35);
		this.commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompletionInstrumentFormDialog.this.saveInstrument();
			}
		});
		this.getContentPane().add(this.commitButton);

		this.rejectButton = new JButton(Resources.CLOSE_ICON);
		this.rejectButton.setToolTipText(HolderMessage.getMessage("button.action.reject"));
		this.rejectButton.setBounds(803, 235, 35, 35);
		this.rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CompletionInstrumentFormDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.rejectButton);
	}

	/**
	 * La función encargada de agregar una frase a la lista de frases para completar.
	 */
	private void addCompletePhrase() throws NumberFormatException {
		// Los datos que vamos a utilizar.
		String index = this.indexTextField.getText().trim();
		String phrase = this.phraseTextField.getText().trim();

		// Agregamos solo si tenemos algo en el campo de texto y tenemos un lado seleccionado.
		if (phrase.length() > 0 && index.length() > 0) {
			try {
				CompletionAnswer answer = this.editingAnswer != null ? this.editingAnswer : new CompletionAnswer();

				answer.setPhrase(phrase);
				answer.setIndex(Integer.parseInt(index));

				DefaultListModel<CompletionAnswer> completeModel = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();
				completeModel.addElement(answer);

				this.emptyCompletePhrase();

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, HolderMessage.getMessage("instrument.formal.objective.completion.form.error.index.parser"),
						HolderMessage.getMessage("dialog.message.error.title"), JOptionPane.WARNING_MESSAGE);
				this.indexTextField.requestFocus();
			}
		} else if (this.editingAnswer != null) {
			DefaultListModel<CompletionAnswer> completeModel = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();
			completeModel.addElement(this.editingAnswer);
			this.emptyCompletePhrase();
		}
	}

	/**
	 * La función encargada de vaciar los campos correspondientes a las frases para completar.
	 */
	private void emptyCompletePhrase() {
		this.indexTextField.setText("");
		this.phraseTextField.setText("");
		this.indexTextField.requestFocus();
		this.editingAnswer = null;

	}

	/**
	 * La función encargada de remover la frase y cargarla dentro del campo para editarla.
	 */
	private void modifyCompletePhrase() {
		Integer index = this.completeList.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<CompletionAnswer> model = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();
			CompletionAnswer complete = model.remove(index);

			this.addCompletePhrase();

			this.editingAnswer = complete;

			this.indexTextField.setText(complete.getIndex().toString());
			this.phraseTextField.setText(complete.getPhrase());
			this.indexTextField.requestFocus();
		}
	}

	/**
	 * La función encargada de quitar una frase del lado seleccionado.
	 */
	private void deleteCompletePhrase() {
		Integer index = this.completeList.getSelectedIndex();

		if (index >= 0) {
			DefaultListModel<CompletionAnswer> model = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();
			model.remove(index);
		}
	}

	@Override
	public void setEnabled(boolean b) {
		super.setEnabled(b);
		this.descriptionTextArea.setEnabled(b);

		this.completeList.setEnabled(b);
		this.indexTextField.setEnabled(b);
		this.phraseTextField.setEnabled(b);

		this.commitButton.setEnabled(b);
		this.rejectButton.setEnabled(b);
	}

	@Override
	protected void fromDialogToInstrument() throws CheckedException {
		// Agregamos la descripción.
		if (this.descriptionTextArea.getText().trim().isEmpty()) {
			throw new CheckedException("instrument.formal.objective.completion.form.error.description");
		} else {
			this.completionInstrument.setDescription(this.descriptionTextArea.getText().trim());
		}

		// Los modelos.
		DefaultListModel<CompletionAnswer> completeModel = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();

		List<CompletionAnswer> completions = new ArrayList<CompletionAnswer>();

		// Obtenemos el listado de las relaciones.
		for (Integer index = 0; index < completeModel.getSize(); index++) {
			CompletionAnswer answer = completeModel.get(index);
			completions.add(answer);
		}

		if (completions.isEmpty()) {
			throw new CheckedException("instrument.formal.objective.completion.form.error.completes");
		}

		// Agregamos las relaciones al listado del instrumento.
		this.completionInstrument.clearCompletes();
		this.completionInstrument.addAllCompletes(completions);
	}

	@Override
	protected void fromInstrumentToDialog() {
		this.descriptionTextArea.setText(this.completionInstrument.getDescription());

		// Los modelos.
		DefaultListModel<CompletionAnswer> completeModel = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();

		for (CompletionAnswer complete : this.completionInstrument.getAnswers()) {
			completeModel.addElement(complete);
		}
	}

	@Override
	protected void emptyFields() {
		DefaultListModel<CompletionAnswer> completeModel = (DefaultListModel<CompletionAnswer>) this.completeList.getModel();
		completeModel.clear();

		this.descriptionTextArea.setText("");
		this.indexTextField.setText("");
		this.phraseTextField.setText("");
	}

	@Override
	protected CompletionInstrumentService getInstrumentService() {
		return this.completionInstrumentService;
	}

	@Override
	protected CompletionInstrument getInstrument() {
		return this.completionInstrument;
	}

	@Override
	protected void setNewInstrument() {
		this.completionInstrument = new CompletionInstrument();
	}

	@Override
	protected <E extends Instrument> void setEditInstrument(E instrument) {
		this.completionInstrument = (CompletionInstrument) instrument;
	}

	@Override
	protected JLabel getProgressLabel() {
		return this.progressLabel;
	}

	@Override
	protected String getNewTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.completion.form.title.new");
	}

	@Override
	protected String getEditTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.completion.form.title.edit");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			CompletionInstrument instrument = HolderApplicationContext.getContext().getBean(CompletionInstrumentService.class).findById(7);
			CompletionInstrumentFormDialog dialog = (CompletionInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(CompletionInstrumentFormDialog.class).createEditDialog(instrument);
			// CompletionInstrumentFormDialog dialog = (CompletionInstrumentFormDialog)
			// HolderApplicationContext.getContext().getBean(CompletionInstrumentFormDialog.class)
			// .createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}