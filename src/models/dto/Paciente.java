package models.dto;

import java.util.ArrayList;

public class Paciente extends Persona {
    
    private String enfermedad, eps;

    public String getEps() { return eps; }
    public String getEnfermedad() { return enfermedad; }

    public void setEps(String eps) { this.eps = eps; }
    public void setEnfermedad(String enfermedad) { this.enfermedad = enfermedad; }

    public String clasificarEdad() {
    
        int edad = getEdad();
        
        if (edad >= 21 && edad <= 30) return "Joven adulto";
        else if (edad > 30 && edad <= 60) return "Adulto";

        return "Tercera edad";
    }

    public static String getCiudad(ArrayList<Paciente> pacientes) {

        if (pacientes.size() > 0) {
            
            ArrayList<String> ciudades = new ArrayList<String>(pacientes.
            stream().map(paciente -> paciente.getCiudad()).distinct().toList());
            
            int longitud = ciudades.size();
            int[] conteos = new int[longitud];
            
            pacientes.forEach(paciente -> conteos[ciudades.indexOf(paciente.getCiudad())]++);
    
            int maximo = conteos[0];
            String ciudad = ciudades.get(0);
    
            for (int i = 1; i < longitud; i++) {
    
                if (conteos[i] > maximo) {
                    
                    maximo = conteos[i];
                    ciudad = ciudades.get(i);
                }
            }

            return ciudad;
        }
        
        return null;
    }

    @Override public String toString() { return eps + " " + enfermedad; }
    @Override public String getDatos() { return String.join(" ", super.toString(), this.toString()); }
}
