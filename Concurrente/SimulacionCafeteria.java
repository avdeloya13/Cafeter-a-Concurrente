import java.util.ArrayList;
import java.util.Random;

/**
 * Clase que simula una cafeteria para probar la implementacion.
 * @author Ana Valeria Deloya Andrade
 */
public class SimulacionCafeteria{

    /**
     * Hace un pedido aleatorio usando random. 
     * Va agregando platillos a la lista de pedido.
     * @param menu el menu de la cafeteria
     * @return la lista con el pedido aleatorio
     */
    public static ArrayList<Platillo> pedidoAleatorio(ArrayList<Platillo> menu){
        
        ArrayList<Platillo> pedido = new ArrayList<>();
        Random r = new Random();

        //Numero de platillos en el pedido
        int numPlat = r.nextInt(3) + 1;

        //Agrega platillos aleatorios a ls lista del pedido
        for(int i = 0; i < numPlat; i++){
            int indicePlatillo = r.nextInt(menu.size());
            Platillo platilloAleatorio = menu.get(indicePlatillo);
            pedido.add(platilloAleatorio);
        }
        return pedido;
    }

    /**
     * Asigna monstruos a una mesa y una vez asignados se les toma su pedido
     * @param cafeteria La cafeteria de Monsters Inc
     * @param numeroMonstruos El numero de mounstruos que quieren compartir una mesa
     */
    private static void asignacionYPedido(Cafeteria cafeteria, int numeroMonstruos){
        int capacidad = cafeteria.getCapacidad();
        Thread[] capMax = new Thread[capacidad];

        for(int i = 0; i < capacidad; i++){
            ArrayList<Platillo> pedido = pedidoAleatorio(cafeteria.menu());
            Thread cliente = new Thread(() -> cafeteria.asignarMesa(numeroMonstruos, pedido));
            capMax[i] = cliente;
            capMax[i].start();
        }

        //Nos aseguramos de que todos los clientes terminen
        for(Thread cliente: capMax){
            try{
                cliente.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        } 
    }

    public static void main(String[] args){
        
        //Chef profesional y chefs ayudantes
        ChefProfesional chefProfesional = new ChefProfesional("John");
        ChefNormal chefNormal1 = new ChefNormal("Pedro");
        ChefNormal chefNormal2 = new ChefNormal("Isaac");
        ChefNormal chefNormal3 = new ChefNormal("Sam");
        
        chefProfesional.getChefs().add(chefNormal1);
        chefProfesional.getChefs().add(chefNormal2);
        chefProfesional.getChefs().add(chefNormal3);

        //Cafeteria de Monsters Inc.
        Cafeteria cafeteria = new Cafeteria(chefProfesional, 4);
        cafeteria.setChefProfesional(chefProfesional);

        //Recepcionista
        Recepcionista recepcionista = new Recepcionista();
        cafeteria.setRecepcionista(recepcionista);

        //Camareros
        Camarero camarero1 = new Camarero("Angel");
        Camarero camarero2 = new Camarero("Mario");
        Camarero camarero3 = new Camarero("Pablo");

        cafeteria.getCamareros().add(camarero1);
        cafeteria.getCamareros().add(camarero2);
        cafeteria.getCamareros().add(camarero3);

        //Para el menu
        Platillo polloAsado = new Platillo("Pollo Asado", 2);
        Platillo pizza = new Platillo("Pizza", 5);
        Platillo carneEnchilada = new Platillo("Carne Enchilada", 2);
        Platillo barbacoa = new Platillo("Barbacoa", 2);
        Platillo pozole = new Platillo("Pozole", 2);
        Platillo pescadillas = new Platillo("Pescadillas", 3);
        Platillo chilaquiles = new Platillo("Chilaquiles", 2);
        Platillo enchiladas = new Platillo("Enchiladas", 3);

        cafeteria.getInventario().add(polloAsado);
        cafeteria.getInventario().add(pizza);
        cafeteria.getInventario().add(carneEnchilada);
        cafeteria.getInventario().add(barbacoa);
        cafeteria.getInventario().add(pozole);
        cafeteria.getInventario().add(pescadillas);
        cafeteria.getInventario().add(chilaquiles);
        cafeteria.getInventario().add(enchiladas);
        
        //Mesas
        Mesa mesa0 = new Mesa(6, 3, 0);
        Mesa mesa1 = new Mesa(10, 5, 1);
        Mesa mesa2 = new Mesa(12, 6, 2);
        Mesa mesa3 = new Mesa(16, 8, 3);

        cafeteria.getMesas()[0] = mesa0;
        cafeteria.getMesas()[1] = mesa1;
        cafeteria.getMesas()[2] = mesa2;
        cafeteria.getMesas()[3] = mesa3;

        cafeteria.calculaCapacidad();

        asignacionYPedido(cafeteria, 8);
    }
}
