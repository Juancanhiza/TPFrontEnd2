package py.com.eko.fisiocenter.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FichaClinica {
    @SerializedName("idFichaClinica")
    @Expose
    private Integer idFichaClinica;
    @SerializedName("motivoConsulta")
    @Expose
    private String motivo;
    @SerializedName("diagnostico")
    @Expose
    private String diagnostico;
    @SerializedName("idEmpleado")
    @Expose
    private PersonaShort medico;
    @SerializedName("idCliente")
    @Expose
    private PersonaShort cliente;
    @SerializedName("fechaHora")
    @Expose
    private String fechaHora;
    @SerializedName("idTipoProducto")
    @Expose
    private TipoProducto idTipoProducto;
    @SerializedName("observacion")
    @Expose
    private String observacion;

    public FichaClinica() {
    }

    public Integer getIdFichaClinica() {
        return idFichaClinica;
    }
    public void setIdFichaClinica(Integer idFichaClinica) {
        this.idFichaClinica = idFichaClinica;
    }

    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public PersonaShort getMedico() {
        return medico;
    }
    public void setMedico(PersonaShort medico) {
        this.medico = medico;
    }

    public PersonaShort getCliente() {
        return cliente;
    }
    public void setCliente(PersonaShort cliente) {
        this.cliente = cliente;
    }

    public String getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }
    public void setIdTipoProducto(TipoProducto tipoProducto) {
        this.idTipoProducto = tipoProducto;
    }

    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
