package aula06.exercicio01;

public class Triangle extends Figure {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double x, double y, double a, double b, double c) {
        super(x, y);

        if (a + b <= c || a + c <= b || b + c <= a)
            throw new IllegalArgumentException("Invalid Triangle");

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s-a) * (s-b) * (s-c));
    }

    public double getA() { return a; }

    public double getB() { return b; }

    public double getC() { return c; }
}
