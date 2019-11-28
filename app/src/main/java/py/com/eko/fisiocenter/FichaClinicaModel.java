package py.com.eko.fisiocenter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FichaClinicaModel {
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
    private Persona medico;
    @SerializedName("idCliente")
    @Expose
    private Persona cliente;
    public FichaClinicaModel() {
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

    public Persona getMedico() {
        return medico;
    }
    public void setMedico(Persona medico) {
        this.medico = medico;
    }

    public Persona getCliente() {
        return cliente;
    }
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }
}
