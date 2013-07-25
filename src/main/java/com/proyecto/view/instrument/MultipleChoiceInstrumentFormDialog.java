package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.ChoiceInstrument;
import com.proyecto.model.instrument.MultipleChoiceInstrument;
import com.proyecto.service.instrument.ChoiceInstrumentService;
import com.proyecto.service.instrument.MultipleChoiceInstrumentService;

/**
 * La clase que despliega la ventana de selección para los instrumentos de selección multiple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
public class MultipleChoiceInstrumentFormDialog extends ChoiceInstrumentFormDialog {

	private static final long serialVersionUID = -5572643162022447980L;

	@Autowired
	private MultipleChoiceInstrumentService multipleChoiceInstrumentService;

	/**
	 * El constructor por omisión.
	 */
	public MultipleChoiceInstrumentFormDialog() {
		super();
	}

	@Override
	protected ChoiceInstrument newChoiceInstrument() {
		return new MultipleChoiceInstrument();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected <E extends ChoiceInstrument> ChoiceInstrumentService<E> getChoiceInstrumentService() {
		return (ChoiceInstrumentService<E>) this.multipleChoiceInstrumentService;
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

			MultipleChoiceInstrument instrument = HolderApplicationContext.getContext().getBean(MultipleChoiceInstrumentService.class).findById(37);
			MultipleChoiceInstrumentFormDialog dialog = (MultipleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(MultipleChoiceInstrumentFormDialog.class).createEditDialog(instrument);
			// MultipleChoiceInstrumentFormDialog dialog = (MultipleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(MultipleChoiceInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}