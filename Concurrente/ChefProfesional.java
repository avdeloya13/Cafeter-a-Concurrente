import java.util.ArrayList;

/** 
 * Clase Chef Profesional.
 * @author Ana Valeria Deloya Andrade
 * @author Erick Bernal Marquez
 */
public class ChefProfesional implements Chef{

    /** Nombre del chef profesional */
    private String nombre;

    /** Lista de chefs que ayudan al chef profesional */
    private ArrayList<ChefNormal> chefs;

    /**
     * Constructor de Chef Profesional
     * @param nombre El nombre del chef profesional
     */
    public ChefProfesional(String nombre){
        this.nombre = nombre;
        chefs = new ArrayList<>();
    }

    /**
     * Obtiene el nombre del chef profesional
     * @return El nombre del chef profesional
    */
    public String getNombre(){
        return nombre;
    }

    /**
     * Obtiene la lista de Chefs que ayudan al chef profesional
     * @return Los chefs que ayudan al chef profesional
    */
    public ArrayList<ChefNormal> getChefs(){
        return chefs;
    }

    @Override
    public String prepararPlatillo(Platillo platillo){
        return "El chef profesional ha preparado el platillo " + platillo.getNombre() + " con " + platillo.getIngredientes() + " ingredientes.";
    }
}
