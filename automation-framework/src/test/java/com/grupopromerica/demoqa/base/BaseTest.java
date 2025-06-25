package com.grupopromerica.demoqa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.grupopromerica.adaptador_selenium.Adaptador;

public class BaseTest
{
	//Se inicializa y pone como protected para que solo se pueda ser utilizado para las clases que lo heredan
    protected Adaptador adaptador;
    
    @Parameters("navegador")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String navegador)
    {
    	 adaptador = Adaptador.getInstance(navegador);
    	 adaptador.abrirUrl("https://www.clubpromerica.com/costarica/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        if (adaptador != null) 
        {
        	adaptador.capturarPantallaAllure();
            adaptador.cerrarDriver();
        }
    }

}
