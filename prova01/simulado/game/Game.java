package prova01.simulado.game;

import prova01.simulado.deck.Hand;
import prova01.simulado.deck.Player;

public class Game {
    private final Player p1;
    private final Player p2;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private Hand currentHand;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.currentHand = new Hand(p1, p2);
    }

    public void play() {
        if (isDone()) return;

        if (currentHand.isDone()) {
            String winner = currentHand.getWinner();
            if (winner != null) {
                if (winner.equals(p1.getName())) scoreP1++;
                else scoreP2++;
                System.out.println("Mão vencida por: " + winner);
            } else {
                System.out.println("Mão empatada!");
            }

            currentHand = new Hand(p1, p2);
        }

        currentHand.playRound();
    }

    public boolean isDone() {
        return scoreP1 >= 12 || scoreP2 >= 12;
    }

    public Player getWinner() {
        if (!isDone()) return null;
        return scoreP1 >= 12 ? p1 : p2;
    }
}