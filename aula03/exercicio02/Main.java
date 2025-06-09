package aula03.exercicio02;

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Time", "São Carlos", "Leo Freitas");

        Player p1 = new Player("Player 1", 1, "Goleiro");
        Player p2 = new Player("Player 2", 2, "Defesa");
        Player p3 = new Player("Player 3", 3, "Meio Campo");
        Player p4 = new Player("Player 4", 4, "Atacante");

        team.addPlayer(p1);
        team.addPlayer(p2);
        team.addPlayer(p3);
        team.addPlayer(p4);

        team.setcaptain(p1);

        p1.setFielded(true);
        p2.setFielded(true);

        System.out.println("\nTitulares:");
        for (Player p : team.getFieldedPlayers()) {
            System.out.println(p);
        }

        System.out.println("\nReservas:");
        for (Player p : team.getOutfieldedPlayers()) {
            System.out.println(p);
        }

        System.out.println("\nSubstituindo p2 por p3...");
        team.substitute(p3, p2);

        System.out.println("\nTitulares após substituição:");
        for (Player p : team.getFieldedPlayers()) {
            System.out.println(p);
        }

        System.out.println("\nRemovendo p4...");
        team.removePlayer(p4);

        System.out.println("\nTime completo:");
        System.out.println(team);
    }
}