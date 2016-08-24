package Controlador;

import Modelo.CrearArchivo;
import Modelo.PagosDosDAO;
import Modelo.PagosUnoDAO;
import Modelo.RecargasDAO;
import POJOS.PagoDos;
import POJOS.PagoUno;
import POJOS.Recargas;
import com.epagoinc.client.ApiClient;
import com.epagoinc.client.Globals;
import com.epagoinc.clientswitchtransactionservicev2.ApplyTransactionResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;


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
    
    
    public String Recarga() throws IOException
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
    
    
    private String NuevaRecarga(Recargas recarga) throws IOException
    {
        String mensaje;
        RecargasDAO r=new RecargasDAO();
        
        
        String[] rec=new String[3];
        rec[0]=recarga.getConceptCode();
        rec[1]=recarga.getPhone();
        rec[2]=recarga.getSubtotalAmount();
        
       
        
        
            BigDecimal subTotalAmount = new BigDecimal(recarga.getSubtotalAmount());
            String conceptCode = recarga.getConceptCode();
            String account = recarga.getPhone();
            
            System.out.println(subTotalAmount);
            System.out.println(conceptCode);
            System.out.println(account);
            
            Globals.ClientCatalogQueryAddress="https://c01.epagoonline.com/mx/AgentService2-R3/ClientCatalogQueryService.svc";
            Globals.ClientSwitchDepositNotificationServiceAddress="https://c01.epagoonline.com/mx/AgentService2-R3/ClientSwitchDepositNotificationService.svc";
            Globals.ClientSwitchTransactionServiceAddress="https://c01.epagoonline.com/mx/AgentService2-R3/ClientSwitchTransactionServiceV2.svc";
            Globals.ClientSwitchAccountBalanceServiceAddress="https://c01.epagoonline.com/mx/AgentService2-R3/ClientSwitchAccountBalanceService.svc";
            
            System.out.println(subTotalAmount);
            System.out.println(conceptCode);
            System.out.println(account);
            
            ApiClient client = new ApiClient();

            ApplyTransactionResponse t = client.executeTransaction(conceptCode, account,subTotalAmount ,null);
            
            
            
            mensaje=t.getClientSwitchTransactionId()+" - "+t.getStatusCode();
            
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
