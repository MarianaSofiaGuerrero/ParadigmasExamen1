package co.edu.poli.Examen1.model;

public abstract class ExamenMedico {

    private String codigo_identificacion;
    private String nombre;
    private String fecha_realizacion;
    private int costo_del_procedimiento;

    public ExamenMedico() {
    }

    public ExamenMedico(String codigo_identificacion,
                        String nombre,
                        String fecha_realizacion,
                        int costo_del_procedimiento) {

        this.codigo_identificacion = codigo_identificacion;
        this.nombre = nombre;
        this.fecha_realizacion = fecha_realizacion;
        this.costo_del_procedimiento = costo_del_procedimiento;
    }

    public String getCodigo_identificacion() {
        return codigo_identificacion;
    }

    public void setCodigo_identificacion(String codigo_identificacion) {
        this.codigo_identificacion = codigo_identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_realizacion() {
        return fecha_realizacion;
    }

    public void setFecha_realizacion(String fecha_realizacion) {
        this.fecha_realizacion = fecha_realizacion;
    }

    public int getCosto_del_procedimiento() {
        return costo_del_procedimiento;
    }

    public void setCosto_del_procedimiento(int costo_del_procedimiento) {
        this.costo_del_procedimiento = costo_del_procedimiento;
    }

    public abstract double calcularcosto();

    @Override
    public String toString() {

        return "Código: " + codigo_identificacion +
               " | Paciente: " + nombre +
               " | Fecha: " + fecha_realizacion +
               " | Costo base: " + costo_del_procedimiento;
    }
}