/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

import java.sql.Timestamp;

/**
 *
 * @author cripoponc
 */
public class Venta {

    private int idVenta;
    private Timestamp dataVenta;
    private double preu;
    private Farmacia farmacia;
    private Farmaceutic farmaceutic;
    private Medicament medicament;
    
    public Venta() {
    }

    /*Constructor sin clave primaria para manejar los objetos que se van a utilizar para insertar informacion en
    * la base de datos*/
    public Venta(Timestamp dataVenta, double preu, Farmacia farmacia, Farmaceutic farmaceutic, Medicament medicament) {
        this.dataVenta = dataVenta;
        this.preu = preu;
        this.farmacia = farmacia;
        this.farmaceutic = farmaceutic;
        this.medicament = medicament;
    }

    /*
    Constructor con clave primaria para menajer los objets que se van a instanciar utilizando la informacion obtenida de la base de datos
     */
    public Venta(int idVenta, Timestamp dataVenta, double preu, Farmacia farmacia, Farmaceutic farmaceutic, Medicament medicament) {
        this.idVenta = idVenta;
        this.dataVenta = dataVenta;
        this.preu = preu;
        this.farmacia = farmacia;
        this.farmaceutic = farmaceutic;
        this.medicament = medicament;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Timestamp getDataVenta() {
        return dataVenta;
    }

    public void setDataVenta(Timestamp dataVenta) {
        this.dataVenta = dataVenta;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public Farmaceutic getFarmaceutic() {
        return farmaceutic;
    }

    public void setFarmaceutic(Farmaceutic farmaceutic) {
        this.farmaceutic = farmaceutic;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
    
    
}
