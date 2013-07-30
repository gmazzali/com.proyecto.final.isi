package com.proyecto.view.instrument;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.Instrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.model.instrument.type.InstrumentTypeInterface;
import com.proyecto.service.instrument.SingleChoiceInstrumentService;
import com.proyecto.view.Resources;

/**
 * La ventana que permite desplegar un listado de instrumento que tenemos dentro del sistema para administrarlos o seleccionar uno para su inclusión a
 * un reactivo.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class InstrumentListDialog extends JDialog {

	private static final long serialVersionUID = -3837565156703793373L;

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
	 * Constructor de la ventana de administración de instrumento.
	 */
	public InstrumentListDialog() {
		super();
		this.getContentPane().setFont(new Font("Arial", Font.PLAIN, 12));
		this.init();
	}

	/**
	 * La función de inicialización de los componentes.
	 */
	private void init() {
		this.setResizable(false);
		this.setModal(true);
		this.setBounds(100, 100, 611, 420);
		this.getContentPane().setLayout(null);

		this.levelOneComboBox = new JComboBox<>();
		this.levelOneComboBox.setEnabled(false);
		this.levelOneComboBox.setBounds(10, 13, 170, 30);
		this.getContentPane().add(this.levelOneComboBox);

		this.levelTwoComboBox = new JComboBox<>();
		this.levelTwoComboBox.setEnabled(false);
		this.levelTwoComboBox.setBounds(192, 13, 170, 30);
		this.getContentPane().add(this.levelTwoComboBox);

		this.levelThreeComboBox = new JComboBox<>();
		this.levelThreeComboBox.setEnabled(false);
		this.levelThreeComboBox.setBounds(374, 13, 170, 30);
		this.getContentPane().add(this.levelThreeComboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 534, 323);
		this.getContentPane().add(scrollPane);

		this.table = new JTable();
		this.table.setFont(this.getContentPane().getFont());
		scrollPane.setViewportView(this.table);

		JButton newButton = new JButton(Resources.ADD_ICON);
		newButton.setBounds(556, 56, 35, 35);
		this.getContentPane().add(newButton);

		JButton modifyButton = new JButton(Resources.MODIFY_ICON);
		modifyButton.setBounds(556, 104, 35, 35);
		this.getContentPane().add(modifyButton);

		JButton deleteButton = new JButton(Resources.DELETE_ICON);
		deleteButton.setBounds(556, 152, 35, 35);
		this.getContentPane().add(deleteButton);

		JButton selectButton = new JButton();
		selectButton.setBounds(556, 200, 35, 35);
		this.getContentPane().add(selectButton);

		JButton closeButton = new JButton(Resources.RETURN_ICON);
		closeButton.setBounds(556, 344, 35, 35);
		this.getContentPane().add(closeButton);
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

			List<SingleChoiceInstrument> instruments = HolderApplicationContext.getContext().getBean(SingleChoiceInstrumentService.class).findAll();

			for (Instrument instrument : instruments) {
				System.out.println(instrument.getClass().getSimpleName() + " -> " + instrument.getDescription());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}