package com.demo.converter;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.converter.service.PrintInterfaceImpl;




@SpringBootApplication
public class ConverterApplication implements CommandLineRunner {	// bunu implement etmeyince run fonksiyonunu override edemiyorum????

	@Autowired
	private PrintInterfaceImpl printInterface;

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}


	@Override
	public void run(String... args) {

		Path odtPath = Paths.get("C:\\Users\\esra\\Desktop\\odt-to-pdf\\converter\\src\\main\\java\\raporlar\\generatedODT.odt");
		Path pdfPath = Paths.get("C:\\Users\\esra\\Desktop\\odt-to-pdf\\converter\\src\\main\\java\\raporlar\\generatedPDF.pdf");

		printInterface.createOdt("Esra", "1234", odtPath);
		printInterface.createPdf("Ayse", "abcd", pdfPath);

	}

}

