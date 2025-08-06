package cosas;

import tdas.Stack;

/**
 * Clase que representa un camión destinado a un destino específico.
 * Almacena cajas en una pila (Stack) y permite cargar, verificar si se puede despachar,
 * mostrar la carga y despachar el camión si cumple con las condiciones de peso.
 * También permite calcular pesos volumétricos y facturados de su carga.
 * 
 * @author piped
 */
public class Camion {
    /**
     * Destino al que está asignado el camión.
     */
    private GuiaEnvio.Destino destino;

    /**
     * Pila de cajas cargadas en el camión.
     */
    private Stack<Caja> carga;

    /**
     * Peso total facturado de la carga.
     */
    private double pesoTotal;

    /**
     * Crea un nuevo camión para el destino especificado.
     * 
     * @param d Destino al cual está asignado el camión.
     */
    public Camion(GuiaEnvio.Destino d) {
        destino = d;
        carga = new Stack<>();
        pesoTotal = 0;
    }

    /**
     * Carga una caja al camión, aumentando el peso total facturado.
     * 
     * @param c Caja a cargar.
     */
    public void cargarCaja(Caja c) {
        carga.push(c);
        pesoTotal += c.getPesoFacturado();
        System.out.println("Cargando caja en camion " + destino + ": " + c);
    }

    /**
     * Verifica si el camión puede ser despachado,
     * cumpliendo con los pesos volumétricos mínimos y máximos definidos en GuiaEnvio.
     * 
     * @return true si el camión puede despacharse, false en caso contrario.
     */
    public boolean puedeDespachar() {
        return pesoTotal >= GuiaEnvio.PESO_MINIMO_VOLUMETRICO && pesoTotal <= GuiaEnvio.PESO_MAXIMO_VOLUMETRICO;
    }

    /**
     * Muestra por consola todas las cajas cargadas actualmente en el camión.
     */
    public void mostrarCarga() {
        System.out.println("=== Camion " + destino + " (kg=" + pesoTotal + ") ===");
        for (Caja c : carga) System.out.println(c);
    }

    /**
     * Intenta despachar el camión. Solo se despachará si el peso total cumple con los requisitos.
     * Si se despacha, imprime cada caja entregada y limpia la carga.
     * 
     * @return true si el camión fue despachado, false si no cumple con las condiciones.
     */
    public boolean despachar() {
        if (pesoTotal < GuiaEnvio.PESO_MINIMO_VOLUMETRICO) {
            System.out.println("No cumple con el peso minimo requerido. Peso actual: " + pesoTotal + " kg.");
            return false;
        }
        if (pesoTotal > GuiaEnvio.PESO_MAXIMO_VOLUMETRICO) {
            System.out.println("El camion sobrepasa el limite de peso permitido. Peso actual: " + pesoTotal + " kg.");
            return false;
        }

        System.out.println("Despachando camion " + destino + ":");
        while (!carga.isEmpty()) {
            System.out.println("Entregada: " + carga.pop());
        }
        pesoTotal = 0;
        return true;        
    }

    /**
     * Calcula el peso volumétrico total de todas las cajas en la carga.
     * 
     * @return Peso volumétrico total (kg).
     */
    public double calcularPesoVolumetrico() {
        double total = 0;
        for (Caja c : carga) {
            total += c.getPesoVolumetrico();
        }
        return total;
    }

    /**
     * Calcula el peso facturado total de todas las cajas en la carga.
     * 
     * @return Peso facturado total (kg).
     */
    public double calcularPesoFacturado() {
        double total = 0;
        for (Caja c : carga) {
            total += c.getPesoFacturado();
        }
        return total;
    }

    /**
     * Devuelve el número de cajas cargadas en el camión.
     * 
     * @return Cantidad de cajas en la pila de carga.
     */
    public int obtenerNumeroCajas() {
        return carga.size();
    }
}
