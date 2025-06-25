package com.grupopromerica.adaptador_selenium;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.grupopromerica.utils.LoggerAdaptador;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;

public class Adaptador
{
	private WebDriver driver;
	private WebDriverWait wait;

	private final int TIEMPO_ESPERA = 60; // tiempo de espera por defecto

	// Dejamos preparado el adaptador para permitir la ejecucion en multiples
	// navegadores en un mismo pipeline por medio de hilos
	private static final ThreadLocal<Adaptador> instanciaDelAdaptador = new ThreadLocal<>();

	private Adaptador(String navegador)
	{
		this.driver = crearDriver(navegador);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIEMPO_ESPERA));
	}

	// Permite crear la instancia del adaptador por navegador
	public static Adaptador getInstance(String navegador)
	{
		if (instanciaDelAdaptador.get() == null)
		{
			instanciaDelAdaptador.set(new Adaptador(navegador));
		}
		return instanciaDelAdaptador.get();
	}

	//Crea el driver dependiendo del navegodor enviado, este obtiene la versión de acuerdo al SO y el navegador utilizado 
	private WebDriver crearDriver(String navegador)
	{
		WebDriver driver;

		switch (navegador.toLowerCase())
		{
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "ie":
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.ignoreZoomSettings();
			ieOptions.introduceFlakinessByIgnoringSecurityDomains();
			ieOptions.requireWindowFocus();
			driver = new InternetExplorerDriver(ieOptions);
			break;

		case "chrome":
		default:
			WebDriverManager.chromedriver().setup();
			ChromeOptions optionsChrome = new ChromeOptions();
			optionsChrome.addArguments("test-type");
			optionsChrome.addArguments("ignore-certificate-errors");
			optionsChrome.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(optionsChrome);
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIEMPO_ESPERA));
		return driver;
	}

	public void abrirUrl(String url)
	{
		driver.get(url);
	}

	// Esperar a que un elemento esté visible
	private WebElement esperarAlElementoEsteVisible(String xpath)
	{
		try
		{
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (TimeoutException e)
		{
			LoggerAdaptador.error("Tiempo agotado esperando visibilidad del elemento: " + xpath);
			throw e;
		}
	}

	// Esperar a que un elemento sea clickeable
	private WebElement esperarAlElementoSeaClickeable(String xpath)
	{
		try
		{
			return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (TimeoutException e)
		{
			LoggerAdaptador.error("Elemento no fue clickeable a tiempo: " + xpath);
			throw e;
		}
	}

	public void escribirTexto(String xpath, String texto)
	{
		try
		{
			WebElement campo = esperarAlElementoEsteVisible(xpath);
			campo.clear();
			campo.sendKeys(texto);
			capturarPantallaAllure();
		} catch (Exception e)
		{
			LoggerAdaptador.error("Error al escribir texto en: " + xpath + " - " + e.getMessage());
			throw e;
		}
	}

	public void hacerClick(String xpath)
	{
		try
		{
			WebElement boton = esperarAlElementoSeaClickeable(xpath);
			boton.click();
			capturarPantallaAllure();
		} catch (Exception e)
		{
			LoggerAdaptador.error("Error al hacer clic en: " + xpath + " - " + e.getMessage());
			throw e;
		}
	}

	public String obtenerTexto(String xpath)
	{
		try
		{
			return esperarAlElementoEsteVisible(xpath).getText();
		} catch (Exception e)
		{
			LoggerAdaptador.error("Error al obtener texto de: " + xpath + " - " + e.getMessage());
			throw e;
		}
	}

	// Validar si un elemento está presente
	public boolean estaPresente(String xpath)
	{
		try
		{
			driver.findElement(By.xpath(xpath));
			return true;
		} catch (NoSuchElementException e)
		{
			LoggerAdaptador.info("Elemento NO presente: " + xpath);
			return false;
		}
	}
	
	//Hace un hover al elemento 
	public void pasarMousePorElemento(String xpath)
	{
		try
		{
			WebElement elemento = esperarAlElementoEsteVisible(xpath);
			Actions actions = new Actions(driver);
			actions.moveToElement(elemento).perform();

		} catch (Exception e)
		{
			LoggerAdaptador.error("Error al posicionarse sobre el elemento: " + xpath + " - " + e.getMessage());
			throw e;
		}
	}
	
	public void esperarVisibilidadDelElemento(String xpath)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIEMPO_ESPERA));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
	}

	public void cerrarDriver()
	{
		if (driver != null)
		{
			driver.quit();
			instanciaDelAdaptador.remove(); // Libera instancia de este hilo
		}
	}
	
	//Se encarga de realizar las capturas, se puede llamar en diferentes acciones del adaptador según sea necesario
	public void capturarPantallaAllure()
	{
		try
		{
			byte[] captura = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Captura de pantalla", new ByteArrayInputStream(captura));
		} catch (Exception e)
		{
			System.out.println("Error al capturar pantalla: " + e.getMessage());
		}
	}

}
