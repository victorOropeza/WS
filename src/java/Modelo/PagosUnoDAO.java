package Modelo;

import Modelo.HibernateUtil;
import POJOS.PagoUno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class PagosUnoDAO {
    SessionFactory sf=null;
    Session sesion= null;
    Transaction tx=null;
    //Metodo para almacenar los datos
    public void IngresarPagoUno(PagoUno pu)
    {
        
        try
        {
            sf= HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            tx=sesion.beginTransaction();
            sesion.save(pu);
            tx.commit();
            sesion.close();
        }
        catch(Exception ex)
        {
            tx.rollback();
            
            throw new RuntimeException("No se realizó el pago");
        }
        
    }
    
    
    
}
