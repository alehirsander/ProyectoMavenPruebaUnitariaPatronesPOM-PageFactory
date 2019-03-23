package com.qvision.test.pom_pagefactory;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver remotoWebDriver;
	WebElement txtUsuario;
	WebElement txtContraseña;
	WebElement btnVentanaLogin;
	WebElement btnIniciarSesion;
	WebElement lblMensage;
	Workbook libroExcel=null;

	public void iniciarPagina(String navegador, String sitioWeb) {
		try {
			Base iniciar = new Base();
			iniciar.recibirNavegador(navegador);
			iniciar.recibirSitioWeb(sitioWeb);
			iniciar.configuracionDriver();
			this.remotoWebDriver = iniciar.remotoWebDriver;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void configurarLibroExcel(String archivoExcel) {
		Base iniciarExcel=new Base();
		try {
			libroExcel=iniciarExcel.configuracionLibroDeExcel("", archivoExcel);
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
	}

	public String obtenerUsuario(String nombreHojaExcel, int fila) {
		String datosFila="";
		Sheet hojaLibro=libroExcel.getSheet(nombreHojaExcel);
		
		Row filaExcel=hojaLibro.getRow(fila);
		
		return filaExcel.getCell(0).getStringCellValue();	
		//datosFila=filaExcel.getCell(1).getStringCellValue();	
			//return datosFila;
		}
		
	public String obtenerContraseña(String nombreHojaExcel, int fila) {
		String datosFila="";
		Sheet hojaLibro=libroExcel.getSheet(nombreHojaExcel);
		
		Row filaExcel=hojaLibro.getRow(fila);
		
		return filaExcel.getCell(1).getStringCellValue();	
		//datosFila=filaExcel.getCell(1).getStringCellValue();	
			//return datosFila;
		}			
	
	
	public void abrirVentanaLogin() {
		btnVentanaLogin = remotoWebDriver
				.findElement(By.xpath("/html/body/header/div/section/div/div[2]/div[2]/div/i"));
		btnVentanaLogin.click();
	}

	

	public String obtenerContraseña(String nombreHoja) {

		Base libroExcel = new Base();
		return libroExcel.devolverValoresHojaExcel(nombreHoja, "contraseña", 1);
	}

	public void ejecutarLogin(String usuario, String contraseña) {
		// Identificar el campos de texto usuario e ingresar el valor correspondiente
		txtUsuario = remotoWebDriver.findElement(By.id("cal_login_username"));
		txtUsuario.sendKeys(usuario);

		// Identificar el campos de contraseña e ingresar la clave correspondiente
		txtContraseña = remotoWebDriver.findElement(By.id("cal_login_password"));
		txtContraseña.sendKeys(contraseña);

		// Identificar el botón de iniciar sesión y enviar un click
		btnIniciarSesion = remotoWebDriver.findElement(By.xpath("//*[@id=\"loginModal\"]/div/button"));
		btnIniciarSesion.click();
	}

	public boolean esCorrectoUsuarioContraseña() {
		boolean indicador = false;

		try {
			lblMensage = remotoWebDriver.findElement(By.xpath("//*[@id=\'loginModal\']/p"));
			if (lblMensage.isDisplayed()) {
				indicador = false;
			} else {
				indicador = true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (e.getMessage().indexOf("no such element: Unable to locate element: {\"method\":\"xpath\",\"selector\":\"//*[@id='loginModal']/p\"}") != -1) {
				indicador = true;
			}
		}
		return indicador;
	}

}
