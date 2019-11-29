package py.com.eko.fisiocenter.Modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonaShort {
    @SerializedName("idPersona")
    @Expose
    private Integer idPesona;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("apellido")
    @Expose
    private String apellido;

    public PersonaShort() {
    }

    public Integer getIdPesona() {
        return idPesona;
    }
    public void setIdPesona(Integer idPesona) {
        this.idPesona = idPesona;
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
    public String getNombreCompleto() {
        return nombre + ' ' + apellido;
    }
}