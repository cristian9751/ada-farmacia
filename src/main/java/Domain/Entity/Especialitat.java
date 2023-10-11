/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc
 */
public class Especialitat {
    private int idEspecialitat;
    private String nom;

    public Especialitat() {
    }

    public Especialitat(String nom) {
        this.nom = nom;
    }

    public Especialitat(int idEspecialitat, String nom) {
        this.idEspecialitat = idEspecialitat;
        this.nom = nom;
    }


    public int getIdEspecialitat() {
        return idEspecialitat;
    }

    public void setIdEspecialitat(int idEspecialitat) {
        this.idEspecialitat = idEspecialitat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
