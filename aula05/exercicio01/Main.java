package aula05.exercicio01;

public class Main {
    public static void main(String[] args) {
        Figure circle = new Circle(0, 0, 5);
        Figure rectangle = new Rectangle(1, 1, 4, 6);
        Figure triangle = new Triangle(2, 2, 3, 4, 5);

        System.out.printf("Círculo: área = %.2f\n", circle.area());
        System.out.printf("Retângulo: área = %.2f\n", rectangle.area());
        System.out.printf("Triângulo: área = %.2f\n", triangle.area());
    }
}