/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc
 */
public class Venta {

    private int idVenta;
    private String dataVenta;
    private double preu;
    private Farmacia farmacia;
    private Farmaceutic farmaceutic;
    private Medicament medicament;
    
    public Venta() {
    }

    public Venta(String dataVenta, double preu, Farmacia farmacia, Farmaceutic farmaceutic, Medicament medicament) {
        this.dataVenta = dataVenta;
        this.preu = preu;
        this.farmacia = farmacia;
        this.farmaceutic = farmaceutic;
        this.medicament = medicament;
    }

    public Venta(int idVenta, String dataVenta, double preu, Farmacia farmacia, Farmaceutic farmaceutic, Medicament medicament) {
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

    public String getDataVenta() {
        return dataVenta;
    }

    public void setDataVenta(String dataVenta) {
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
