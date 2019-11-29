package py.com.eko.fisiocenter.Modelos;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Local implements Serializable, Parcelable
{

    @SerializedName("idLocal")
    @Expose
    private Integer idLocal;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("flagCasaCentral")
    @Expose
    private String flagCasaCentral;
    @SerializedName("cantidadIngreso")
    @Expose
    private Integer cantidadIngreso;
    @SerializedName("anhoMesActual")
    @Expose
    private String anhoMesActual;
    @SerializedName("fechaHoraUltimoIngreso")
    @Expose
    private String fechaHoraUltimoIngreso;
    @SerializedName("minutosSesion")
    @Expose
    private Integer minutosSesion;
    @SerializedName("nombreEmpresa")
    @Expose
    private Object nombreEmpresa;
    @SerializedName("urlImagen")
    @Expose
    private Object urlImagen;
    @SerializedName("secuencia")
    @Expose
    private Object secuencia;
    @SerializedName("pin")
    @Expose
    private Object pin;
    @SerializedName("appMovil")
    @Expose
    private Object appMovil;
    @SerializedName("qr")
    @Expose
    private Object qr;
    @SerializedName("qrSoloEvaluacion")
    @Expose
    private Object qrSoloEvaluacion;
    @SerializedName("moneda")
    @Expose
    private Object moneda;
    @SerializedName("evaluacionItem")
    @Expose
    private Object evaluacionItem;
    @SerializedName("evaluacionLocal")
    @Expose
    private Object evaluacionLocal;
    @SerializedName("habilitarFacebook")
    @Expose
    private Object habilitarFacebook;
    @SerializedName("habilitarDatosManualmente")
    @Expose
    private Object habilitarDatosManualmente;
    @SerializedName("habilitarAnonimo")
    @Expose
    private Object habilitarAnonimo;
    @SerializedName("mostrarPreciosEnAccesoPublico")
    @Expose
    private Object mostrarPreciosEnAccesoPublico;
    @SerializedName("habilitarReserva")
    @Expose
    private Object habilitarReserva;
    @SerializedName("habilitarPedidosEnLocal")
    @Expose
    private Object habilitarPedidosEnLocal;
    @SerializedName("habilitarLlamarAlMozo")
    @Expose
    private Object habilitarLlamarAlMozo;
    @SerializedName("textoLamarAlMozo")
    @Expose
    private Object textoLamarAlMozo;
    @SerializedName("textoRealizarPedido")
    @Expose
    private Object textoRealizarPedido;
    @SerializedName("recurso")
    @Expose
    private Object recurso;
    @SerializedName("flagRequiereAutorizacion")
    @Expose
    private Object flagRequiereAutorizacion;
    @SerializedName("solicitarRucEnPedidos")
    @Expose
    private Object solicitarRucEnPedidos;
    public final static Parcelable.Creator<Local> CREATOR = new Creator<Local>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Local createFromParcel(Parcel in) {
            return new Local(in);
        }

        public Local[] newArray(int size) {
            return (new Local[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2759239463672205467L;

    protected Local(Parcel in) {
        this.idLocal = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nombre = ((String) in.readValue((String.class.getClassLoader())));
        this.flagCasaCentral = ((String) in.readValue((String.class.getClassLoader())));
        this.cantidadIngreso = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.anhoMesActual = ((String) in.readValue((String.class.getClassLoader())));
        this.fechaHoraUltimoIngreso = ((String) in.readValue((String.class.getClassLoader())));
        this.minutosSesion = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nombreEmpresa = ((Object) in.readValue((Object.class.getClassLoader())));
        this.urlImagen = ((Object) in.readValue((Object.class.getClassLoader())));
        this.secuencia = ((Object) in.readValue((Object.class.getClassLoader())));
        this.pin = ((Object) in.readValue((Object.class.getClassLoader())));
        this.appMovil = ((Object) in.readValue((Object.class.getClassLoader())));
        this.qr = ((Object) in.readValue((Object.class.getClassLoader())));
        this.qrSoloEvaluacion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.moneda = ((Object) in.readValue((Object.class.getClassLoader())));
        this.evaluacionItem = ((Object) in.readValue((Object.class.getClassLoader())));
        this.evaluacionLocal = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarFacebook = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarDatosManualmente = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarAnonimo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mostrarPreciosEnAccesoPublico = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarReserva = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarPedidosEnLocal = ((Object) in.readValue((Object.class.getClassLoader())));
        this.habilitarLlamarAlMozo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.textoLamarAlMozo = ((Object) in.readValue((Object.class.getClassLoader())));
        this.textoRealizarPedido = ((Object) in.readValue((Object.class.getClassLoader())));
        this.recurso = ((Object) in.readValue((Object.class.getClassLoader())));
        this.flagRequiereAutorizacion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.solicitarRucEnPedidos = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Local() {
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFlagCasaCentral() {
        return flagCasaCentral;
    }

    public void setFlagCasaCentral(String flagCasaCentral) {
        this.flagCasaCentral = flagCasaCentral;
    }

    public Integer getCantidadIngreso() {
        return cantidadIngreso;
    }

    public void setCantidadIngreso(Integer cantidadIngreso) {
        this.cantidadIngreso = cantidadIngreso;
    }

    public String getAnhoMesActual() {
        return anhoMesActual;
    }

    public void setAnhoMesActual(String anhoMesActual) {
        this.anhoMesActual = anhoMesActual;
    }

    public String getFechaHoraUltimoIngreso() {
        return fechaHoraUltimoIngreso;
    }

    public void setFechaHoraUltimoIngreso(String fechaHoraUltimoIngreso) {
        this.fechaHoraUltimoIngreso = fechaHoraUltimoIngreso;
    }

    public Integer getMinutosSesion() {
        return minutosSesion;
    }

    public void setMinutosSesion(Integer minutosSesion) {
        this.minutosSesion = minutosSesion;
    }

    public Object getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(Object nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Object getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(Object urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Object getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Object secuencia) {
        this.secuencia = secuencia;
    }

    public Object getPin() {
        return pin;
    }

    public void setPin(Object pin) {
        this.pin = pin;
    }

    public Object getAppMovil() {
        return appMovil;
    }

    public void setAppMovil(Object appMovil) {
        this.appMovil = appMovil;
    }

    public Object getQr() {
        return qr;
    }

    public void setQr(Object qr) {
        this.qr = qr;
    }

    public Object getQrSoloEvaluacion() {
        return qrSoloEvaluacion;
    }

    public void setQrSoloEvaluacion(Object qrSoloEvaluacion) {
        this.qrSoloEvaluacion = qrSoloEvaluacion;
    }

    public Object getMoneda() {
        return moneda;
    }

    public void setMoneda(Object moneda) {
        this.moneda = moneda;
    }

    public Object getEvaluacionItem() {
        return evaluacionItem;
    }

    public void setEvaluacionItem(Object evaluacionItem) {
        this.evaluacionItem = evaluacionItem;
    }

    public Object getEvaluacionLocal() {
        return evaluacionLocal;
    }

    public void setEvaluacionLocal(Object evaluacionLocal) {
        this.evaluacionLocal = evaluacionLocal;
    }

    public Object getHabilitarFacebook() {
        return habilitarFacebook;
    }

    public void setHabilitarFacebook(Object habilitarFacebook) {
        this.habilitarFacebook = habilitarFacebook;
    }

    public Object getHabilitarDatosManualmente() {
        return habilitarDatosManualmente;
    }

    public void setHabilitarDatosManualmente(Object habilitarDatosManualmente) {
        this.habilitarDatosManualmente = habilitarDatosManualmente;
    }

    public Object getHabilitarAnonimo() {
        return habilitarAnonimo;
    }

    public void setHabilitarAnonimo(Object habilitarAnonimo) {
        this.habilitarAnonimo = habilitarAnonimo;
    }

    public Object getMostrarPreciosEnAccesoPublico() {
        return mostrarPreciosEnAccesoPublico;
    }

    public void setMostrarPreciosEnAccesoPublico(Object mostrarPreciosEnAccesoPublico) {
        this.mostrarPreciosEnAccesoPublico = mostrarPreciosEnAccesoPublico;
    }

    public Object getHabilitarReserva() {
        return habilitarReserva;
    }

    public void setHabilitarReserva(Object habilitarReserva) {
        this.habilitarReserva = habilitarReserva;
    }

    public Object getHabilitarPedidosEnLocal() {
        return habilitarPedidosEnLocal;
    }

    public void setHabilitarPedidosEnLocal(Object habilitarPedidosEnLocal) {
        this.habilitarPedidosEnLocal = habilitarPedidosEnLocal;
    }

    public Object getHabilitarLlamarAlMozo() {
        return habilitarLlamarAlMozo;
    }

    public void setHabilitarLlamarAlMozo(Object habilitarLlamarAlMozo) {
        this.habilitarLlamarAlMozo = habilitarLlamarAlMozo;
    }

    public Object getTextoLamarAlMozo() {
        return textoLamarAlMozo;
    }

    public void setTextoLamarAlMozo(Object textoLamarAlMozo) {
        this.textoLamarAlMozo = textoLamarAlMozo;
    }

    public Object getTextoRealizarPedido() {
        return textoRealizarPedido;
    }

    public void setTextoRealizarPedido(Object textoRealizarPedido) {
        this.textoRealizarPedido = textoRealizarPedido;
    }

    public Object getRecurso() {
        return recurso;
    }

    public void setRecurso(Object recurso) {
        this.recurso = recurso;
    }

    public Object getFlagRequiereAutorizacion() {
        return flagRequiereAutorizacion;
    }

    public void setFlagRequiereAutorizacion(Object flagRequiereAutorizacion) {
        this.flagRequiereAutorizacion = flagRequiereAutorizacion;
    }

    public Object getSolicitarRucEnPedidos() {
        return solicitarRucEnPedidos;
    }

    public void setSolicitarRucEnPedidos(Object solicitarRucEnPedidos) {
        this.solicitarRucEnPedidos = solicitarRucEnPedidos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idLocal);
        dest.writeValue(nombre);
        dest.writeValue(flagCasaCentral);
        dest.writeValue(cantidadIngreso);
        dest.writeValue(anhoMesActual);
        dest.writeValue(fechaHoraUltimoIngreso);
        dest.writeValue(minutosSesion);
        dest.writeValue(nombreEmpresa);
        dest.writeValue(urlImagen);
        dest.writeValue(secuencia);
        dest.writeValue(pin);
        dest.writeValue(appMovil);
        dest.writeValue(qr);
        dest.writeValue(qrSoloEvaluacion);
        dest.writeValue(moneda);
        dest.writeValue(evaluacionItem);
        dest.writeValue(evaluacionLocal);
        dest.writeValue(habilitarFacebook);
        dest.writeValue(habilitarDatosManualmente);
        dest.writeValue(habilitarAnonimo);
        dest.writeValue(mostrarPreciosEnAccesoPublico);
        dest.writeValue(habilitarReserva);
        dest.writeValue(habilitarPedidosEnLocal);
        dest.writeValue(habilitarLlamarAlMozo);
        dest.writeValue(textoLamarAlMozo);
        dest.writeValue(textoRealizarPedido);
        dest.writeValue(recurso);
        dest.writeValue(flagRequiereAutorizacion);
        dest.writeValue(solicitarRucEnPedidos);
    }

    public int describeContents() {
        return 0;
    }

}
