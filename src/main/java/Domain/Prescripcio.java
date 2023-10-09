/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc
 */
public class Prescripcio {

    private int idPrescripcio;
    private Medicament medicament;
    private Pacient pacient;
    private String data;
    
    public Prescripcio() {
    }

    public Prescripcio(Medicament medicament, Pacient pacient, String data) {
        this.medicament = medicament;
        this.pacient = pacient;
        this.data = data;
    }

    public Prescripcio(int idPrescripcio, Medicament medicament, Pacient pacient, String data) {
        this.idPrescripcio = idPrescripcio;
        this.medicament = medicament;
        this.pacient = pacient;
        this.data = data;
    }
    
    
}
