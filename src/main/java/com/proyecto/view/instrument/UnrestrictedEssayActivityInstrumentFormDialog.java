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

/**
 * La clase que permite desplegar una ventana de edición de instrumentos de ensayos formales no restringidos.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
@View
public class UnrestrictedEssayActivityInstrumentFormDialog extends EssayActivityInstrumentFormDialog {

	private static final long serialVersionUID = 5647831063178782508L;

	@Autowired
	private UnrestrictedEssayActivityInstrumentService unrestrictedEssayActivityInstrumentService;

	/**
	 * El constructor por omisión.
	 */
	public UnrestrictedEssayActivityInstrumentFormDialog() {
		super();
	}

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

			EssayActivityInstrumentFormDialog dialog = HolderApplicationContext.getContext()
					.getBean(UnrestrictedEssayActivityInstrumentFormDialog.class).createNewDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}