package aula05.exercicio01;

public class Figure {
    private final double x;
    private final double y;

    public Figure(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double area() { return x * y; }

    public double getX() { return x; }

    public double getY() { return y; }
}
