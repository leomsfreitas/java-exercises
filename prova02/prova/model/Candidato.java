package prova02.prova.model;

import java.util.Objects;

public class Candidato {
    private final String nome;
    private final int numero;
    private final Cargo cargo;
    private final Partido partido;
    private int votos = 0;

    public Candidato(String nome, int numero, Cargo cargo, Partido partido) {
        this.nome = nome;
        this.numero = numero;
        this.cargo = cargo;
        this.partido = partido;
    }

    public void registrarVoto() {
        votos++;
    }

    public String getNome() {
        return nome;
    }

    public int getNumero() {
        return numero;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Partido getPartido() {
        return partido;
    }

    public int getVotos() {
        return votos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidato)) return false;
        Candidato that = (Candidato) o;
        return numero == that.numero && cargo == that.cargo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, cargo);
    }
}