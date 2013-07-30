package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.common.util.holder.HolderMessage;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.service.instrument.RestrictedEssayActivityInstrumentService;

/**
 * La clase que permite desplegar una ventana de edición de instrumentos de ensayos formales restringidos.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@View
@SuppressWarnings("unchecked")
public class RestrictedEssayActivityInstrumentFormDialog extends EssayActivityInstrumentFormDialog {

	private static final long serialVersionUID = 1592287212965844417L;

	@Autowired
	private RestrictedEssayActivityInstrumentService restrictedEssayActivityInstrumentService;

	/**
	 * El constructor por omisión.
	 */
	public RestrictedEssayActivityInstrumentFormDialog() {
		super();
	}

	@Override
	protected RestrictedEssayActivityInstrumentService getInstrumentService() {
		return this.restrictedEssayActivityInstrumentService;
	}

	@Override
	protected void setNewInstrument() {
		this.essayActivityInstrument = new RestrictedEssayActivityInstrument();
	}

	@Override
	protected String getNewTitle() {
		return HolderMessage.getMessage("instrument.formal.essay.restricted.form.title.new");
	}

	@Override
	protected String getEditTitle() {
		return HolderMessage.getMessage("instrument.formal.essay.restricted.form.title.edit");
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

			EssayActivityInstrumentFormDialog dialog = (EssayActivityInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(RestrictedEssayActivityInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}