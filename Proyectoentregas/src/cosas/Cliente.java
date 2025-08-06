package cosas;

import java.util.Random;

/**
 * Clase que representa un cliente con datos personales como identificación, 
 * nombre, teléfono, correo electrónico y dirección.
 * Puede crearse con datos específicos o de forma aleatoria.
 * 
 * La generación aleatoria utiliza listas predefinidas de nombres, apellidos y calles.
 * 
 * @author piped
 */
public class Cliente {
    private String id, nombre, telefono, email, direccion;

    /**
     * Constructor que crea un cliente con datos específicos.
     * 
     * @param id Identificación única del cliente.
     * @param nombre Nombre completo del cliente.
     * @param tel Número de teléfono.
     * @param email Dirección de correo electrónico.
     * @param dir Dirección física del cliente.
     */
    public Cliente(String id, String nombre, String tel, String email, String dir) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = tel;
        this.email = email;
        this.direccion = dir;
    }

    /**
     * Constructor que genera un cliente con datos aleatorios si el parámetro es verdadero.
     * 
     * @param aleatorio Indica si se deben generar datos aleatorios.
     */
    public Cliente(boolean aleatorio) {
        if (aleatorio) {
            String[] nombres = {
                "Camila", "Santiago", "Valentina", "Mateo", "Laura",
                "Juan", "Sara", "Andres", "Isabella", "Felipe",
                "Daniela", "Carlos", "Lucia", "Sebastian", "Mariana"
            };
            String[] apellidos = {
                "Gomez", "Rodriguez", "Martinez", "Perez", "Garcia",
                "Lopez", "Hernandez", "Torres", "Ramirez", "Moreno",
                "Vargas", "Castaño", "Rincon", "Navarro", "Ortiz"
            };
            String[] calles = {
                "Calle 26", "Carrera 7", "Avenida Caracas", "Calle 80",
                "Carrera 30", "Avenida Boyaca", "Transversal 93", 
                "Diagonal 40", "Calle 100", "Carrera 13",
                "Calle 72", "Carrera 15", "Avenida Suba", "Calle 116", "Carrera 68"
            };

            this.nombre = nombres[(int)(Math.random() * nombres.length)] + " " +
                          apellidos[(int)(Math.random() * apellidos.length)];
            this.id = "C" + (int)(Math.random() * 10000);
            this.telefono = "3" + (int)(Math.random() * 1000000000);
            this.email = this.nombre.toLowerCase().replace(" ", ".") + "@correo.com";
            this.direccion = calles[(int)(Math.random() * calles.length)] + 
                             " #" + ((int)(Math.random() * 100) + 1);
        }
    }

    /**
     * Obtiene la identificación del cliente.
     * 
     * @return ID del cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna una nueva identificación al cliente.
     * 
     * @param id Nueva identificación.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return Nombre completo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * 
     * @return Teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * 
     * @param telefono Nuevo teléfono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la dirección de correo electrónico del cliente.
     * 
     * @return Email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     * 
     * @param email Nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la dirección física del cliente.
     * 
     * @return Dirección.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece una nueva dirección física para el cliente.
     * 
     * @param direccion Nueva dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Devuelve una representación en texto del cliente con todos sus datos.
     * 
     * @return Cadena representando al cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + ", direccion=" + direccion + '}';
    }
}
