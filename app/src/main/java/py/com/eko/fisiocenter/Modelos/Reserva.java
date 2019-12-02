package py.com.eko.fisiocenter.Modelos;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserva implements Serializable
{

    @SerializedName("marcado")
    @Expose
    private Boolean marcado;

    @SerializedName("idReserva")
    @Expose
    private Integer idReserva;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;
    @SerializedName("horaFin")
    @Expose
    private String horaFin;
    @SerializedName("fechaHoraCreacion")
    @Expose
    private String fechaHoraCreacion;
    @SerializedName("flagEstado")
    @Expose
    private String flagEstado;
    @SerializedName("flagAsistio")
    @Expose
    private Object flagAsistio;
    @SerializedName("observacion")
    @Expose
    private String observacion;
    @SerializedName("idFichaClinica")
    @Expose
    private Object idFichaClinica;
    @SerializedName("idLocal")
    @Expose
    private Local idLocal;
    @SerializedName("Cliente")
    @Expose
    private PersonaShort Cliente;
    @SerializedName("Empleado")
    @Expose
    private PersonaShort Empleado;
    @SerializedName("idCliente")
    @Expose
    private Persona idCliente;
    @SerializedName("idEmpleado")
    @Expose
    private Persona idEmpleado;
    @SerializedName("fechaCadena")
    @Expose
    private String fechaCadena;
    @SerializedName("fechaDesdeCadena")
    @Expose
    private Object fechaDesdeCadena;
    @SerializedName("fechaHastaCadena")
    @Expose
    private Object fechaHastaCadena;
    @SerializedName("horaInicioCadena")
    @Expose
    private String horaInicioCadena;
    @SerializedName("horaFinCadena")
    @Expose
    private String horaFinCadena;



    public Reserva() {
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(String fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getFlagEstado() {
        return flagEstado;
    }

    public void setFlagEstado(String flagEstado) {
        this.flagEstado = flagEstado;
    }

    public Object getFlagAsistio() {
        return flagAsistio;
    }

    public void setFlagAsistio(Object flagAsistio) {
        this.flagAsistio = flagAsistio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Object getIdFichaClinica() {
        return idFichaClinica;
    }

    public void setIdFichaClinica(Object idFichaClinica) {
        this.idFichaClinica = idFichaClinica;
    }

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
    }

    public Persona getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Persona idCliente) {
        this.idCliente = idCliente;
    }

    public Persona getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Persona idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getFechaCadena() {
        return fechaCadena;
    }

    public void setFechaCadena(String fechaCadena) {
        this.fechaCadena = fechaCadena;
    }

    public Object getFechaDesdeCadena() {
        return fechaDesdeCadena;
    }

    public void setFechaDesdeCadena(Object fechaDesdeCadena) {
        this.fechaDesdeCadena = fechaDesdeCadena;
    }

    public Object getFechaHastaCadena() {
        return fechaHastaCadena;
    }

    public void setFechaHastaCadena(Object fechaHastaCadena) {
        this.fechaHastaCadena = fechaHastaCadena;
    }

    public String getHoraInicioCadena() {
        return horaInicioCadena;
    }

    public void setHoraInicioCadena(String horaInicioCadena) {
        this.horaInicioCadena = horaInicioCadena;
    }

    public String getHoraFinCadena() {
        return horaFinCadena;
    }

    public void setHoraFinCadena(String horaFinCadena) {
        this.horaFinCadena = horaFinCadena;
    }

    public Boolean getMarcado() {
        return marcado;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }

    public PersonaShort getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(PersonaShort empleado) {
        Empleado = empleado;
    }

    public PersonaShort getCliente() {
        return Cliente;
    }

    public void setCliente(PersonaShort cliente) {
        Cliente = cliente;
    }
}
