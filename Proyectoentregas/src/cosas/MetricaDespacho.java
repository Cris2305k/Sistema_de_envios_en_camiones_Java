/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cosas;

import tdas.Bag;

/**
 * Clase que permite llevar un registro de métricas asociadas al despacho de cajas.
 * Las métricas que se almacenan incluyen: peso volumétrico total, peso facturado total
 * y el número total de cajas despachadas. Utiliza una bolsa (Bag) genérica para almacenar los valores.
 * 
 * Los datos se agregan y luego se clasifican por tipo para su cálculo total.
 * 
 * @author piped
 */
public class MetricaDespacho {

    /**
     * Bolsa genérica que almacena objetos de tipo Double, Float e Integer para representar
     * peso volumétrico, peso facturado y número de cajas respectivamente.
     */
    private Bag<Object> metricaBag;

    /**
     * Constructor por defecto que inicializa la bolsa de métricas.
     */
    public MetricaDespacho() {
        metricaBag = new Bag<>();
    }

    /**
     * Agrega un peso volumétrico al registro.
     *
     * @param pesoVol Peso volumétrico a agregar (tipo double).
     */
    public void agregarPesoVolumetrico(double pesoVol) {
        metricaBag.add(pesoVol);
    }

    /**
     * Agrega un peso facturado al registro.
     *
     * @param pesoFacturado Peso facturado a agregar (tipo float).
     */
    public void agregarPesoFacturado(float pesoFacturado) {
        metricaBag.add(pesoFacturado);
    }

    /**
     * Agrega el número de cajas despachadas al registro.
     *
     * @param numCajas Número de cajas (tipo int).
     */
    public void agregarNumeroCajas(int numCajas) {
        metricaBag.add(numCajas);
    }

    /**
     * Calcula el peso volumétrico total sumando todos los valores de tipo Double en la bolsa.
     *
     * @return Suma total del peso volumétrico.
     */
    public double obtenerPesoVolumetricoTotal() {
        double total = 0;
        for (Object obj : metricaBag) {
            if (obj instanceof Double) {
                total += (Double) obj;
            }
        }
        return total;
    }

    /**
     * Calcula el peso facturado total sumando todos los valores de tipo Float en la bolsa.
     *
     * @return Suma total del peso facturado.
     */
    public double obtenerPesoFacturadoTotal() {
        double total = 0;
        for (Object obj : metricaBag) {
            if (obj instanceof Float) {
                total += (Float) obj;
            }
        }
        return total;
    }

    /**
     * Calcula el número total de cajas despachadas sumando todos los valores de tipo Integer en la bolsa.
     *
     * @return Suma total del número de cajas.
     */
    public int obtenerNumeroCajasTotal() {
        int total = 0;
        for (Object obj : metricaBag) {
            if (obj instanceof Integer) {
                total += (Integer) obj;
            }
        }
        return total;
    }

    /**
     * Devuelve una representación textual de las métricas de despacho.
     *
     * @return Cadena con los valores de las métricas almacenadas.
     */
    @Override
    public String toString() {
        return "Metricas de Despacho: " +
                "\nPeso Volumetrico Total: " + obtenerPesoVolumetricoTotal() +
                "\nPeso Facturado Total: " + obtenerPesoFacturadoTotal() +
                "\nNumero Total de Cajas Despachadas: " + obtenerNumeroCajasTotal();
    }
}
