package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;
import com.proyecto.model.instrument.type.impl.InstrumentType;

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
	 * Las ventanas de edici�n de los instrumentos.
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
	private JComboBox<InstrumentTypeInterface> levelOneComboBox;
	private JComboBox<InstrumentTypeInterface> levelTwoComboBox;
	private JComboBox<InstrumentTypeInterface> levelThreeComboBox;
	private JComboBox<InstrumentTypeInterface> levelFourComboBox;

	/**
	 * El instrumento que vamos a dar de alta o a modificar y la clase que lo crea.
	 */
	private Class<? extends Instrument> instrumentClass;
	private Instrument instrument;

	/**
	 * Constructor de una ventana de selecci�n de tipo de instrumento.
	 */
	public SelectInstrumentDialog() {
		super();
		this.init();
		this.loadLevelOneComboBox();
	}

	/**
	 * La funci�n encargada de inicializar la ventana.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setBounds(100, 100, 570, 258);
		this.getContentPane().setLayout(null);

		this.levelOneComboBox = new JComboBox<InstrumentTypeInterface>();
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

		this.levelTwoComboBox = new JComboBox<InstrumentTypeInterface>();
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

		this.levelThreeComboBox = new JComboBox<InstrumentTypeInterface>();
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

		this.levelFourComboBox = new JComboBox<InstrumentTypeInterface>();
		this.levelFourComboBox.setEnabled(false);
		this.levelFourComboBox.setBounds(10, 134, 548, 30);
		this.getContentPane().add(this.levelFourComboBox);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 175, 548, 2);
		this.getContentPane().add(separator);

		JButton commitButton = new JButton("Aceptar");
		commitButton.setFont(new Font("Arial", Font.BOLD, 12));
		commitButton.setBounds(346, 188, 100, 35);
		commitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectInstrumentDialog.this.createNewInstrument();
			}
		});
		this.getContentPane().add(commitButton);
		this.getRootPane().setDefaultButton(commitButton);

		JButton rejectButton = new JButton("Cancelar");
		rejectButton.setFont(new Font("Arial", Font.BOLD, 12));
		rejectButton.setBounds(458, 188, 100, 35);
		rejectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectInstrumentDialog.this.dispose();
			}
		});
		this.getContentPane().add(rejectButton);
	}

	/**
	 * La funci�n que nos permite manejar el combo box n�mero 1.
	 */
	private void loadLevelOneComboBox() {
		this.levelOneComboBox.removeAllItems();

		// Volvemos a cargar el combo.
		for (InstrumentTypeInterface item : InstrumentType.values()) {
			this.levelOneComboBox.addItem(item);
		}
		this.levelOneComboBox.setSelectedIndex(-1);
		this.levelOneComboBox.setEnabled(true);
	}

	/**
	 * La funci�n encargada de cargar el tipo de instrumento que queremos listar dentro de esta ventana de selecci�n.
	 * 
	 * @param instrumentClass
	 *            El tipo de instrumento que queremos buscar para listar dentro de esta ventana.
	 */
	private void loadInstrumentTypesInComboBox(Class<? extends Instrument> instrumentClass) {
		// Solo si recibimos una clase, la filtramos.
		if (instrumentClass != null) {

			// Buscamos el tipo de instrumento para el nivel 1.
			for (InstrumentTypeInterface levelOne : InstrumentType.values()) {

				// Si es un hijo de ese tipo de instrumento.
				if (levelOne.getInstrumentClass().isAssignableFrom(instrumentClass)) {
					this.levelOneComboBox.setSelectedItem(levelOne);
					this.levelOneComboBox.setEnabled(false);

					if (levelOne.getSubInstruments() != null) {
						// Buscamos el segundo nivel.
						for (InstrumentTypeInterface levelTwo : levelOne.getSubInstruments()) {

							// Si es un hijo de ese tipo de instrumento.
							if (levelTwo.getInstrumentClass().isAssignableFrom(instrumentClass)) {
								this.levelTwoComboBox.setSelectedItem(levelTwo);
								this.levelTwoComboBox.setEnabled(false);

								if (levelTwo.getSubInstruments() != null) {
									// Buscamos el tercer nivel.
									for (InstrumentTypeInterface levelThree : levelTwo.getSubInstruments()) {

										// Si es un hijo de ese tipo de instrumento.
										if (levelThree.getInstrumentClass().isAssignableFrom(instrumentClass)) {
											this.levelThreeComboBox.setSelectedItem(levelThree);
											this.levelThreeComboBox.setEnabled(false);

											if (levelThree.getSubInstruments() != null) {
												// Buscamos el cuarto nivel.
												for (InstrumentTypeInterface levelFour : levelThree.getSubInstruments()) {

													// Si es un hijo de ese tipo de instrumento.
													if (levelFour.getInstrumentClass().isAssignableFrom(instrumentClass)) {
														this.levelFourComboBox.setSelectedItem(levelFour);
														this.levelFourComboBox.setEnabled(false);
														break;
													}
												}
												break;
											}
										}
									}
									break;
								}
							}
						}
						break;
					}
				}
			}
		}
	}

	/**
	 * La funci�n encargada de tomar el tipo de instrumento de un combo y cargar otro con los sub instrumentos de este.
	 * 
	 * @param preComboBox
	 *            El combo desde el que se va a sacar el instrumento.
	 * @param postComboBox
	 *            El combo donde se van a cargar los sub instrumentos del instrumento seleccionado en el otro combo.
	 */
	private void reloadComboBox(JComboBox<InstrumentTypeInterface> preComboBox, JComboBox<InstrumentTypeInterface> postComboBox) {
		// Vaciamos el combo que vamos a cargar y lo deshabilitamos.
		postComboBox.removeAllItems();
		postComboBox.setEnabled(false);

		// Volvemos a cargar el combo.
		InstrumentTypeInterface type = (InstrumentTypeInterface) preComboBox.getSelectedItem();
		if (type != null) {

			// Si se tiene m�s sub instrumentos los cargamos.
			if (type.getSubInstruments() != null) {

				for (InstrumentTypeInterface item : type.getSubInstruments()) {
					postComboBox.addItem(item);
				}
				postComboBox.setSelectedIndex(-1);
				postComboBox.setEnabled(true);
			}
		}
	}

	/**
	 * La funci�n encargada de tomar el nombre de la clase que vamos a crear como instrumento dentro del sistema.
	 */
	private void createNewInstrument() {
		if (this.levelFourComboBox.getSelectedItem() != null) {
			this.instrumentClass = ((InstrumentTypeInterface) this.levelFourComboBox.getSelectedItem()).getInstrumentClass();
		} else if (this.levelThreeComboBox.getSelectedItem() != null) {
			this.instrumentClass = ((InstrumentTypeInterface) this.levelThreeComboBox.getSelectedItem()).getInstrumentClass();
		}

		// Llamamos a la funci�n para abrir la ventana correspondiente al instrumento seleccionado.
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

			// Si tenemos alg�n dialogo.
			if (dialog != null) {
				dialog.setLocationRelativeTo(this);
				dialog.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, HolderMessage.getMessage("instrument.select.dialog.not.implement"), "Informaci�n",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	/**
	 * La funci�n encargada de inicializar la ventana de selecci�n de instrumentos.
	 * 
	 * @param instrumenClass
	 *            La clase de instrumentos que vamos a poder seleccionar a partir de dicho nivel.
	 * @return La ventana de selecci�n de instrumento.
	 */
	public SelectInstrumentDialog createNewDialog(Class<? extends Instrument> instrumenClass) {
		this.setTitle(HolderMessage.getMessage("instrument.select.dialog.title"));

		// Seteamos el tipo de instrumento m�nimo de selecci�n para crear.
		this.loadInstrumentTypesInComboBox(instrumenClass);

		this.setModal(true);
		return this;
	}

	/**
	 * La funci�n encargada de crear una ventana propia para la edici�n de un instrumento dado.
	 * 
	 * @param instrument
	 *            El instrumento que queremos editar.
	 */
	public void createEditDialog(Instrument instrument) {
		if (instrument != null) {
			this.instrument = instrument;
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
			String[] files =
				{ "/com/proyecto/spring/general-application-context.xml" };
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