package py.com.eko.fisiocenter.Modelos;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Persona implements Serializable, Parcelable
{

    @SerializedName("idPersona")
    @Expose
    private Integer idPersona;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telefono")
    @Expose
    private Object telefono;
    @SerializedName("seguroMedico")
    @Expose
    private Object seguroMedico;
    @SerializedName("seguroMedicoNumero")
    @Expose
    private Object seguroMedicoNumero;
    @SerializedName("ruc")
    @Expose
    private Object ruc;
    @SerializedName("cedula")
    @Expose
    private String cedula;
    @SerializedName("tipoPersona")
    @Expose
    private String tipoPersona;
    @SerializedName("usuarioLogin")
    @Expose
    private String usuarioLogin;
    @SerializedName("flagVendedor")
    @Expose
    private String flagVendedor;
    @SerializedName("observacion")
    @Expose
    private Object observacion;
    @SerializedName("tipoCliente")
    @Expose
    private String tipoCliente;
    @SerializedName("fechaHoraAprobContrato")
    @Expose
    private String fechaHoraAprobContrato;
    @SerializedName("soloUsuariosDelSistema")
    @Expose
    private Object soloUsuariosDelSistema;
    @SerializedName("nombreCompleto")
    @Expose
    private String nombreCompleto;
    @SerializedName("limiteCredito")
    @Expose
    private Double limiteCredito;
    @SerializedName("fechaNacimiento")
    @Expose
    private Object fechaNacimiento;
    @SerializedName("soloProximosCumpleanhos")
    @Expose
    private Object soloProximosCumpleanhos;
    @SerializedName("todosLosCampos")
    @Expose
    private Object todosLosCampos;
    public final static Parcelable.Creator<Persona> CREATOR = new Creator<Persona>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        public Persona[] newArray(int size) {
            return (new Persona[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5097504403749082700L;

    protected Persona(Parcel in) {
        this.idPersona = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.nombre = ((String) in.readValue((String.class.getClassLoader())));
        this.apellido = ((String) in.readValue((String.class.getClassLoader())));
        this.email = ((String) in.readValue((String.class.getClassLoader())));
        this.telefono = ((Object) in.readValue((Object.class.getClassLoader())));
        this.seguroMedico = ((Object) in.readValue((Object.class.getClassLoader())));
        this.seguroMedicoNumero = ((Object) in.readValue((Object.class.getClassLoader())));
        this.ruc = ((Object) in.readValue((Object.class.getClassLoader())));
        this.cedula = ((String) in.readValue((String.class.getClassLoader())));
        this.tipoPersona = ((String) in.readValue((String.class.getClassLoader())));
        this.usuarioLogin = ((String) in.readValue((String.class.getClassLoader())));
        this.flagVendedor = ((String) in.readValue((String.class.getClassLoader())));
        this.observacion = ((Object) in.readValue((Object.class.getClassLoader())));
        this.tipoCliente = ((String) in.readValue((String.class.getClassLoader())));
        this.fechaHoraAprobContrato = ((String) in.readValue((String.class.getClassLoader())));
        this.soloUsuariosDelSistema = ((Object) in.readValue((Object.class.getClassLoader())));
        this.nombreCompleto = ((String) in.readValue((String.class.getClassLoader())));
        this.limiteCredito = ((Double) in.readValue((Double.class.getClassLoader())));
        this.fechaNacimiento = ((Object) in.readValue((Object.class.getClassLoader())));
        this.soloProximosCumpleanhos = ((Object) in.readValue((Object.class.getClassLoader())));
        this.todosLosCampos = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Persona() {
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getTelefono() {
        return telefono;
    }

    public void setTelefono(Object telefono) {
        this.telefono = telefono;
    }

    public Object getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(Object seguroMedico) {
        this.seguroMedico = seguroMedico;
    }

    public Object getSeguroMedicoNumero() {
        return seguroMedicoNumero;
    }

    public void setSeguroMedicoNumero(Object seguroMedicoNumero) {
        this.seguroMedicoNumero = seguroMedicoNumero;
    }

    public Object getRuc() {
        return ruc;
    }

    public void setRuc(Object ruc) {
        this.ruc = ruc;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getFlagVendedor() {
        return flagVendedor;
    }

    public void setFlagVendedor(String flagVendedor) {
        this.flagVendedor = flagVendedor;
    }

    public Object getObservacion() {
        return observacion;
    }

    public void setObservacion(Object observacion) {
        this.observacion = observacion;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getFechaHoraAprobContrato() {
        return fechaHoraAprobContrato;
    }

    public void setFechaHoraAprobContrato(String fechaHoraAprobContrato) {
        this.fechaHoraAprobContrato = fechaHoraAprobContrato;
    }

    public Object getSoloUsuariosDelSistema() {
        return soloUsuariosDelSistema;
    }

    public void setSoloUsuariosDelSistema(Object soloUsuariosDelSistema) {
        this.soloUsuariosDelSistema = soloUsuariosDelSistema;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public Object getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Object fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Object getSoloProximosCumpleanhos() {
        return soloProximosCumpleanhos;
    }

    public void setSoloProximosCumpleanhos(Object soloProximosCumpleanhos) {
        this.soloProximosCumpleanhos = soloProximosCumpleanhos;
    }

    public Object getTodosLosCampos() {
        return todosLosCampos;
    }

    public void setTodosLosCampos(Object todosLosCampos) {
        this.todosLosCampos = todosLosCampos;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(idPersona);
        dest.writeValue(nombre);
        dest.writeValue(apellido);
        dest.writeValue(email);
        dest.writeValue(telefono);
        dest.writeValue(seguroMedico);
        dest.writeValue(seguroMedicoNumero);
        dest.writeValue(ruc);
        dest.writeValue(cedula);
        dest.writeValue(tipoPersona);
        dest.writeValue(usuarioLogin);
        dest.writeValue(flagVendedor);
        dest.writeValue(observacion);
        dest.writeValue(tipoCliente);
        dest.writeValue(fechaHoraAprobContrato);
        dest.writeValue(soloUsuariosDelSistema);
        dest.writeValue(nombreCompleto);
        dest.writeValue(limiteCredito);
        dest.writeValue(fechaNacimiento);
        dest.writeValue(soloProximosCumpleanhos);
        dest.writeValue(todosLosCampos);
    }

    public int describeContents() {
        return 0;
    }

}
