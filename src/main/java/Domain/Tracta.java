/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

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

    public Tracta(Metge metge, Pacient pacient) {
        this.metge = metge;
        this.pacient = pacient;
    }

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
