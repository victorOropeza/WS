package Controlador;

import Modelo.PagosDosDAO;
import Modelo.PagosUnoDAO;
import Modelo.RecargasDAO;
import POJOS.PagoDos;
import POJOS.PagoUno;
import POJOS.Recargas;
import com.epagoinc.client.ApiClient;
import com.epagoinc.clientswitchaccountbalanceservice.AccountBalanceQueryResponse;
import com.epagoinc.clientswitchtransactionservicev2.ApplyTransactionResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class Controller {

    private Recargas r;
    private PagoUno pu;
    private PagoDos pd;
    ApiClient client = new ApiClient();

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

    public ApplyTransactionResponse Recarga() throws IOException {
        return NuevaRecarga(r);
    }

    public ApplyTransactionResponse PagoUno() {
        return NuevoPagoUno(pu);
    }

    public ApplyTransactionResponse PagoDos() {
        return NuevoPagoDos(pd);
    }
    
    public AccountBalanceQueryResponse Consulta(String ConceptCode,String Account) {
        return ConsultarSaldo(ConceptCode,Account);
    }

    private ApplyTransactionResponse NuevaRecarga(Recargas recarga) throws IOException {

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

        return t;
    }

    private ApplyTransactionResponse NuevoPagoUno(PagoUno pagouno) {

        //Declaración de variables
        String mensaje;
        int Folio = 0;
        ApplyTransactionResponse t = null;
        BigDecimal subTotalAmount = new BigDecimal(pagouno.getSubtotalAmount());
        String conceptCode = pagouno.getConceptCode();
        String account =  pagouno.getAccount();

        try {
            //Creamos objeto tipo recarga con los datos del WS
            PagosUnoDAO PuDAO = new PagosUnoDAO();
            Folio = Integer.valueOf(PuDAO.IngresarPagoUno(pagouno));
        } catch (Exception e0) {
            mensaje = "Error de almacenamiento";
        }

        
        try {
            //Realizamos la transacción
           
            t = client.executeTransaction(conceptCode, account, subTotalAmount, null);

            //Realizamos la actualización del folio y el estatus
            PagosUnoDAO PuDAO = new PagosUnoDAO();
            if (t.getStatusCode() == 0) {
                PuDAO.Actualizar(pagouno,Folio, t.getClientSwitchTransactionId(), "Exito");
            } else {
                PuDAO.Actualizar(pagouno,Folio, t.getClientSwitchTransactionId(), "Error: " + t.getStatusCode());
            }

            mensaje = "Folio: " + t.getClientSwitchTransactionId() + "  Estatus" + t.getStatusCode();

        } catch (Exception e2) {
            mensaje = "Error de transacción";
        }

        return t;

    }

    private ApplyTransactionResponse NuevoPagoDos(PagoDos pagodos) {

        //Declaración de variables
        String mensaje;
        int Folio = 0;
        ApplyTransactionResponse t = null;
        BigDecimal subTotalAmount = new BigDecimal(pagodos.getSubtotalAmount());
        String conceptCode =  pagodos.getConceptCode();
        String account =  pagodos.getPhone();
        String DV = pagodos.getDv();
        try {
            //Creamos objeto tipo recarga con los datos del WS
            
            PagosDosDAO PdDAO = new PagosDosDAO();
            Folio = Integer.valueOf(PdDAO.IngresarPagoDos(pagodos));
        } catch (Exception e0) {
            mensaje = "Error de almacenamiento";
        }

        

        try {
            //Realizamos la transacción
           
            t = client.executeTransaction(conceptCode, account, subTotalAmount, DV);

            //Realizamos la actualización del folio y el estatus
            PagosDosDAO PdDAO = new PagosDosDAO();
            if (t.getStatusCode() == 0) {
                PdDAO.Actualizar(pagodos,Folio, t.getClientSwitchTransactionId(), "Exito");
            } else {
                PdDAO.Actualizar(pagodos,Folio, t.getClientSwitchTransactionId(), "Error: " + t.getStatusCode());
            }

            mensaje = "Folio: " + t.getClientSwitchTransactionId() + "  Estatus" + t.getStatusCode();

        } catch (Exception e2) {
            mensaje = "Error de transacción";
        }

        return t;

    }
    
    private AccountBalanceQueryResponse ConsultarSaldo(String ConceptCode, String Account)
    {
        
        AccountBalanceQueryResponse ABQR=new AccountBalanceQueryResponse();
        return ABQR=client.executeAccountBalanceQuery(ConceptCode, Account);
    }

}
