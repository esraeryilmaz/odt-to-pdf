package com.demo.converter.service;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.converter.generator.ODTGenerator;
import com.demo.converter.generator.PDFGenerator;

@Service
public class PrintInterfaceImpl implements PrintInterface {

	@Autowired
	ODTGenerator odtGenerator;
	
	@Autowired
	PDFGenerator pdfGenerator;
    
	@Override
	public void createOdt(String name, String password, Path reportPath) {
		odtGenerator.odtGenerator(name, password, reportPath);
	}

	@Override
	public void createPdf(String name, String password, Path reportPath) {
		pdfGenerator.pdfGenerator(name, password, reportPath);
	}

}
