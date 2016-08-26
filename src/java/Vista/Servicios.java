package Vista;

import Controlador.Controller;
import Modelo.ValidacionDeTelefono;
import POJOS.PagoDos;
import POJOS.PagoUno;
import POJOS.Recargas;
import com.epagoinc.clientswitchaccountbalanceservice.AccountBalanceQueryResponse;
import com.epagoinc.clientswitchtransactionservicev2.ApplyTransactionResponse;
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
    public ApplyTransactionResponse Recarga(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Phone") String Phone, @WebParam(name = "SubtotalAmount") String SubtotalAmount) throws IOException {

        ValidacionDeTelefono validar = new ValidacionDeTelefono();
        if (validar.VerificarNumero(Phone.substring(1, Phone.length() - 1))) {

            Recargas recarga = new Recargas();
            recarga.setConceptCode(ConceptCode.substring(1, ConceptCode.length() - 1));
            recarga.setPhone(Phone.substring(1, Phone.length() - 1));
            recarga.setSubtotalAmount(SubtotalAmount.substring(1, SubtotalAmount.length() - 1));
            recarga.setEstatus("Pendiente");
            recarga.setFolio("N/A");

            Controller rec = new Controller(recarga);

            return rec.Recarga();

        } else {
            return null;
        }

    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "PagoUno")
    public ApplyTransactionResponse PagoUno(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account, @WebParam(name = "SubtotalAmount") String SubtotalAmount) {

        PagoUno pu = new PagoUno();
        pu.setConceptCode(ConceptCode.substring(1, ConceptCode.length() - 1));
        pu.setAccount(Account.substring(1, Account.length() - 1));
        pu.setSubtotalAmount(SubtotalAmount.substring(1, SubtotalAmount.length() - 1));
        pu.setEstatus("Pendiente");
        pu.setFolio("N/A");

        Controller c = new Controller(pu);

        return c.PagoUno();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "PagoDos")
    public ApplyTransactionResponse PagoDos(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account, @WebParam(name = "SubtotalAmount") String SubtotalAmount, @WebParam(name = "DV") String DV) {

        PagoDos pd = new PagoDos();
        pd.setConceptCode(ConceptCode.substring(1, ConceptCode.length() - 1));
        pd.setPhone(Account.substring(1, Account.length() - 1));
        pd.setSubtotalAmount(SubtotalAmount.substring(1, SubtotalAmount.length() - 1));
        pd.setDv(DV.substring(1, DV.length() - 1));
        pd.setEstatus("Pendiente");
        pd.setFolio("N/A");

        Controller c = new Controller(pd);

        return c.PagoDos();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultaServicios")
    public AccountBalanceQueryResponse ConsultaServicios(@WebParam(name = "ConceptCode") String ConceptCode, @WebParam(name = "Account") String Account) {

        Controller consulta = new Controller();
        return consulta.Consulta(ConceptCode.substring(1, ConceptCode.length() - 1), Account.substring(1, Account.length() - 1));
    }
}
