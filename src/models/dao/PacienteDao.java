package models.dao;

import models.dto.Paciente;
import java.util.ArrayList;
import java.sql.SQLException;

public class PacienteDao extends SQLite {

    public ArrayList<Paciente> getPacientes() {

        try {

            conexion = getConexion();

            sql = "SELECT * FROM paciente ORDER BY CAST(cedula AS UNSIGNED)";

            sentencia = conexion.createStatement();

            resultado = sentencia.executeQuery(sql);

            ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

            while (resultado.next()) {

                Paciente paciente = new Paciente();

                paciente.setEdad(resultado.getInt("edad"));
                paciente.setEps(resultado.getString("eps"));
                paciente.setCedula(resultado.getString("cedula"));
                paciente.setCiudad(resultado.getString("ciudad"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setEnfermedad(resultado.getString("enfermedad"));

                pacientes.add(paciente);
            }

            return pacientes;

        } catch (SQLException e) {

            System.out.println("Error al buscar pacientes");
            System.out.println(e.getMessage());

        } finally {

            try {
                sentencia.close();
                conexion.close();
            }

            catch (SQLException e) {

                System.out.println("Error al cerrar la conexión");
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    public boolean getPaciente(Paciente paciente) {

        try {

            conexion = getConexion();

            sql = "SELECT * FROM paciente WHERE cedula=?";

            preparada = conexion.prepareStatement(sql);

            preparada.setString(1, paciente.getCedula());

            resultado = preparada.executeQuery();

            if (resultado.next()) {

                System.out.println("Paciente encontrado");
                
                paciente.setEdad(resultado.getInt("edad"));
                paciente.setEps(resultado.getString("eps"));
                paciente.setCedula(resultado.getString("cedula"));
                paciente.setCiudad(resultado.getString("ciudad"));
                paciente.setNombre(resultado.getString("nombre"));
                paciente.setEnfermedad(resultado.getString("enfermedad"));

                return true;
            }

            System.out.println("Cedula no registrada");
            
            return false;

        } catch (SQLException e) {

            System.out.println("Error al buscar paciente");
            System.out.println(e.getMessage());

            return false;

        } finally {

            try {
                preparada.close();
                conexion.close();
            }

            catch (SQLException e) {

                System.out.println("Error al cerrar la conexión");
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean insertPaciente(Paciente paciente) {

        try {

            conexion = getConexion();

            sql = "INSERT INTO paciente(cedula, nombre, edad, ciudad, eps, enfermedad) VALUES(?, ?, ?, ?, ?, ?)";

            preparada = conexion.prepareStatement(sql);

            preparada.setInt(3, paciente.getEdad());
            preparada.setString(5, paciente.getEps());
            preparada.setString(1, paciente.getCedula());
            preparada.setString(4, paciente.getCiudad());
            preparada.setString(2, paciente.getNombre());
            preparada.setString(6, paciente.getEnfermedad());

            preparada.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar los datos");
            System.out.println(e.getMessage());

            return false;

        } finally {

            try {
                preparada.close();
                conexion.close();
            }

            catch (SQLException e) {

                System.out.println("Error al cerrar la conexión");
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean updatePaciente(Paciente paciente) {

        try {

            conexion = getConexion();

            sql = "UPDATE paciente SET nombre=?, edad=?, ciudad=?, eps=?, enfermedad=? WHERE cedula=?";

            preparada = conexion.prepareStatement(sql);

            preparada.setInt(2, paciente.getEdad());
            preparada.setString(4, paciente.getEps());
            preparada.setString(6, paciente.getCedula());
            preparada.setString(3, paciente.getCiudad());
            preparada.setString(1, paciente.getNombre());
            preparada.setString(5, paciente.getEnfermedad());

            preparada.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al actualizar los datos");
            System.out.println(e.getMessage());

            return false;

        } finally {

            try {
                preparada.close();
                conexion.close();
            } 

            catch (SQLException e) {

                System.out.println("Error al cerrar la conexión");
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean deletePaciente(Paciente paciente) {

        try {

            conexion = getConexion();

            sql = "DELETE FROM paciente WHERE cedula=?";

            preparada = conexion.prepareStatement(sql);

            preparada.setString(1, paciente.getCedula());

            preparada.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al eliminar paciente");
            System.out.println(e.getMessage());

            return false;

        } finally {

            try {
                preparada.close();
                conexion.close();
            } 

            catch (SQLException e) {

                System.out.println("Error al cerrar la conexión");
                System.out.println(e.getMessage());
            }
        }
    }
}
