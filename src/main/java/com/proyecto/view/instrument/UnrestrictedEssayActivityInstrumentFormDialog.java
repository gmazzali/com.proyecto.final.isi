package com.proyecto.view.instrument;

import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.springframework.beans.factory.annotation.Autowired;

import com.common.util.annotations.View;
import com.common.util.holder.HolderApplicationContext;
import com.proyecto.model.instrument.EssayActivityInstrument;
import com.proyecto.model.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.service.instrument.UnrestrictedEssayActivityInstrumentService;

@View
public class UnrestrictedEssayActivityInstrumentFormDialog extends EssayActivityInstrumentForm{

	private static final long serialVersionUID = 5647831063178782508L;

	public UnrestrictedEssayActivityInstrumentFormDialog() {
	}
	
	@Autowired
	private UnrestrictedEssayActivityInstrumentService unrestrictedEssayActivityInstrumentService;

	@Override
	protected EssayActivityInstrument newEssayActivityInstrument() {
		return new UnrestrictedEssayActivityInstrument();
	}

	@Override
	@SuppressWarnings("unchecked")
	protected UnrestrictedEssayActivityInstrumentService getEssayActivityInstrumentService() {
		return this.unrestrictedEssayActivityInstrumentService;
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

			UnrestrictedEssayActivityInstrumentFormDialog dialog = (UnrestrictedEssayActivityInstrumentFormDialog) HolderApplicationContext.getContext()
					.getBean(UnrestrictedEssayActivityInstrumentFormDialog.class);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
