package Modelo;

import Modelo.HibernateUtil;
import POJOS.Recargas;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class RecargasDAO {
    SessionFactory sf=null;
    Session sesion= null;
    Transaction tx=null;
    //Metodo para almacenar los datos
    public String IngresarRecarga(Recargas r)
    {
        String x;
        try
        {
            sf= HibernateUtil.getSessionFactory();
            sesion=sf.openSession();
            tx=sesion.beginTransaction();
            sesion.save(r);
            x=String.valueOf(r.getId()) +" - "+ sesion.getIdentifier(r);
            
            tx.commit();
            sesion.close();
        }
        catch(Exception ex)
        {
            tx.rollback();
            
            throw new RuntimeException("No se realizó la recarga");
        }
        return x;
        
    }
    
    
    
}
