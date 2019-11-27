package py.com.eko.fisiocenter.models;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reserva implements Serializable, Parcelable
{

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
    private Integer idLocal;
    @SerializedName("idCliente")
    @Expose
    private Integer idCliente;
    @SerializedName("idEmpleado")
    @Expose
    private Integer idEmpleado;
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
    public final static Parcelable.Creator<Reserva> CREATOR = new Creator<Reserva>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Reserva createFromParcel(Parcel in) {
            return new Reserva(in);
        }

        public Reserva[] newArray(int size) {
            return (new Reserva[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3313912433718721402L;

    protected Reserva(Parcel in) {
        this.idReserva = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.fecha = ((String) in.readValue((String.class.getClassLoader())));
        this.horaInicio = ((String) in.readValue((String.class.getClassLoader())));
        this.horaFin = ((String) in.readValue((String.class.getClassLoader())));
        this.fechaHoraCreacion = ((String) in.readValue((String.class.getClassLoader())));
        this.flagEstado = ((String) in.readValue((String.class.getClassLoader())));
        this.flagAsistio = ((Object) in.readValue((Object.class.getClassLoader())));
        this.observacion = ((String) in.readValue((String.class.getClassLoader())));
        this.idFichaClinica = ((Object) in.readValue((Object.class.getClassLoader())));
        this.idLocal = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idCliente = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.idEmpleado = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.fechaCadena = ((String) in.readValue((String.class.getClassLoader())));
        this.fechaDesdeCadena = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fechaHastaCadena = ((Object) in.readValue((Object.class.getClassLoader())));
        this.horaInicioCadena = ((String) in.readValue((String.class.getClassLoader())));
        this.horaFinCadena = ((String) in.readValue((String.class.getClassLoader())));
    }

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

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idReserva);
        dest.writeValue(fecha);
        dest.writeValue(horaInicio);
        dest.writeValue(horaFin);
        dest.writeValue(fechaHoraCreacion);
        dest.writeValue(flagEstado);
        dest.writeValue(flagAsistio);
        dest.writeValue(observacion);
        dest.writeValue(idFichaClinica);
        dest.writeValue(idLocal);
        dest.writeValue(idCliente);
        dest.writeValue(idEmpleado);
        dest.writeValue(fechaCadena);
        dest.writeValue(fechaDesdeCadena);
        dest.writeValue(fechaHastaCadena);
        dest.writeValue(horaInicioCadena);
        dest.writeValue(horaFinCadena);
    }

    public int describeContents() {
        return 0;
    }

}
