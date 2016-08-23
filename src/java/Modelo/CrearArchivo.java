package Modelo;

import java.io.FileWriter;
import java.io.PrintWriter;

public class CrearArchivo 
{
    public boolean CrearArchivo(String[] datos)
    {
        return Crear(datos);
    }
    
    private boolean Crear(String[] datos)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Users\\Alex\\Desktop\\data.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < datos.length; i++)
            {
                pw.println(datos[i]);
            }
            return true;   

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        } 
        finally 
        {
           try 
           {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
            if (null != fichero)
              fichero.close();
           } 
           catch (Exception e2) 
           {
              e2.printStackTrace();
           }
        }
    }
    
}
