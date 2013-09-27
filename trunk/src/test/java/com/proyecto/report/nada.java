package com.proyecto.report;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;

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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
	private int constNumberItem = 65;
	private static Font fontSubject = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(255, 0, 0));
	private static Font fontAssessment = new Font(FontFamily.HELVETICA, 18, Font.UNDERLINE, new BaseColor(0, 0, 255));
	private static Font fontActivity = new Font(FontFamily.HELVETICA, 10, Font.UNDERLINE, new BaseColor(100, 15, 0));
	private static Font fontReactive = new Font(FontFamily.HELVETICA, 10, Font.ITALIC, new BaseColor(120, 25, 255));
	private static Font fontInstrument = new Font(FontFamily.HELVETICA, 10, Font.BOLD, new BaseColor(50, 0, 255));
	private static Font fontInstrumentOption = new Font(FontFamily.HELVETICA, 10, Font.BOLDITALIC, new BaseColor(0, 75, 255));

	public nada() {

		//Instrumentos de prueba
		Instrument instrumentTest1 = CreateExampleMaterial.createInstrumentRestrictedEssayActivity(11);
		Instrument instrumentTest2 = CreateExampleMaterial.createInstrumentUnrestrictedEssayActivity(12);
		Instrument instrumentTest3 = CreateExampleMaterial.createInstrumentSingleChoice(13);
		Instrument instrumentTest4 = CreateExampleMaterial.createInstrumentMultipleChoice(15);
		Instrument instrumentTest5 = CreateExampleMaterial.createInstrumentCompletion(16);
		Instrument instrumentTest6 = CreateExampleMaterial.createInstrumentCorrespondence(17);
		
		//Reactivos de prueba
		Reactive reactiveTest1 = CreateExampleMaterial.createReactive(5, instrumentTest1);
		Reactive reactiveTest2 = CreateExampleMaterial.createReactive(6, instrumentTest2);
		Reactive reactiveTest3 = CreateExampleMaterial.createReactive(7, instrumentTest3);
		Reactive reactiveTest4 = CreateExampleMaterial.createReactive(8, instrumentTest4);
		Reactive reactiveTest5 = CreateExampleMaterial.createReactive(9, instrumentTest5);
		Reactive reactiveTest6 = CreateExampleMaterial.createReactive(10, instrumentTest6);
		
	    //Actividades de prueba
		Activity activityTest1 = CreateExampleMaterial.createActivity(2, reactiveTest1, reactiveTest2);
		Activity activityTest2 = CreateExampleMaterial.createActivity(3, reactiveTest3, reactiveTest4);
		Activity activityTest3 = CreateExampleMaterial.createActivity(4, reactiveTest5, reactiveTest6);
		
		//Evaluacion de prueba
		final Assessment assessmentTest = CreateExampleMaterial.createAssessment(1, activityTest1, activityTest2, activityTest3);

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
						Paragraph subject = new Paragraph("Materia: Inteligencia Artificial", fontSubject);
						subject.setAlignment(Element.ALIGN_LEFT);
						Paragraph dateTime = new Paragraph(formatoDelTexto.format(assessmentTest.getAssessmentDate()));
						dateTime.setAlignment(Element.ALIGN_RIGHT);
					
						
						//Nombre-Descripción de la evaluación
						Paragraph assessment = new Paragraph(assessmentTest.getDescription(), fontAssessment);
						assessment.setAlignment(Element.ALIGN_CENTER); 
					
						assessmentPDF.add(dateTime);
						assessmentPDF.add(subject);
						assessmentPDF.add(assessment);
						
						//Conjunto de Actividades
						 for (Activity activity : assessmentTest.getActivities()) {
					  
						 Paragraph activityAssessment = new Paragraph("Actividad "+contActivity++ +": " +activity.getDescription(), fontActivity);
						 assessmentPDF.add(activityAssessment);
						 printReactives(activity);
						 addEmptyLine(2);
						
						 }

						assessmentPDF.close();
		
						texto_salida.close();
						
					} catch (Exception ex) {
					}
				}

			}
			
			private void printReactives(Activity activityAssessment) {
				
				for (Reactive reactive : activityAssessment.getReactives()) {
					 Paragraph reactiveActivity = new Paragraph("Ractivo: "+ contReactive++ +":"+ reactive.getDescription(), fontReactive);
					 try {
						assessmentPDF.add(reactiveActivity);
						printInstrument(reactive);
						 addEmptyLine(1);
					
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
			private  void addEmptyLine(int number) {
			    for (int i = 0; i < number; i++) {
			    	try {
						assessmentPDF.add(new Paragraph(" "));
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			  }

			private void printInstrument(Reactive reactive) {
				constNumberItem = 65;
				Paragraph instrumentReactive = new Paragraph("Instrumento: "+reactive.getInstrument().getDescription(), fontInstrument);
				try {
					assessmentPDF.add(instrumentReactive);
					
					if(reactive.getInstrument().getClass().equals(SingleChoiceInstrument.class))
					{
						 SingleChoiceInstrument singleChoiceInstrument = (SingleChoiceInstrument) reactive.getInstrument();
						 for (Option option : singleChoiceInstrument.getOptions()) 
						 {
							 Paragraph optionInstrument = new Paragraph((char)constNumberItem++ +" - " + option.getDescription(), fontInstrumentOption);
							 assessmentPDF.add(optionInstrument);		 
						 }		
					}
					else if(reactive.getInstrument().getClass().equals(MultipleChoiceInstrument.class))
					{
						constNumberItem = 65;
						MultipleChoiceInstrument multipleChoiceInstrument = (MultipleChoiceInstrument) reactive.getInstrument();
						 for (Option option : multipleChoiceInstrument.getOptions()) 
						 {
							 Paragraph optionInstrument = new Paragraph((char)constNumberItem++ +" - " + option.getDescription(), fontInstrumentOption);
							 assessmentPDF.add(optionInstrument);		 
						 }		 
					}
					else if(reactive.getInstrument().getClass().equals(CompletionInstrument.class))
					{
						CompletionInstrument completionInstrument = (CompletionInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(completionInstrument.getDescription(), fontInstrumentOption);
					    assessmentPDF.add(optionInstrument);		 
					}
					else if(reactive.getInstrument().getClass().equals(CorrespondenceInstrument.class))
					{
						ArrayList<String> leftSideList = new ArrayList<String>();
						ArrayList<String> rightSideList = new ArrayList<String>();
						CorrespondenceInstrument correspondenceInstrument = (CorrespondenceInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(correspondenceInstrument.getDescription(), fontInstrument);
					    assessmentPDF.add(optionInstrument);
					    
					    //Carga las lista con las distintas opciones de cada lado
					    for (RelationAnswer relationAnswer : correspondenceInstrument.getRelations()) 
						 {
					    	leftSideList.add(relationAnswer.getLeftSide());
					    	rightSideList.add(relationAnswer.getRightSide());
						 }	
					    
					    //Genera el random de las listas
					    Collections.shuffle(leftSideList);
					    Collections.shuffle(rightSideList);
					    
					    PdfPTable table = new PdfPTable(leftSideList.size());
					
					   for(int i = 0; i< leftSideList.size(); i++)
					   {
						   PdfPCell c1 = new PdfPCell(new Phrase("- "+ leftSideList.get(i)));
						   c1.setHorizontalAlignment(Element.ALIGN_LEFT);
						   c1.setBorderColor(BaseColor.WHITE);
						   table.addCell(c1);
						   
						   PdfPCell c2 = new PdfPCell(new Phrase("- "+ rightSideList.get(i)));
						   c2.setHorizontalAlignment(Element.ALIGN_LEFT);
						   c2.setBorderColor(BaseColor.WHITE);
						   table.addCell(c2);					  
						  
					   }
					   assessmentPDF.add(table);
					}
					else if(reactive.getInstrument().getClass().equals(RestrictedEssayActivityInstrument.class))
					{
						RestrictedEssayActivityInstrument restrictedEssayActivityInstrument = (RestrictedEssayActivityInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(restrictedEssayActivityInstrument.getDescription(), fontInstrument);
					    assessmentPDF.add(optionInstrument);		 
					}
					else if(reactive.getInstrument().getClass().equals(UnrestrictedEssayActivityInstrument.class))
					{
						UnrestrictedEssayActivityInstrument unrestrictedEssayActivityInstrument = (UnrestrictedEssayActivityInstrument) reactive.getInstrument();
						
					    Paragraph optionInstrument = new Paragraph(unrestrictedEssayActivityInstrument.getDescription(), fontInstrument);
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
