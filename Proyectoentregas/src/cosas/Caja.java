package cosas;

/**
 * Clase que representa una caja con dimensiones, peso real y peso volumétrico.
 * Puede estar asociada a una guía de envío que incluye información sobre el destino,
 * distancia de entrega y cliente.
 * 
 * El peso volumétrico se calcula con la fórmula: 
 * (longitud * altura * ancho) / 5000, siguiendo estándares de paquetería.
 * El peso facturado es el mayor entre el peso real y el peso volumétrico.
 * 
 * @author piped
 */
public class Caja {
    private double altura, longitud, ancho, peso;
    private double pesoVolumetrico;
    private GuiaEnvio guia;

    /**
     * Crea una nueva caja con las dimensiones y peso real especificados.
     * 
     * @param alt Altura de la caja (en cm).
     * @param lon Longitud de la caja (en cm).
     * @param anc Ancho de la caja (en cm).
     * @param pesoReal Peso real de la caja (en kg).
     */
    public Caja(double alt, double lon, double anc, double pesoReal) {
        this.altura = alt;
        this.longitud = lon;
        this.ancho = anc;
        this.peso = pesoReal;
        calcularPesoVolumetrico();
    }

    /**
     * Calcula el peso volumétrico de la caja usando la fórmula:
     * (longitud * altura * ancho) / 5000.
     */
    private void calcularPesoVolumetrico() {
        this.pesoVolumetrico = (longitud * altura * ancho) / 5000.0;
    }

    /**
     * Devuelve el peso volumétrico calculado de la caja.
     * 
     * @return Peso volumétrico en kilogramos.
     */
    public double getPesoVolumetrico() {
        return pesoVolumetrico;
    }

    /**
     * Devuelve el peso que se facturará por la caja,
     * que corresponde al mayor entre el peso real y el volumétrico.
     * 
     * @return Peso facturable en kilogramos.
     */
    public double getPesoFacturado() {
        return Math.max(peso, pesoVolumetrico);
    }

    /**
     * Asigna una guía de envío a la caja.
     * 
     * @param guia Objeto de tipo GuiaEnvio que contiene detalles del envío.
     */
    public void setGuia(GuiaEnvio guia) {
        this.guia = guia;
    }

    /**
     * Devuelve la guía de envío asociada a la caja.
     * 
     * @return La guía de envío asignada.
     */
    public GuiaEnvio getGuia() {
        return guia;
    }

    /**
     * Representación en texto de la caja, incluyendo peso volumétrico, 
     * peso real, destino, distancia y cliente.
     * 
     * @return Cadena de texto representando la caja.
     */
    @Override
    public String toString() {
        return String.format("Caja[vol=%.2f kg, real=%.2f kg, dest=%s, dist=%.1fkm cliente=%s]",
                pesoVolumetrico, peso, guia.getDestino(), guia.getDistanciaAprox(), guia.getCliente().getNombre());
    }
}
