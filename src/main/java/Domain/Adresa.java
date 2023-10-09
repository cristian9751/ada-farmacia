/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc    
 */
public class Adresa {
    private int id;
    private String carrer;
    private String provincia;
   
    /***
     * Constructor para trabajar con Adresa que ya existe
     * @param id 
     * @param carrer
     * @param provincia 
     */
    public Adresa(int id, String carrer, String provincia) {
        this.id = id;
        this.carrer = carrer;
        this.provincia = provincia;
    }
    
    public Adresa(){};
    /***
     * Constructor para crear Adresa
     * @param carrer
     * @param provincia 
     */
    public Adresa(String carrer, String provincia) {
        this.carrer = carrer;
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public String getCarrer() {
        return carrer;
    }

    public void setCarrer(String carrer) {
        this.carrer = carrer;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    
}
