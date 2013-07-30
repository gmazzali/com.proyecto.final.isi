package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.SingleChoiceInstrument;
import com.proyecto.service.instrument.SingleChoiceInstrumentService;

/**
 * La clase que despliega la ventana de selección para los instrumentos de selección simple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
public class SingleChoiceInstrumentFormDialog extends ChoiceInstrumentFormDialog {

	private static final long serialVersionUID = -5572643162022447980L;

	@Autowired
	private SingleChoiceInstrumentService singleChoiceInstrumentService;

	/**
	 * El constructor por omisión.
	 */
	public SingleChoiceInstrumentFormDialog() {
		super();
	}

	@Override
	protected SingleChoiceInstrumentService getInstrumentService() {
		return this.singleChoiceInstrumentService;
	}

	@Override
	protected void setNewInstrument() {
		this.choiceInstrument = new SingleChoiceInstrument();
	}

	@Override
	protected String getNewTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.choice.single.form.title.new");
	}

	@Override
	protected String getEditTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.choice.single.form.title.edit");
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

			// SingleChoiceInstrument instrument = HolderApplicationContext.getContext().getBean(SingleChoiceInstrumentService.class).findById(38);
			// SingleChoiceInstrumentFormDialog dialog = (SingleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(SingleChoiceInstrumentFormDialog.class).createEditDialog(instrument);
			SingleChoiceInstrumentFormDialog dialog = (SingleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(SingleChoiceInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}