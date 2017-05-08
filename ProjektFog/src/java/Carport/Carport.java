/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carport;

/**
 *
 * @author Kasper
 */
public class Carport {

    public Carport(Base base, Roof roof, Shed shed) {
        this.base = base;
        this.roof = roof;
        this.shed = shed;
    }

    private Base base;
    private Roof roof;
    private Shed shed;

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }

}
