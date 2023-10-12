/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain.Entity;

/**
 *
 * @author cripoponc    
 */
public class Adresa {
    private int id;
    private String carrer;
    private String ciutat;
   
    /*
    Constructor para instanciar objetss con la informacion obtenida de la base de datos
     */
    public Adresa(int id, String carrer, String provincia) {
        this.id = id;
        this.carrer = carrer;
        this.ciutat = provincia;
    }

    public Adresa(){};

    /*
    Constructor sin clave primaria para instanciar objetos cuy informacion se va a almacenar en la base de  datos
     */
    public Adresa(String carrer, String ciutat) {
        this.carrer = carrer;
        this.ciutat = ciutat;
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

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }
    
}
