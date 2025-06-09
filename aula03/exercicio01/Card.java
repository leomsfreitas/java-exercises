package aula03.exercicio01;

public class Card {
    private final String suit;
    private final String rank;
    private boolean faceUp;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.faceUp = false;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        if (faceUp) {
            return rank + " de " + suit;
        } else {
            return "Carta encoberta";
        }
    }
}