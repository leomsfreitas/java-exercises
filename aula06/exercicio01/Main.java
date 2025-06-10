package aula06.exercicio01;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Figure> listOfFigures = new ArrayList<>();

        for (int i = 1; i < 50; i++) {
            listOfFigures.add(new Circle(i,i,i));
            listOfFigures.add(new Rectangle(i,i,i,i));
            listOfFigures.add(new Triangle(i,i,i,i,i));
        }

        double totalArea = 0;
        for (Figure fig : listOfFigures) {
            totalArea += fig.area();
        }

        System.out.printf("Área total: %.2f\n", totalArea);
    }
}