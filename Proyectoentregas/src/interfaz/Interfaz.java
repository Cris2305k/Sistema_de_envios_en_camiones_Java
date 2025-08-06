package interfaz;

import tdas.*;
import java.util.Scanner;
import cosas.*;

/**
 * Clase principal que actúa como interfaz de usuario para gestionar el
 * sistema de logística y envíos. Permite gestionar clientes, cajas, bodegas,
 * camiones y métricas de despacho mediante una consola interactiva.
 */
public class Interfaz {
    private static Queue<Caja> cola = new Queue<>();
    private static Bodega[] bodegas = new Bodega[5];
    private static Camion[] camiones = new Camion[5];
    private static List<Cliente> clientes = new List<>();
    private static MetricaDespacho metricaDespacho = new MetricaDespacho();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Método principal que inicia el menú de interacción con el usuario.
     */
    public static void main(String[] args) {
        for (int i = 0; i < GuiaEnvio.Destino.values().length; i++) {
            bodegas[i] = new Bodega(GuiaEnvio.Destino.values()[i]);
            camiones[i] = new Camion(GuiaEnvio.Destino.values()[i]);
        }
        boolean salir = false;
        while (!salir) {
            System.out.println("--- Menu Principal---\n"
                    + "1-Encolar \n"
                    + "2-Mostrar cola\n"
                    + "3-Procesar\n"
                    + "4-Mostrar bodega\n"
                    + "5-Ordenar bodega\n"
                    + "6-Cargar camion \n"
                    + "7-Mostrar camion\n"
                    + "8-Despachar\n"
                    + "9-Agregar cliente \n"
                    + "10-Mostrar clientes \n"
                    + "11-Mostrar Metricas \n"
                    + "12-Salir");
            int opt = sc.nextInt(); sc.nextLine();
            switch(opt) {
                case 1: encolarCaja(); break;
                case 2: System.out.println("Cajas en cola=" + cola.size()); break;
                case 3: procesarSiguiente(); break;
                case 4: mostrarBodega(); break;
                case 5: ordenarBodega(); break;
                case 6: cargarCamion(); break;
                case 7: mostrarCamion(); break;
                case 8: despacharCamion(); break;
                case 9: agregarCliente(); break;
                case 10: mostrarClientes(); break;
                case 11: mostrarMetricas(); break;
                case 12: salir = true; break;
            }
        }
        System.out.println("Fin aplicacion.");
    }

    /**
     * Permite agregar un cliente de forma manual o automática.
     */
    private static void agregarCliente(){
        System.out.println("1.Agregar manual \n" + "2.Agregar aleatorio \n");
        int opt = sc.nextInt(); sc.nextLine();
        switch(opt) {
            case 1: agregarClienteManual();
                    System.out.println("Cliente agregado");
                    break;
            case 2: agregarClienteAleatorio();
                    System.out.println("Cliente agregado");
                    break;
        }
    }

    /**
     * Muestra todos los clientes registrados.
     */
    private static void mostrarClientes(){
        for(Cliente unCliente: clientes){
            System.out.println(unCliente);
        }
    }

    /**
     * Agrega un cliente con datos ingresados por el usuario.
     */
    private static void agregarClienteManual(){
        System.out.println("Ingrese ID"); String id = sc.nextLine();
        System.out.println("Ingrese nombre"); String nombre = sc.nextLine();
        System.out.println("Ingrese telefono"); String tel = sc.nextLine();
        System.out.println("Ingrese email"); String email = sc.nextLine();
        System.out.println("Ingrese dirección"); String dir = sc.nextLine();
        clientes.addFirst(new Cliente(id, nombre, tel, email, dir));
    }

    /**
     * Agrega un cliente con datos generados aleatoriamente.
     */
    private static void agregarClienteAleatorio(){
        clientes.addFirst(new Cliente(true));
    }

    /**
     * Permite encolar una caja asociada a un cliente y un destino.
     */
    private static void encolarCaja() {
        System.out.print("Altura(cm): "); double a = sc.nextDouble();
        System.out.print("Longitud(cm): "); double l = sc.nextDouble();
        System.out.print("Ancho(cm): ");   double w = sc.nextDouble();
        System.out.print("Peso real(kg): "); double p = sc.nextDouble(); sc.nextLine();

        System.out.println("Ingrese el id del cliente"); 
        String id = sc.nextLine();

        Cliente cli = null;
        for (Cliente unCliente : clientes) {
            if (unCliente.getId().equalsIgnoreCase(id)) {
                cli = unCliente;
                break;
            }
        }
        if (cli == null) {
            System.out.println("ID de cliente no encontrado. No se puede encolar la caja.");
            return;
        }

        Caja c = new Caja(a, l, w, p);
        double pesoFacturado = c.getPesoFacturado();

        if (pesoFacturado > 4000) {
            System.out.println("️Senor usuario, el peso de la caja que desea enviar (" 
                + pesoFacturado + " kg) excede la capacidad permitida de 4000 kg.(INGRESE DE NUEVO)");
            return;
        }

        System.out.print("OPCIONES DE BODEGAS DONDE SE VA ALMACENAR\n" 
                + "0- BODEGA-NORTE\n" 
                + "1- BODEGA-SUR\n" 
                + "2- BODEGA-ORIENTE\n" 
                + "3- BODEGA-OCCIDENTE\n" 
                + "4- BODEGA-CENTRO\n "); 
        int d = sc.nextInt(); sc.nextLine();
        GuiaEnvio g = new GuiaEnvio("G" + System.currentTimeMillis(), cli, GuiaEnvio.Destino.values()[d]);
        g.calcularCostos(pesoFacturado);
        c.setGuia(g);
        cola.enqueue(c);
        System.out.println("Caja encolada correctamente: " + c);
    }

    /**
     * Procesa la siguiente caja en la cola y la envía a la bodega correspondiente.
     */
    private static void procesarSiguiente() {
        Caja c = cola.dequeue();
        if (c == null) { 
            System.out.println("Cola vacia"); 
            return; 
        }
        bodegas[c.getGuia().getDestino().ordinal()].recibirCaja(c);
    }

    /**
     * Muestra el contenido de una bodega seleccionada.
     */
    private static void mostrarBodega() {
        System.out.print("---Mostrar Bodega---\n"
                +"0- BODEGA-NORTE\n" 
                +"1- BODEGA-SUR\n" 
                +"2- BODEGA-ORIENTE\n" 
                +"3- BODEGA-OCCIDENTE\n" 
                +"4- BODEGA-CENTRO\n "); 
        int d = sc.nextInt();
        if (bodegas[d].getAlmacen().isEmpty()) {
            System.out.println("La bodega esta vacia.");
        } else {
            bodegas[d].mostrarCajas();
        }
    }

    /**
     * Ordena las cajas de una bodega según la distancia.
     */
    private static void ordenarBodega() {
        System.out.print("---Ordenar Bodega---\n"
                +"0- BODEGA-NORTE\n" 
                +"1- BODEGA-SUR\n" 
                +"2- BODEGA-ORIENTE\n" 
                +"3- BODEGA-OCCIDENTE\n" 
                +"4- BODEGA-CENTRO\n "); 
        int d = sc.nextInt();
        bodegas[d].ordenarPorDistancia();
    }

    /**
     * Carga todas las cajas de una bodega a su camión correspondiente.
     */
    private static void cargarCamion() {
        System.out.print("---Cargar Camion---\n"
                +"0- CAMION-NORTE\n" 
                +"1- CAMION-SUR\n" 
                +"2- CAMION-ORIENTE\n" 
                +"3- CAMION-OCCIDENTE\n" 
                +"4- CAMION-CENTRO\n "); 
        int d = sc.nextInt();
        List<Caja> almacen = bodegas[d].getAlmacen();
        for (int i = almacen.size() - 1; i >= 0; i--){
            Caja caja = almacen.get(i);
            camiones[d].cargarCaja(caja);
            almacen.remove(i);
        }
    }

    /**
     * Muestra las cajas cargadas en un camión.
     */
    private static void mostrarCamion() {
        System.out.print("---MOSTRAR CAMION---\n"
                +"0- CAMION-NORTE\n" 
                +"1- CAMION-SUR\n" 
                +"2- CAMION-ORIENTE\n" 
                +"3- CAMION-OCCIDENTE\n" 
                +"4- CAMION-CENTRO\n "); 
        int d = sc.nextInt();
        camiones[d].mostrarCarga();
    }

    /**
     * Despacha un camión, actualizando las métricas de despacho.
     */
    private static void despacharCamion() {
        System.out.print("---DESPACHAR CAMION---\n"
                + "0- CAMION-NORTE\n"
                + "1- CAMION-SUR\n"
                + "2- CAMION-ORIENTE\n"
                + "3- CAMION-OCCIDENTE\n"
                + "4- CAMION-CENTRO\n ");
        int d = sc.nextInt();
        sc.nextLine();
        Camion camion = camiones[d];

        double pesoVolumetrico = camion.calcularPesoVolumetrico();
        float pesoFacturado = (float) camion.calcularPesoFacturado();
        int numeroCajas = camion.obtenerNumeroCajas();

        boolean despachado = camion.despachar();

        if (despachado) {
            metricaDespacho.agregarPesoVolumetrico(pesoVolumetrico);
            metricaDespacho.agregarPesoFacturado(pesoFacturado * 3);
            metricaDespacho.agregarNumeroCajas(numeroCajas);
        }
    }

    /**
     * Muestra las métricas acumuladas del sistema de despacho.
     */
    private static void mostrarMetricas() {
        System.out.println(metricaDespacho);
    }
}
