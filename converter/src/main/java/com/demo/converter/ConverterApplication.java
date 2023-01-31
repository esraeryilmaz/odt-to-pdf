package com.demo.converter;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ConverterApplication implements CommandLineRunner {	// bunu implement etmeyince run fonksiyonunu override edemiyorum????

	private Converter converter;
	private String odtFileName = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\test.odt";		// C:\\Users\\esra\\Desktop\\test.odt	C:/Users/esra/Desktop/test.odt
	private String pdfFileName = "C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\test.pdf";

	public static void main(String[] args) {
		SpringApplication.run(ConverterApplication.class, args);
	}


	@Override
	public void run(String... args) {
		File odtFile = new File(odtFileName);
		File pdfFile = new File(pdfFileName);

		converter = new Converter(odtFile, pdfFile);
		converter.converter();

	}

}

