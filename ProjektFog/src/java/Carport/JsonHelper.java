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
public class JsonHelper {

    private GuiCarportType carport;
    private GuiRoofType roof;
    private GuiShedType shed;

    public GuiCarportType getCarport() {
        return carport;
    }

    public void setCarport(GuiCarportType carport) {
        this.carport = carport;
    }

    public GuiRoofType getRoof() {
        return roof;
    }

    public void setRoof(GuiRoofType roof) {
        this.roof = roof;
    }

    public GuiShedType getShed() {
        return shed;
    }

    public void setShed(GuiShedType shed) {
        this.shed = shed;
    }

}
