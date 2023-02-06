package com.demo.converter;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.converter.generator.ODTGenerator;
import com.demo.converter.generator.PDFGenerator;



@SpringBootApplication
public class ConverterApplication implements CommandLineRunner {	// bunu implement etmeyince run fonksiyonunu override edemiyorum????

	private PDFGenerator pdfGenerator;
	private ODTGenerator odtGenerator;

//	private String odtFileName = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\test.odt";		// C:\\Users\\esra\\Desktop\\test.odt	C:/Users/esra/Desktop/test.odt
//	private String pdfFileName = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\test.pdf";

	private String markedOdtFile = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\markedODT.odt";
	private String generatedOdtFile = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\generatedODT.odt";
	
	private String generatedPdfFile = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\generatedPDF.pdf";

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}


	@Override
	public void run(String... args) {

		//File odtFile = new File(odtFileName);
		//File pdfFile = new File(pdfFileName);
		// It converts odt to pdf
		//pdfGenerator = new PDFGenerator(odtFile, pdfFile);
		//pdfGenerator.converter();

		// It generates odt file with spesific parameters
		File markedOdt = new File(markedOdtFile);
		File generatedOdt = new File(generatedOdtFile);
		File generatedPdf = new File(generatedPdfFile);

		odtGenerator = new ODTGenerator(markedOdt, generatedOdt);
		odtGenerator.odtGenerator();

		// It converts odt to pdf
		pdfGenerator = new PDFGenerator(generatedOdt, generatedPdf);
		pdfGenerator.converter();

	}

}

