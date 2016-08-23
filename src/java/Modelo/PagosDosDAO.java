package Modelo;

import Modelo.HibernateUtil;
import POJOS.PagoDos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class PagosDosDAO {
    SessionFactory sf=null;
    Session sesion= null;
    Transaction tx=null;
    //Metodo para almacenar los datos
    public void IngresarPagoDos(PagoDos pd)
    {
        
        try
        {
            sf= HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            tx=sesion.beginTransaction();
            sesion.save(pd);
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
