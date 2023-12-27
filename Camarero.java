import java.util.ArrayList;

/** 
 * Clase Camarero.
 * @author Ana Valeria Deloya Andrade
 * @author Erick Bernal Marquez
 */
public class Camarero{
    
    /** El nombre del camarero */
    private String nombre;

    /**
     * Constructor para la clase Camarero
     * @param nombre El nombre del camarero
     */
    public Camarero(String nombre){
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del camarero
     * @return el nombre del camarero
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * El camarero toma el pedido del cliente
     * @param pedido Un arraylist con el pedido del cliente
     * @return el pedido del cliente
     */
    public String tomarOrden(ArrayList<Platillo> pedido){
        String p = "";
        System.out.println("Hola, mi nombre es " + getNombre() + ", he tomado su pedido: ");

        for(Platillo platillo : pedido){
            p += " | " + platillo.getNombre() + " | ";
        }
        return p;
    }

    /**
     * El camarero lleva comida a la mesa cuyo id pasa como parametro.
     * @param idMesa El id de la mesa
     * @return El mensaje de que el camarero lleva la comida
     */
    public String llevarPlatillo(int idMesa){
        return "El camarero " + getNombre() + " llevando la comida a la mesa " + idMesa;
    }
}