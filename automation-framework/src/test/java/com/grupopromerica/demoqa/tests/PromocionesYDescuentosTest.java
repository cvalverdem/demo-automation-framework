package com.grupopromerica.demoqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.grupopromerica.demoqa.base.BaseTest;
import com.grupopromerica.demoqa.pages.HomePage;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PromocionesYDescuentosTest extends BaseTest
{
	private HomePage homePage;
	
	@Test(description = "Validar Promocion y descuento restaurantes Master Card")
	@Feature("Promociones Mastercard")
	@Story("Validar beneficios en restaurantes")
	@Severity(SeverityLevel.NORMAL)
	public void validarPromocionYDescuentoRestaurantesMasterCard()
	{
		homePage = new HomePage(adaptador);
		homePage.ingresarAPromocionesYDescuentosRestaurantes();
		homePage.ingresarAlBeneficioRestauranteMasterCard();
		
		homePage.esperarVisibilidadTituloDelProducto();
		
		String mensajeTitulo = homePage.mensajeDelTituloDelProducto();
		
		Assert.assertEquals(mensajeTitulo, "BENEFICIOS EN RESTAUTANTES (MASTERCARD)", 
			    "El texto del título no coincide con el esperado.");
	}
}
