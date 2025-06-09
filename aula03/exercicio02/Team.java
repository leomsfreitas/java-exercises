package aula03.exercicio02;

import java.util.Arrays;
import java.util.Objects;

public class Team {
    private String name;
    private String baseLocation;
    private String coachName;

    private Player[] players = new Player[18];
    private Player captain;

    public Team(String name, String baseLocation, String coachName) {
        this.name = name;
        this.baseLocation = baseLocation;
        setCoachName(coachName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) return;
        this.name = name;
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        if (baseLocation == null) return;
        this.baseLocation = baseLocation;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        if (players == null) return;
        this.players = players;
    }

    public Player getcaptain() {
        return captain;
    }

    public void setcaptain(Player captain) {
        if (captain == null) return;
        this.captain = captain;
    }

    public void addPlayer(Player player) {
        if (player == null) return;
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = player;
                return;
            }
        }
    }

    public void removePlayer(Player player) {
        if (player == null) return;
        for (int i = 0; i < players.length; i++) {
            if (player.equals(players[i])) {
                players[i] = null;
                return;
            }
        }
    }

    public void substitute(Player substitute, Player starter) {
        if (starter == null || substitute == null) return;
        if (!starter.isFielded() || substitute.isFielded()) return;

        starter.setFielded(false);
        substitute.setFielded(true);
    }

    public Player[] getFieldedPlayers() {
        Player[] fieldedPlayers = new Player[players.length];
        int i = 0;
        for (Player p : players) {
            if (p != null && p.isFielded()) {
                fieldedPlayers[i++] = p;
            }
        }
        return Arrays.copyOf(fieldedPlayers, i);
    }

    public Player[] getOutfieldedPlayers() {
        Player[] outfieldedPlayers = new Player[players.length];
        int i = 0;
        for (Player p : players) {
            if (p != null && !p.isFielded()) {
                outfieldedPlayers[i++] = p;
            }
        }
        return Arrays.copyOf(outfieldedPlayers, i);
    }

    @Override
    public String toString() {
        StringBuilder jogadores = new StringBuilder();
        for (Player p : players) {
            if (p != null) {
                jogadores.append("\t").append(p).append("\n");
            }
        }
        return String.format(
                "\nTeam: %s | Base Local: %s | Técnico: %s | Capitão: %s  \nJogadores:\n%s",
                name,
                baseLocation,
                coachName,
                captain != null ? captain.getName() : "Nenhum",
                jogadores
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(baseLocation, team.baseLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, baseLocation);
    }
}
