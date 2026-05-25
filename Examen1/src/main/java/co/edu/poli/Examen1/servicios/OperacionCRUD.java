package co.edu.poli.Examen1.servicios;

import co.edu.poli.Examen1.model.ExamenMedico;

public interface OperacionCRUD {

    String crear(ExamenMedico examenMedico);

    ExamenMedico consultar(String id);

    boolean actualizar(String id, ExamenMedico examenMedico);

    boolean eliminar(String id);
}