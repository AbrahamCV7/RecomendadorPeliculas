/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paq;

/**
 *
 * @author Ittay
 */
public class Actor {
    
    private int idP;
   private  String Actor;

    public Actor() {
    }

    public Actor(int idP, String Actor) {
        this.idP = idP;
        this.Actor = Actor;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String Actor) {
        this.Actor = Actor;
    }

    @Override
    public String toString() {
        return "Actor{" + "idP=" + idP + ", Actor=" + Actor + '}';
    }
   
    
}
