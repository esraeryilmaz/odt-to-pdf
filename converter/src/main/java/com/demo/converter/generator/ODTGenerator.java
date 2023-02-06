package com.demo.converter.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
//import fr.opensagres.xdocreport.samples.odtandvelocity.ODTProjectWithVelocity;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

import com.demo.converter.model.ODT;
//import fr.opensagres.xdocreport.samples.odtandvelocity.model.Project;
import com.demo.converter.model.Project;


public class ODTGenerator {

	private File markedODT;
	private File generatedODT;


	public ODTGenerator() {

	}

	public ODTGenerator(File markedODT, File generatedODT) {
		this.markedODT = markedODT;
		this.generatedODT = generatedODT;
	}

	public void odtGenerator() {

		try {
			// 1) Load ODT file by filling Velocity template engine and cache it to the registry
			InputStream in = new FileInputStream(markedODT);
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
			
			// 2) Create fields metadata to manage lazy loop (#forech velocity) for table row.
			FieldsMetadata metadata = new FieldsMetadata();
			metadata.addFieldAsList("odtRows.Name");
			metadata.addFieldAsList("odtRows.Password");
			report.setFieldsMetadata(metadata);

			// 3) Create context Java model
			IContext context = report.createContext();
			Project project = new Project("ODT Report");
			context.put("project", project);
			// odt list
			List<ODT> odtRows = new ArrayList<ODT>();
			odtRows.add(new ODT("Esra", "1234"));
			odtRows.add(new ODT("Ayse", "abcd"));
			odtRows.add(new ODT("Ahmet", "12ab"));
			odtRows.add(new ODT("Mehmet", "34cd"));
			context.put("odtRows", odtRows);


			// 4) Generate report by merging Java model with the ODT
			OutputStream out = new FileOutputStream(generatedODT);
			report.process(context, out);

/*
			OutputStream out = new FileOutputStream(new File("C:\\Users\\esra\\Desktop\\converter\\src\\main\\java\\generatedPDF.pdf"));
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);
			report.convert(context, options, out);
*/


	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}



}
