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
public class Shed {

    public Shed(boolean hasShed, int depth, int doorPlacement, String side, boolean rotateDoor) {
        this.hasShed = hasShed;
        this.depth = depth;
        this.doorPlacement = doorPlacement;
        this.side = side;
        this.rotateDoor = rotateDoor;
    }

    private boolean hasShed;
    private int depth;
    private int doorPlacement;
    private String side;
    private boolean rotateDoor;

    public boolean isHasShed() {
        return hasShed;
    }

    public void setHasShed(boolean hasShed) {
        this.hasShed = hasShed;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getDoorPlacement() {
        return doorPlacement;
    }

    public void setDoorPlacement(int doorPlacement) {
        this.doorPlacement = doorPlacement;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public boolean isRotateDoor() {
        return rotateDoor;
    }

    public void setRotateDoor(boolean rotateDoor) {
        this.rotateDoor = rotateDoor;
    }

}
