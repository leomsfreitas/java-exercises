package prova01.simulado.deck;

public class Round {
    private final Card card1;
    private final Card card2;
    private final Player winner;

    public Round(Player p1, Player p2, Card vira) {
        this.card1 = p1.chooseCard();
        this.card2 = p2.chooseCard();
        int result = card1.compareValueTo(card2, vira);
        if (result > 0) {
            winner = p1;
        } else if (result < 0) {
            winner = p2;
        } else {
            winner = null;
        }
    }

    public String getWinner() {
        return winner != null ? winner.getName() : null;
    }
}