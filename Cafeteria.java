import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * Clase Cafeteria.
 * @author Ana Valeria Deloya Andrade
 * @author Erick Bernal Marquez
 */
public class Cafeteria{

    /** La recepcionista de la cafeteria. */
    private Recepcionista recepcionista;

    /** Lista de Camareros de la cafeteria */
    private ArrayList<Camarero> camareros;

    /** El chef profesional de la cafeteria */
    private ChefProfesional chp;

    /** Los platillos de la cafeteria */
    private ArrayList<Platillo> inventario;

    /** Mesas en la cafeteria. */
    private Mesa[] mesas;

    /** Para el acceso al arreglo de mesas. */
    private Lock lockMesas;

    /** La capacidad maxima de la cafeteria */
    private int capacidad;

    /**
     * Constructor para la clase Cafeteria.
     * @param chefProfesional El chef profesional de la cafeteria
     * @param numMesas El numero de mesas en la cafeteria
     */
    public Cafeteria(ChefProfesional chefProfesional, int numMesas){
        recepcionista = new Recepcionista();
        camareros = new ArrayList<>();
        this.chp = chefProfesional;
        inventario = new ArrayList<Platillo>();
        lockMesas = new ReentrantLock();

        mesas = new Mesa[numMesas];
    }

    /**
     * Obtiene el inventario de la cafeteria
     * @return el inventario
     */
    public ArrayList<Platillo> getInventario(){
        return inventario;
    }

    /**
     * Muestra el nombre de los platillos en el menu de la cafeteria
     * @return El nombre de los platillos en el menu
     */
    public String menuNombres(){
        String m = "";
        for(Platillo platillo : inventario){
            m += " | " + platillo.getNombre() + " | ";
        }
        return m;
    }

    /**
     * Muestra el menu de la cafeteria
     * @return Los platillos en el menu
     */
    public ArrayList<Platillo> menu(){
    ArrayList<Platillo> menu = new ArrayList<>();
    for(Platillo platillo : inventario) {
        menu.add(platillo);
    }
    return menu;
    }

    /**
     * Para modificar atributo recepcionista de null a una recepcionista
     * @param r La recepcionista
     */
    public void setRecepcionista(Recepcionista r){
        this.recepcionista = r;
    }

    /**
     * Para modificar atributo  de chef profesional chp de null a un chef profesional
     * @param c El chef profesional
     */
    public void setChefProfesional(ChefProfesional c){
        this.chp = c;
    }

    /**
     * Obtiene la lista de camareros de la cafeteria
     * @return la lista de camareros
     */
    public ArrayList<Camarero> getCamareros(){
        return camareros;
    }

    /**
     * Obtiene el arreglo de mesas
     * @return el arreglo de mesas
     */
    public Mesa[] getMesas(){
        return mesas;
    }

    /**
     * Obtiene la capacidad de la cafeteria
     * @return la capacidad
     */
    public int getCapacidad(){
        return capacidad;
    }

    /**
     * Calcula la capacidad de la cafeteria en base a los asientos de cada mesa
     */
    public void calculaCapacidad(){
        for(Mesa mesa : mesas){
            this.capacidad += mesa.getCapacidad();
        }
    }

    /**
     * Asigna un camarero random de la lista de camareros
     * @return El camarero asignado
     */
    private Camarero asignacionCamarero(){
        if(camareros.isEmpty()) {
            System.out.println("La cafeteria no cuenta con camareros disponibles.");
            return null;
        }

        Random r = new Random();
        int i = r.nextInt(camareros.size());
        return camareros.get(i); 
    }

    /**
     * Metodo auxiliar para preparar el pedido de los clientes
     * @param pedido El pedido de los clientes
     */
    private void preparaPedido(ArrayList<Platillo> pedido){
        for(Platillo platillo : pedido){
            boolean platilloPreparado = false;

            //Los chefs ayudantes
            for(ChefNormal chef : chp.getChefs()){
                System.out.println(chef.prepararPlatillo(platillo));
                if(chef.getPreparado()){
                    platilloPreparado = true;
                    break;  //Se sale si el platillo fue preparado
                }
            }

            //Si el platillo no fue preparado, hace el chef profesional
            if(!platilloPreparado) {
                System.out.println(chp.prepararPlatillo(platillo));
            }
        } 
    }

    /**
     * Asiganmos la mesa de acuerdo al numero de monstruos, y se toma pedido.
     * @param numeroMonstruos el numero de monstruos
     * @param pedido un arraylist con el pedido de los clientes asignados a la mesa
     */
    public void asignarMesa(int numeroMonstruos, ArrayList<Platillo> pedido){ 
        lockMesas.lock();
        try{
            for(Mesa mesa : mesas) {
                //Comprueba los requisitos para poder ocupar una mesa
                if(!mesa.estaOcupada() && mesa.intentarOcupar() && mesa.getCapacidad() >= numeroMonstruos){
                    mesa.intentarOcupar();
                    
                    //Ocupan la mesa
                    mesa.setOcupada(true);
                    System.out.println("------------------------------------------------------");
                    System.out.println(recepcionista.asignaMesa() + ", la mesa " + mesa.getId() + " está siendo ocupada ahora.");
                    
                    //Muestra el menu
                    System.out.println("El menu de la cafeteria es: "+  menuNombres());
                    
                    //Se asigna camarero y toma pedido
                    Camarero c = asignacionCamarero();
                    System.out.println(c.tomarOrden(pedido));
                    System.out.println(c.llevarPlatillo(mesa.getId()));

                    preparaPedido(pedido);
                    System.out.println(desocuparMesa(mesa.getId()));
                }
            } 
        } finally {
            lockMesas.unlock(); //Se libera el bloqueo de la mesa   
        }
    }

    /**
     * Desocupa la mesa en la posicion indicada
     * @param pos la posicion de la mesa
     * @return un mensaje de confirmacion
     */
    public String desocuparMesa(int pos){
        Mesa mesaActual = mesas[pos];
        if(mesaActual != null) {
            mesaActual.desocuparMesa();
            return "La mesa " + pos + " ha sido desocupada";
        } else
            return "No se pudo encontrar la mesa para desocupar";
    }
}