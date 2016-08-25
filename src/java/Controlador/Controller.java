package Controlador;

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
import java.math.BigDecimal;

public class Controller {

    private Recargas r;
    private PagoUno pu;
    private PagoDos pd;
    

    public Controller() {
    }

    public Controller(Recargas recarga) {
        r = recarga;
    }

    public Controller(PagoUno pagoUno) {
        pu = pagoUno;
    }

    public Controller(PagoDos pagoDos) {
        pd = pagoDos;
    }

    public String Recarga() throws IOException {
        return NuevaRecarga(r);
    }

    public String PagoUno() {
        return NuevoPagoUno(pu);
    }

    public String PagoDos() {
        return NuevoPagoDos(pd);
    }
    
    public String Consulta(String ConceptCode,String Account) {
        return ConsultarSaldo(ConceptCode,Account);
    }

    private String NuevaRecarga(Recargas recarga) throws IOException {

        //Declaración de variables
        String mensaje = null;
        int Folio = 0;
        ApplyTransactionResponse t = null;
        BigDecimal subTotalAmount = new BigDecimal(recarga.getSubtotalAmount());
        String conceptCode = recarga.getConceptCode();
        String account = recarga.getPhone();

        try {
            //Creamos objeto tipo recarga con los datos del WS
            RecargasDAO RDAO = new RecargasDAO();
            Folio = Integer.valueOf(RDAO.IngresarRecarga(recarga));
        } catch (Exception e0) {
            mensaje = "Error de almacenamiento";
        }

       
        try {
            //Realizamos la transacción
            ApiClient client = new ApiClient();
            t = client.executeTransaction(conceptCode, account, subTotalAmount, null);

            //Realizamos la actualización del folio y el estatus
            RecargasDAO RDAO = new RecargasDAO();
            

            try {
                if (t.getStatusCode() == 0) {
                    RDAO.Actualizar(recarga, Folio, t.getEPagoTransactionId(), "Exito");
                     mensaje = "Folio: " + t.getEPagoTransactionId() + "  Estatus: " + t.getStatusCode();
                } else {
                    RDAO.Actualizar(recarga, Folio, t.getEPagoTransactionId(), "Error: " + t.getStatusCode());
                     mensaje = "Folio: " + "Error" + "  Estatus: " + t.getStatusCode();
                }
                

            } catch (Exception e4) {
                e4.printStackTrace();
            }

           
        } catch (Exception e2) {
            e2.printStackTrace();
            mensaje = "Error de transacción";
        }

        return mensaje;
    }

    private String NuevoPagoUno(PagoUno pagouno) {

        //Declaración de variables
        String mensaje;
        int Folio = 0;
        ApplyTransactionResponse t = null;
        BigDecimal subTotalAmount = new BigDecimal(pagouno.getSubtotalAmount());
        String conceptCode = (String) pagouno.getConceptCode();
        String account = (String) pagouno.getAccount();

        try {
            //Creamos objeto tipo recarga con los datos del WS
            PagoUno pu = new PagoUno();
            PagosUnoDAO PuDAO = new PagosUnoDAO();
            Folio = Integer.valueOf(PuDAO.IngresarPagoUno(pu));
        } catch (Exception e0) {
            mensaje = "Error de almacenamiento";
        }

        
        try {
            //Realizamos la transacción
            ApiClient client = new ApiClient();
            t = client.executeTransaction(conceptCode, account, subTotalAmount, null);

            //Realizamos la actualización del folio y el estatus
            PagosUnoDAO PuDAO = new PagosUnoDAO();
            if (t.getStatusCode() == 0) {
                PuDAO.Actualizar(Folio, t.getClientSwitchTransactionId(), "Exito");
            } else {
                PuDAO.Actualizar(Folio, t.getClientSwitchTransactionId(), "Error: " + t.getStatusCode());
            }

            mensaje = "Folio: " + t.getClientSwitchTransactionId() + "  Estatus" + t.getStatusCode();

        } catch (Exception e2) {
            mensaje = "Error de transacción";
        }

        return mensaje;

    }

    private String NuevoPagoDos(PagoDos pagodos) {

        //Declaración de variables
        String mensaje;
        int Folio = 0;
        ApplyTransactionResponse t = null;
        BigDecimal subTotalAmount = new BigDecimal(pagodos.getSubtotalAmount());
        String conceptCode = (String) pagodos.getConceptCode();
        String account = (String) pagodos.getPhone();
        String DV = pagodos.getDv();

        try {
            //Creamos objeto tipo recarga con los datos del WS
            PagoDos pd = new PagoDos();
            PagosDosDAO PdDAO = new PagosDosDAO();
            Folio = Integer.valueOf(PdDAO.IngresarPagoDos(pd));
        } catch (Exception e0) {
            mensaje = "Error de almacenamiento";
        }

        

        try {
            //Realizamos la transacción
            ApiClient client = new ApiClient();
            t = client.executeTransaction(conceptCode, account, subTotalAmount, DV);

            //Realizamos la actualización del folio y el estatus
            PagosDosDAO PdDAO = new PagosDosDAO();
            if (t.getStatusCode() == 0) {
                PdDAO.Actualizar(Folio, t.getClientSwitchTransactionId(), "Exito");
            } else {
                PdDAO.Actualizar(Folio, t.getClientSwitchTransactionId(), "Error: " + t.getStatusCode());
            }

            mensaje = "Folio: " + t.getClientSwitchTransactionId() + "  Estatus" + t.getStatusCode();

        } catch (Exception e2) {
            mensaje = "Error de transacción";
        }

        return mensaje;

    }
    
    private String ConsultarSaldo(String ConceptCode, String Account)
    {
        
        
        
        
        return null;
    }

}
