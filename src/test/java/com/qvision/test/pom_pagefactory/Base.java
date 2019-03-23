package com.qvision.test.pom_pagefactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Base {
	private String sitioWeb;
	private String navegador;
	WebDriver remotoWebDriver;
	File archivoExcel;
	Workbook libro=null;
	/*public Base() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
	}*/
	// System("webdriver.chrome.driver","chromedriver.exe");

	public void recibirNavegador(String navegador) {
		this.navegador=navegador;
	}
	public void recibirSitioWeb(String sitioWeb) {
		this.sitioWeb=sitioWeb;
	}
	public WebDriver configuracionDriver() {
		if (navegador.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			remotoWebDriver= new ChromeDriver();
		}
		if (navegador.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			remotoWebDriver = new FirefoxDriver();
		}
		remotoWebDriver.get(sitioWeb);
		return remotoWebDriver;
	}
	
	/*'----------------------------------------------------------'
	 * EXCEL
	 *'----------------------------------------------------------'*/
	
	public Workbook configuracionLibroDeExcel(String rutaArchivo,String NombreArchivo) throws IOException{
		//crear un objeto de archivo clase para abrir un archivo  xlsx 
		//archivoExcel =    new File(rutaArchivo+"\\"+NombreArchivo);
		archivoExcel =    new File(NombreArchivo);
		//crear un objeto de la clase FileInputStream para leer el archivo excel
		FileInputStream flujoEntrada= new FileInputStream(archivoExcel);
		
		//encontrar la extensión de Excel mediante la captura con la función substring y la funcion indexOf
		String extencionArchivo=NombreArchivo.substring(NombreArchivo.indexOf("."));
		
		//validamos si el archivo es xlsx o xls por medio de una condición
		if (extencionArchivo.equals(".xlsx")) {
			libro=new XSSFWorkbook(flujoEntrada);
			
		}//validamos si el archivo es xls
		else if(extencionArchivo.equals(".xls")) {
			libro=new HSSFWorkbook(flujoEntrada);
			
		}
		//leer dentro del archivo de excel  por nombre

		return libro;	
	}
	
	public String devolverValoresHojaExcel(String nombreHoja, String campo, int fila) {
		String datosFila="";
		Sheet hojaLibro=libro.getSheet(nombreHoja);
		
		//encontrar el numero de filas en el la hoja de Excel
		int numeroFilas=hojaLibro.getLastRowNum()-hojaLibro.getFirstRowNum();
		Row filaExcel=hojaLibro.getRow(fila);
		if(campo.equalsIgnoreCase("usuario")) {
			datosFila=filaExcel.getCell(0).getStringCellValue();	
		}else if(campo.equals("contraseña")) {
			datosFila=filaExcel.getCell(1).getStringCellValue();	
		}
		
		
		/*for (int contadorFila=0; contadorFila<numeroFilas+1; contadorFila++) {
			Row fila=hojaLibro.getRow(contadorFila);
			for (int contadorColumna=0;contadorColumna<fila.getLastCellNum();contadorColumna++) {
				datosFila=fila.getCell(contadorColumna).getStringCellValue();
			}
			
		}*/
		return datosFila;
	}

	    
}