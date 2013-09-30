package com.proyecto.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import com.proyecto.CreateExampleMaterial;
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

/**
 * LA clase de prueba para la impresión de una evaluación en un PDF.
 * 
 * @author Marcelo Romitti
 * @version 1.0
 */
public class nada extends JFrame {

	private static final long serialVersionUID = 7973727240594885042L;

	private static SimpleDateFormat formatoDelTexto;

	private static BaseFont helvetica;

	private static Font fontSubject;
	private static Font fontAssessment;
	private static Font fontActivity;
	private static Font fontReactive;
	private static Font fontInstrument;
	private static Font fontInstrumentOption;

	static {
		try {
			nada.formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");

			nada.helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.NOT_EMBEDDED);

			nada.fontSubject = new Font(nada.helvetica, 10, Font.BOLD, new Color(255, 0, 0));
			nada.fontAssessment = new Font(nada.helvetica, 18, Font.UNDERLINE, new Color(0, 0, 255));
			nada.fontActivity = new Font(nada.helvetica, 10, Font.UNDERLINE, new Color(100, 15, 0));
			nada.fontReactive = new Font(nada.helvetica, 10, Font.ITALIC, new Color(120, 25, 255));
			nada.fontInstrument = new Font(nada.helvetica, 10, Font.BOLD, new Color(50, 0, 255));
			nada.fontInstrumentOption = new Font(nada.helvetica, 10, Font.BOLDITALIC, new Color(0, 75, 255));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final JButton guardarPDF;
	private final JPanel panel;
	private final JFileChooser seleccionar_archivo;
	private final JEditorPane editor;

	final Document assessmentPDF = new Document(PageSize.A4);

	private int contReactive = 65;
	private int contActivity = 1;

	private int constNumberItemSimple = 97;
	private int constNumberItemMultiple = 97;

	public nada() {

		// Instrumentos de prueba
		Instrument instrumentTest1 = CreateExampleMaterial.createInstrumentRestrictedEssayActivity(11);
		Instrument instrumentTest2 = CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(12);
		Instrument instrumentTest3 = CreateExampleMaterial.createInstrumentSingleChoice(13);
		Instrument instrumentTest4 = CreateExampleMaterial.createInstrumentMultipleChoice(15);
		Instrument instrumentTest5 = CreateExampleMaterial.createInstrumentCompletion(16);
		Instrument instrumentTest6 = CreateExampleMaterial.createInstrumentCorrespondence(17);

		// Reactivos de prueba
		Reactive reactiveTest1 = CreateExampleMaterial.createReactive(5, instrumentTest1);
		Reactive reactiveTest2 = CreateExampleMaterial.createReactive(6, instrumentTest2);
		Reactive reactiveTest3 = CreateExampleMaterial.createReactive(7, instrumentTest3);
		Reactive reactiveTest4 = CreateExampleMaterial.createReactive(8, instrumentTest4);
		Reactive reactiveTest5 = CreateExampleMaterial.createReactive(9, instrumentTest5);
		Reactive reactiveTest6 = CreateExampleMaterial.createReactive(10, instrumentTest6);

		// Actividades de prueba
		Activity activityTest1 = CreateExampleMaterial.createActivity(2, reactiveTest1, reactiveTest2);
		Activity activityTest2 = CreateExampleMaterial.createActivity(3, reactiveTest3, reactiveTest4);
		Activity activityTest3 = CreateExampleMaterial.createActivity(4, reactiveTest5, reactiveTest6);

		// Evaluación de prueba
		final Assessment assessmentTest = CreateExampleMaterial.createAssessment(1, activityTest1, activityTest2, activityTest3);

		this.guardarPDF = new JButton("Guardar PDF");
		this.panel = new JPanel();
		this.seleccionar_archivo = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", ".pdf");
		this.seleccionar_archivo.setFileFilter(filter);
		this.editor = new JEditorPane();

		this.guardarPDF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				int opcion = nada.this.seleccionar_archivo.showSaveDialog(null);
				if (opcion == JFileChooser.APPROVE_OPTION) {
					try {
						// Donde se va a guardar el pdf y la apertura del mismo
						OutputStream texto_salida = new FileOutputStream(nada.this.seleccionar_archivo.getSelectedFile() + ".pdf");
						PdfWriter.getInstance(nada.this.assessmentPDF, texto_salida);
						nada.this.assessmentPDF.open();

						// Hay que invocar a la materia
						Paragraph subject = new Paragraph("Materia: Inteligencia Artificial", nada.fontSubject);
						subject.setAlignment(Element.ALIGN_LEFT);
						Paragraph dateTime = new Paragraph(nada.formatoDelTexto.format(assessmentTest.getAssessmentDate()));
						dateTime.setAlignment(Element.ALIGN_RIGHT);

						// Nombre-DescripciÃ³n de la evaluaciÃ³n
						Paragraph assessment = new Paragraph(assessmentTest.getDescription(), nada.fontAssessment);
						assessment.setAlignment(Element.ALIGN_CENTER);

						nada.this.assessmentPDF.add(dateTime);
						nada.this.assessmentPDF.add(subject);
						nada.this.assessmentPDF.add(assessment);
						this.addEmptyLine(1);

						// Conjunto de Actividades
						for (Activity activity : assessmentTest.getActivities()) {

							Paragraph activityAssessment = new Paragraph("Actividad " + nada.this.contActivity++ + ": " + activity.getDescription(),
									nada.fontActivity);
							nada.this.assessmentPDF.add(activityAssessment);
							this.printReactives(activity);
							this.addEmptyLine(2);

						}

						nada.this.assessmentPDF.close();

						texto_salida.close();

					} catch (Exception ex) {
					}
				}

			}

			private void printReactives(Activity activityAssessment) {

				for (Reactive reactive : activityAssessment.getReactives()) {
					Paragraph reactiveActivity = new Paragraph((char) nada.this.contReactive++ + " - " + reactive.getDescription(), nada.fontReactive);
					try {
						nada.this.assessmentPDF.add(reactiveActivity);
						this.printInstrument(reactive);
						this.addEmptyLine(1);

					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				nada.this.contReactive = 65;

			}

			private void addEmptyLine(int number) {
				for (int i = 0; i < number; i++) {
					try {
						nada.this.assessmentPDF.add(new Paragraph(" "));
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			private void printInstrument(Reactive reactive) {
				try {

					if (reactive.getInstrument().getClass().equals(SingleChoiceInstrument.class)) {
						nada.this.constNumberItemSimple = 97;
						Paragraph instrumentReactive = new Paragraph(reactive.getInstrument().getDescription(), nada.fontInstrument);
						nada.this.assessmentPDF.add(instrumentReactive);
						SingleChoiceInstrument singleChoiceInstrument = (SingleChoiceInstrument) reactive.getInstrument();
						for (Option option : singleChoiceInstrument.getOptions()) {
							Paragraph optionInstrument = new Paragraph((char) nada.this.constNumberItemSimple++ + " - " + option.getDescription(),
									nada.fontInstrumentOption);
							nada.this.assessmentPDF.add(optionInstrument);
						}
					} else if (reactive.getInstrument().getClass().equals(MultipleChoiceInstrument.class)) {
						nada.this.constNumberItemMultiple = 97;
						Paragraph instrumentReactive = new Paragraph(reactive.getInstrument().getDescription(), nada.fontInstrument);
						nada.this.assessmentPDF.add(instrumentReactive);

						MultipleChoiceInstrument multipleChoiceInstrument = (MultipleChoiceInstrument) reactive.getInstrument();
						for (Option option : multipleChoiceInstrument.getOptions()) {
							Paragraph optionInstrument = new Paragraph((char) nada.this.constNumberItemMultiple++ + " - " + option.getDescription(),
									nada.fontInstrumentOption);
							nada.this.assessmentPDF.add(optionInstrument);
						}
					} else if (reactive.getInstrument().getClass().equals(CompletionInstrument.class)) {
						CompletionInstrument completionInstrument = (CompletionInstrument) reactive.getInstrument();

						Paragraph optionInstrument = new Paragraph(completionInstrument.getDescription(), nada.fontInstrumentOption);
						nada.this.assessmentPDF.add(optionInstrument);
					} else if (reactive.getInstrument().getClass().equals(CorrespondenceInstrument.class)) {
						ArrayList<String> leftSideList = new ArrayList<String>();
						ArrayList<String> rightSideList = new ArrayList<String>();
						CorrespondenceInstrument correspondenceInstrument = (CorrespondenceInstrument) reactive.getInstrument();

						Paragraph optionInstrument = new Paragraph(correspondenceInstrument.getDescription(), nada.fontInstrument);
						nada.this.assessmentPDF.add(optionInstrument);

						// Carga las lista con las distintas opciones de cada lado
						for (RelationAnswer relationAnswer : correspondenceInstrument.getRelations()) {
							leftSideList.add(relationAnswer.getLeftSide());
							rightSideList.add(relationAnswer.getRightSide());
						}

						// Genera el random de las listas
						Collections.shuffle(leftSideList);
						Collections.shuffle(rightSideList);

						PdfPTable table = new PdfPTable(leftSideList.size());

						for (int i = 0; i < leftSideList.size(); i++) {
							PdfPCell c1 = new PdfPCell(new Phrase("- " + leftSideList.get(i)));
							c1.setHorizontalAlignment(Element.ALIGN_LEFT);
							c1.setBorderColor(Color.WHITE);
							table.addCell(c1);

							PdfPCell c2 = new PdfPCell(new Phrase("- " + rightSideList.get(i)));
							c2.setHorizontalAlignment(Element.ALIGN_LEFT);
							c2.setBorderColor(Color.WHITE);
							table.addCell(c2);

						}
						this.addEmptyLine(1);
						nada.this.assessmentPDF.add(table);
					} else if (reactive.getInstrument().getClass().equals(RestrictedEssayActivityInstrument.class)) {
						RestrictedEssayActivityInstrument restrictedEssayActivityInstrument = (RestrictedEssayActivityInstrument) reactive
								.getInstrument();

						Paragraph optionInstrument = new Paragraph(restrictedEssayActivityInstrument.getDescription(), nada.fontInstrument);
						nada.this.assessmentPDF.add(optionInstrument);
					} else if (reactive.getInstrument().getClass().equals(UnrestrictedEssayActivityInstrument.class)) {
						UnrestrictedEssayActivityInstrument unrestrictedEssayActivityInstrument = (UnrestrictedEssayActivityInstrument) reactive
								.getInstrument();

						Paragraph optionInstrument = new Paragraph(unrestrictedEssayActivityInstrument.getDescription(), nada.fontInstrument);
						nada.this.assessmentPDF.add(optionInstrument);
					}

				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		this.panel.add(this.guardarPDF);
		this.add(this.panel, BorderLayout.NORTH);

		this.add(this.editor, BorderLayout.CENTER);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new nada();

	}

}
