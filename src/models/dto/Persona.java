package models.dto;

public abstract class Persona {

    private int edad;
    private String cedula, ciudad, nombre;
    
    public int getEdad() { return edad; }
    public String getCedula() { return cedula; }
    public String getCiudad() { return ciudad; }
    public String getNombre() { return nombre; }
    
    public void setEdad(int edad) { this.edad = edad; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setNombre(String nombre) { this.nombre = nombre; }
   
    @Override public String toString() { return String.join(" ", nombre, cedula, String.valueOf(edad), ciudad); }

    public abstract String getDatos();
}
