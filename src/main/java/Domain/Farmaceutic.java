/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

import java.sql.Date;

/**
 *
 * @author cripoponc
 */
public class Farmaceutic {
    private String dni;
    private String nom;
    private String cognom1;
    private String cognom2;
    private Date anyLicenciatura;
    private Boolean actiu;

    /**
     * Get the value of actiu
     *
     * @return the value of actiu
     */
    public Boolean isActiu() {
        return actiu;
    }

    /**
     * Set the value of actiu
     *
     * @param actiu new value of actiu
     */
    public void setActiu(Boolean actiu) {
        this.actiu = actiu;
    }


    public Farmaceutic() {
    }

    //Constructor para crear nuevo farmaceutico
    public Farmaceutic(String dni, String nom, String cognom1, String cognom2, Date anyLicenciatura) {
        this(dni, nom, cognom1, cognom2, anyLicenciatura, true);
        
    }
    //Constructor con todos los parametros
    public Farmaceutic(String dni, String nom, String cognom1, String cognom2, Date anyLicenciatura, Boolean actiu) {
        this.dni = dni;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.anyLicenciatura = anyLicenciatura;
        this.actiu = actiu;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Date getAnyLicenciatura() {
        return anyLicenciatura;
    }

    public void setAnyLicenciatura(Date anyLicenciatura) {
        this.anyLicenciatura = anyLicenciatura;
    }

    public Boolean getActiu() {
        return actiu;
    }
}
