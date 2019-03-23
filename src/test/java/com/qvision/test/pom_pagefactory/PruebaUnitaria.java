package com.qvision.test.pom_pagefactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PruebaUnitaria {
	LoginPage PageLogin;
	@Before
	public void abrirSitioWeb() {
		PageLogin=new LoginPage();
		PageLogin.iniciarPagina("chrome", "https://www.infotechnology.com/");
		PageLogin.configurarLibroExcel("usuarios.xlsx");
	}
	@Test
	public void testLogin() {
		String usuario="";
		String contraseña="";
		try {
			
			PageLogin.abrirVentanaLogin();
			usuario=PageLogin.obtenerUsuario("datos", 1);
			contraseña=PageLogin.obtenerContraseña("datos", 1);
			
			Thread.sleep(5000);
			PageLogin.ejecutarLogin(usuario, contraseña);
			//Thread.sleep(5000);
			
			//
			Assert.assertTrue(PageLogin.esCorrectoUsuarioContraseña());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

}
