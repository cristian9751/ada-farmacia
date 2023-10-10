/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc
 */
public class Pacient {
    private String dni;
    private String nom;
    private String cognom1;
    private String cognom2;
    private boolean actiu;

    public Pacient() {
    }

    /*
    Constructor con todos los parametros para manejar pacientes ya existentes en la base de datos
     */
    public Pacient(String dni, String nom, String cognom1, String cognom2, boolean actiu) {
        this.dni = dni;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.actiu = actiu;
    }

    /*
        Constructor sin parametro actiu para manejar las instancias de paciente que se utilizan para insertar a la bd
     */
    public Pacient(String dni, String nom, String cognom1, String cognom2) {
        this(dni, nom, cognom1, cognom2, true);
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

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    
    
}
