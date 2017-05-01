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
public class Roof {

    public Roof(boolean isGable, int overhangFront, int overhangBack, int overhangSides) {
        this.isGable = isGable;
        this.overhangFront = overhangFront;
        this.overhangBack = overhangBack;
        this.overhangSides = overhangSides;
    }

    private boolean isGable;
    private int overhangFront;
    private int overhangBack;
    private int overhangSides;

    public boolean isIsGable() {
        return isGable;
    }

    public void setIsGable(boolean isGable) {
        this.isGable = isGable;
    }

    public int getOverhangFront() {
        return overhangFront;
    }

    public void setOverhangFront(int overhangFront) {
        this.overhangFront = overhangFront;
    }

    public int getOverhangBack() {
        return overhangBack;
    }

    public void setOverhangBack(int overhangBack) {
        this.overhangBack = overhangBack;
    }

    public int getOverhangSides() {
        return overhangSides;
    }

    public void setOverhangSides(int overhangSides) {
        this.overhangSides = overhangSides;
    }

}
