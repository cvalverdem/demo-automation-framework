package com.grupopromerica.demoqa.pages;

import com.grupopromerica.adaptador_selenium.Adaptador;

import io.qameta.allure.Step;

public class HomePage
{
	private String menuPromocionesYDescuentos = "//ul[@class='mega-menu']//span[contains(text(),'Promociones y Descuentos')]";
	private String menuPromocionesYDescuentosRestaurantes = "//div[contains(@class,'active')]//a[@title='Restaurantes']";
	
	private String menuContactenos = "//ul[@class='mega-menu']//span[contains(text(),'Contáctenos')]";
	
	//Promociones y descuentos - restaurantes 
	private String beneficiosEnRestaurantesMasterCard = "//div[@data-productid='5394']";
	private String titleProducto = "//h1[@itemprop='name']";
	
	private Adaptador adaptador;
	
	public HomePage(Adaptador adaptador) 
	{
        this.adaptador = adaptador;
    }
	
	@Step("Ingresar a promociones y descuentos > Restaurantes")
	public void ingresarAPromocionesYDescuentosRestaurantes()
	{
		adaptador.pasarMousePorElemento(menuPromocionesYDescuentos);
		adaptador.hacerClick(menuPromocionesYDescuentosRestaurantes);
	}
	
	@Step("Hacer click en la opción de Contáctenos")
	public void ingresarAContactenos()
	{
		adaptador.hacerClick(menuContactenos);
	}
	
	@Step("Ingresar a beneficio restaurante Master Card")
	public void ingresarAlBeneficioRestauranteMasterCard()
	{
		adaptador.hacerClick(beneficiosEnRestaurantesMasterCard);
	}
	
	@Step("Esperar visibilidad del título del producto")
	public void esperarVisibilidadTituloDelProducto()
	{
		adaptador.esperarVisibilidadDelElemento(titleProducto);
	}
	
	@Step("Obtener el mensaje del título del producto")
	public String mensajeDelTituloDelProducto()
	{
	    return adaptador.obtenerTexto(titleProducto);
	}
}
