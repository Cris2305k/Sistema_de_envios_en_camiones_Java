package cosas;

import tdas.List;

/**
 * La clase Bodega representa un almacén de cajas destinadas a un destino específico.
 * Permite recibir cajas, ordenarlas por distancia aproximada de entrega, y mostrarlas.
 * 
 * @author piped
 */
public class Bodega {
    /**
     * Destino asociado a esta bodega.
     */
    private GuiaEnvio.Destino destino;

    /**
     * Lista de cajas almacenadas en la bodega.
     */
    private List<Caja> almacen;

    /**
     * Crea una nueva bodega asociada a un destino específico.
     * 
     * @param d El destino al cual pertenece esta bodega.
     */
    public Bodega(GuiaEnvio.Destino d) {
        destino = d;
        almacen = new List<>();
    }

    /**
     * Recibe una caja y la agrega al almacén.
     * 
     * @param c La caja que se va a agregar.
     */
    public void recibirCaja(Caja c) {
        almacen.list(c);
        System.out.println("Caja agregada a bodega " + destino + ": " + c);
    }

    /**
     * Ordena las cajas del almacén según su distancia aproximada usando el algoritmo Selection Sort.
     */
    public void ordenarPorDistancia() {
        int n = almacen.size();
        Caja[] arr = new Caja[n];
        for (int i = 0; i < n; i++) arr[i] = almacen.get(i);
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getGuia().getDistanciaAprox() < arr[min].getGuia().getDistanciaAprox()) {
                    min = j;
                }
            }
            Caja tmp = arr[i]; arr[i] = arr[min]; arr[min] = tmp;
        }

        almacen.clear();
        for (Caja c : arr) almacen.list(c);
    }

    /**
     * Muestra todas las cajas almacenadas en la bodega.
     */
    public void mostrarCajas() {
        System.out.println("--- Bodega " + destino + " ---");
        for (int i = 0; i < almacen.size(); i++) {
            System.out.println(almacen.get(i));
        }
    }

    /**
     * Devuelve la lista de cajas almacenadas en la bodega.
     * 
     * @return Lista de cajas en el almacén.
     */
    public List<Caja> getAlmacen() {
        return almacen;
    }
}
