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

    public Roof(boolean gable, int sides, int front, int back) {
        this.gable = gable;
        this.sides = sides;
        this.front = front;
        this.back = back;
    }

    private boolean gable;
    private int sides;
    private int front;
    private int back;

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public boolean isGable()
    {
        return gable;
    }
}
