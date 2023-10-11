/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc
 */
public class Emplea {
    private Farmacia farmacia;
    private Farmaceutic farmaceutic;

    private int EmpleaId;

    public Emplea() {
    }

    /*
    * Constructor sin clave primaria para crear las instancias que se utilizaran para insertar datos en
    * la base de datos*/
    public Emplea(Farmacia farmacia, Farmaceutic farmaceutic) {
        this.farmacia = farmacia;
        this.farmaceutic = farmaceutic;
    }

    /*
    Constructor con id para manejar los objetos instanciados a partir de los datos obtenidos de la base de datos
     */
    public Emplea(int EmpleaId, Farmacia farmacia, Farmaceutic farmaceutic) {
        this(farmacia, farmaceutic);
        this.EmpleaId = EmpleaId;
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
