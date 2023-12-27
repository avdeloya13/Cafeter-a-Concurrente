/** 
 * Clase Platillo.
 * @author Erick Bernal Marquez
 */
public class Platillo{

    /** El nombre del platillo */
    private String nombre;

    /** Numero de ingredientes del platillo. Para simular los ingredientes */
    private int ingredientes;

    /**
     * Constructor para la clase Platillo
     * @param nombre El nombre del platillo
     * @param ingredientes El numero de ingredientes
     */
    public Platillo(String nombre, int ingredientes){
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    /**
     * Obtiene el nombre del platillo
     * @return el nombre del platillo
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el numero de ingredientes del platillo
     * @return el numero de ingredientes
     */
    public int getIngredientes(){
        return ingredientes;
    }
}
