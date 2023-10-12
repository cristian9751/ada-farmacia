/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc
 */
public class Farmacia {
    private Adresa adresa;
    private String cif;
    private Boolean actiu;

    public Farmacia() {
    }

    /*
    Constructor para instanciar objetos con la informacion de la base de datos
     */
    public Farmacia(Adresa adresa, String cif, Boolean actiu) {
        this.adresa = adresa;
        this.cif = cif;
        this.actiu = actiu;
    }

    /*
    Constructor para instanciar objetos cuya informacion se va a almacenar en la base de datos
     */
    public Farmacia(String cif, Adresa adresa) {
        this.adresa = adresa;
        this.actiu = true;
    }


    public Boolean getAcitu() {
        return actiu;
    }


    public void setAcitu(Boolean acitu) {
        this.actiu = acitu;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
    
    
}
