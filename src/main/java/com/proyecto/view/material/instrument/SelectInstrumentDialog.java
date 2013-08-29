package com.proyecto.view.material.instrument;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.type.InstrumentType;
import com.proyecto.model.material.instrument.type.impl.InstrumentTypeImpl;
import com.proyecto.view.Resources;

/**
 * La ventana que va a desplegar un conjunto de combos para poder seleccionar el tipo de instrumento que vamos a crear en el sistema.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class SelectInstrumentDialog extends JDialog {

	private static final long serialVersionUID = -3301595004234653551L;

	/**
	 * Las ventanas de edición de los instrumentos.
	 */
	@Autowired
	private CorrespondenceInstrumentFormDialog correspondenceInstrumentFormDialog;

	@Autowired
	private MultipleChoiceInstrumentFormDialog multipleChoiceInstrumentFormDialog;

	@Autowired
	private SingleChoiceInstrumentFormDialog singleChoiceInstrumentFormDialog;

	@Autowired
	private CompletionInstrumentFormDialog completionInstrumentFormDialog;

	@Autowired
	private RestrictedEssayActivityInstrumentFormDialog restrictedEssayActivityInstrumentFormDialog;

	@Autowired
	private UnrestrictedEssayActivityInstrumentFormDialog unrestrictedEssayActivityInstrumentFormDialog;

	/**
	 * Los combos para cada uno de los niveles de los instrumentos.
	 */
	private JComboBox<InstrumentType> levelOneComboBox;
	private JComboBox<InstrumentType> levelTwoComboBox;
	private JComboBox<InstrumentType> levelThreeComboBox;
	private JComboBox<InstrumentType> levelFourComboBox;

	/**
	 * El listado de los tipos de instrumentos que vamos a poder administrar y seleccionar dentro de esta ventana.
	 */
	private List<InstrumentType> instrumentTypes;

	/**
	 * El instrumento que vamos a dar de alta o a modificar y la clase que lo crea.
	 */
	private Class<? extends Instrument> instrumentClass;
	private Instrument instrument;

	/**
	 * Constructor de una ventana de selección de tipo de instrumento.
	 */
	public SelectInstrumentDialog() {
		super();
		this.init();
	}

	/**
	 * La función encargada de inicializar la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 570, 258);
		this.getContentPane().setLayout(null);

		this.levelOneComboBox = new JComboBox<InstrumentType>();
		this.levelOneComboBox.setEnabled(false);
		this.levelOneComboBox.setBounds(10, 11, 548, 30);
		this.levelOneComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SelectInstrumentDialog.this
						.reloadComboBox(SelectInstrumentDialog.this.levelOneComboBox, SelectInstrumentDialog.this.levelTwoComboBox);
			}
		});
		this.getContentPane().add(this.levelOneComboBox);

		this.levelTwoComboBox = new JComboBox<InstrumentType>();
		this.levelTwoComboBox.setEnabled(false);
		this.levelTwoComboBox.setBounds(10, 52, 548, 30);
		this.levelTwoComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SelectInstrumentDialog.this.reloadComboBox(SelectInstrumentDialog.this.levelTwoComboBox,
						SelectInstrumentDialog.this.levelThreeComboBox);
			}
		});
		this.getContentPane().add(this.levelTwoComboBox);

		this.levelThreeComboBox = new JComboBox<InstrumentType>();
		this.levelThreeComboBox.setEnabled(false);
		this.levelThreeComboBox.setBounds(10, 93, 548, 30);
		this.levelThreeComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				SelectInstrumentDialog.this.reloadComboBox(SelectInstrumentDialog.this.levelThreeComboBox,
						SelectInstrumentDialog.this.levelFourComboBox);
			}
		});
		this.getContentPane().add(this.levelThreeComboBox);

		this.levelFourComboBox = new JComboBox<InstrumentType>();
		this.levelFourComboBox.setEnabled(false);
		this.levelFourComboBox.setBounds(10, 134, 548, 30);
		this.getContentPane().add(this.levelFourComboBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 175, 548, 2);
		this.getContentPane().add(separator);

		JButton commitButton = new JButton(Resources.COMMIT_ICON);
		commitButton.setBounds(476, 189, 35, 35);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectInstrumentDialog.this.createNewInstrument();
			}
		});
		this.getContentPane().add(commitButton);

		JButton rejectButton = new JButton(Resources.CLOSE_ICON);
		rejectButton.setBounds(523, 189, 35, 35);
		rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectInstrumentDialog.this.dispose();
			}
		});
		this.getContentPane().add(rejectButton);
	}

	/**
	 * La función encargada de actualizar el listado de los instrumentos del combo numero 1 de acuerdo a los instrumentos habilitados para esta
	 * ventana.
	 */
	private void updateLevelOneComboBox() {
		this.levelOneComboBox.removeAllItems();

		// Si el tipo de evaluación no es nulo, cargamos los tipos permitidos.
		if (this.instrumentTypes != null) {
			for (InstrumentType item : this.instrumentTypes) {
				this.levelOneComboBox.addItem(item);
			}
		}
		// Sino, cargamos el combo con todos los tipos posibles.
		else {
			// Volvemos a cargar el combo.
			for (InstrumentType item : InstrumentTypeImpl.values()) {
				this.levelOneComboBox.addItem(item);
			}
		}
		// No seleccionamos nada en el combo y lo habilitamos.
		this.levelOneComboBox.setSelectedIndex(-1);
		this.levelOneComboBox.setEnabled(true);
	}

	/**
	 * La función encargada de tomar el tipo de instrumento de un combo y cargar otro con los sub instrumentos de este.
	 * 
	 * @param preComboBox
	 *            El combo desde el que se va a sacar el instrumento.
	 * @param postComboBox
	 *            El combo donde se van a cargar los sub instrumentos del instrumento seleccionado en el otro combo.
	 */
	private void reloadComboBox(JComboBox<InstrumentType> preComboBox, JComboBox<InstrumentType> postComboBox) {
		// Vaciamos el combo que vamos a cargar y lo deshabilitamos.
		postComboBox.removeAllItems();
		postComboBox.setEnabled(false);

		// Volvemos a cargar el combo.
		InstrumentType type = (InstrumentType) preComboBox.getSelectedItem();
		if (type != null) {

			// Si se tiene más sub instrumentos los cargamos.
			if (type.getSubInstruments() != null) {

				for (InstrumentType item : type.getSubInstruments()) {
					postComboBox.addItem(item);
				}
				postComboBox.setSelectedIndex(-1);
				postComboBox.setEnabled(true);
			}
		}
	}

	/**
	 * La función encargada de tomar el nombre de la clase que vamos a crear como instrumento dentro del sistema.
	 */
	private void createNewInstrument() {
		if (this.levelFourComboBox.getSelectedItem() != null) {
			this.instrumentClass = ((InstrumentType) this.levelFourComboBox.getSelectedItem()).getInstrumentClass();
		} else if (this.levelThreeComboBox.getSelectedItem() != null) {
			this.instrumentClass = ((InstrumentType) this.levelThreeComboBox.getSelectedItem()).getInstrumentClass();
		}

		// Llamamos a la función para abrir la ventana correspondiente al instrumento seleccionado.
		this.openEditInstrumentDialog();
	}

	/**
	 * Vemos que clase de instrumento tenemos para editar o dar de alta.
	 */
	private void openEditInstrumentDialog() {
		// Solo si la clase del instrumento no es nula.
		if (this.instrumentClass != null) {

			JDialog dialog = null;

			// Si el instrumento es de ensayo restrictivo.
			if (this.instrumentClass.getName().equals(RestrictedEssayActivityInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.restrictedEssayActivityInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.restrictedEssayActivityInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si el instrumento es de ensayo no restrictivo.
			if (this.instrumentClass.getName().equals(UnrestrictedEssayActivityInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.unrestrictedEssayActivityInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.unrestrictedEssayActivityInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si el instrumento es de correspondencia.
			if (this.instrumentClass.getName().equals(CorrespondenceInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.correspondenceInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.correspondenceInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si el instrumento es de completar.
			if (this.instrumentClass.getName().equals(CompletionInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.completionInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.completionInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si el instrumento es de simple choice.
			if (this.instrumentClass.getName().equals(SingleChoiceInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.singleChoiceInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.singleChoiceInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si el instrumento es de multiple choice.
			if (this.instrumentClass.getName().equals(MultipleChoiceInstrument.class.getName())) {
				if (this.instrument == null) {
					dialog = this.multipleChoiceInstrumentFormDialog.createNewDialog();
				} else {
					dialog = this.multipleChoiceInstrumentFormDialog.createEditDialog(this.instrument);
				}
			}

			// Si tenemos algún dialogo.
			if (dialog != null) {
				dialog.setLocationRelativeTo(this);
				this.dispose();
				dialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, HolderMessage.getMessage("instrument.form.select.not.implement"), "Información",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * La función encargada de inicializar la ventana de selección de instrumentos.
	 * 
	 * @param instrumentsType
	 *            El listado de los tipos de instrumentos que vamos a poder seleccionar dentro de esta ventana.
	 * @return La ventana de selección de instrumento.
	 */
	public SelectInstrumentDialog createNewDialog(List<InstrumentType> instrumentTypes) {
		this.setTitle(HolderMessage.getMessage("instrument.form.select.title"));

		// Seteamos el tipo de instrumento que podemos crear dentro de esta ventana.
		this.instrumentTypes = instrumentTypes;
		this.updateLevelOneComboBox();

		return this;
	}

	/**
	 * La función encargada de crear una ventana propia para la edición de un instrumento dado.
	 * 
	 * @param instrument
	 *            El instrumento que queremos editar.
	 * @param instrumentsType
	 *            El listado de los tipos de instrumentos que vamos a poder seleccionar dentro de esta ventana.
	 */
	public void createEditDialog(Instrument instrument, List<InstrumentType> instrumentTypes) {
		if (instrument != null) {
			this.instrument = instrument;
			this.instrumentTypes = instrumentTypes;
			this.instrumentClass = instrument.getClass();
			this.openEditInstrumentDialog();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			String[] files = { "/com/proyecto/spring/general-application-context.xml" };
			HolderApplicationContext.initApplicationContext(files);

			// CorrespondenceInstrument instrument =
			// HolderApplicationContext.getContext().getBean(CorrespondenceInstrumentService.class).findById(4);
			//
			// SingleChoiceInstrument instrument = HolderApplicationContext.getContext().getBean(SingleChoiceInstrumentService.class).findById(8);
			//
			// HolderApplicationContext.getContext().getBean(SelectInstrumentDialog.class).createEditDialog(instrument);
			//
			SelectInstrumentDialog dialog = HolderApplicationContext.getContext().getBean(SelectInstrumentDialog.class).createNewDialog(null);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}