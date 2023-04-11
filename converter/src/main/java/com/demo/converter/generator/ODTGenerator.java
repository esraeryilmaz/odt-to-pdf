package com.demo.converter.generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
//import fr.opensagres.xdocreport.samples.odtandvelocity.ODTProjectWithVelocity;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

import com.demo.converter.model.ODT;



// elle oluşturulmuş ve işaretlenmiş (işaretleme örn : $odtRows.Name) odt dosyasının(marketODT.odt) içerisini gönderilen verilerle doldurur
// ve gönderilen path'e kaydeder
@Component
public class ODTGenerator {

	//private String PRINT_TEMPLATE_ODT = "C:\\Users\\esra\\Desktop\\odt-to-pdf\\converter\\src\\main\\java\\raporlar\\markedODT.odt";
	private String PRINT_TEMPLATE_ODT = "../../../../raporlar/markedODT.odt";

	public ODTGenerator() {

	}


	// fonksiyona gönderilen name ve password'u kullanarak verilen path'e, verilerle doldurulmuş bir odt dosyası yaratır
	public void odtGenerator(String name, String password, Path reportPath) {

		try {
			// 1) Load ODT file by filling Velocity template engine and cache it to the registry
			//InputStream in = new FileInputStream(PRINT_TEMPLATE_ODT);		// absolute path
			InputStream in = getClass().getResourceAsStream(PRINT_TEMPLATE_ODT);	//relative path
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
			
			// 2) Create context Java model
			IContext context = report.createContext();
			ODT namePassword = new ODT(name, password);
			context.put("odtRows", namePassword);

			// 3) Generate report by merging Java model with the ODT
			OutputStream outODT = new FileOutputStream(reportPath.toFile());
			report.process(context, outODT);

	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}



}
