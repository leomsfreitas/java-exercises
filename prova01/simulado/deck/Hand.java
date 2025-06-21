package prova01.simulado.deck;

import java.util.Arrays;
import java.util.Objects;


public class Hand {
    private final Player player1;
    private final Player player2;
    private final Card vira;
    private final Round[] rounds = new Round[3];
    private int currentRound = 0;

    public Hand(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        Deck deck = new Deck();
        deck.shuffle();

        this.vira = deck.takeOne();
        player1.setCards(deck.take(3));
        player2.setCards(deck.take(3));
    }

    public void playRound() {
        if (isDone()) return;

        rounds[currentRound] = new Round(player1, player2, vira);
        String winner = rounds[currentRound].getWinner();

        System.out.println("Round " + (currentRound + 1) + ": " +
                (winner != null ? winner + " won" : "Draw"));

        currentRound++;
    }

    public boolean isDone() {
        if (currentRound < 2) return false;

        int winsP1 = 0;
        int winsP2 = 0;

        for (int i = 0; i < currentRound; i++) {
            if (rounds[i] == null) continue;
            String winner = rounds[i].getWinner();
            if (winner == null) continue;
            if (winner.equals(player1.getName())) winsP1++;
            if (winner.equals(player2.getName())) winsP2++;
        }

        return winsP1 == 2 || winsP2 == 2 || currentRound == 3;
    }

    public String getWinner() {
        if (!isDone()) return null;

        String[] winners = new String[3];
        for (int i = 0; i < 3; i++) {
            if (rounds[i] != null) {
                winners[i] = rounds[i].getWinner();
            }
        }

        int winsP1 = 0;
        int winsP2 = 0;

        for (String winner : winners) {
            if (winner == null) continue;
            if (winner.equals(player1.getName())) winsP1++;
            else if (winner.equals(player2.getName())) winsP2++;
        }

        if (winsP1 == 2) return player1.getName();
        if (winsP2 == 2) return player2.getName();

        if (winners[0] == null && winners[1] != null) return winners[1];
        if (winners[1] == null && winners[0] != null) return winners[0];
        if (winners[0] == null && winners[2] != null) return winners[2];
        if (winners[2] == null && winners[0] != null) return winners[0];

        return null;
    }

    public Card getVira() {
        return vira;
    }
}