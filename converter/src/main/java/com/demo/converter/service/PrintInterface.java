package com.demo.converter.service;

import java.nio.file.Path;

public interface PrintInterface {
	void createOdt(String name, String password, Path reportPath);		// elle işaretlenmiş odt dosyasına verileri ekler ve odt olarak kaydeder.
	void createPdf(String name, String password, Path reportPath);		// elle işaretlenmiş odt dosyasına verileri ekler ve pdf olarak kaydeder.
}
