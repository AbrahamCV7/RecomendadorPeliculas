/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

/**
 *
 * @author JOS THE ONLY ONE
 */
public class Genero {
    
   private int idG;
   private  String genero;

    public Genero(int idG, String genero) {
        this.idG = idG;
        this.genero = genero;
    }

   
   
    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Genero{" + "idG=" + idG + ", genero=" + genero + '}';
    }
   
   
}
