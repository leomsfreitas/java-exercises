package aula03.exercicio01;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();

        System.out.println("Cartas restantes no baralho: " + deck.remainingCards());

        // Comprar uma carta
        Card carta = deck.drawCard();
        carta.setFaceUp(true);
        System.out.println("Carta comprada: " + carta);

        // Comprar várias cartas
        List<Card> cartas = deck.drawCards(3);
        System.out.println("Cartas compradas:");
        for (Card c : cartas) {
            c.setFaceUp(true);
            System.out.println(c);
        }

        System.out.println("Cartas restantes no baralho: " + deck.remainingCards());
    }
}