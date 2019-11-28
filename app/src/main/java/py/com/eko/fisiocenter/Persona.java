package py.com.eko.fisiocenter;

public class Persona {
    private Integer idPersona;
    private String apellidos;
    private String nombres;

    public Persona(Integer idPersona, String nombres, String apellidos){
        this.idPersona = idPersona;
        this.apellidos = apellidos;
        this.nombres = nombres;
    }

    public Integer getIdPersona(){return idPersona;}
    public void setIdPersona(Integer idPersona){this.idPersona=idPersona;}

    public String getApellidos(){return apellidos;}
    public void setApellidos(String apellidos){this.apellidos=apellidos;}

    public String getNombres(){return nombres;}
    public void setNombres(String nombres){this.nombres=nombres;}

    public String getNombreCompleto() { return nombres + ' ' + apellidos;}
}
