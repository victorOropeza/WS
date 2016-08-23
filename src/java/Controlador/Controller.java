package Controlador;

import Modelo.CrearArchivo;
import Modelo.PagosDosDAO;
import Modelo.PagosUnoDAO;
import Modelo.RecargasDAO;
import POJOS.PagoDos;
import POJOS.PagoUno;
import POJOS.Recargas;


public class Controller 
{
    private Recargas r;
    private PagoUno pu;
    private PagoDos pd;
    
   
    public Controller() 
    {
    }
    public Controller(Recargas recarga) 
    {
        r=recarga;
    }
    public Controller(PagoUno pagoUno) 
    {
        pu=pagoUno;
    }
    public Controller(PagoDos pagoDos) 
    {
        pd=pagoDos;
    }
    
    
    public String Recarga()
    {
        return  NuevaRecarga(r);
    }
    public String PagoUno()
    {
        return  NuevoPagoUno(pu);
    }
    public String PagoDos()
    {
        return  NuevoPagoDos(pd);
    }
    
    
    private String NuevaRecarga(Recargas recarga)
    {
        String mensaje;
        RecargasDAO r=new RecargasDAO();
        
        
        String[] rec=new String[3];
        rec[0]=recarga.getConceptCode();
        rec[1]=recarga.getPhone();
        rec[2]=recarga.getSubtotalAmount();
        
        CrearArchivo ca=new CrearArchivo();
        if(ca.CrearArchivo(rec))
        {
            //Metodo
            mensaje= r.IngresarRecarga(recarga);
        }
        else
        {
            mensaje="Error de transaccion";
        }
        
        return mensaje;
    }
    private String NuevoPagoUno(PagoUno pagouno)
    {
        String mensaje;
        PagosUnoDAO pu=new PagosUnoDAO();
        pu.IngresarPagoUno(pagouno);
        
        String[] pagou=new String[3];
        pagou[0]=pagouno.getConceptCode();
        pagou[1]=pagouno.getAccount();
        pagou[2]=pagouno.getSubtotalAmount();
        
        CrearArchivo ca=new CrearArchivo();
       if(ca.CrearArchivo(pagou))
        {
            //Metodo
            mensaje="";
        }
        else
        {
            mensaje="Error de transaccion";
        }
        
        return mensaje;
        
        
    }
    private String NuevoPagoDos(PagoDos pagodos)
    {
        String mensaje;
        PagosDosDAO pd=new PagosDosDAO();
        pd.IngresarPagoDos(pagodos);
        
        String[] pagod=new String[4];
        
        pagod[0]=pagodos.getConceptCode();
        pagod[1]=pagodos.getAccount();
        pagod[2]=pagodos.getSubtotalAmount();
        pagod[3]=pagodos.getDv();
        CrearArchivo ca=new CrearArchivo();
       if(ca.CrearArchivo(pagod))
        {
            //Metodo
            mensaje="";
        }
        else
        {
            mensaje="Error de transaccion";
        }
        
        return mensaje;
    }

            
    
}
