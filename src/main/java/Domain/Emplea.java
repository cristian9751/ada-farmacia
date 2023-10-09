/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc
 */
public class Emplea {
    private Farmacia farmacia;
    private Farmaceutic farmaceutic;

    public Emplea() {
    }

    public Emplea(Farmacia farmacia, Farmaceutic farmaceutic) {
        this.farmacia = farmacia;
        this.farmaceutic = farmaceutic;
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
    
        
}
