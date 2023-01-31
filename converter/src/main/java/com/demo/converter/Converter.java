package com.demo.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

public class Converter {

	private File odtFile;
	private File pdfFile;


	public Converter() {
		
	}

	public Converter(File odtFile, File pdfFile) {
		this.odtFile = odtFile;
		this.pdfFile = pdfFile;
	}

	// It converts given odt file to pdf.
	public void converter() {

		InputStream in;
		OdfTextDocument document;

		try {
			// 1) Load ODT into ODFDOM OdfTextDocument 
			in = new FileInputStream(odtFile);
			document = OdfTextDocument.loadDocument(in);

			// 2) Prepare Pdf options
			PdfOptions options = PdfOptions.create();
	
			// 3) Convert OdfTextDocument to PDF via IText
			OutputStream out = new FileOutputStream(pdfFile);
			PdfConverter.getInstance().convert(document, out, options);
	
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
