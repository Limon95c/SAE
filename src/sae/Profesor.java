/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sae;

/**
 *
 * @author Jorge
 */
public class Profesor {
    private String sNombre;
    private String sCorreo;
    private String sPuesto;

    public Profesor(String Nombre, String Correo, String Puesto) {
        this.sNombre = Nombre;
        this.sCorreo = Correo;
        this.sPuesto = Puesto;
    }

    public Profesor() {
        sNombre = "";
        sCorreo = "";
        sPuesto = "";
    }

    public String getNombre() {
        return sNombre;
    }

    public String getCorreo() {
        return sCorreo;
    }

    public String getPuesto() {
        return sPuesto;
    }

    public void setNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public void setCorreo(String sCorreo) {
        this.sCorreo = sCorreo;
    }

    public void setPuesto(String sPuesto) {
        this.sPuesto = sPuesto;
    }
    
    
}
