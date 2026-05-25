package co.edu.poli.Examen1.controlador;

import co.edu.poli.Examen1.model.ExamenMedico;
import co.edu.poli.Examen1.model.ExamenRayosX;
import co.edu.poli.Examen1.servicios.ImplementacionOperacionCRUD;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {

    @FXML
    private TextField txtCodigoExamen;

    @FXML
    private TextField txtNombrePaciente;

    @FXML
    private TextField txtCosto;

    @FXML
    private TextField txtZona;

    @FXML
    private RadioButton rbbajo;

    @FXML
    private RadioButton rbmedio;

    @FXML
    private RadioButton rbalto;

    @FXML
    private ToggleGroup tgNivel;

    @FXML
    private TextArea txtAreaMensajes;

    // SERVICIO CRUD
    private ImplementacionOperacionCRUD crud =
            new ImplementacionOperacionCRUD();

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleGuardar() {

        try {

            String codigo = txtCodigoExamen.getText();
            String nombre = txtNombrePaciente.getText();

            int costo = Integer.parseInt(txtCosto.getText());

            String zona = txtZona.getText();

            String nivel = "";

            if (rbbajo.isSelected()) {
                nivel = "bajo";
            } else if (rbmedio.isSelected()) {
                nivel = "medio";
            } else if (rbalto.isSelected()) {
                nivel = "alto";
            }

            ExamenRayosX rx = new ExamenRayosX(
                    codigo,
                    nombre,
                    "2026",
                    costo,
                    zona,
                    nivel
            );

            String resultado = crud.crear(rx);

            txtAreaMensajes.setText(resultado);

        } catch (NumberFormatException e) {

            txtAreaMensajes.setText(
                    "El costo debe ser numérico"
            );
        }
    }

    @FXML
    private void handleLimpiar() {

        txtCodigoExamen.clear();
        txtNombrePaciente.clear();
        txtCosto.clear();
        txtZona.clear();

        tgNivel.selectToggle(null);

        txtAreaMensajes.clear();
    }

    @FXML
    private void handleSerializar() {

        String mensaje = crud.serializar();

        txtAreaMensajes.setText(mensaje);
    }

    @FXML
    private void handleDeserializar() {

        ExamenMedico ex = crud.deserializar();

        if (ex != null) {

            txtAreaMensajes.setText(
                    "Datos cargados correctamente"
            );

        } else {

            txtAreaMensajes.setText(
                    "No hay datos guardados"
            );
        }
    }

    @FXML
    private void handleListar() {

        ExamenMedico[] lista = crud.getExamenes();

        if (lista.length == 0) {

            txtAreaMensajes.setText(
                    "No existen exámenes registrados"
            );

        } else {

            StringBuilder sb = new StringBuilder();

            for (ExamenMedico ex : lista) {

                sb.append(ex.toString())
                  .append("\n\n");
            }

            txtAreaMensajes.setText(sb.toString());
        }
    }
}