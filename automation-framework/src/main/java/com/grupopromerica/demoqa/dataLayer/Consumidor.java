package com.grupopromerica.demoqa.dataLayer;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Esta clase simula un consumidor, facilita los datos de prueba a los test en este caso por una particularidad del usuario 
 * Lo ideal es que esto sea un proyecto completamente aparte, para separar la capa de datos de los casos de prueba y el adaptador
 * Lo que facilita poder utilizarlo en otros proyectos y que estoy puedan utilizarlos como consumidor de datos de prueba 
*/

public class Consumidor
{
	//Carga los usuario que lee del json en el consumidor 
	private static List<Usuario> listaContactos;

	static
	{
		try
		{
			ObjectMapper mapper = new ObjectMapper();
			//Busca el json alojado en la carpeta de resources/data 
			InputStream is = Consumidor.class.getClassLoader().getResourceAsStream("data/usuarios.json");

			//Carga los usuario en la lista 
			listaContactos = mapper.readValue(is, new TypeReference<List<Usuario>>()
			{
			});
		} catch (Exception e)
		{
			throw new RuntimeException("Error al cargar usuarios desde JSON: " + e.getMessage(), e);
		}
	}

	public static Usuario obtenerPorParticularidad(String particularidad)
	{
		return listaContactos.stream().filter(usuario -> usuario.getParticularidad().equalsIgnoreCase(particularidad)).findFirst()
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + particularidad));
	}
}
