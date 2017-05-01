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
    

    class guiCarport {

        private int width;
        private int depth;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

    }

    class guiRoof {

        private boolean gableRoof;

        class overhang {

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

        }
    }

    class guiShed {

        private boolean shed;
        private int depth;
        private int doorPlacement;
        private String side;
        private boolean rotateDoor;

        public boolean isShed() {
            return shed;
        }

        public void setShed(boolean shed) {
            this.shed = shed;
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

}
