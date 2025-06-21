package prova01.simulado.deck;

public class Player {
    private final String name;
    private Card[] hand = new Card[3];
    private int cardsPlayed = 0;

    public Player(String name) {
        this.name = name;
    }

    public void setCards(Card[] cards) {
        this.hand = cards;
        this.cardsPlayed = 0;
    }

    public Card chooseCard() {
        if (cardsPlayed >= 3) return null;
        return hand[cardsPlayed++];
    }

    public String getName() {
        return name;
    }
}