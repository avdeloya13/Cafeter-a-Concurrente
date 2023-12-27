/** 
 * Clase Recepcionista.
 * @author Ana Valeria Deloya Andrade
 * @author Erick Bernal Marquez
 */
public class Recepcionista{

    /** 
     * Constructor para clase Recepcionista.
     */
    public Recepcionista(){
       
    }

    /**
     * Asignacion de Mesa.
     * @return mensaje de que se ha asignado la mesa
     */
    public String asignaMesa(){
        return "La recepcionista los ha asignado a una mesa";
    }    

    /**
     * No hay asignacion de mesa
     * @return mensaje de que no se ha podido asignar la mesa
     */
    public String noAsignaMesa(){
        return "La recepcionista no ha encontrado ninguna mesa disponible";
    }  
}
