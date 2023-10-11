/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc
 */
public class Metge {
    
    private int numColegiat;
    private Especialitat especialitat;
    private String nom;
    private String cognom1;
    private String cognom2;
    private boolean actiu;
    
    public Metge() {
    }

    /**
     * Constructor para trabajar con un medico existente  en la base de datos
     * @param especialitat
     * @param nom
     * @param cognom1
     * @param cognom2
     * @param actiu 
     */
    public Metge(int numColegiat , Especialitat especialitat, String nom, String cognom1, String cognom2, boolean actiu) {
        this.especialitat = especialitat;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.actiu = actiu;
    }

    /**
     * Constructor para crear un nuevo medico
     * @param especialitat
     * @param nom
     * @param cognom1
     * @param cognom2 
     */
    public Metge(Especialitat especialitat, String nom, String cognom1, String cognom2) {
        this.especialitat = especialitat;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.actiu = true;
    }

    public int getNumColegiat() {
        return numColegiat;
    }

    public void setNumColegiat(int numColegiat) {
        this.numColegiat = numColegiat;
    }

    public Especialitat getEspecialitat() {
        return especialitat;
    }

    public void setEspecialitat(Especialitat especialitat) {
        this.especialitat = especialitat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public boolean isActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    
    
    
    
}
