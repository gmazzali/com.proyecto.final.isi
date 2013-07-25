package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.model.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.service.instrument.RestrictedEssayActivityInstrumentService;


@View
public class RestrictedEssayActivityInstrumentFormDialog extends EssayActivityInstrumentForm {

	private static final long serialVersionUID = 1592287212965844417L;
	
	public RestrictedEssayActivityInstrumentFormDialog() {
	}
	
	@Autowired
	private RestrictedEssayActivityInstrumentService restrictedEssayActivityInstrumentService;

	@Override
	protected EssayActivityInstrument newEssayActivityInstrument() {
		return new RestrictedEssayActivityInstrument();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected RestrictedEssayActivityInstrumentService getEssayActivityInstrumentService() {
		return this.restrictedEssayActivityInstrumentService;
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

			RestrictedEssayActivityInstrumentFormDialog dialog = (RestrictedEssayActivityInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(RestrictedEssayActivityInstrumentFormDialog.class);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
