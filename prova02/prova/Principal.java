package prova02.prova;

import prova02.prova.service.Urna;
import prova02.prova.service.UrnaImpl;

public class Principal {
    private static final int ELEITORES = 3;

    public static void main(String[] args) {
        Urna urna = new UrnaImpl();

        for (int i = 0; i < ELEITORES; i++) {
            System.out.printf("%n--- ELEITOR %d --- %n", i + 1);
            urna.votar();
        }

        urna.imprimirBoletim();
    }
}