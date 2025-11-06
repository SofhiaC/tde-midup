package br.com.midup.tde_midup;

public class Wall {
    private double cx;
    private double cy;
    private double width;
    private double height;
    private String id;

    public Wall(double cx, double cy, double width, double height, String id) {
        this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    public double getCx() { return cx; }
    public double getCy() { return cy; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public String getId() { return id; }

    // minimal stub for API compatibility
    public void draw() { }
}
