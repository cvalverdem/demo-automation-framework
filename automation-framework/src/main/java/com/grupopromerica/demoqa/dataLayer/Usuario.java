package com.grupopromerica.demoqa.dataLayer;

public class Usuario
{
	private String nombre;
	private String email;
	private String comentario;
	private String particularidad;

	public Usuario()
	{
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getComentario()
	{
		return comentario;
	}

	public void setComentario(String comentario)
	{
		this.comentario = comentario;
	}

	public String getParticularidad()
	{
		return particularidad;
	}

	public void setParticularidad(String particularidad)
	{
		this.particularidad = particularidad;
	}
}
