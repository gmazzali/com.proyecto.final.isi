package com.proyecto.report;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ibm.icu.text.SimpleDateFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.proyecto.CreateExampleMaterial;
import com.proyecto.model.material.activity.Activity;
import com.proyecto.model.material.assessment.Assessment;
import com.proyecto.model.material.instrument.CompletionInstrument;
import com.proyecto.model.material.instrument.CorrespondenceInstrument;
import com.proyecto.model.material.instrument.Instrument;
import com.proyecto.model.material.instrument.MultipleChoiceInstrument;
import com.proyecto.model.material.instrument.RestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.SingleChoiceInstrument;
import com.proyecto.model.material.instrument.UnrestrictedEssayActivityInstrument;
import com.proyecto.model.material.instrument.type.impl.InstrumentTypeImpl;
import com.proyecto.model.material.reactive.Reactive;
import com.proyecto.model.option.Option;

public class nada extends JFrame {

	private JButton guardarPDF;
	private JPanel panel;
	private JFileChooser seleccionar_archivo;
	private JEditorPane editor;
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
	final Document assessmentPDF = new Document(PageSize.A4);
	int contReactive = 1;
	int contActivity = 1;
	int conaux = 0;
	public nada() {

		// Creacion de la evaluacion de ejemplo

		Activity activityTest = new Activity();
		Reactive reactiveTest = new Reactive();
		RestrictedEssayActivityInstrument instrumentTest = new RestrictedEssayActivityInstrument();

		instrumentTest = CreateExampleMaterial.createInstrumentRestrictedEssayActivity(1);
		SingleChoiceInstrument instrumentTest1 = CreateExampleMaterial.createInstrumentSingleChoice(10);
		reactiveTest = CreateExampleMaterial.createReactive(2, instrumentTest);
		Reactive reactive = CreateExampleMaterial.createReactive(45, instrumentTest1);
		
	
		Activity activityTest1 = CreateExampleMaterial.createActivity(3, reactive);
		Activity activityTest2 = CreateExampleMaterial.createActivity(5, reactiveTest);
		Activity activityTest3 = CreateExampleMaterial.createActivity(6, reactiveTest);

		final Assessment assessmentTest = CreateExampleMaterial.createAssessment(4, activityTest1, activityTest2, activityTest3);

		guardarPDF = new JButton("Guardar PDF");
		panel = new JPanel();
		seleccionar_archivo = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", ".pdf");
		seleccionar_archivo.setFileFilter(filter);
		editor = new JEditorPane();
		

		guardarPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int opcion = seleccionar_archivo.showSaveDialog(null);
				if (opcion == seleccionar_archivo.APPROVE_OPTION) {
					try {
						OutputStream texto_salida = new FileOutputStream(seleccionar_archivo.getSelectedFile() + ".pdf");

						// PDF
						
						PdfWriter.getInstance(assessmentPDF, texto_salida);
						assessmentPDF.open();
						
						//Hay que invocar a la materia
						Paragraph matter = new Paragraph("Materia: Inteligencia Artificial", new
					    Font(FontFamily.HELVETICA, 10, Font.UNDERLINE, new BaseColor(120, 0, 255)) );
						matter.setAlignment(Element.ALIGN_LEFT);
						Paragraph dateTime = new Paragraph(formatoDelTexto.format(assessmentTest.getAssessmentDate()));
						dateTime.setAlignment(Element.ALIGN_RIGHT);
					
						
						//Nombre-Descripción de la evaluación
						Paragraph name = new Paragraph(assessmentTest.getDescription(), new
							    Font(FontFamily.HELVETICA, 15, Font.UNDERLINE, new BaseColor(0, 0, 255)) );
						name.setAlignment(Element.ALIGN_MIDDLE); 
					
						assessmentPDF.add(dateTime);
						assessmentPDF.add(matter);
						assessmentPDF.add(name);

						
						//Conjunto de Actividades
						 for (Activity activity : assessmentTest.getActivities()) {
					  
						 Paragraph activityAssessment = new Paragraph("Actividad "+contActivity++ +": " +activity.getDescription());
						 assessmentPDF.add(activityAssessment);
						 printReactives(activity);
						
						 }

//						//PAGINA SIGUIENTE
//						ASSESSMENTPDF.NEWPAGE();
//						PARAGRAPH MATTER1 = NEW PARAGRAPH("MATERIA: INTELIGENCIA ARTIFICIAL");
//						MATTER.SETALIGNMENT(ELEMENT.ALIGN_LEFT);
//						ASSESSMENTPDF.ADD(MATTER1);
						assessmentPDF.close();
		
						texto_salida.close();
						
					} catch (Exception ex) {
					}
				}

			}
			
			private void printReactives(Activity activityAssessment) {
				
				for (Reactive reactive : activityAssessment.getReactives()) {
					 Paragraph reactiveActivity = new Paragraph("Ractivo: "+ contReactive++ +":"+ reactive.getDescription());
					 try {
						assessmentPDF.add(reactiveActivity);
						printInstrument(reactive);
					
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}

			private void printInstrument(Reactive reactive) {
				Paragraph instrumentReactive = new Paragraph("Instrumento: "+reactive.getInstrument().getDescription());
				try {
					assessmentPDF.add(instrumentReactive);
					
					if(reactive.getInstrument().getClass().equals(SingleChoiceInstrument.class))
					{
						 SingleChoiceInstrument singleChoiceInstrument = (SingleChoiceInstrument) reactive.getInstrument();
						 for (Option option : singleChoiceInstrument.getOptions()) 
						 {
							 Paragraph optionInstrument = new Paragraph("-" + option.getDescription());
							 assessmentPDF.add(optionInstrument);		 
						 }		
					}
					else if(reactive.getInstrument().getClass().equals(CompletionInstrument.class))
					{
						CompletionInstrument completionInstrument = (CompletionInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(completionInstrument.getDescription());
					    assessmentPDF.add(optionInstrument);		 
					}
					else if(reactive.getInstrument().getClass().equals(CorrespondenceInstrument.class))
					{
						CorrespondenceInstrument correspondenceInstrument = (CorrespondenceInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(correspondenceInstrument.getDescription());
					    assessmentPDF.add(optionInstrument);		 
					}
					else if(reactive.getInstrument().getClass().equals(RestrictedEssayActivityInstrument.class))
					{
						RestrictedEssayActivityInstrument restrictedEssayActivityInstrument = (RestrictedEssayActivityInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(restrictedEssayActivityInstrument.getDescription());
					    assessmentPDF.add(optionInstrument);		 
					}
					else if(reactive.getInstrument().getClass().equals(UnrestrictedEssayActivityInstrument.class))
					{
						UnrestrictedEssayActivityInstrument unrestrictedEssayActivityInstrument = (UnrestrictedEssayActivityInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(unrestrictedEssayActivityInstrument.getDescription());
					    assessmentPDF.add(optionInstrument);		 
					}
				
				
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

		panel.add(guardarPDF);
		this.add(panel, BorderLayout.NORTH);

		this.add(editor, BorderLayout.CENTER);

		this.setSize(300, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new nada();

	}

}
