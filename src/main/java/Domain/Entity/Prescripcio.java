/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

import java.sql.Timestamp;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author cripoponc
 */
public class Prescripcio {

    private int idPrescripcio;
    private Medicament medicament;
    private Pacient pacient;
    private Timestamp data;

    private Metge metge;
    
    public Prescripcio() {
    }

    /*Constructor sin clave primaria para manejar los objetos que se van a utilizar para insertar*/
    public Prescripcio(Medicament medicament, Pacient pacient, Metge metge,  Timestamp data) {
        this.medicament = medicament;
        this.pacient = pacient;
        this.data = data;
        this.metge = metge;
    }

    /*Constructor con clave primaria para manejar los objetos  que se obtienen a partir de la informacion en la base de datos*/
    public Prescripcio(int idPrescripcio, Medicament medicament, Pacient pacient, Metge metge, Timestamp data) {
        this.idPrescripcio = idPrescripcio;
        this.medicament = medicament;
        this.pacient = pacient;
        this.data = data;
        this.metge = metge;
    }

    public int getIdPrescripcio() {
        return idPrescripcio;
    }

    public void setIdPrescripcio(int idPrescripcio) {
        this.idPrescripcio = idPrescripcio;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Metge getMetge() {
        return metge;
    }

    public void setMetge(Metge metge) {
        this.metge = metge;
    }
}

