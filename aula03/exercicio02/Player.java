package aula03.exercicio02;

import java.util.Objects;

public class Player {
    private String name;
    private int number;;
    private String position;
    private boolean isFielded;

    public Player(String name, int number, String position) {
        this.name = name;
        this.number = number;
        this.position = position;
        this.isFielded = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isFielded() {
        return isFielded;
    }

    public void setFielded(boolean fielded) {
        isFielded = fielded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return number == player.number && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }

    @Override
    public String toString() {
        return String.format(
                "Nome: %s | Número: %d | Posição: %s | Em campo: %s",
                name,
                number,
                position,
                isFielded ? "Sim" : "Não"
        );
    }
}
