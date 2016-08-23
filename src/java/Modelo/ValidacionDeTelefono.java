package Modelo;

public class ValidacionDeTelefono 
{
    public boolean VerificarNumero(String numero)
    {
        return verificacion(numero);
    }
    
    private boolean verificacion(String numero)
    {
        if(numero.length()== 10)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
}
