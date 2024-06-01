package controllers;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import models.dto.Paciente;
import models.dao.PacienteDao;

public class CrudController implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        paciente = new Paciente();
        pacienteDao = new PacienteDao();
    }

    private Paciente paciente;
    private PacienteDao pacienteDao;

    @FXML private TextField txtEps;
    @FXML private TextField txtEdad;
    @FXML private TextField txtBuscar;
    @FXML private TextField txtCedula;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtNombre;
    @FXML private TextField txtEnfermedad;

    @FXML private TextField _txtEps;
    @FXML private TextField _txtEdad;
    @FXML private TextField _txtCedula;
    @FXML private TextField _txtCiudad;
    @FXML private TextField _txtNombre;
    @FXML private TextField _txtEnfermedad;

    @FXML private TextArea taObtener;
    @FXML private TextArea taProcesar;

    @FXML private Button btnBuscar;
    @FXML private Button btnEditar;
    @FXML private Button btnObtener;
    @FXML private Button btnEliminar;
    @FXML private Button btnIngresar;
    @FXML private Button btnProcesar;

    @FXML void onBuscar(ActionEvent event) {

        if (txtBuscar.getText().isBlank()) JOptionPane.showMessageDialog(null, "Olvido ingresar la cedula", "Complete el campo", 2);
        
        else {

            paciente.setCedula(txtBuscar.getText());

            if (pacienteDao.getPaciente(paciente)) {
    
                _txtEps.setText(paciente.getEps());
                _txtCedula.setText(paciente.getCedula());
                _txtCiudad.setText(paciente.getCiudad());
                _txtNombre.setText(paciente.getNombre());
                _txtEnfermedad.setText(paciente.getEnfermedad());
                _txtEdad.setText(String.valueOf(paciente.getEdad()));
        
            } else {

                JOptionPane.showMessageDialog(null, "No se registra paciente con cedula " + paciente.getCedula(), "Consulta fallida", 0);
                limpiarEdicion();
            }
        }
    }

    @FXML void onEditar(ActionEvent event) {

        if (pruebaEdicion()) {

            cargarModelo();
    
            if (pacienteDao.updatePaciente(paciente)) JOptionPane.showMessageDialog(null, "Los datos del paciente han sido actualizados", "Consulta exitosa", 1);
            else JOptionPane.showMessageDialog(null, "No fue posible actualizar los datos del paciente", "Consulta fallida", 0);
        
        } else JOptionPane.showMessageDialog(null, "Primero proceda a consultar el paciente", "Advertencia", 2);

        limpiarEdicion();
    }

    @FXML void onEliminar(ActionEvent event) {

        if (pruebaEdicion()) {

            cargarModelo();
            
            if (pacienteDao.deletePaciente(paciente)) JOptionPane.showMessageDialog(null, "El paciente con cedula " + paciente.getCedula() + " ha sido eliminado", "Consulta exitosa", 1);
            else JOptionPane.showMessageDialog(null, "No fue posible eliminar el paciente", "Consulta fallida", 0);

        } else JOptionPane.showMessageDialog(null, "Primero proceda a consultar el paciente", "Advertencia", 2);

        limpiarEdicion();
    }

    @FXML void onIngresar(ActionEvent event) {

        if (pruebaRegistro()) {

            paciente.setEps(txtEps.getText());
            paciente.setCedula(txtCedula.getText());
            paciente.setCiudad(txtCiudad.getText());
            paciente.setNombre(txtNombre.getText());
            paciente.setEnfermedad(txtEnfermedad.getText());
            paciente.setEdad(Integer.parseInt(txtEdad.getText()));
    
            if (pacienteDao.insertPaciente(paciente)) JOptionPane.showMessageDialog(null, "El Paciente ha sido agregado correctamente", "Consulta exitosa", 1);
            else JOptionPane.showMessageDialog(null, "Ya existe un paciente registrado con esa cedula", "Consulta fallida", 0);

            limpiarRegistro();

        } else JOptionPane.showMessageDialog(null, "Uno o mas campos estan sin completar", "Complete los campos", 2);
    }

    @FXML void onObtener(ActionEvent event) {

        ArrayList<Paciente> pacientes = pacienteDao.getPacientes();

        taObtener.setText("");

        pacientes.forEach((paciente) -> taObtener.setText(
        taObtener.getText() + paciente.getDatos() + "\n"));
    }

    @FXML void onProcesar(ActionEvent event) {

        ArrayList<Paciente> pacientes = pacienteDao.getPacientes();

        taProcesar.setText(Paciente.getCiudad(pacientes));

        pacientes.forEach(paciente -> taProcesar.setText(taProcesar.getText()
        + "\n" + paciente.getCedula() + " " + paciente.clasificarEdad()));
    }

    private void cargarModelo() {

        paciente.setEps(_txtEps.getText());
        paciente.setCedula(_txtCedula.getText());
        paciente.setCiudad(_txtCiudad.getText());
        paciente.setNombre(_txtNombre.getText());
        paciente.setEnfermedad(_txtEnfermedad.getText());
        paciente.setEdad(Integer.parseInt(_txtEdad.getText()));
    }

    private void limpiarRegistro() {

        txtEps.setText("");
        txtEdad.setText("");
        txtCedula.setText("");
        txtCiudad.setText("");
        txtNombre.setText("");
        txtEnfermedad.setText("");
    }

    private void limpiarEdicion() {

        _txtEps.setText("");
        _txtEdad.setText("");
        _txtCedula.setText("");
        _txtCiudad.setText("");
        _txtNombre.setText("");
        _txtEnfermedad.setText("");
    }

    private boolean pruebaRegistro() {

        return prueba(new TextField[] { 
            txtEps, txtEdad,
            txtCedula, txtCiudad,
            txtNombre, txtEnfermedad 
        });
    }

    private boolean pruebaEdicion() {

        return prueba(new TextField[] { 
            _txtEps, _txtEdad, 
            _txtCedula, _txtCiudad, 
            _txtNombre, _txtEnfermedad 
        });
    }

    private boolean prueba(TextField[] campos) {
        for (TextField campo : campos) if (campo.getText().isBlank()) return false; return true;
    }
}
