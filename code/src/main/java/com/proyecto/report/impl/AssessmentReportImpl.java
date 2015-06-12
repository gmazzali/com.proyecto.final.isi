package com.proyecto.report.impl;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import com.common.util.annotations.Service;
import com.common.util.holder.HolderMessage;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.proyecto.model.answer.RelationAnswer;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.option.Option;
import com.proyecto.report.AssessmentReport;

/**
 * La clase que implementa la interfaz que permite la carga de una evaluación dentro de un reporte PDF.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
@Service
public class AssessmentReportImpl implements AssessmentReport {

	private static final long serialVersionUID = 7973727240594885042L;

	/**
	 * El formato de la fecha.
	 */
	private SimpleDateFormat formatoDeFecha;
	/**
	 * Las fuentes que vamos a usar dentro de este creador del proceso.
	 */
	private Font fontSubject;
	private Font fontAssessment;
	private Font fontActivity;
	private Font fontReactive;
	private Font fontInstrument;
	private Font fontInstrumentOption;
	/**
	 * El documento PDF.
	 */
	private Document assessmentPDF;
	/**
	 * Los índice de los elementos de las entidades.
	 */
	private int contReactive;
	private int contActivity;
	private int constNumberItemSimple;

	/**
	 * El constructor de la clase para el reporte.
	 */
	public AssessmentReportImpl() {
		try {
			this.formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");

			BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);

			this.fontSubject = new Font(helvetica, 10, Font.BOLD, new Color(255, 0, 0));
			this.fontAssessment = new Font(helvetica, 18, Font.UNDERLINE, new Color(0, 0, 255));
			this.fontActivity = new Font(helvetica, 10, Font.UNDERLINE, new Color(100, 15, 0));
			this.fontReactive = new Font(helvetica, 10, Font.ITALIC, new Color(120, 25, 255));
			this.fontInstrument = new Font(helvetica, 10, Font.BOLD, new Color(50, 0, 255));
			this.fontInstrumentOption = new Font(helvetica, 10, Font.BOLDITALIC, new Color(0, 75, 255));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public File createAssessmentReport(String path, Assessment assessment) throws Exception {
		// Creamos el documento e iniciamos todos los indices.
		this.assessmentPDF = new Document(PageSize.A4);
		this.contActivity = 1;

		File file = new File(path + AssessmentReport.EXTENSION);
		try {
			// Donde se va a guardar el PDF y la apertura del mismo.
			PdfWriter.getInstance(this.assessmentPDF, new FileOutputStream(file));
			this.assessmentPDF.open();

			// Cargamos la cabecera.
			this.addHeader(assessment);

			// Cargamos la descripción de la evaluación.
			this.addAssessmentDescription(assessment);

			// Agregamos una línea en blanco.
			this.addEmptyLine(1);

			// Conjunto de Actividades.
			for (Activity activity : assessment.getActivities()) {
				this.addActivity(activity);
			}

			this.assessmentPDF.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return file;
	}

	/**
	 * La función encargada de cargar el encabezado de la evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que vamos a cargar.
	 * @throws Exception
	 *             En caso de un error al cargar la cabecera.
	 */
	private void addHeader(Assessment assessment) throws Exception {
		// Cargamos los datos de la materia al inicio de la primer hoja.
		Paragraph dateTimeParagraph = new Paragraph(this.formatoDeFecha.format(assessment.getAssessmentDate()));
		dateTimeParagraph.setAlignment(Element.ALIGN_RIGHT);
		this.assessmentPDF.add(dateTimeParagraph);

		if (assessment.getSubject() != null) {
			String subjectString = HolderMessage.getMessage("pdf.title.subject") + ": " + assessment.getSubject().getName();
			Paragraph subjectParagraph = new Paragraph(subjectString, this.fontSubject);
			subjectParagraph.setAlignment(Element.ALIGN_LEFT);
			this.assessmentPDF.add(subjectParagraph);
		}
	}

	/**
	 * La función encargada de cargar la descripción de una evaluación.
	 * 
	 * @param assessment
	 *            La evaluación que vamos a cargar.
	 * @throws Exception
	 *             En caso de que falle la carga dentro del reporte.
	 */
	private void addAssessmentDescription(Assessment assessment) throws Exception {
		// Descripción de la evaluación.
		Paragraph assessmentParagraph = new Paragraph(assessment.getDescription(), this.fontAssessment);
		assessmentParagraph.setAlignment(Element.ALIGN_CENTER);
		this.assessmentPDF.add(assessmentParagraph);
	}

	/**
	 * La función encargada de cargar una actividad dentro del documento.
	 * 
	 * @param activity
	 *            La actividad que vamos a cargar.
	 * @throws Exception
	 *             En caso de que falle la carga dentro del reporte.
	 */
	private void addActivity(Activity activity) throws Exception {
		if (activity != null) {
			this.contReactive = 65;
			String activityString = HolderMessage.getMessage("pdf.title.activity") + " " + this.contActivity++ + ": " + activity.getDescription();
			Paragraph activityParagraph = new Paragraph(activityString, this.fontActivity);
			this.assessmentPDF.add(activityParagraph);

			// Agregamos los reactivos de al actividad.
			for (Reactive reactive : activity.getReactives()) {
				this.addReactive(reactive);
			}
		}
	}

	/**
	 * La función encargada de cargar un reactivo dentro del documento.
	 * 
	 * @param reactive
	 *            El reactivo que vamos a cargar.
	 * @throws Exception
	 *             En caso de que falle la carga dentro del reporte.
	 */
	private void addReactive(Reactive reactive) throws Exception {
		if (reactive != null) {
			this.contReactive = 65;
			String reactiveString = HolderMessage.getMessage("pdf.title.reactive") + " " + (char) this.contReactive++ + " - "
					+ reactive.getDescription();
			Paragraph reactiveActivity = new Paragraph(reactiveString, this.fontReactive);
			this.assessmentPDF.add(reactiveActivity);

			// Agregamos el instrumento.
			this.addInstrument(reactive.getInstrument());
		}
	}

	/**
	 * La función encargada de cargar un reactivo dentro del documento.
	 * 
	 * @param reactive
	 *            El reactivo que vamos a cargar.
	 * @throws Exception
	 *             En caso de que falle la carga dentro del reporte.
	 */
	private void addInstrument(Instrument instrument) throws Exception {
		// Reiniciamos los contadores de los instrumentos.
		this.constNumberItemSimple = 97;
		if (instrument != null) {
			// Agregamos la descripción del instrumento.
			String instrumentString = instrument.getDescription();
			Paragraph instrumentReactive = new Paragraph(instrumentString, this.fontInstrument);
			this.assessmentPDF.add(instrumentReactive);

			// Si el instrumento es SingleChoice.
			if (instrument.getClass().equals(SingleChoiceInstrument.class)) {
				SingleChoiceInstrument singleChoiceInstrument = (SingleChoiceInstrument) instrument;

				for (Option option : singleChoiceInstrument.getOptions()) {
					Paragraph optionInstrument = new Paragraph((char) this.constNumberItemSimple++ + " - " + option.getDescription(),
							this.fontInstrumentOption);
					this.assessmentPDF.add(optionInstrument);
				}
			} else
			// Si el instrumento es MultipleChoice.
			if (instrument.getClass().equals(MultipleChoiceInstrument.class)) {
				MultipleChoiceInstrument multipleChoiceInstrument = (MultipleChoiceInstrument) instrument;

				for (Option option : multipleChoiceInstrument.getOptions()) {
					Paragraph optionInstrument = new Paragraph((char) this.constNumberItemSimple++ + " - " + option.getDescription(),
							this.fontInstrumentOption);
					this.assessmentPDF.add(optionInstrument);
				}
			} else
			// Si el instrumento es para completar.
			if (instrument.getClass().equals(CompletionInstrument.class)) {
			} else
			// Si el instrumento es de correspondencia.
			if (instrument.getClass().equals(CorrespondenceInstrument.class)) {
				CorrespondenceInstrument correspondenceInstrument = (CorrespondenceInstrument) instrument;

				// Carga las lista con las distintas opciones de cada lado.
				ArrayList<String> leftSideList = new ArrayList<String>();
				ArrayList<String> rightSideList = new ArrayList<String>();
				for (RelationAnswer relationAnswer : correspondenceInstrument.getRelations()) {
					if (relationAnswer.getLeftSide() != null) {
						leftSideList.add(relationAnswer.getLeftSide());
					}
					if (relationAnswer.getRightSide() != null) {
						rightSideList.add(relationAnswer.getRightSide());
					}
				}

				// Genera el random de las listas.
				Collections.shuffle(leftSideList);
				Collections.shuffle(rightSideList);

				PdfPTable table = new PdfPTable(2);
				String iconChoice = HolderMessage.getMessage("pdf.title.instrument.choice");

				for (int i = 0; i < leftSideList.size() || i < rightSideList.size(); i++) {
					PdfPCell leftCell = new PdfPCell(new Phrase(iconChoice + " " + leftSideList.get(i)));
					leftCell.setHorizontalAlignment(Element.ALIGN_LEFT);
					leftCell.setBorderColor(Color.WHITE);
					table.addCell(leftCell);

					PdfPCell rightCell = new PdfPCell(new Phrase(iconChoice + " " + rightSideList.get(i)));
					rightCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					rightCell.setBorderColor(Color.WHITE);
					table.addCell(rightCell);
				}

				this.addEmptyLine(1);
				this.assessmentPDF.add(table);
			} else
			// Si el instrumento es de ensayo objetivo restringido.
			if (instrument.getClass().equals(RestrictedEssayActivityInstrument.class)) {
			} else
			// Si el instrumento es de ensayo objetivo no restricto.
			if (instrument.getClass().equals(UnrestrictedEssayActivityInstrument.class)) {
			}
		}
	}

	/**
	 * Función encargada de agregar un conjunto de líneas en blanco dentro del documento.
	 * 
	 * @param number
	 *            El numero de líneas que vamos a agregar.
	 */
	private void addEmptyLine(int number) {
		for (int i = 0; i < number; i++) {
			try {
				this.assessmentPDF.add(new Paragraph(" "));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
}