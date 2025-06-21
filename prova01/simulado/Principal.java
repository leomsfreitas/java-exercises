package prova01.simulado;

import prova01.simulado.deck.Player;
import prova01.simulado.game.Game;

public class Principal {
    public static void main(String[] args) {
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");

        Game game = new Game(p1, p2);

        while (!game.isDone()) {
            game.play();
        }

        Player winner = game.getWinner();
        System.out.println("Vencedor da partida: " + (winner != null ? winner.getName() : "Empate geral"));
    }
}