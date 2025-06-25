package com.grupopromerica.demoqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.grupopromerica.demoqa.base.BaseTest;
import com.grupopromerica.demoqa.dataLayer.Consumidor;
import com.grupopromerica.demoqa.dataLayer.Usuario;
import com.grupopromerica.demoqa.pages.ContactUsPage;
import com.grupopromerica.demoqa.pages.HomePage;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class ContactUsTest extends BaseTest
{
	private ContactUsPage contactUsPage;
	private HomePage homePage;
	
	@Test(description = "Validar envio formulario exitoso")
	@Feature("Contactenos")
	@Story("Validar envio de formulario contactenos")
	@Severity(SeverityLevel.CRITICAL)
	public void validarEnvioFormularioExitoso()
	{
		homePage = new HomePage(adaptador);
		homePage.ingresarAContactenos();
		contactUsPage = new ContactUsPage(adaptador);
		Usuario usuario = Consumidor.obtenerPorParticularidad("VALIDO");
		contactUsPage.ingresarNombre(usuario.getNombre());
		contactUsPage.ingresarEmail(usuario.getEmail());
		contactUsPage.ingresarComentario(usuario.getComentario());
		contactUsPage.hacerClickEnviar();
		
		String mensaje = contactUsPage.mensajeDeExitoEnvioComentario();
		Assert.assertEquals(mensaje, "Su comentario ha sido enviado con éxito al propietario de la tienda.", 
			    "El mensaje de envio de comentario no coincide con el esperado.");
	}
	
	@Test(description = "Validar envio formulario correo incorrecto")
	@Feature("Contactenos")
	@Story("Validar envio de formulario contactenos")
	@Severity(SeverityLevel.CRITICAL)
	public void validarEnvioFormularioCorreoIncorrecto()
	{
		homePage = new HomePage(adaptador);
		homePage.ingresarAContactenos();
		contactUsPage = new ContactUsPage(adaptador);
		Usuario usuario = Consumidor.obtenerPorParticularidad("CORREO_NO_VALIDO");
		contactUsPage.ingresarNombre(usuario.getNombre());
		contactUsPage.ingresarEmail(usuario.getEmail());
		contactUsPage.ingresarComentario(usuario.getComentario());
		contactUsPage.hacerClickEnviar();
		
		contactUsPage.esperarVisibilidadMensajeErrorCorreoNoValido();
		
		String mensajeDeError = contactUsPage.mensajeDelCorreoNoValido();
		Assert.assertEquals(mensajeDeError, "Correo electrónico incorrecto", 
			    "El mensaje de correo incorrecto, no coincide con el esperado.");
	}
}
