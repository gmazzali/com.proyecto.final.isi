package com.proyecto.view.instrument;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class InstrumentListDialog extends JDialog {

	private static final long serialVersionUID = -3837565156703793373L;
	private final JTable table;

	/**
	 * Create the dialog.
	 */
	public InstrumentListDialog() {
		super();
		this.setResizable(false);
		this.setModal(true);
		this.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 577, 512);
		this.getContentPane().add(scrollPane);

		this.table = new JTable();
		scrollPane.setViewportView(this.table);
		this.init();
	}

	private void init() {
		this.setBounds(100, 100, 690, 562);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InstrumentListDialog dialog = new InstrumentListDialog();
					dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
