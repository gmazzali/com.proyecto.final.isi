package com.proyecto.view.material.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.service.material.instrument.MultipleChoiceInstrumentService;

/**
 * La clase que despliega la ventana de selección para los instrumentos de selección multiple.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
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
	protected MultipleChoiceInstrumentService getInstrumentService() {
		return this.multipleChoiceInstrumentService;
	}

	@Override
	protected void setNewInstrument() {
		this.choiceInstrument = new MultipleChoiceInstrument();
	}

	@Override
	protected String getNewTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.choice.multiple.form.title.new");
	}

	@Override
	protected String getEditTitle() {
		return HolderMessage.getMessage("instrument.formal.objective.choice.multiple.form.title.edit");
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

			// MultipleChoiceInstrument instrument =
			// HolderApplicationContext.getContext().getBean(MultipleChoiceInstrumentService.class).findById(37);
			// MultipleChoiceInstrumentFormDialog dialog = (MultipleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
			// .getBean(MultipleChoiceInstrumentFormDialog.class).createEditDialog(instrument);
			MultipleChoiceInstrumentFormDialog dialog = (MultipleChoiceInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(MultipleChoiceInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}