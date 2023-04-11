package com.demo.converter.generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.springframework.stereotype.Component;

import com.demo.converter.model.ODT;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;


@Component
public class PDFGenerator {

	private String PRINT_TEMPLATE_ODT = "../../../../raporlar/markedODT.odt";

	public PDFGenerator() {
		
	}


	// fonksiyona gönderilen name ve password'u kullanarak verilen path'e, verilerle doldurulmuş bir pdf dosyası yaratır
	public void pdfGenerator(String name, String password, Path reportPath) {

		try {
			// 1) Load ODT file by filling Velocity template engine and cache it to the registry
			InputStream in = getClass().getResourceAsStream(PRINT_TEMPLATE_ODT);
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);

			// 2) Create context Java model
			IContext context = report.createContext();
			ODT namePassword = new ODT(name, password);
			context.put("odtRows", namePassword);

			// 3) Generate report by merging Java model with the ODT
			OutputStream outODT = new FileOutputStream(reportPath.toFile());
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
			report.convert(context, options, outODT);

	      } catch (Exception e) {
	        e.printStackTrace();
	      }
	}


	// It converts given odt file to pdf.
	public void converter(Path odtFile, Path reportPath) {
		InputStream in;
		OdfTextDocument document;

		try {
			// 1) Load ODT into ODFDOM OdfTextDocument 
			in = new FileInputStream(odtFile.toFile());
			document = OdfTextDocument.loadDocument(in);

			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create();
	
			// 3) Convert OdfTextDocument to PDF via IText
			OutputStream out = new FileOutputStream(reportPath.toFile());
			PdfConverter.getInstance().convert(document, out, options);
	
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
