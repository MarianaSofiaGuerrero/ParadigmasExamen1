package co.edu.poli.Examen1.servicios;

import co.edu.poli.Examen1.model.ExamenMedico;
import co.edu.poli.Examen1.model.ExamenRayosX;


import java.io.*;
import java.util.Arrays;

public class ImplementacionOperacionCRUD implements OperacionCRUD, OperacionArchivo {

    private static final int MAX = 100;
    private static final String ARCHIVO_TXT = "examenes_rayosx.txt";

    private ExamenMedico[] examenMedico = new ExamenMedico[MAX];
    private int count = 0;

    @Override
    public String crear(ExamenMedico examen) {

        if (count >= MAX) {
            return "Error: arreglo lleno";
        }

        if (consultar(examen.getCodigo_identificacion()) != null) {
            return "Error: ya existe un examen con ese código";
        }

        examenMedico[count] = examen;
        count++;

        return "Examen registrado correctamente";
    }

    @Override
    public ExamenMedico consultar(String id) {

        for (int i = 0; i < count; i++) {

            if (examenMedico[i].getCodigo_identificacion().equals(id)) {
                return examenMedico[i];
            }
        }

        return null;
    }

    @Override
    public boolean actualizar(String id, ExamenMedico nuevo) {

        for (int i = 0; i < count; i++) {

            if (examenMedico[i].getCodigo_identificacion().equals(id)) {

                examenMedico[i] = nuevo;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(String id) {

        for (int i = 0; i < count; i++) {

            if (examenMedico[i].getCodigo_identificacion().equals(id)) {

                for (int j = i; j < count - 1; j++) {
                    examenMedico[j] = examenMedico[j + 1];
                }

                examenMedico[count - 1] = null;
                count--;

                return true;
            }
        }

        return false;
    }

    public ExamenMedico[] getExamenes() {
        return Arrays.copyOf(examenMedico, count);
    }

    @Override
    public String serializar() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_TXT))) {

            for (int i = 0; i < count; i++) {

                ExamenMedico ex = examenMedico[i];

                String linea =
                        ex.getCodigo_identificacion() + "|" +
                        ex.getNombre() + "|" +
                        ex.getFecha_realizacion() + "|" +
                        ex.getCosto_del_procedimiento();

                if (ex instanceof ExamenRayosX) {

                    ExamenRayosX rx = (ExamenRayosX) ex;

                    linea += "|" +
                             rx.getZonacuerpo() + "|" +
                             rx.getNivelradiacion();
                }

                pw.println(linea);
            }

            return "Datos guardados correctamente en el archivo";

        } catch (IOException e) {

            return "Error al serializar: " + e.getMessage();
        }
    }

    @Override
    public ExamenMedico deserializar() {

        File file = new File(ARCHIVO_TXT);

        if (!file.exists()) {
            return null;
        }

        count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linea;
            ExamenMedico primero = null;

            while ((linea = br.readLine()) != null && count < MAX) {

                String[] p = linea.split("\\|");

                if (p.length >= 6) {

                    ExamenRayosX rx = new ExamenRayosX(
                            p[0],
                            p[1],
                            p[2],
                            Integer.parseInt(p[3]),
                            p[4],
                            p[5]
                    );

                    examenMedico[count] = rx;
                    count++;

                    if (primero == null) {
                        primero = rx;
                    }
                }
            }

            return primero;

        } catch (IOException | NumberFormatException e) {

            System.out.println("Error al leer archivo");
            return null;
        }
    }
}