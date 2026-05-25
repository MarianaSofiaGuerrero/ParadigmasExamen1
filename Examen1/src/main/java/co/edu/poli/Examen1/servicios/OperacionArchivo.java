package co.edu.poli.Examen1.servicios;

import co.edu.poli.Examen1.model.ExamenMedico;

public interface OperacionArchivo {

    String serializar();

    ExamenMedico deserializar();
}