package com.proyecto.view.instrument;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.exception.CheckedException;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.converter.InstrumentClassToNameConverter;
import com.proyecto.model.assessment.type.AssessmentType;
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.ConceptualMapInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.EssayInstrument;
import com.proyecto.model.instrument.ExerciseInstrument;
import com.proyecto.model.instrument.FormalInstrument;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.model.instrument.PortfolioInstrument;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;
import com.proyecto.model.instrument.type.impl.InstrumentType;
import com.proyecto.service.instrument.CompletionInstrumentService;
import com.proyecto.service.instrument.ConceptualMapInstrumentService;
import com.proyecto.service.instrument.CorrespondenceInstrumentService;
import com.proyecto.service.instrument.EssayInstrumentService;
import com.proyecto.service.instrument.ExerciseInstrumentService;
import com.proyecto.service.instrument.InstrumentService;
import com.proyecto.service.instrument.MultipleChoiceInstrumentService;
import com.proyecto.service.instrument.PortfolioInstrumentService;
import com.proyecto.service.instrument.RestrictedEssayActivityInstrumentService;
import com.proyecto.service.instrument.SingleChoiceInstrumentService;
import com.proyecto.service.instrument.UnrestrictedEssayActivityInstrumentService;
import com.proyecto.view.Resources;

/**
 * La ventana que permite desplegar un listado de instrumento que tenemos dentro del sistema para administrarlos o seleccionar uno para su inclusi�n a
 * un reactivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class InstrumentListDialog extends JDialog {

	private static final long serialVersionUID = -3837565156703793373L;

	/**
	 * Los servicios para los instrumentos concretos.
	 */
	@Autowired
	private RestrictedEssayActivityInstrumentService restrictedEssayActivityInstrumentService;

	@Autowired
	private UnrestrictedEssayActivityInstrumentService unrestrictedEssayActivityInstrumentService;

	@Autowired
	private SingleChoiceInstrumentService singleChoiceInstrumentService;

	@Autowired
	private MultipleChoiceInstrumentService multipleChoiceInstrumentService;

	@Autowired
	private CompletionInstrumentService completionInstrumentService;

	@Autowired
	private CorrespondenceInstrumentService correspondenceInstrumentService;

	@Autowired
	private ConceptualMapInstrumentService conceptualMapInstrumentService;

	@Autowired
	private EssayInstrumentService essayInstrumentService;

	@Autowired
	private ExerciseInstrumentService exerciseInstrumentService;

	@Autowired
	private PortfolioInstrumentService portfolioInstrumentService;

	/**
	 * La ventana de selecci�n de un instrumento para su creaci�n o edici�n.
	 */
	@Autowired
	private SelectInstrumentDialog selectInstrumentDialog;

	/**
	 * El listado de instrumentos que tenemos dentro de la tabla y la clase que corresponde a este y el mapa de servicio para los mismos.
	 */
	private final List<Instrument> instruments;
	private Class<? extends Instrument> instrumentClass;
	private Map<Class<? extends Instrument>, InstrumentService<? extends Instrument>> services;

	/**
	 * El valor booleano que nos determina si la ventana es de selecci�n o de administraci�n y el instrumento seleccionado.
	 */
	private Boolean isSelectDialog;
	private Instrument selectedInstrument;

	/**
	 * Los combos de filtrado de b�squeda.
	 */
	private JComboBox<InstrumentTypeInterface> levelOneComboBox;
	private JComboBox<InstrumentTypeInterface> levelTwoComboBox;
	private JComboBox<InstrumentTypeInterface> levelThreeComboBox;

	/**
	 * La tabla de los instrumentos.
	 */
	private JTable table;
	/**
	 * Los botones de acci�n.
	 */
	private JButton updateButton;
	private JButton newButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton selectButton;
	private JButton closeButton;
	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;

	/**
	 * Constructor de la ventana de administraci�n de instrumento.
	 */
	public InstrumentListDialog() {
		super();

		this.init();
		this.instruments = new ArrayList<Instrument>();
	}

	/**
	 * La funci�n de inicializaci�n de los componentes.
	 */
	private void init() {
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		this.setBounds(100, 100, 693, 431);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		this.levelOneComboBox = new JComboBox<InstrumentTypeInterface>();
		this.levelOneComboBox.setEnabled(false);
		this.levelOneComboBox.setBounds(10, 15, 200, 30);
		this.levelOneComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				InstrumentListDialog.this.reloadComboBox(InstrumentListDialog.this.levelOneComboBox, InstrumentListDialog.this.levelTwoComboBox);
			}
		});
		this.getContentPane().add(this.levelOneComboBox);

		this.levelTwoComboBox = new JComboBox<InstrumentTypeInterface>();
		this.levelTwoComboBox.setEnabled(false);
		this.levelTwoComboBox.setBounds(222, 15, 200, 30);
		this.levelTwoComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				InstrumentListDialog.this.reloadComboBox(InstrumentListDialog.this.levelTwoComboBox, InstrumentListDialog.this.levelThreeComboBox);
			}
		});
		this.getContentPane().add(this.levelTwoComboBox);

		this.levelThreeComboBox = new JComboBox<InstrumentTypeInterface>();
		this.levelThreeComboBox.setEnabled(false);
		this.levelThreeComboBox.setBounds(434, 15, 200, 30);
		this.getContentPane().add(this.levelThreeComboBox);

		this.updateButton = new JButton(Resources.REFRESH_ELEMENT_ICON);
		this.updateButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.updateButton.setBounds(646, 13, 35, 35);
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.updateInstruments();
			}
		});
		this.getContentPane().add(this.updateButton);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 57, 671, 2);
		this.getContentPane().add(separator);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 71, 624, 323);
		this.getContentPane().add(scrollPane);

		this.table = new JTable();
		this.table.setModel(new DefaultTableModel());
		this.table.setFont(this.getContentPane().getFont());
		scrollPane.setViewportView(this.table);
		this.initTableModel();

		this.newButton = new JButton(Resources.ADD_ELEMENT_ICON);
		this.newButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.newButton.setBounds(646, 71, 35, 35);
		this.newButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.newInstrument();
			}
		});
		this.getContentPane().add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ELEMENT_ICON);
		this.modifyButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.modifyButton.setBounds(646, 118, 35, 35);
		this.modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.modifyInstrument();
			}
		});
		this.getContentPane().add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ELEMENT_ICON);
		this.deleteButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.deleteButton.setBounds(646, 165, 35, 35);
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.removeInstrument();
			}
		});
		this.getContentPane().add(this.deleteButton);

		this.selectButton = new JButton(Resources.SELECT_ELEMENT_ICON);
		this.selectButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.selectButton.setBounds(646, 212, 35, 35);
		this.selectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.selectInstrument();
			}
		});
		this.getContentPane().add(this.selectButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(646, 312, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.closeButton = new JButton(Resources.CLOSE_ICON);
		this.closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		this.closeButton.setBounds(646, 359, 35, 35);
		this.closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.dispose();
			}
		});
		this.getContentPane().add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {

		this.table.setEnabled(enabled);

		this.updateButton.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
		this.selectButton.setEnabled(enabled && this.isSelectDialog);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La funci�n encargada de crear el modelo de la tabla.
	 */
	private void initTableModel() {
		DefaultTableModel tableModel = new DefaultTableModel() {
			private static final long serialVersionUID = -890706019332687145L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addColumn("Descripcion");
		tableModel.addColumn("Tipo");

		// Seteamos el modelo a la tabla.
		this.table.setModel(tableModel);

		this.table.getColumnModel().getColumn(0).setPreferredWidth(400);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(200);

	}

	/**
	 * La funci�n que nos permite manejar el combo box n�mero 1.
	 */
	@Deprecated
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
	 * La funci�n encargada de actualizar el listado de los instrumentos del combo numero 1 de acuerdo al tipo de evaluaci�n que recibimos como par�metro.
	 * @param assessmentType El tipo de clase que recibimos como parametro para recargar el combo de tipo de instrumentos de nivel 1.
	 */
	private void updateLevelOneComboBox(AssessmentType assessmentType) {
		this.levelOneComboBox.removeAllItems();

		// Si el tipo de evaluaci�n no es nulo, cargamos los tipos permitidos.
		if(assessmentType != null) {
			for (InstrumentTypeInterface item : assessmentType.getInstrumentsTypeAllowed()) {
				this.levelOneComboBox.addItem(item);
			}
		} 
		// Sino, cargamos el combo con todos los tipos posibles.
		else {
			// Volvemos a cargar el combo.
			for (InstrumentTypeInterface item : InstrumentType.values()) {
				this.levelOneComboBox.addItem(item);
			}
		}
		// No seleccionamos nada en el combo y lo habilitamos.
		this.levelOneComboBox.setSelectedIndex(-1);
		this.levelOneComboBox.setEnabled(true);
		
	}

	/**
	 * La funci�n encargada de cargar el tipo de instrumento que queremos listar dentro de esta ventana de selecci�n.
	 * 
	 * @param instrumentClass
	 *            El tipo de instrumento que queremos buscar para listar dentro de esta ventana.
	 */
	@Deprecated
	private void loadInstrumentTypesInComboBox(Class<? extends Instrument> instrumentClass) {
		// Cargamos el combo inicial.
		this.loadLevelOneComboBox();

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
		}
		// Ahora actualizamos el listado de instrumentos.
		this.updateInstruments();
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
	 * La funci�n encargada de actualizar el listado de los instrumentos que tenemos dentro de la ventana.
	 */
	private void updateInstruments() {
		new Thread() {
			@Override
			public void run() {
				// Ejecutamos las acciones antes de procesar.
				InstrumentListDialog.this.beforeExecuteProccess();

				// Vaciamos la lista de instrumentos.
				DefaultTableModel tableModel = (DefaultTableModel) InstrumentListDialog.this.table.getModel();
				tableModel.getDataVector().clear();
		
				new Thread() {
					@Override
					public void run() {
						try {
							// Actualizamos la clase de los instrumentos que vamos a cargar.
							InstrumentListDialog.this.updateInstrumentClass();

							// Volvemos a cargar el listado de instrumentos y la tabla de los mismos.
							InstrumentListDialog.this.updateInstrumentsList();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(InstrumentListDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							InstrumentListDialog.this.afterExecuteProccess();
						}
					}
				}.start();
			}
		}.start();
	}

	/**
	 * La funci�n antes de procesar los instrumentos.
	 */
	private void beforeExecuteProccess() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La funci�n despu�s de procesar los instrumentos.
	 */
	private void afterExecuteProccess() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La funci�n encargada de actualizar la clase de instrumento que vamos a cargar dentro de la ventana.
	 */
	private void updateInstrumentClass() {
		InstrumentTypeInterface instrumentType = null;

		// Si el combo 3 esta seleccionado.
		if (this.levelThreeComboBox.getSelectedItem() != null) {
			instrumentType = (InstrumentTypeInterface) this.levelThreeComboBox.getSelectedItem();
		}

		// Si el combo 2 esta seleccionado.
		else if (this.levelTwoComboBox.getSelectedItem() != null) {
			instrumentType = (InstrumentTypeInterface) this.levelTwoComboBox.getSelectedItem();
		}

		// Si el combo 1 esta seleccionado.
		else if (this.levelOneComboBox.getSelectedItem() != null) {
			instrumentType = (InstrumentTypeInterface) this.levelOneComboBox.getSelectedItem();
		}

		// Solo si es nulo el tipo de instrumento seleccionado.
		if (instrumentType != null) {
			this.instrumentClass = instrumentType.getInstrumentClass();
		} else {
			this.instrumentClass = Instrument.class;
		}
	}

	/**
	 * La funci�n encargada de actualizar el listado de instrumentos que tenemos de acuerdo a la clase de instrumento que tenemos seleccionado y cargamos dicho listado dentro de la tabla.
	 * 
	 * @throws CheckedException
	 *             En caso de alguna falla durante la recuperaci�n de los instrumentos.
	 */
	private void updateInstrumentsList() throws CheckedException {
		// Cargamos el mapa de servicios.
		this.initServices();

		// Vaciamos el listado.
		this.instruments.clear();

		for (Class<? extends Instrument> clazz : this.services.keySet()) {
			if (this.instrumentClass.isAssignableFrom(clazz)) {
				this.instruments.addAll(this.services.get(clazz).findAll());
			}
		}
		
		// Cargamos el listado dentro de la tabla.
		this.loadInstrumentTable();
	}

	/**
	 * La funci�n encargada de cargar dentro de la tabla el listado de los instrumentos.
	 */
	private void loadInstrumentTable() {
		// Recuperamos el modelo de la tabla.
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();

		// Volvemos a cargar el modelo.
		for (Instrument instrument : this.instruments) {
			String[] row = new String[2];

			row[0] = instrument.getDescription();
			row[1] = InstrumentClassToNameConverter.converter(instrument.getClass());

			tableModel.addRow(row);
		}
	}

	/**
	 * La funci�n encargada de recuperar el servicio para un tipo de instrumento recibido como par�metro.
	 * 
	 * @param instrumentClass
	 *            El tipo de instrumento recibido como par�metro.
	 * @return El elemento que presta servicios a este instrumento.
	 */
	@SuppressWarnings("unchecked")
	private InstrumentService<Instrument> getInstrumentService(Class<? extends Instrument> instrumentClass) {
		// Cargamos el mapa de servicios.
		this.initServices();

		for (Class<? extends Instrument> clazz : this.services.keySet()) {
			if (instrumentClass.equals(clazz)) {
				return (InstrumentService<Instrument>) this.services.get(clazz);
			}
		}

		return null;
	}

	/**
	 * La funci�n encargada de cargar el mapa de los servicios para cada uno de los instrumentos que tenemos dentro del sistema.
	 */
	private void initServices() {
		// Si es la primera vez que usamos el mapa, lo cargamos.
		if (this.services == null) {
			this.services = new HashMap<>();

			this.services.put(RestrictedEssayActivityInstrument.class, this.restrictedEssayActivityInstrumentService);
			this.services.put(UnrestrictedEssayActivityInstrument.class, this.unrestrictedEssayActivityInstrumentService);

			this.services.put(SingleChoiceInstrument.class, this.singleChoiceInstrumentService);
			this.services.put(MultipleChoiceInstrument.class, this.multipleChoiceInstrumentService);
			this.services.put(CompletionInstrument.class, this.completionInstrumentService);
			this.services.put(CorrespondenceInstrument.class, this.correspondenceInstrumentService);

			this.services.put(ConceptualMapInstrument.class, this.conceptualMapInstrumentService);
			this.services.put(EssayInstrument.class, this.essayInstrumentService);
			this.services.put(ExerciseInstrument.class, this.exerciseInstrumentService);

			this.services.put(PortfolioInstrument.class, this.portfolioInstrumentService);
		}
	}

	/**
	 * La funci�n encargada de crear una nueva instancia de un instrumento dentro del sistema.
	 */
	private void newInstrument() {
		// Creamos la ventana para dar de alta un nuevo instrumento.
		JDialog dialog = this.selectInstrumentDialog.createNewDialog(null);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);

		// Recargamos la tabla de instrumentos.
		this.updateInstruments();
	}

	/**
	 * La funci�n encargada de modificar un instrumento seleccionado dentro del listado de los mismos.
	 */
	private void modifyInstrument() {
		// Tomamos el �ndice de la tabla que tenemos seleccionado.
		Integer instrumentIndex = this.table.getSelectedRow();

		// Si tenemos algo seleccionado.
		if (instrumentIndex >= 0) {
			// Creamos la ventana para modificar instrumento.
			this.selectInstrumentDialog.createEditDialog(this.instruments.get(this.table.convertRowIndexToModel(instrumentIndex)));
			this.updateInstruments();
		}
	}

	/**
	 * La funci�n encargada de eliminar un instrumento seleccionado dentro del listado de los mismos.
	 */
	private void removeInstrument() {
		// Tomamos el �ndice de la tabla que tenemos seleccionado.
		Integer instrumentIndex = this.table.getSelectedRow();

		// Si tenemos algo seleccionado.
		if (instrumentIndex >= 0) {
			final Instrument deleteInstrument = this.instruments.get(this.table.convertRowIndexToModel(instrumentIndex));
			final InstrumentService<Instrument> instrumentService = this.getInstrumentService(deleteInstrument.getClass());

			if (JOptionPane.showConfirmDialog(this, HolderMessage.getMessage("instrument.manager.dialog.delete.confirm"), "Confirmaci�n",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

				new Thread() {
					@Override
					public void run() {
						try {
							InstrumentListDialog.this.beforeExecuteProccess();
							instrumentService.delete(deleteInstrument);
							InstrumentListDialog.this.updateInstruments();
						} catch (CheckedException e) {
							JOptionPane.showMessageDialog(InstrumentListDialog.this,
									HolderMessage.getMessage("instrument.manager.dialog.delete.failed"), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							InstrumentListDialog.this.afterExecuteProccess();

						}
					}
				}.start();
			}
		}
	}

	/**
	 * La funci�n encargada de tomar el instrumento seleccionado y mantenerlo para retornarlo mas adelante en el sistema.
	 */
	private void selectInstrument() {
		// Tomamos el �ndice de la tabla que tenemos seleccionado.
		Integer instrumentIndex = this.table.getSelectedRow();

		// Si tenemos algo seleccionado.
		if (instrumentIndex >= 0) {
			// Tomamos el instrumento seleccionado.
			this.selectedInstrument = this.instruments.get(this.table.convertRowIndexToModel(instrumentIndex));
			this.dispose();
		}
	}

	/**
	 * La funci�n encargada de retornar el instrumento que seleccionamos dentro de la ventana.
	 * 
	 * @return El instrumento que seleccionamos dentro de la ventana.
	 */
	public Instrument getSelectedInstrument() {
		return this.selectedInstrument;
	}

	/**
	 * La funci�n encargada de inicializar la ventana de administraci�n de instrumentos.
	 * 
	 * @return La ventana de administraci�n de instrumento.
	 */
	public InstrumentListDialog createCrudDialog() {
		this.setTitle(HolderMessage.getMessage("instrument.manager.dialog.title.crud"));

		this.selectedInstrument = null;
		this.isSelectDialog = false;

		this.loadLevelOneComboBox();

		return this;
	}

	/**
	 * La funci�n encargada de inicializar la ventana de selecci�n de instrumentos.
	 * 
	 * @param instrumentClass
	 *            La clase m�nima de los instrumentos que vamos a listar dentro de esta ventana.
	 * @return La ventana de selecci�n de instrumento.
	 */
	public InstrumentListDialog createSelectDialog(Class<? extends Instrument> instrumentClass) {
		this.setTitle(HolderMessage.getMessage("instrument.manager.dialog.title.select"));

		this.selectedInstrument = null;
		this.isSelectDialog = true;

		this.loadInstrumentTypesInComboBox(instrumentClass);

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

			// InstrumentListDialog dialog = HolderApplicationContext.getContext().getBean(InstrumentListDialog.class).createCrudDialog();
			InstrumentListDialog dialog = HolderApplicationContext.getContext().getBean(InstrumentListDialog.class)
					.createSelectDialog(FormalInstrument.class);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}