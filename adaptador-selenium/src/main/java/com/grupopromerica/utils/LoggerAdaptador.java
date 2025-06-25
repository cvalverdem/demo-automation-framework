package com.grupopromerica.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerAdaptador 
{
	//Nombre del folder para guardar los logs 
	private static final String NOMBRE_CARPETA_LOG = "logs";
	
	//Formato de archivo de log, se maneja un solo log por día, pero podria hacerse por ejecución 
    private static final DateTimeFormatter FORMATO_FECHA_ARCHIVO_LOG = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    //Formato de fecha para el registro de las lineas del log para cada mensaje que se registre 
    private static final DateTimeFormatter FORMATO_FECHA_REGISTRO_MENSAJES = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //Se llama antes de llamar cualquier metodo static de LoggerAdaptador y crea la carpeta para el log correspondiente
    static
    {
        crearDirectorioLogs();
    }

    public static void info(String mensaje) 
    {	
        String log = formato("INFO", mensaje);
        System.out.println(log);
        escribirArchivo(log);
    }

    public static void error(String mensaje) 
    {
        String log = formato("ERROR", mensaje);
        System.err.println(log);
        escribirArchivo(log);
    }

    //Da formato al mensaje que se guardara en el log 
    private static String formato(String tipo, String mensaje) 
    {
        return "[" + tipo + "] " + LocalDateTime.now().format(FORMATO_FECHA_REGISTRO_MENSAJES) + " → " + mensaje;
    }

    //Escribe le mensaje en el archivo log 
    private static void escribirArchivo(String mensaje) 
    {
        String nombreArchivo = "registro-" + LocalDate.now().format(FORMATO_FECHA_ARCHIVO_LOG) + ".log";
        File archivoLog = new File(NOMBRE_CARPETA_LOG, nombreArchivo);

        try (FileWriter escritor = new FileWriter(archivoLog, true)) 
        {
            escritor.write(mensaje + "\n");
        } 
        catch (IOException e) 
        {
            System.err.println("No se pudo escribir en el archivo de log: " + e.getMessage());
        }
    }

    private static void crearDirectorioLogs() 
    {
        File carpeta = new File(NOMBRE_CARPETA_LOG);
        if ( ! carpeta.exists()) 
        {
            boolean seCreoLaCarpeta = carpeta.mkdirs();
            if ( ! seCreoLaCarpeta) 
            {
                System.err.println("No se pudo crear la carpeta logs. Verifica permisos.");
            }
        }
    }
}
