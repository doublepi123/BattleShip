package model;
class ShipColor {
    int r, g, b;
    ShipColor() {
        r = (int) (255 * Math.random());
        g = (int) (255 * Math.random());
        b = (int) (255 * Math.random());
    }

    ShipColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public int getR() {
        return r;
    }
}