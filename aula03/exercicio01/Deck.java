package aula03.exercicio01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("O baralho está vazio.");
        }
        return cards.removeFirst();
    }

    public List<Card> drawCards(int n) {
        if (n > cards.size()) {
            throw new IllegalArgumentException("Não há cartas suficientes no baralho.");
        }
        List<Card> drawn = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            drawn.add(drawCard());
        }
        return drawn;
    }

    public int remainingCards() {
        return cards.size();
    }
}