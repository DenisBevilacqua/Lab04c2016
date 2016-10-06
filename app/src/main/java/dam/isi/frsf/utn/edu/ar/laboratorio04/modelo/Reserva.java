package dam.isi.frsf.utn.edu.ar.laboratorio04.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mdominguez on 22/09/16.
 */
public class Reserva implements Serializable {

    private Integer id;
    private String fechaInicio;
    private String fechaFin;
    private Departamento departamento;
    private Double precio;
    private Usuario usuario;
    private Boolean confirmada;

    public Reserva(){}

    public Reserva(Integer id, String fechaInicio, String fechaFin, Departamento departamento) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.departamento = departamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Departamento  getAlojamiento() {
        return departamento;
    }

    public void setAlojamiento(Departamento  alojamiento) {
        this.departamento = alojamiento;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;


    }


    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }

}
