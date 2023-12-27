import java.util.Random;

/** 
 * Clase Chef Normal.
 * @author Erick Bernal Marquez
 * @author Ana Valeria Deloya Andrade
 */
public class ChefNormal implements Chef{

    /** Nombre del chef normal. */
    private String nombre;

    /** Para saber si el platillo ya fue preparado */
    private boolean preparado;

    /**
     * Constructor de Chef Normal
     * @param nombre El nombre del chef normal
     */
    public ChefNormal(String nombre){
        this.nombre = nombre;
        this.preparado = false;
    }

    /**
     * Obtiene el nombre del chef normal.
     * @return El nombre del chef normal
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene el estado de si el platillo fue preparado o no por el chef normal.
     * @return El estado de preparado o no
     */
    public boolean getPreparado(){
        return preparado;
    }

    /**
     * Obtiene el estado de si el platillo fue preparado o no por el chef normal.
     */
    public void setPreparado(boolean modificado){
        this.preparado = modificado;
    }

    @Override
    public String prepararPlatillo(Platillo platillo){
        Random r = new Random();
        boolean valorAleatorio = r.nextBoolean();
    
        if(valorAleatorio){
            preparado = true;
            return "El chef normal " + nombre + " ha preparado tu platillo: " + platillo.getNombre() + " con " + platillo.getIngredientes() + " ingredientes.";
        } else {
            preparado = false;
            return "El chef normal " + nombre + " no pudo preparar tu platillo " + platillo.getNombre();
        }
    }
}
