package Vista;

import Controlador.Controller;
import Modelo.ValidacionDeTelefono;
import POJOS.PagoDos;
import POJOS.PagoUno;
import POJOS.Recargas;
import java.io.IOException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alex
 */
@WebService(serviceName = "Servicios")
public class Servicios {

     /**
     * Web service operation
     */
    @WebMethod(operationName = "Recarga")
    public String Recarga(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Phone") String Phone, @WebParam(name = "SubtotalAmount") String SubtotalAmount) throws IOException {
        
        ValidacionDeTelefono validar=new ValidacionDeTelefono();
        if(validar.VerificarNumero(Phone.substring(1,Phone.length()-1)))
        {
            Recargas recarga=new Recargas();
            //recarga.setId();
            recarga.setConceptCode(ConceptCode.substring(1,ConceptCode.length()-1));
            recarga.setPhone(Phone.substring(1,Phone.length()-1));
            recarga.setSubtotalAmount(SubtotalAmount.substring(1,SubtotalAmount.length()-1));
            recarga.setEstatus("Pendiente");
            recarga.setFolio("N/A");
            
             
            System.out.println(ConceptCode);
            System.out.println(Phone);
            System.out.println(SubtotalAmount);
            
            
            
            Controller rec=new Controller(recarga);
            
            return rec.Recarga();
            
        }
        else
        {
            return "Telefono invalido "+ Phone.substring(1,Phone.length()-1);
        }
        
        
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "PagoUno")
    public String PagoUno(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account, @WebParam(name = "SubtotalAmount") String SubtotalAmount) {
        
        
        PagoUno pu=new PagoUno();
        pu.setId(0);
        pu.setConceptCode(ConceptCode);
        pu.setAccount(Account);
        pu.setSubtotalAmount(SubtotalAmount);
        pu.setEstatus("Pendiente");
        pu.setFolio("N/A");
        
        Controller c=new Controller(pu);
        c.PagoUno();
        
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "PagoDos")
    public String PagoDos(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account, @WebParam(name = "SubtotalAmount") String SubtotalAmount, @WebParam(name = "DV") String DV) {
        
        PagoDos pd=new PagoDos();
        pd.setId(0);
        pd.setConceptCode(ConceptCode);
        pd.setAccount(Account);
        pd.setSubtotalAmount(SubtotalAmount);
        pd.setDv(DV);
        pd.setEstatus("Pendiente");
        pd.setFolio("N/A");
        
        Controller c=new Controller(pd);
        c.PagoDos();
        
        return null;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultaServicios")
    public String ConsultaServicios(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account) {
        //TODO write your implementation code here:
        return null;
    }
}
