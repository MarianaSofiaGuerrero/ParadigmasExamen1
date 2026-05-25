package co.edu.poli.Examen1.model;

public class ExamenRayosX extends ExamenMedico {

    private String zonacuerpo;
    private String nivelradiacion;

    public ExamenRayosX() {
    }

    public ExamenRayosX(String codigo_identificacion,
                        String nombre,
                        String fecha_realizacion,
                        int costo_del_procedimiento,
                        String zonacuerpo,
                        String nivelradiacion) {

        super(codigo_identificacion,
              nombre,
              fecha_realizacion,
              costo_del_procedimiento);

        this.zonacuerpo = zonacuerpo;
        this.nivelradiacion = nivelradiacion;
    }

    public String getZonacuerpo() {
        return zonacuerpo;
    }

    public void setZonacuerpo(String zonacuerpo) {
        this.zonacuerpo = zonacuerpo;
    }

    public String getNivelradiacion() {
        return nivelradiacion;
    }

    public void setNivelradiacion(String nivelradiacion) {
        this.nivelradiacion = nivelradiacion;
    }

    @Override
    public double calcularcosto() {

        double factor;

        switch (nivelradiacion.toLowerCase()) {

            case "alto":
                factor = 1.5;
                break;

            case "medio":
                factor = 1.2;
                break;

            default:
                factor = 1.0;
                break;
        }

        return getCosto_del_procedimiento() * factor;
    }

    @Override
    public String toString() {

        return super.toString() +
               " | Zona del cuerpo: " + zonacuerpo +
               " | Nivel radiación: " + nivelradiacion +
               " | Costo total: " + calcularcosto();
    }
}