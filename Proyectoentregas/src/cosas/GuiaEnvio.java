package cosas;

/**
 * Clase que representa una guía de envío para una caja.
 * Contiene información del cliente, el destino, el peso facturado,
 * la distancia aproximada del envío y el costo del mismo.
 * También define las constantes de peso mínimo y máximo para el despacho.
 * 
 * La distancia se calcula aleatoriamente entre 1 y 100 km.
 * El costo de envío se calcula como {@code pesoFacturado * COSTO_POR_KG}.
 * 
 * @author piped
 */
public class GuiaEnvio {
    
    /**
     * Enum que representa los posibles destinos de envío.
     */
    public enum Destino { NORTE, SUR, ORIENTE, OCCIDENTE, CENTRO }
    
    private String idCaja;
    private Cliente cliente;
    private Destino destino;
    private double pesoFacturado;
    private double distanciaAprox;
    private double costoEnvio;
    
    /**
     * Costo por kilogramo utilizado para calcular el costo total del envío.
     */
    private static final double COSTO_POR_KG = 3.0;

    /**
     * Peso volumétrico mínimo requerido para despachar un camión.
     */
    public static final double PESO_MINIMO_VOLUMETRICO = 2000.0;

    /**
     * Peso volumétrico máximo permitido para despachar un camión.
     */
    public static final double PESO_MAXIMO_VOLUMETRICO = 4000.0;

    /**
     * Constructor de la guía de envío.
     * 
     * @param idCaja ID de la caja a la que corresponde la guía.
     * @param cliente Cliente asociado al envío.
     * @param dest Destino al que se dirige el envío.
     */
    public GuiaEnvio(String idCaja, Cliente cliente, Destino dest) {
        this.idCaja = idCaja;
        this.cliente = cliente;
        this.destino = dest;
        this.distanciaAprox = calcularDistancia(dest);
    }

    /**
     * Calcula los costos del envío con base en el peso facturado.
     * 
     * @param pesoFacturado Peso con el que se facturará el envío.
     */
    public void calcularCostos(double pesoFacturado) {
        this.pesoFacturado = pesoFacturado;
        this.costoEnvio = pesoFacturado * COSTO_POR_KG;
    }

    /**
     * Genera una distancia aleatoria para el destino proporcionado.
     * 
     * @param d Destino del envío.
     * @return Distancia aleatoria entre 1 y 100 km.
     */
    private double calcularDistancia(Destino d) {
        return 1 + new java.util.Random().nextInt(100);
    }

    /**
     * Devuelve el cliente asociado a la guía de envío.
     * 
     * @return Cliente del envío.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la distancia aproximada al destino.
     * 
     * @return Distancia aproximada en kilómetros.
     */
    public double getDistanciaAprox() {
        return distanciaAprox;
    }

    /**
     * Devuelve el destino de la guía de envío.
     * 
     * @return Destino.
     */
    public Destino getDestino() {
        return destino;
    }

    /**
     * Devuelve una representación textual de la guía de envío.
     * 
     * @return Cadena con los datos de la guía.
     */
    @Override
    public String toString() {
        return String.format("Guia[id=%s, dest=%s, dist=%.1fkm, kg=%.2f, costo=USD%.2f]",
                idCaja, destino, distanciaAprox, pesoFacturado, costoEnvio);
    }
}
