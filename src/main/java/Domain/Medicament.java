/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Domain;

/**
 *
 * @author cripoponc
 */
public class Medicament {
    private String nomComercial;
    private String formula;
    private boolean actiu;

    public Medicament() {
    }

    public Medicament(String nomComercial, String formula, boolean actiu) {
        this.nomComercial = nomComercial;
        this.formula = formula;
        this.actiu = actiu;
    }

    public Medicament(String nomComercial, String formula) {
        this(nomComercial, formula, true);
    }

    public String getNomComercial() {
        return nomComercial;
    }

    public void setNomComercial(String nomComercial) {
        this.nomComercial = nomComercial;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public boolean getActiu() {
        return actiu;
    }

    public void setActiu(boolean actiu) {
        this.actiu = actiu;
    }
    
    
    
    
}
