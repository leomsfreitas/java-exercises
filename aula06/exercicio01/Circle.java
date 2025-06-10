package aula06.exercicio01;

public class Circle extends Figure {
    private final double radius;

    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public double area() { return Math.PI * Math.pow(radius, 2); }

    public double getRadius() { return radius; }
}
