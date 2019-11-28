package py.com.eko.fisiocenter;

import java.io.Serializable;

public class FichaClinica implements Serializable {
    private Integer idFichaClinica;
    private String motivo;
    private String diagnostico;
    private String observacion;
    private Persona medico;
    private Persona paciente;

    FichaClinica(Integer idFichaClinica, String motivo, String diagnostico, Persona medico, Persona paciente){
        this.idFichaClinica = idFichaClinica;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Integer getIdFichaClinica(){return idFichaClinica;}
    public void setIdFichaClinica(Integer idFichaClinica){this.idFichaClinica=idFichaClinica;}

    public String getMotivo(){return motivo;}
    public void setMotivo(String motivo){this.motivo=motivo;}

    public String getDiagnostico(){return diagnostico;}
    public void setDiagnostico(String diagnostico){this.diagnostico=diagnostico;}

    public String getObservacion(){return observacion;}
    public void setObservacion(String observacion){this.observacion=observacion;}

    public Persona getMedico(){return medico;}
    public void setMedico(Persona medico){this.medico=medico;}

    public Persona getPaciente(){return paciente;}
    public void setPaciente(Persona paciente){this.paciente=paciente;}
}
