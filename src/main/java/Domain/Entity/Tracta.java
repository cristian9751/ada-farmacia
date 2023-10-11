/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc
 */
public class Tracta {
    private int idTracta;
    private Metge metge;
    private Pacient pacient;

    public Tracta() {
    }

    /*Constructor sin clave primeria para menejar los objetos que se van a utilizar para insertar en la base de datos*/
    public Tracta(Metge metge, Pacient pacient) {
        this.metge = metge;
        this.pacient = pacient;
    }

    /*Constructor con clave primaria para manejar los objetos creados a partir de la informacion de la base de datos*/
    public Tracta(int idTracta, Metge metge, Pacient pacient) {
        this.idTracta = idTracta;
        this.metge = metge;
        this.pacient = pacient;
    }

    public int getIdTracta() {
        return idTracta;
    }

    public void setIdTracta(int idTracta) {
        this.idTracta = idTracta;
    }

    public Metge getMetge() {
        return metge;
    }

    public void setMetge(Metge metge) {
        this.metge = metge;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }
    
    
}
