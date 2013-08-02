package com.proyecto.view.instrument;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

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
import com.proyecto.model.instrument.CompletionInstrument;
import com.proyecto.model.instrument.ConceptualMapInstrument;
import com.proyecto.model.instrument.CorrespondenceInstrument;
import com.proyecto.model.instrument.EssayInstrument;
import com.proyecto.model.instrument.ExerciseInstrument;
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
 * La ventana que permite desplegar un listado de instrumento que tenemos dentro del sistema para administrarlos o seleccionar uno para su inclusión a
 * un reactivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
public class InstrumentListDialog extends JDialog {

	private static final long serialVersionUID = -3837565156703793373L;

	/**
	 * El servicio para recuperar los instrumentos de ensayo formal restringidos.
	 */
	@Autowired
	private RestrictedEssayActivityInstrumentService restrictedEssayActivityInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de ensayo formal no restringidos.
	 */
	@Autowired
	private UnrestrictedEssayActivityInstrumentService unrestrictedEssayActivityInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de selección simple.
	 */
	@Autowired
	private SingleChoiceInstrumentService singleChoiceInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de selección multiple.
	 */
	@Autowired
	private MultipleChoiceInstrumentService multipleChoiceInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de completar.
	 */
	@Autowired
	private CompletionInstrumentService completionInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de correlación.
	 */
	@Autowired
	private CorrespondenceInstrumentService correspondenceInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de mapas conceptuales.
	 */
	@Autowired
	private ConceptualMapInstrumentService conceptualMapInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de ensayo semiformales.
	 */
	@Autowired
	private EssayInstrumentService essayInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de ejercicios.
	 */
	@Autowired
	private ExerciseInstrumentService exerciseInstrumentService;
	/**
	 * El servicio para recuperar los instrumentos de portfolios.
	 */
	@Autowired
	private PortfolioInstrumentService portfolioInstrumentService;

	/**
	 * El listado de instrumentos que tenemos dentro de la tabla y la clase que corresponde a este.
	 */
	private final List<Instrument> instruments;
	private Class<? extends Instrument> instrumentClass;

	/**
	 * Los combos de filtrado de búsqueda.
	 */
	private JComboBox<InstrumentTypeInterface> levelOneComboBox;
	private JComboBox<InstrumentTypeInterface> levelTwoComboBox;
	private JComboBox<InstrumentTypeInterface> levelThreeComboBox;

	/**
	 * La tabla de los instrumentos.
	 */
	private JTable table;

	/**
	 * Los botones de acción.
	 */
	private JButton newButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JButton selectButton;
	private JButton closeButton;

	/**
	 * El label de progreso.
	 */
	private JLabel progressLabel;
	private JButton updateButton;

	/**
	 * Constructor de la ventana de administración de instrumento.
	 */
	public InstrumentListDialog() {
		super();

		this.init();
		this.instruments = new ArrayList<>();
	}

	/**
	 * La función de inicialización de los componentes.
	 */
	private void init() {
		this.setResizable(false);
		this.setModal(true);
		this.setBounds(100, 100, 699, 430);
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.getContentPane().setLayout(null);

		this.levelOneComboBox = new JComboBox<>();
		this.levelOneComboBox.setEnabled(false);
		this.levelOneComboBox.setBounds(10, 15, 200, 30);
		this.levelOneComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				InstrumentListDialog.this.reloadComboBox(InstrumentListDialog.this.levelOneComboBox, InstrumentListDialog.this.levelTwoComboBox);
			}
		});
		this.getContentPane().add(this.levelOneComboBox);

		this.levelTwoComboBox = new JComboBox<>();
		this.levelTwoComboBox.setEnabled(false);
		this.levelTwoComboBox.setBounds(222, 15, 200, 30);
		this.levelTwoComboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				InstrumentListDialog.this.reloadComboBox(InstrumentListDialog.this.levelTwoComboBox, InstrumentListDialog.this.levelThreeComboBox);
			}
		});
		this.getContentPane().add(this.levelTwoComboBox);

		this.levelThreeComboBox = new JComboBox<>();
		this.levelThreeComboBox.setEnabled(false);
		this.levelThreeComboBox.setBounds(434, 15, 200, 30);
		this.getContentPane().add(this.levelThreeComboBox);

		this.updateButton = new JButton();
		this.updateButton.setBounds(646, 13, 35, 35);
		this.updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InstrumentListDialog.this.update();
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

		this.newButton = new JButton(Resources.ADD_ICON);
		this.newButton.setBounds(646, 71, 35, 35);
		this.getContentPane().add(this.newButton);

		this.modifyButton = new JButton(Resources.MODIFY_ICON);
		this.modifyButton.setBounds(646, 119, 35, 35);
		this.getContentPane().add(this.modifyButton);

		this.deleteButton = new JButton(Resources.DELETE_ICON);
		this.deleteButton.setBounds(646, 167, 35, 35);
		this.getContentPane().add(this.deleteButton);

		this.selectButton = new JButton();
		this.selectButton.setBounds(646, 215, 35, 35);
		this.getContentPane().add(this.selectButton);

		this.progressLabel = new JLabel();
		this.progressLabel.setBounds(646, 312, 35, 35);
		this.getContentPane().add(this.progressLabel);

		this.closeButton = new JButton(Resources.RETURN_ICON);
		this.closeButton.setBounds(646, 359, 35, 35);
		this.getContentPane().add(this.closeButton);
	}

	@Override
	public void setEnabled(boolean enabled) {

		this.table.setEnabled(enabled);

		this.updateButton.setEnabled(enabled);

		this.newButton.setEnabled(enabled);
		this.modifyButton.setEnabled(enabled);
		this.deleteButton.setEnabled(enabled);
		this.selectButton.setEnabled(enabled);
		this.closeButton.setEnabled(enabled);
	}

	/**
	 * La función encargada de crear el modelo de la tabla.
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
	 * La función que nos permite manejar el combo box número 1.
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
	 * La función encargada de tomar el tipo de instrumento de un combo y cargar otro con los sub instrumentos de este.
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

			// Si se tiene más sub instrumentos los cargamos.
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
	 * La función encargada de actualizar el listado de los instrumentos que tenemos dentro de la ventana.
	 */
	private void update() {
		new Thread() {
			@Override
			public void run() {
				try {
					InstrumentListDialog.this.beforeLoadList();

					// Actualizamos la clase de los instrumentos que vamos a cargar.
					InstrumentListDialog.this.updateInstrumentClass();

					// Actualizamos el listado de los instrumentos.
					InstrumentListDialog.this.updateInstrumentsList();

					// Actualizamos la tabla de los instrumentos.
					InstrumentListDialog.this.updateInstrumentTable();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(InstrumentListDialog.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					InstrumentListDialog.this.afterLoadList();
				}
			}
		}.start();
	}

	/**
	 * La función antes de recuperar los instrumentos.
	 */
	private void beforeLoadList() {
		this.setEnabled(false);

		Resources.PROGRESS_LIST_ICON.setImageObserver(this.progressLabel);
		this.progressLabel.setIcon(Resources.PROGRESS_LIST_ICON);
	}

	/*
	 * La función después de recuperar los instrumentos.
	 */
	private void afterLoadList() {
		this.setEnabled(true);
		this.progressLabel.setIcon(null);
	}

	/**
	 * La función encargada de actualizar la clase de instrumento que vamos a cargar dentro de la ventana.
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
	 * La función encargada de actualizar el listado de instrumentos que tenemos de acuerdo a la clase de instrumento que tenemos seleccionado.
	 * 
	 * @throws CheckedException
	 *             En caso de alguna falla durante la recuperación de los instrumentos.
	 */
	private void updateInstrumentsList() throws CheckedException {
		// Creamos los listados que vamos a ocupar.
		Class<? extends Instrument>[] instrumentsClass = new Class[]
			{ RestrictedEssayActivityInstrument.class, UnrestrictedEssayActivityInstrument.class, SingleChoiceInstrument.class,
					MultipleChoiceInstrument.class, CompletionInstrument.class, CorrespondenceInstrument.class, ConceptualMapInstrument.class,
					EssayInstrument.class, ExerciseInstrument.class, PortfolioInstrument.class };

		// Creamos el listado de los servicios que manejan cada unos de esos instrumentos.
		InstrumentService<? extends Instrument>[] instrumentsService = new InstrumentService[]
			{ this.restrictedEssayActivityInstrumentService, this.unrestrictedEssayActivityInstrumentService, this.singleChoiceInstrumentService,
					this.multipleChoiceInstrumentService, this.completionInstrumentService, this.correspondenceInstrumentService,
					this.conceptualMapInstrumentService, this.essayInstrumentService, this.exerciseInstrumentService, this.portfolioInstrumentService };

		// Vaciamos el listado.
		this.instruments.clear();

		// Lo volvemos a cargar.
		for (int i = 0; i < instrumentsClass.length; i++) {
			// Solo agregamos los instrumentos si la clase del mismo es un hijo de la clase recibida.
			if (this.instrumentClass.isAssignableFrom(instrumentsClass[i])) {
				this.instruments.addAll(instrumentsService[i].findAll());
			}
		}
	}

	/**
	 * La función encargada de cargar dentro de la tabla el listado de los instrumentos.
	 */
	protected void updateInstrumentTable() {
		// Recuperamos el modelo de la tabla.
		DefaultTableModel tableModel = (DefaultTableModel) this.table.getModel();

		// Vaciamos el modelo.
		tableModel.getDataVector().clear();

		// Volvemos a cargar el modelo.
		for (Instrument instrument : this.instruments) {
			String[] row = new String[2];

			row[0] = instrument.getDescription();
			row[1] = instrument.getClass().getSimpleName();

			tableModel.addRow(row);
		}
	}

	/**
	 * La función encargada de inicializar la ventana de administración de instrumentos.
	 * 
	 * @return La ventana de administración de instrumento.
	 */
	public InstrumentListDialog createCrudDialog() {
		this.loadLevelOneComboBox();
		this.setTitle("Administración de instrumentos");
		this.setModal(true);
		return this;
	}

	/**
	 * La función encargada de inicializar la ventana de selección de instrumentos.
	 * 
	 * @return La ventana de selección de instrumento.
	 */
	public InstrumentListDialog createSelectDialog() {
		this.loadLevelOneComboBox();
		this.setTitle("Selección de instrumentos");
		this.setModal(true);
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

			InstrumentListDialog dialog = HolderApplicationContext.getContext().getBean(InstrumentListDialog.class).createCrudDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}