import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * Clase Mesa.
 * @author Erick Bernal Marquez
 */
public class Mesa{

    /** Numero de cubiertos de la mesa */
    private int cubiertos;
    
    /** Numero de lugares en la mesa. No necesariamente cuando disponibilidad == 0; ya que puede ser que no haya suficientes sillas */
    private int capacidad;

    /** Indica que es disponible la mesa */
    private boolean ocupada;

    /** Para bloquear la mesa */
    private Lock lockMesa;

    /** Para identificar la mesa */
    private int id;

    /**
     * Constructor para la clase Mesa
     * @param cubiertos Numero de cubiertos de la mesa
     * @param capacidad Numero de lugares en la mesa
     * @param id Identificador de la mesa
     */
    public Mesa(int cubiertos, int capacidad, int id){
        this.cubiertos = cubiertos;
        this.capacidad = capacidad;
        this.lockMesa=new ReentrantLock();
        this.id = id;
    }

    /** 
     * Obtiene el numero de cubiertos de la mesa
     * @return el numero de cubiertos
     */
    public int getCubiertos(){
        return cubiertos;
    }

    /** 
     * Obtiene la capacidad de la mesa
     * @return la capacidad de la mesa
     */
    public int getCapacidad(){
        return capacidad;
    }

    /** 
     * Obtiene el id de la mesa
     * @return el id de la mesa
     */
    public int getId(){
        return id;
    }

    /**
     * Cambia disponibilidad de la mesa
     * @return nuevo valor booleano que marca la disponibilidad de la mesa
     */
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }

    /**
     * Nos dice si la mesa esta ocupada
     * @return 'true' si esta ocupada, 'false' si no lo esta
     */
    public boolean estaOcupada(){
        return ocupada;
    }

    /**
     * Intenta bloquear la mesa, pues se va a ocupar
     * @return 'true' si se logra bloquear, 'false' si no se logra
     */
    public boolean intentarOcupar(){
        return lockMesa.tryLock();
    }

    /**
     * Intenta bloquear la mesa, pues se va a desocupar
     */
    public void desocuparMesa(){
        //el metodo comprueba si el bloqueo está siendo bloqueado que intenta liberar, lol
        if(((ReentrantLock) lockMesa).isHeldByCurrentThread()){
            lockMesa.unlock();
            //liberamos la mesa
            ocupada=false;
        }
    }
}