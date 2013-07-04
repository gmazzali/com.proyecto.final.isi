package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.service.instrument.SingleChoiceInstrumentService;

/**
 * La clase que despliega la ventana de selección para los instrumentos de selección simple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class SingleChoiceInstrumentFormDialog extends ChoiceInstrumentFormDialog {

	private static final long serialVersionUID = -5572643162022447980L;

	@Autowired
	private SingleChoiceInstrumentService singleChoiceInstrumentService;

	@Override
	protected ChoiceInstrument newChoiceInstrument() {
		return new SingleChoiceInstrument();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected SingleChoiceInstrumentService getChoiceInstrumentService() {
		return this.singleChoiceInstrumentService;
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

			SingleChoiceInstrument instrument = HolderApplicationContext.getContext().getBean(SingleChoiceInstrumentService.class).findById(38);
			SingleChoiceInstrumentFormDialog dialog = (SingleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(SingleChoiceInstrumentFormDialog.class).createEditDialog(instrument);
			// SingleChoiceInstrumentFormDialog dialog = (SingleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(SingleChoiceInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
