package POJOS;
// Generated 22-ago-2016 17:30:39 by Hibernate Tools 3.6.0



/**
 * Recargas generated by hbm2java
 */
public class Recargas  implements java.io.Serializable {


     private int id;
     private String conceptCode;
     private String phone;
     private String subtotalAmount;
     private String estatus;
     private String folio;

    public Recargas() {
    }

	
    public Recargas(int id, String conceptCode, String phone, String subtotalAmount, String estatus) {
        this.id = id;
        this.conceptCode = conceptCode;
        this.phone = phone;
        this.subtotalAmount = subtotalAmount;
        this.estatus = estatus;
    }
    public Recargas(int id, String conceptCode, String phone, String subtotalAmount, String estatus, String folio) {
       this.id = id;
       this.conceptCode = conceptCode;
       this.phone = phone;
       this.subtotalAmount = subtotalAmount;
       this.estatus = estatus;
       this.folio = folio;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getConceptCode() {
        return this.conceptCode;
    }
    
    public void setConceptCode(String conceptCode) {
        this.conceptCode = conceptCode;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSubtotalAmount() {
        return this.subtotalAmount;
    }
    
    public void setSubtotalAmount(String subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }
    public String getEstatus() {
        return this.estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    public String getFolio() {
        return this.folio;
    }
    
    public void setFolio(String folio) {
        this.folio = folio;
    }




}


