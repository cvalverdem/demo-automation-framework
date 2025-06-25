package com.grupopromerica.demoqa.pages;

import com.grupopromerica.adaptador_selenium.Adaptador;

import io.qameta.allure.Step;

public class ContactUsPage 
{
	private String inputNombre = "//input[@id='FullName']";
	private String inputCorreo = "//input[@id='Email']";
	private String inputComentario = "//textarea[@id='Enquiry']";
	private String buttonEnviar = "//input[@name='send-email']";
	private String msgEnvioComentarioExitoso = "//div[@class='result']";
	
	//Mensajes de error 
	private String msgCorreoIncorrecto = "//span[@class='field-validation-error']/span";
	
	private Adaptador adaptador;
	
	public ContactUsPage(Adaptador adaptador) 
	{
        this.adaptador = adaptador;
    }
	
	@Step("Se ingresa el nombre")
	public void ingresarNombre(String nombre)
	{
		adaptador.escribirTexto(inputNombre, nombre);
	}
	
	@Step("Se ingresa el correo")
	public void ingresarEmail(String correo)
	{
		adaptador.escribirTexto(inputCorreo, correo);
	}
	
	@Step("Se ingresa un comentario")
	public void ingresarComentario(String comentario)
	{
		adaptador.escribirTexto(inputComentario, comentario);
	}
	
	@Step("Se hace click en el botón enviar")
	public void hacerClickEnviar()
	{
		adaptador.hacerClick(buttonEnviar);
	}
	
	@Step("Se obtiene el mensaje de éxito de envío de comentario")
	public String mensajeDeExitoEnvioComentario()
	{
		return adaptador.obtenerTexto(msgEnvioComentarioExitoso);
	}
	
	@Step("Esperar visibilidad del error de correo incorrecto")
	public void esperarVisibilidadMensajeErrorCorreoNoValido()
	{
		adaptador.esperarVisibilidadDelElemento(msgCorreoIncorrecto);
	}
	
	@Step("Obtener el mensaje de error de correo incorrecto")
	public String mensajeDelCorreoNoValido()
	{
	    return adaptador.obtenerTexto(msgCorreoIncorrecto);
	}

}
